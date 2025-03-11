package edu.unizg.foi.uzdiz.tsmetisko.Podaci;

import java.time.LocalTime;

public class Vlak {
  public String oznakaPruge;
  public String smjer;
  public String polaznaStanica;
  public String odredisnaStanica;
  public String oznakaVlaka;
  public String vrstaVlaka;
  public LocalTime vrijemePolaska;
  public LocalTime trajanjeVoznje;
  public Integer oznakaDana;

  public Vlak(String oznakaPruge, String smjer, String polaznaStanica, String odredisnaStanica,
      String oznakaVlaka, String vrstaVlaka, LocalTime vrijemePolaska, LocalTime trajanjeVoznje,
      Integer oznakaDana) {
    super();
    this.oznakaPruge = oznakaPruge;
    this.smjer = smjer;
    this.polaznaStanica = polaznaStanica;
    this.odredisnaStanica = odredisnaStanica;
    this.oznakaVlaka = oznakaVlaka;
    this.vrstaVlaka = vrstaVlaka;
    this.vrijemePolaska = vrijemePolaska;
    this.trajanjeVoznje = trajanjeVoznje;
    this.oznakaDana = oznakaDana;
  }


}
