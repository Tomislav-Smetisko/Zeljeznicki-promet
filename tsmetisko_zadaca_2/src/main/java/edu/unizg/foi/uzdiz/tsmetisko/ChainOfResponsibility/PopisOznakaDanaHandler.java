package edu.unizg.foi.uzdiz.tsmetisko.ChainOfResponsibility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.unizg.foi.uzdiz.tsmetisko.SustavTvrtke;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.PopisOznakaDana;

public class PopisOznakaDanaHandler implements CitacHandler {
  private String putanja;
  private SustavTvrtke sustavTvrtke = SustavTvrtke.getInstance();

  /*
   * public PopisOznakaDanaHandler(String putanja) { this.putanja = sustavTvrtke.putanja + putanja;
   * }
   */

  @Override
  public void setNextHandler(CitacHandler nextHandler) {

  }

  @Override
  public void procitaj(String naziv) {
    String[] dioNaziv = naziv.split(" ");
    if (naziv.contains("--zod")) {
      try {
        putanja = sustavTvrtke.putanja + dioNaziv[1];
        BufferedReader br = new BufferedReader(new FileReader(putanja));
        String line = br.readLine();

        while ((line = br.readLine()) != null) {

          if (line.matches("^;+$") || line.startsWith("#") || !validirajRed(line)) {
            continue;
          }

          String[] podaci = new String[2];
          String[] linija = line.split(";");
          for (int i = 0; i < linija.length; i++) {
            podaci[i] = linija[i];
          }

          PopisOznakaDana oznakaDana = new PopisOznakaDana(Integer.parseInt(podaci[0]), podaci[1]);
          sustavTvrtke.oznakeDana.add(oznakaDana);
        }

        br.close();
      } catch (Exception e) {
        System.out.println("Neispravan naziv datoteke s popisom oznaka dana.");
        System.out.println(e);
        System.exit(0);
      }
    }
  }

  private boolean validirajRed(String line) {
    String regex = "^\\d+;(Po)?(U)?(Sr)?(Č)?(Pe)?(Su)?(N)?$";
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
    String[] regexRedDio = {"^\\d+$", "^(Po)?(U)?(Sr)?(Č)?(Pe)?(Su)?(N)?$"};

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
}
