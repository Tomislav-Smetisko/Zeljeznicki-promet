package edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod;

public class CreatorKomandaDK extends KomandaCreator {
  @Override
  public KomandaDK factoryMethod(String komanda) {
    return new KomandaDK(komanda);
  }
}
