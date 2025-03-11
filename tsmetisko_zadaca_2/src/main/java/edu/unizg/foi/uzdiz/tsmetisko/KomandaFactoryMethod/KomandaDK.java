package edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod;

import edu.unizg.foi.uzdiz.tsmetisko.SustavTvrtke;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Korisnik;

public class KomandaDK implements Komanda {
  SustavTvrtke sustavTvrtke = SustavTvrtke.getInstance();

  public KomandaDK(String komanda) {
    izvrsi(komanda);
  }

  @Override
  public void izvrsi(String line) {
    String[] polje = line.split(" ");

    Korisnik korisnik = new Korisnik(polje[1], polje[2]);

    boolean postoji = false;
    for (Korisnik k : sustavTvrtke.sviKorisnici) {
      if (k.ime.equals(korisnik.ime) && k.prezime.equals(korisnik.prezime)) {
        postoji = true;
        sustavTvrtke.redniBrojPogreske++;
        System.out.println(
            "Redni broj pogreške: " + sustavTvrtke.redniBrojPogreske + ". Korisnik već postoji.");
      }
    }
    if (!postoji) {
      sustavTvrtke.sviKorisnici.add(korisnik);
    }
  }
}
