package edu.unizg.foi.uzdiz.tsmetisko.CitacFactoryMethod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.unizg.foi.uzdiz.tsmetisko.SustavTvrtke;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Kompozicija;

public class KompozicijaCitac implements DatotekaCitac {
  private String putanja;
  private SustavTvrtke sustavTvrtke = SustavTvrtke.getInstance();

  public KompozicijaCitac(String putanja) {
    this.putanja = sustavTvrtke.putanja + putanja;
  }

  @Override
  public void procitaj() {
    try {
      BufferedReader br = new BufferedReader(new FileReader(putanja));
      String line = br.readLine();

      while ((line = br.readLine()) != null) {

        if (line.matches("^;+$") || line.matches("^#") || !validirajKompoziciju(line)) {
          continue;
        }

        String[] podaci = line.split(";");

        Kompozicija kompozicija = new Kompozicija(podaci[0], podaci[1], podaci[2]);

        sustavTvrtke.kompozicije.add(kompozicija);
        sustavTvrtke.kompozicijePoOznakama
            .computeIfAbsent(kompozicija.oznaka, k -> new ArrayList<>()).add(kompozicija);
      }

      br.close();
    } catch (Exception e) {
      System.out.println("Neispravan naziv datoteke s kompozicijama.");
      System.exit(0);
    }
  }


  public boolean validirajKompoziciju(String line) {
    String regexRed = "^\\d+;[A-Z\\d\\-]+;(P|V)$";
    Pattern pattern = Pattern.compile(regexRed);
    Matcher matcher = pattern.matcher(line);

    if (matcher.matches()) {
      return true;
    } else {
      sustavTvrtke.redniBrojPogreske++;
      pronadiGreskuKompozicija(line);
      return false;
    }
  }

  public void pronadiGreskuKompozicija(String line) {
    String[] regexRedDio = {"^\\d+$", "^[A-Z\\d\\-]+$", "^(P|V)$"};

    String[] polje = line.split(";");

    if (polje.length != regexRedDio.length) {
      System.out.println(
          "Redni broj pogreske: " + sustavTvrtke.redniBrojPogreske + ". Neispravan broj atributa");
      System.out.println(line + "\n");
      return;
    }

    for (int i = 0; i < regexRedDio.length; i++) {
      if (i < polje.length && !polje[i].matches(regexRedDio[i])) {
        System.out.println("Redni broj pogreske: " + sustavTvrtke.redniBrojPogreske + ". Atribut "
            + (i + 1) + " nije ispravan.");
        System.out.println(line + "\n");
        return;
      }
    }
  }

}
