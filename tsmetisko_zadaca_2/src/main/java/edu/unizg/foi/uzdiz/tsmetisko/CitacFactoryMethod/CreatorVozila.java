package edu.unizg.foi.uzdiz.tsmetisko.CitacFactoryMethod;

public class CreatorVozila extends CitacCreator {
  public VozilaCitac factoryMethod(String ime) {
    return new VozilaCitac(ime);
  }
}
