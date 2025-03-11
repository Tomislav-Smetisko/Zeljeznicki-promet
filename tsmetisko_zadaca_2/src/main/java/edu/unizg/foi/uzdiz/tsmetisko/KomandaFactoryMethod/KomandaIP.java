package edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod;

import java.util.List;
import java.util.Map;
import edu.unizg.foi.uzdiz.tsmetisko.SustavTvrtke;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Stanica;

public class KomandaIP implements Komanda {
  SustavTvrtke sustavTvrtke = SustavTvrtke.getInstance();

  public KomandaIP(String komanda) {
    izvrsi(komanda);
  }

  @Override
  public void izvrsi(String line) {
    System.out
        .println("-----------------------------------------------------------------------------");
    System.out.printf("| %-1s | %-22s | %-22s | %1s |%n", "OZNAKA", "POČETNA STANICA",
        "ZAVRŠNA STANICA", "UKUPAN BROJ KM");
    System.out
        .println("-----------------------------------------------------------------------------");
    for (Map.Entry<String, List<Stanica>> element : sustavTvrtke.stanicePoPrugama.entrySet()) {
      String oznakaPruge = element.getKey();
      List<Stanica> listaStanica = element.getValue();

      Stanica prvaStanica = listaStanica.get(0);
      Stanica zadnjaStanica = listaStanica.get(listaStanica.size() - 1);
      double ukupnaUdaljenost = 0;
      for (Stanica s : listaStanica) {
        ukupnaUdaljenost += s.duzina;
      }

      System.out.printf("| %-6s | %-22s | %-22s | %2s%-10s |%n", oznakaPruge, prvaStanica.mjesto,
          zadnjaStanica.mjesto, ukupnaUdaljenost, "km");
    }
    System.out
        .println("-----------------------------------------------------------------------------");
  }

}
