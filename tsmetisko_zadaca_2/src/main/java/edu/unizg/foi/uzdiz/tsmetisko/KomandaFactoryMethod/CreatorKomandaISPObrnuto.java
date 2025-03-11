package edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod;

public class CreatorKomandaISPObrnuto extends KomandaCreator {
  @Override
  public KomandaISPObrnuto factoryMethod(String komanda) {
    return new KomandaISPObrnuto(komanda);
  }
}
