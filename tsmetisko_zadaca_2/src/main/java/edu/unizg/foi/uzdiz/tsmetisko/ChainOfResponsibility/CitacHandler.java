package edu.unizg.foi.uzdiz.tsmetisko.ChainOfResponsibility;

public interface CitacHandler {
  void procitaj(String naziv);

  void setNextHandler(CitacHandler nextHandler);
}
