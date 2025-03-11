package edu.unizg.foi.uzdiz.tsmetisko.CitacFactoryMethod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.unizg.foi.uzdiz.tsmetisko.SustavTvrtke;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Stanica;
import edu.unizg.foi.uzdiz.tsmetisko.StanicaBuilder.StanicaBuildDirector;
import edu.unizg.foi.uzdiz.tsmetisko.StanicaBuilder.StanicaBuilder;
import edu.unizg.foi.uzdiz.tsmetisko.StanicaBuilder.StanicaBuilderImpl;

public class StanicaCitac implements DatotekaCitac {

  private String putanja;
  private SustavTvrtke sustavTvrtke = SustavTvrtke.getInstance();

  public StanicaCitac(String putanja) {
    this.putanja = sustavTvrtke.putanja + putanja;
  }

  @Override
  public void procitaj() {
    try {
      BufferedReader br = new BufferedReader(new FileReader(putanja));
      String line = br.readLine();

      while ((line = br.readLine()) != null) {

        if (line.matches("^;+$") || line.startsWith("#") || !validirajRedStanica(line)) {
          continue;
        }

        String[] polje = new String[17];
        String[] podaci = line.split(";");
        for (int i = 0; i < podaci.length; i++) {
          polje[i] = podaci[i];
        }


        final StanicaBuilder builder = new StanicaBuilderImpl();
        final StanicaBuildDirector director = new StanicaBuildDirector(builder);
        Stanica stanica = director.construct(polje);

        sustavTvrtke.stanice.add(stanica);
        sustavTvrtke.stanicePoPrugama.computeIfAbsent(stanica.oznakaPruge, k -> new ArrayList<>())
            .add(stanica);
      }

      br.close();
    } catch (Exception e) {
      System.out.println("Neispravan naziv datoteke sa stanicama.");
      System.exit(0);
    }

  }

  public boolean validirajRedStanica(String line) {
    String regexRed =
        "^[A-Za-zčćžšđČĆŽŠĐ]+(\\s?\\-?([A-Za-zčćžšđČĆŽŠĐ])+(\\s[A-Za-zčćžšđČĆŽŠĐ]+)?)?;[A-Za-z0-9]+;(staj\\.|kol\\.|rasp\\.);(O|Z);(DA|NE);(DA|NE);(L|R|M);\\d+;(K|E);\\d+;\\d+(,\\d+)?;\\d+(,\\d+)?;(I|K|Z);\\d+;(\\d+)?;(\\d+)?;(\\d+)?$";
    Pattern pattern = Pattern.compile(regexRed);
    Matcher matcher = pattern.matcher(line);

    if (matcher.matches()) {
      return true;
    } else {
      sustavTvrtke.redniBrojPogreske++;
      pronadiGreskuStanica(line);
      return false;
    }
  }

  public void pronadiGreskuStanica(String line) {
    String[] regexRedDio =
        {"^[A-Za-zčćžšđČĆŽŠĐ]+(\\s?-?([A-Za-zčćžšđČĆŽŠĐ])+(\\s[A-Za-zčćžšđČĆŽŠĐ]+)?)?$",
            "^[A-Za-z0-9]+$", "^(staj.|kol.)$", "^(O|Z)$", "^(DA|NE)$", "^(DA|NE)$", "^(L|R|M)$",
            "^\\d+$", "^(K|E)$", "^\\d+$", "^\\d+(,\\d+)?$", "^\\d+(,\\d+)?$", "^(I|K|Z)$",
            "^\\d+$", "^(\\d+)?$", "^(\\d+)?$", "^(\\d+)?$"};

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
