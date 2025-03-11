package edu.unizg.foi.uzdiz.tsmetisko.CitacFactoryMethod;

public class CreatorKompozicija extends CitacCreator {
  public KompozicijaCitac factoryMethod(String ime) {
    return new KompozicijaCitac(ime);
  }
}
