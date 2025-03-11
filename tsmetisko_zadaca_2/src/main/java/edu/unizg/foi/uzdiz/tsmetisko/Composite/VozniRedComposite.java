package edu.unizg.foi.uzdiz.tsmetisko.Composite;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import edu.unizg.foi.uzdiz.tsmetisko.SustavTvrtke;

public class VozniRedComposite extends VozniRedComponent {
  protected List<VozniRedComponent> vlakovi = new ArrayList<>();

  public VozniRedComposite() {

  }

  public void ispisi() {
    // ispisuje sve vlakove
    System.out.println(
        "-------------------------------------------------------------------------------------------------------------------");
    System.out.printf("| %-5s | %-22s | %-22s | %1s | %1s | %1s%n", "OZNAKA VLAKA",
        "POČETNA STANICA", "ODREDIŠNA STANICA", "VRIJEME POLASKA", "VRIJEME DOLASKA",
        "UKUPAN BROJ KM");
    System.out.println(
        "-------------------------------------------------------------------------------------------------------------------");

    for (VozniRedComponent c : this.vlakovi) {
      c.ispisi();
    }

    System.out.println(
        "-------------------------------------------------------------------------------------------------------------------");
  }

  public boolean add(VozniRedComponent component) {
    this.vlakovi.add(component);
    return true;
  }

  public boolean remove(VozniRedComponent component) {
    this.vlakovi.remove(component);
    return true;
  }

  public VlakComposite dohvatiVlak(String oznakaVlaka) {
    VlakComposite vlak = null;
    for (VozniRedComponent c : vlakovi) {
      if (((VlakComposite) c).getOznakaVlaka().equals(oznakaVlaka)) {
        vlak = (VlakComposite) c;
      }
    }
    return vlak;
  }

  public List<VozniRedComponent> getVlakovi() {
    return this.vlakovi;
  }

  public void komandaIEVD(String line) {
    SustavTvrtke sustavTvrtke = SustavTvrtke.getInstance();

    String[] polje = line.split(" ");
    String[] oznakeDana = polje[1].split("(?=[A-ZČ])");

    System.out.println(
        "------------------------------------------------------------------------------------------------------------------------------------");
    System.out.printf("| %-5s | %-5s | %-22s | %-22s | %1s | %1s | %-1s%n", "OZNAKA VLAKA",
        "OZNAKA PRUGE", "POČETNA STANICA", "ODREDIŠNA STANICA", "VRIJEME POLASKA",
        "VRIJEME DOLASKA", "DANI U TJEDNU");
    System.out.println(
        "------------------------------------------------------------------------------------------------------------------------------------");

    for (VozniRedComponent vlakComponent : vlakovi) {
      VlakComposite vlak = (VlakComposite) vlakComponent;

      for (VozniRedComponent etapaComponent : vlak.getEtape()) {
        EtapaComposite etapa = (EtapaComposite) etapaComponent;

        for (var dani : sustavTvrtke.oznakeDana) {
          if (etapa.oznakaDana.equals(dani.oznakaDana)) {
            boolean sadrziSve = true;
            for (String s : oznakeDana) {
              if (!dani.daniVoznje.contains(s)) {
                sadrziSve = false;
                break;
              }
            }
            if (sadrziSve) {
              // postojiBaremJedan = true;
              LocalTime vrijemeDolaska =
                  etapa.vrijemePolaska.plusHours(etapa.trajanjeVoznje.getHour())
                      .plusMinutes(etapa.trajanjeVoznje.getMinute());

              System.out.printf("| %-12s | %-12s | %-22s | %-22s | %-15s | %-15s | %-13s | %n",
                  vlak.oznakaVlaka, etapa.oznakaPruge, etapa.polaznaStanica, etapa.odredisnaStanica,
                  etapa.vrijemePolaska, vrijemeDolaska, dani.daniVoznje);
            }
          }
        }
      }
    }
    System.out.println(
        "------------------------------------------------------------------------------------------------------------------------------------");
  }
}
