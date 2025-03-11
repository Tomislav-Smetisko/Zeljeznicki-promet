package edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod;

public class CreatorKomandaIK extends KomandaCreator {
  @Override
  public KomandaIK factoryMethod(String komanda) {
    return new KomandaIK(komanda);
  }
}
