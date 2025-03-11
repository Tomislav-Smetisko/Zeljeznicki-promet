package edu.unizg.foi.uzdiz.tsmetisko.ChainOfResponsibility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.unizg.foi.uzdiz.tsmetisko.SustavTvrtke;
import edu.unizg.foi.uzdiz.tsmetisko.Composite.EtapaComposite;
import edu.unizg.foi.uzdiz.tsmetisko.Composite.VlakComposite;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Vlak;

public class VozniRedHandler implements CitacHandler {
  private String putanja;
  private SustavTvrtke sustavTvrtke = SustavTvrtke.getInstance();
  private CitacHandler nextHandler;

  /*
   * public VozniRedCitac(String putanja) { this.putanja = sustavTvrtke.putanja + putanja; }
   */

  @Override
  public void setNextHandler(CitacHandler nextHandler) {
    this.nextHandler = nextHandler;
  }

  @Override
  public void procitaj(String naziv) {
    String[] dioNaziv = naziv.split(" ");
    if (naziv.contains("--zvr")) {
      try {
        putanja = sustavTvrtke.putanja + dioNaziv[1];
        BufferedReader br = new BufferedReader(new FileReader(putanja));
        String line = br.readLine();

        while ((line = br.readLine()) != null) {

          if (line.matches("^;+$") || line.startsWith("#") || !validirajRed(line)) {
            continue;
          }

          String[] polje = new String[9];
          String[] podaci = line.split(";");
          for (int i = 0; i < podaci.length; i++) {
            polje[i] = (i < podaci.length) ? podaci[i] : null;
          }

          Vlak vozniRed = new Vlak(polje[0], polje[1], polje[2], polje[3], polje[4], polje[5],
              prilagodiVrijeme(polje[6]), prilagodiVrijeme(polje[7]), checkIfNull(polje[8]));

          sustavTvrtke.vozniRedovi.add(vozniRed);

          VlakComposite vlak = new VlakComposite(polje[4], polje[5]);
          EtapaComposite etapa = new EtapaComposite(polje[0], polje[1], polje[2], polje[3],
              prilagodiVrijeme(polje[6]), prilagodiVrijeme(polje[7]), checkIfNull(polje[8]));

          EtapaComposite popunjenaEtapa = popuniPraznoEtapa(etapa);
          VlakComposite popunjeniVlak = popuniPraznoVlak(vlak);


          VlakComposite vlakPostoji =
              (VlakComposite) sustavTvrtke.vozniRedComposite.dohvatiVlak(polje[4]);
          if (vlakPostoji == null) {
            sustavTvrtke.vozniRedComposite.add(popunjeniVlak);
            vlak.add(popunjenaEtapa);
          } else {
            sustavTvrtke.vozniRedComposite.dohvatiVlak(polje[4]).add(popunjenaEtapa);
          }


          // sustavTvrtke.vozniRedComposite.add(vlak);

        }

        br.close();
      } catch (Exception e) {
        System.out.println("Neispravan naziv datoteke sa voznim redovima.");
        // System.out.println(e);
        System.exit(0);
      }
      popuniPrazneVrijednosti();
    } else {
      nextHandler.procitaj(naziv);
    }
  }

  private VlakComposite popuniPraznoVlak(VlakComposite vlak) {
    if (vlak.vrstaVlaka.isEmpty()) {
      vlak.vrstaVlaka = "N";
    }

    return vlak;
  }

  private EtapaComposite popuniPraznoEtapa(EtapaComposite etapa) {
    if (etapa.polaznaStanica.isEmpty()) {
      for (var zapis : sustavTvrtke.stanicePoPrugama.entrySet()) {
        if (zapis.getKey().equals(etapa.oznakaPruge)) {
          var lista = zapis.getValue();
          if (etapa.smjer.equals("N")) {
            String oznaka = lista.get(0).mjesto;
            etapa.polaznaStanica = oznaka;
          } else if (etapa.smjer.equals("O")) {
            String oznaka = lista.get(lista.size() - 1).mjesto;
            etapa.polaznaStanica = oznaka;
          }
        }
      }
    }

    if (etapa.odredisnaStanica.isEmpty()) {
      for (var zapis : sustavTvrtke.stanicePoPrugama.entrySet()) {
        if (zapis.getKey().equals(etapa.oznakaPruge)) {
          var lista = zapis.getValue();
          if (etapa.smjer.equals("N")) {
            String oznaka = lista.get(lista.size() - 1).mjesto;
            etapa.odredisnaStanica = oznaka;
          } else if (etapa.smjer.equals("O")) {
            String oznaka = lista.get(0).mjesto;
            etapa.odredisnaStanica = oznaka;
          }
        }
      }
    }

    if (etapa.oznakaDana == null) {
      etapa.oznakaDana = 11;
    }

    return etapa;
  }

  private LocalTime prilagodiVrijeme(String vrijeme) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    if (vrijeme != null) {
      String[] polje = vrijeme.split(":");
      if (polje[0].length() == 1) {
        vrijeme = "0" + vrijeme;
      }
      return LocalTime.parse(vrijeme, formatter);
    }
    return null;
  }

  public Integer checkIfNull(String podatak) {
    if (podatak == null || podatak.trim().isEmpty()) {
      return null;
    }
    return Integer.parseInt(podatak);
  }

  private boolean validirajRed(String line) {
    String regex =
        "^[\\d\\w]+;(N|O);[A-Za-zčćžšđČĆŽŠĐ\\s]*;[A-Za-zčćžšđČĆŽŠĐ\\s]*;[\\d\\s\\w]+;(N|U|B)?;\\d+:\\d+;(\\d+:\\d+)?;(\\d+)?$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(line);

    if (matcher.matches()) {
      return true;
    } else {
      sustavTvrtke.redniBrojPogreske++;
      pronadiGresku(line);
      return false;
    }
  }

  private void pronadiGresku(String line) {
    String[] regexRedDio =
        {"^[\\d\\w]+$", "^(N|O)$", "^[A-Za-zčćžšđČĆŽŠĐ\\s]*$", "^[A-Za-zčćžšđČĆŽŠĐ\\s]*$",
            "^[\\d\\s\\w]+$", "^(N|U|B)?$", "^\\d+:\\d+$", "^(\\d+:\\d+)?$", "^(\\d+)?$"};

    String[] polje = line.split(";");

    for (int i = 0; i < regexRedDio.length; i++) {
      if (i < polje.length && !polje[i].matches(regexRedDio[i])) {
        System.out.println("Redni broj pogreske: " + sustavTvrtke.redniBrojPogreske + ". Atribut "
            + (i + 1) + " nije ispravan.");
        System.out.println(line + "\n");
        return;
      }
    }
  }

  private void popuniPrazneVrijednosti() {
    for (Vlak vr : sustavTvrtke.vozniRedovi) {
      if (vr.polaznaStanica.isEmpty()) {
        for (var zapis : sustavTvrtke.stanicePoPrugama.entrySet()) {
          if (zapis.getKey().equals(vr.oznakaPruge)) {
            var lista = zapis.getValue();
            if (vr.smjer.equals("N")) {
              String oznaka = lista.get(0).mjesto;
              vr.polaznaStanica = oznaka;
            } else if (vr.smjer.equals("O")) {
              String oznaka = lista.get(lista.size() - 1).mjesto;
              vr.polaznaStanica = oznaka;
            }
          }
        }
      }
      if (vr.odredisnaStanica.isEmpty()) {
        for (var zapis : sustavTvrtke.stanicePoPrugama.entrySet()) {
          if (zapis.getKey().equals(vr.oznakaPruge)) {
            var lista = zapis.getValue();
            if (vr.smjer.equals("N")) {
              String oznaka = lista.get(lista.size() - 1).mjesto;
              vr.odredisnaStanica = oznaka;
            } else if (vr.smjer.equals("O")) {
              String oznaka = lista.get(0).mjesto;
              vr.odredisnaStanica = oznaka;
            }
          }
        }
      }
      if (vr.vrstaVlaka.isEmpty()) {
        vr.vrstaVlaka = "N";
      }
      if (vr.oznakaDana == null) {
        vr.oznakaDana = 11;
      }
    }
  }
}
