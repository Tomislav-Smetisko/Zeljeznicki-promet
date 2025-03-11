package edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod;

public class CreatorKomandaISPNormalno extends KomandaCreator {
  @Override
  public KomandaISPNormalno factoryMethod(String komanda) {
    return new KomandaISPNormalno(komanda);
  }
}
