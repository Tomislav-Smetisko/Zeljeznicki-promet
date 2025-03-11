package edu.unizg.foi.uzdiz.tsmetisko.Composite;

public class StanicaLeaf extends VozniRedComponent {
  public String mjesto;
  public String oznakaPruge;
  public String vrstaStanice;
  public String statusStanice;
  public String aktivnostPutnici;
  public String aktivnostRoba;
  public String kategorijaPruge;
  public int brojPerona;
  public String vrstaPruge;
  public int brojKolosjeka;
  public double doPoOsovini;
  public double doPoDuznomM;
  public String statusPruge;
  public int duzina;
  public int vrijemeNormalniVlak;
  public int vrijemeUbrzaniVlak;
  public int vrijemeBrziVlak;

  public StanicaLeaf(String mjesto, String oznakaPruge, String vrstaStanice, String statusStanice,
      String aktivnostPutnici, String aktivnostRoba, String kategorijaPruge, int brojPerona,
      String vrstaPruge, int brojKolosjeka, double doPoOsovini, double doPoDuznomM,
      String statusPruge, int duzina, int vrijemeNormalniVlak, int vrijemeUbrzaniVlak,
      int vrijemeBrziVlak) {
    super();
    this.mjesto = mjesto;
    this.oznakaPruge = oznakaPruge;
    this.vrstaStanice = vrstaStanice;
    this.statusStanice = statusStanice;
    this.aktivnostPutnici = aktivnostPutnici;
    this.aktivnostRoba = aktivnostRoba;
    this.kategorijaPruge = kategorijaPruge;
    this.brojPerona = brojPerona;
    this.vrstaPruge = vrstaPruge;
    this.brojKolosjeka = brojKolosjeka;
    this.doPoOsovini = doPoOsovini;
    this.doPoDuznomM = doPoDuznomM;
    this.statusPruge = statusPruge;
    this.duzina = duzina;
    this.vrijemeNormalniVlak = vrijemeNormalniVlak;
    this.vrijemeUbrzaniVlak = vrijemeUbrzaniVlak;
    this.vrijemeBrziVlak = vrijemeBrziVlak;
  }

  @Override
  public void ispisi() {
    // TODO Auto-generated method stub

  }

}
