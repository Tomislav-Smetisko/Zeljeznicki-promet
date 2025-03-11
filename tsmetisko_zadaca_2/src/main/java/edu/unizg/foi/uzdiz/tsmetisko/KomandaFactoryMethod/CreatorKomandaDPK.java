package edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod;

public class CreatorKomandaDPK extends KomandaCreator {
  @Override
  public KomandaDPK factoryMethod(String komanda) {
    return new KomandaDPK(komanda);
  }
}
