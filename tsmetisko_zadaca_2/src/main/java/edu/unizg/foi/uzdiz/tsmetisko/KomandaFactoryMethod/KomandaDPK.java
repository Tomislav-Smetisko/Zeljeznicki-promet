package edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod;

import edu.unizg.foi.uzdiz.tsmetisko.SustavTvrtke;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Korisnik;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.KorisnikPracenje;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Stanica;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Vlak;

public class KomandaDPK implements Komanda {
  SustavTvrtke sustavTvrtke = SustavTvrtke.getInstance();

  public KomandaDPK(String komanda) {
    izvrsi(komanda);
  }

  @Override
  public void izvrsi(String line) {
    String[] polje = line.split(" - ");
    String[] imePrez = polje[0].split(" ");
    boolean korisnikPostoji = false;

    for (Korisnik k : sustavTvrtke.sviKorisnici) {
      if (k.ime.equals(imePrez[1]) && k.prezime.equals(imePrez[2])) {
        korisnikPostoji = true;
        if (polje.length == 2 && provjeriVlak(polje[1])) {
          KorisnikPracenje korisnikPracenje = new KorisnikPracenje(k, polje[1], null);
          sustavTvrtke.listaPracenja.add(korisnikPracenje);
          System.out.println("Pracenje uspjesno dodano");
        }
        if (polje.length == 3 && provjeriVlak(polje[1]) && provjeriStanicu(polje[2])) {
          KorisnikPracenje korisnikPracenje = new KorisnikPracenje(k, polje[1], polje[2]);
          sustavTvrtke.listaPracenja.add(korisnikPracenje);
          System.out.println("Pracenje uspjesno dodano");
        }
      }
    }
    if (!korisnikPostoji) {
      sustavTvrtke.redniBrojPogreske++;
      System.out.println("Redni broj pogreške: " + sustavTvrtke.redniBrojPogreske
          + ". Unijeli ste korisnika koji ne postoji.");
    }
  }

  private boolean provjeriVlak(String oznaka) {
    boolean vlakPostoji = false;
    for (Vlak vlak : sustavTvrtke.vozniRedovi) {
      if (vlak.oznakaVlaka.equals(oznaka)) {
        vlakPostoji = true;
      }
    }
    if (!vlakPostoji) {
      sustavTvrtke.redniBrojPogreske++;
      System.out.println("Redni broj pogreške: " + sustavTvrtke.redniBrojPogreske
          + ". Unijeli ste vlak koji ne postoji.");
    }

    return vlakPostoji;
  }

  private boolean provjeriStanicu(String stanica) {
    boolean stanicaPostoji = false;
    for (Stanica s : sustavTvrtke.stanice) {
      if (s.mjesto.equals(stanica)) {
        stanicaPostoji = true;
      }
    }
    if (!stanicaPostoji) {
      sustavTvrtke.redniBrojPogreske++;
      System.out.println("Redni broj pogreške: " + sustavTvrtke.redniBrojPogreske
          + ". Unijeli ste stanicu koja ne postoji.");
    }

    return stanicaPostoji;
  }
}
