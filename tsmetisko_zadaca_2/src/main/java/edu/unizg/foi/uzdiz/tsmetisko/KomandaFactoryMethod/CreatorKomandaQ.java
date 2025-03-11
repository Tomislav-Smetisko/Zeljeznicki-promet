package edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod;

public class CreatorKomandaQ extends KomandaCreator {
  @Override
  public KomandaQ factoryMethod(String komanda) {
    return new KomandaQ(komanda);
  }
}
