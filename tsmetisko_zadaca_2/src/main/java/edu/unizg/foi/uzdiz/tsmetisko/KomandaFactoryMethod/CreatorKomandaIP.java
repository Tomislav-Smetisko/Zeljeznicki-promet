package edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod;

public class CreatorKomandaIP extends KomandaCreator {

  @Override
  public KomandaIP factoryMethod(String komanda) {
    return new KomandaIP(komanda);
  }

}
