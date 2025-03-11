package edu.unizg.foi.uzdiz.tsmetisko.CitacFactoryMethod;

public class CreatorStanica extends CitacCreator {
  public StanicaCitac factoryMethod(String ime) {
    return new StanicaCitac(ime);
  }
}
