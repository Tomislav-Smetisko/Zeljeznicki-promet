package edu.unizg.foi.uzdiz.tsmetisko.Composite;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import edu.unizg.foi.uzdiz.tsmetisko.SustavTvrtke;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Stanica;

public class EtapaComposite extends VozniRedComposite {
  public String oznakaPruge;
  public String smjer;
  public String polaznaStanica;
  public String odredisnaStanica;
  public LocalTime vrijemePolaska;
  public LocalTime trajanjeVoznje;
  public Integer oznakaDana;

  protected List<VozniRedComponent> stanice = new ArrayList<>();
  SustavTvrtke sustavTvrtke = SustavTvrtke.getInstance();

  public EtapaComposite(String oznakaPruge, String smjer, String polaznaStanica,
      String odredisnaStanica, LocalTime vrijemePolaska, LocalTime trajanjeVoznje,
      Integer oznakaDana) {
    super();
    this.oznakaPruge = oznakaPruge;
    this.smjer = smjer;
    this.polaznaStanica = polaznaStanica;
    this.odredisnaStanica = odredisnaStanica;
    this.vrijemePolaska = vrijemePolaska;
    this.trajanjeVoznje = trajanjeVoznje;
    this.oznakaDana = oznakaDana;
  }



  public void ispisi(String oznakaVlaka) {
    // ispisi etape

    LocalTime vrijemeDolaska =
        vrijemePolaska.plusHours(trajanjeVoznje.getHour()).plusMinutes(trajanjeVoznje.getMinute());

    String dani = "";
    for (var zapis : sustavTvrtke.oznakeDana) {
      if (Integer.valueOf(zapis.oznakaDana).equals(oznakaDana)) {
        dani = zapis.daniVoznje;
      }
    }


    System.out.printf("| %-12s | %-12s | %-22s | %-22s | %-15s | %-15s | %12s%1s | %-13s | %n",
        oznakaVlaka, oznakaPruge, polaznaStanica, odredisnaStanica, vrijemePolaska, vrijemeDolaska,
        izracunajBrojKm(polaznaStanica, odredisnaStanica, oznakaPruge), "km", dani);

  }

  private int izracunajBrojKm(String pocetna, String zavrsna, String oznaka) {
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

  public boolean add(VozniRedComponent component) {
    this.stanice.add(component);
    return true;
  }

  public boolean remove(VozniRedComponent component) {
    this.stanice.remove(component);
    return true;
  }

  public VozniRedComponent getChild(int i) {
    return this.stanice.get(i);
  }

  public String getOznakaPruge() {
    return this.oznakaPruge;
  }

  public List<VozniRedComponent> getStanice() {
    return this.stanice;
  }
}
