package edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod;

public class CreatorKomandaPK extends KomandaCreator {
  @Override
  public KomandaPK factoryMethod(String komanda) {
    return new KomandaPK(komanda);
  }
}
