package edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod;

import java.util.List;
import edu.unizg.foi.uzdiz.tsmetisko.SustavTvrtke;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Stanica;

public class KomandaISPNormalno implements Komanda {
  SustavTvrtke sustavTvrtke = SustavTvrtke.getInstance();

  public KomandaISPNormalno(String komanda) {
    izvrsi(komanda);
  }

  @Override
  public void izvrsi(String komanda) {

    String[] polje = komanda.split(" ");
    List<Stanica> stanice = sustavTvrtke.stanicePoPrugama.get(polje[1]);

    if (stanice == null) {
      sustavTvrtke.redniBrojPogreske++;
      System.out.println(
          "Redni broj pogreške: " + sustavTvrtke.redniBrojPogreske + ". Neispravna oznaka pruge.");
      return;
    }

    System.out.println("-----------------------------------------------------------");
    System.out.printf("| %-23s | %-2s | %-1s |%n", "NAZIV STANICE", "VRSTA",
        "UDALJENOST OD POČETNE");
    System.out.println("-----------------------------------------------------------");



    if (stanice != null) {
      int ukupnaUdaljenost = 0;
      for (Stanica s : stanice) {
        ukupnaUdaljenost += s.duzina;
        System.out.printf("| %-23s | %-5s | %2s%-19s |%n", s.mjesto, s.vrstaStanice,
            ukupnaUdaljenost, "km");
      }
    }
    System.out.println("-----------------------------------------------------------");
  }
}
