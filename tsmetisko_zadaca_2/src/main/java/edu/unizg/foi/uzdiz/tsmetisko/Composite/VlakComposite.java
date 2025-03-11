package edu.unizg.foi.uzdiz.tsmetisko.Composite;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import edu.unizg.foi.uzdiz.tsmetisko.SustavTvrtke;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Stanica;

public class VlakComposite extends VozniRedComposite {
  protected List<VozniRedComponent> etape = new ArrayList<>();
  public String oznakaVlaka;
  public String vrstaVlaka;

  public VlakComposite(String oznakaVlaka, String vrstaVlaka) {
    super();
    this.oznakaVlaka = oznakaVlaka;
    this.vrstaVlaka = vrstaVlaka;
  }

  public void ispisi() {
    // ispisuje sve etape za taj vlak

    for (VozniRedComponent etapaComponent : etape) {
      EtapaComposite etapa = (EtapaComposite) etapaComponent;

      LocalTime vrijemeDolaska = etapa.vrijemePolaska.plusHours(etapa.trajanjeVoznje.getHour())
          .plusMinutes(etapa.trajanjeVoznje.getMinute());

      System.out.printf("| %-12s | %-22s | %-22s | %-15s | %-15s | %12s%1s | %n", oznakaVlaka,
          etapa.polaznaStanica, etapa.odredisnaStanica, etapa.vrijemePolaska, vrijemeDolaska,
          izracunajBrojKm(etapa.polaznaStanica, etapa.odredisnaStanica, etapa.oznakaPruge), "km");
    }
  }

  private int izracunajBrojKm(String pocetna, String zavrsna, String oznaka) {
    SustavTvrtke sustavTvrtke = SustavTvrtke.getInstance();
    List<Stanica> lista = null;
    Stanica pocetnaS = null;
    Stanica zavrsnaS = null;
    int udaljenost = 0;

    for (var pruga : sustavTvrtke.stanicePoPrugama.entrySet()) {
      if (pruga.getKey().equals(oznaka)) {
        lista = pruga.getValue();
      }
    }
    for (var s : lista) {
      if (s.mjesto.equals(pocetna)) {
        pocetnaS = s;
      }
      if (s.mjesto.equals(zavrsna)) {
        zavrsnaS = s;
      }
    }
    int index1 = lista.indexOf(pocetnaS);
    int index2 = lista.indexOf(zavrsnaS);


    if (index1 < index2) {
      index2++;
      List<Stanica> podlista = lista.subList(index1, index2);
      for (var p : podlista) {
        udaljenost += p.duzina;
      }
    } else if (index1 > index2) {
      index1++;
      List<Stanica> podlista = lista.subList(index2, index1);
      for (int i = podlista.size() - 1; i >= 0; i--) {
        udaljenost += podlista.get(i).duzina;
      }
    }

    return udaljenost;
  }

  public String getOznakaVlaka() {
    return this.oznakaVlaka;
  }

  public List<VozniRedComponent> getEtape() {
    return this.etape;
  }

  public boolean add(VozniRedComponent component) {
    if (!(component instanceof VozniRedComposite)) {
      return false;
    }
    this.etape.add(component);
    return true;
  }

  public boolean remove(VozniRedComponent component) {
    if (!(component instanceof VozniRedComposite)) {
      return false;
    }
    this.etape.remove(component);
    return true;
  }

  public VozniRedComponent getChild(int i) {
    return this.etape.get(i);
  }

  @Override
  public String toString() {
    // return "Oznaka vlaka: " + oznakaVlaka;
    System.out.println(
        "--------------------------------------------------------------------------------------------------------------------------------------------------");
    System.out.printf("| %-5s | %-5s | %-22s | %-22s | %1s | %1s | %1s | %-1s%n", "OZNAKA VLAKA",
        "OZNAKA PRUGE", "POČETNA STANICA", "ODREDIŠNA STANICA", "VRIJEME POLASKA",
        "VRIJEME DOLASKA", "UKUPAN BROJ KM", "DANI U TJEDNU");
    System.out.println(
        "--------------------------------------------------------------------------------------------------------------------------------------------------");


    for (VozniRedComponent c : this.etape) {
      EtapaComposite etapa = (EtapaComposite) c;
      etapa.ispisi(oznakaVlaka);
    }

    System.out.println(
        "--------------------------------------------------------------------------------------------------------------------------------------------------");
    return "";
  }

  public void komandaIVRV() {
    int udaljenost = 0;

    System.out.println(
        "-------------------------------------------------------------------------------------------");
    System.out.printf("| %-5s | %-5s | %-22s | %1s | %-1s |%n", "OZNAKA VLAKA", "OZNAKA PRUGE",
        "POČETNA STANICA", "VRIJEME POLASKA", "UKUPAN BROJ KM");
    System.out.println(
        "-------------------------------------------------------------------------------------------");

    for (VozniRedComponent etapaComponent : etape) {
      EtapaComposite etapa = (EtapaComposite) etapaComponent;

      LocalTime polazak = etapa.vrijemePolaska;

      for (VozniRedComponent stanicaComponent : etapa.getStanice()) {
        StanicaLeaf stanica = (StanicaLeaf) stanicaComponent;

        udaljenost += stanica.duzina;
        polazak = polazak.plusMinutes(stanica.vrijemeNormalniVlak);

        System.out.printf("| %-12s | %-12s | %-22s | %-15s | %12s%1s | %n", oznakaVlaka,
            stanica.oznakaPruge, stanica.mjesto, polazak, udaljenost, "km");
      }
    }
    System.out.println(
        "-------------------------------------------------------------------------------------------");
  }
}
