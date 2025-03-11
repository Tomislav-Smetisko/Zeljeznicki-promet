package edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod;

import edu.unizg.foi.uzdiz.tsmetisko.SustavTvrtke;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Korisnik;

public class KomandaPK implements Komanda {
  SustavTvrtke sustavTvrtke = SustavTvrtke.getInstance();

  public KomandaPK(String komanda) {
    izvrsi(komanda);
  }

  @Override
  public void izvrsi(String line) {
    System.out.println("---------------------------");
    System.out.printf("| %-10s | %-10s |%n", "IME", "PREZIME");
    System.out.println("---------------------------");

    for (Korisnik k : sustavTvrtke.sviKorisnici) {
      System.out.printf("| %-10s | %-10s |%n", k.ime, k.prezime);
    }

    System.out.println("---------------------------");
  }
}
