package edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod;

import edu.unizg.foi.uzdiz.tsmetisko.SustavTvrtke;

public class KomandaQ implements Komanda {
  SustavTvrtke sustavTvrtke = SustavTvrtke.getInstance();

  public KomandaQ(String komanda) {
    izvrsi(komanda);
  }

  @Override
  public void izvrsi(String line) {
    sustavTvrtke.radi = false;
  }

}
