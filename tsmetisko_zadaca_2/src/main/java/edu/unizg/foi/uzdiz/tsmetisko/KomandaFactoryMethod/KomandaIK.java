package edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod;

import java.util.List;
import edu.unizg.foi.uzdiz.tsmetisko.SustavTvrtke;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Kompozicija;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Vozilo;

public class KomandaIK implements Komanda {
  SustavTvrtke sustavTvrtke = SustavTvrtke.getInstance();

  public KomandaIK(String komanda) {
    izvrsi(komanda);
  }

  @Override
  public void izvrsi(String komanda) {

    String[] polje = komanda.split(" ");
    String opisVozila = polje[1];

    List<Kompozicija> listaKompozicija = sustavTvrtke.kompozicijePoOznakama.get(opisVozila);

    if (listaKompozicija == null) {
      sustavTvrtke.redniBrojPogreske++;
      System.out.println("Redni broj pogreške: " + sustavTvrtke.redniBrojPogreske
          + ". Neispravna oznaka kompozicije.");
      return;
    }

    System.out.println(
        "----------------------------------------------------------------------------------------------------------------------------------");
    System.out.printf("| %-1s | %-1s | %-60s | %-1s | %-1s | %-1s | %-1s |%n", "OZNAKA", "ULOGA",
        "OPIS", "GODINA", "NAMJENA", "VRSTA POGONA", "MAKS. BRZINA");
    System.out.println(
        "----------------------------------------------------------------------------------------------------------------------------------");


    for (Kompozicija k : listaKompozicija) {
      Vozilo v = null;
      for (Vozilo vozilo : sustavTvrtke.vozila) {
        if (k.oznakaPrijevoznogSredstva.equals(vozilo.oznaka)) {
          v = vozilo;
        }
      }
      System.out.printf("| %-6s | %-5s | %-60s | %-6s | %-7s | %-12s | %3s%-9s |%n", k.oznaka,
          k.uloga, v.opis, v.godina, v.namjena, v.vrstaPogona, v.maxBrzina, "km/h");

    }
    System.out.println(
        "----------------------------------------------------------------------------------------------------------------------------------");
  }

}
