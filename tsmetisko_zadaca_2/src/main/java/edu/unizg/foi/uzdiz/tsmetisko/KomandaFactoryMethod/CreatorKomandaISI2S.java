package edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod;

public class CreatorKomandaISI2S extends KomandaCreator {
  @Override
  public KomandaISI2S factoryMethod(String komanda) {
    return new KomandaISI2S(komanda);
  }
}
