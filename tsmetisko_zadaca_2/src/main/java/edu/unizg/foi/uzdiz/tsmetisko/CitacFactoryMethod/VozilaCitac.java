package edu.unizg.foi.uzdiz.tsmetisko.CitacFactoryMethod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.unizg.foi.uzdiz.tsmetisko.SustavTvrtke;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Vozilo;

public class VozilaCitac implements DatotekaCitac {
  private String putanja;
  private SustavTvrtke sustavTvrtke = SustavTvrtke.getInstance();

  public VozilaCitac(String putanja) {
    this.putanja = sustavTvrtke.putanja + putanja;
  }

  @Override
  public void procitaj() {

    try {
      BufferedReader br = new BufferedReader(new FileReader(putanja));
      String line = br.readLine();

      while ((line = br.readLine()) != null) {

        if (line.matches("^;+$") || line.matches("^#") || !validirajRedVozila(line)) {
          continue;
        }

        String[] podaci = line.split(";");

        Vozilo vozilo = new Vozilo(podaci[0], podaci[1], podaci[2], Integer.parseInt(podaci[3]),
            podaci[4], podaci[5], podaci[6], Integer.parseInt(podaci[7]),
            Double.parseDouble(podaci[8].replace(",", ".")), Integer.parseInt(podaci[9]),
            Integer.parseInt(podaci[10]), Integer.parseInt(podaci[11]),
            Integer.parseInt(podaci[12]), Integer.parseInt(podaci[13]),
            Double.parseDouble(podaci[14].replace(",", ".")),
            Double.parseDouble(podaci[15].replace(",", ".")), Integer.parseInt(podaci[16]),
            podaci[17]);

        sustavTvrtke.vozila.add(vozilo);

      }

      br.close();
    } catch (Exception e) {
      System.out.println("Neispravan naziv datoteke s vazilima.");
      System.exit(0);
    }
  }


  public boolean validirajRedVozila(String line) {
    String regexRed =
        "^[A-Za-z\\d\\-]+;[A-Za-zČĆŽŠĐčćžšđ\\d\\s\\\"„“\\-\\…]+;[A-Za-zČĆŽŠĐčćžšđ\\s\\-\\–]+;\\d+;(PSVPVK|PSVP|PSBP);[A-Z]+;(N|D|B|E);\\d+;\\-?\\d+(\\,\\d+)?;\\d+;\\d+;\\d+;\\d+;\\d+;\\d+(\\,\\d+)?;\\d+(\\,\\d+)?;\\d+;(I|K)$";
    Pattern pattern = Pattern.compile(regexRed);
    Matcher matcher = pattern.matcher(line);

    if (matcher.matches()) {
      return true;
    } else {
      sustavTvrtke.redniBrojPogreske++;
      pronadiGreskuVozilo(line);
      return false;
    }
  }

  public void pronadiGreskuVozilo(String line) {
    String[] regexRedDio = {"^[A-Za-z\\d\\-]+$", "^[A-Za-zČĆŽŠĐčćžšđ\\d\\s\"„“\\-\\…]+$",
        "^[A-Za-zČĆŽŠĐčćžšđ\\s\\-\\–]+$", "^\\d+$", "^(PSVPVK|PSVP|PSBP)$", "^[A-Z]+$",
        "^(N|D|B|E)$", "^\\d+$", "^-?\\d+(,\\d+)?$", "^\\d+$", "^\\d+$", "^\\d+$", "^\\d+$",
        "^\\d+$", "^\\d+(,\\d+)?$", "^\\d+(,\\d+)?$", "^\\d+$", "^(I|K)$"};

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
