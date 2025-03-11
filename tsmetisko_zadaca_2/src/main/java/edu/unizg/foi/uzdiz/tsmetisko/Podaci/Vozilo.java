package edu.unizg.foi.uzdiz.tsmetisko.Podaci;

public class Vozilo {
  public String oznaka;
  public String opis;
  public String proizvodac;
  public int godina;
  public String namjena;
  public String vrstaPrijevoza;
  public String vrstaPogona;
  public int maxBrzina;
  public double maxSnaga;
  public int brojSjedecihMjesta;
  public int brojStajacihMjesta;
  public int brojBicikala;
  public int brojKreveta;
  public int brojAutomobila;
  public double nosivost;
  public double povrsina;
  public int zapremnina;
  public String status;

  public Vozilo(String oznaka, String opis, String proizvodac, int godina, String namjena,
      String vrstaPrijevoza, String vrstaPogona, int maxBrzina, double maxSnaga,
      int brojSjedecihMjesta, int brojStajacihMjesta, int brojBicikala, int brojKreveta,
      int brojAutomobila, double nosivost, double povrsina, int zapremnina, String status) {

    super();
    this.oznaka = oznaka;
    this.opis = opis;
    this.proizvodac = proizvodac;
    this.godina = godina;
    this.namjena = namjena;
    this.vrstaPrijevoza = vrstaPrijevoza;
    this.vrstaPogona = vrstaPogona;
    this.maxBrzina = maxBrzina;
    this.maxSnaga = maxSnaga;
    this.brojSjedecihMjesta = brojSjedecihMjesta;
    this.brojStajacihMjesta = brojStajacihMjesta;
    this.brojBicikala = brojBicikala;
    this.brojKreveta = brojKreveta;
    this.brojAutomobila = brojAutomobila;
    this.nosivost = nosivost;
    this.povrsina = povrsina;
    this.zapremnina = zapremnina;
    this.status = status;
  }



}
