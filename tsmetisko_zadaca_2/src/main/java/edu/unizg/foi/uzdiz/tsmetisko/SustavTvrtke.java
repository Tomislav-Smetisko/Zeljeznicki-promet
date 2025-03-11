package edu.unizg.foi.uzdiz.tsmetisko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import edu.unizg.foi.uzdiz.tsmetisko.Composite.EtapaComposite;
import edu.unizg.foi.uzdiz.tsmetisko.Composite.StanicaLeaf;
import edu.unizg.foi.uzdiz.tsmetisko.Composite.VlakComposite;
import edu.unizg.foi.uzdiz.tsmetisko.Composite.VozniRedComponent;
import edu.unizg.foi.uzdiz.tsmetisko.Composite.VozniRedComposite;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Kompozicija;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Korisnik;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.KorisnikPracenje;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.PopisOznakaDana;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Stanica;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Vlak;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Vozilo;

public class SustavTvrtke {
  private static volatile SustavTvrtke INSTANCE = new SustavTvrtke();
  public boolean radi = true;
  public int redniBrojPogreske = 0;
  public String putanja = System.getProperty("user.dir") + "/podaci/";

  public List<Stanica> stanice = new ArrayList<Stanica>();
  public List<Vozilo> vozila = new ArrayList<Vozilo>();
  public List<Kompozicija> kompozicije = new ArrayList<Kompozicija>();

  public Map<String, List<Stanica>> stanicePoPrugama = new HashMap<>();
  public Map<String, List<Kompozicija>> kompozicijePoOznakama = new HashMap<>();

  public List<Vlak> vozniRedovi = new ArrayList<Vlak>();
  public List<PopisOznakaDana> oznakeDana = new ArrayList<PopisOznakaDana>();

  public List<Korisnik> sviKorisnici = new ArrayList<Korisnik>();
  public List<KorisnikPracenje> listaPracenja = new ArrayList<KorisnikPracenje>();

  public VozniRedComposite vozniRedComposite = new VozniRedComposite();

  private SustavTvrtke() {

  }

  public static SustavTvrtke getInstance() {
    return INSTANCE;
  }

  public void provjeriIspravnostKompozicije() {
    Iterator<Map.Entry<String, List<Kompozicija>>> iterator =
        kompozicijePoOznakama.entrySet().iterator();

    while (iterator.hasNext()) {
      Map.Entry<String, List<Kompozicija>> entry = iterator.next();
      List<Kompozicija> listaKompozicija = entry.getValue();

      boolean minJedanVagon = false;
      for (Kompozicija k : listaKompozicija) {
        if (k.uloga.contains("V")) {
          minJedanVagon = true;
        }
      }

      Kompozicija prvaKompozicija = listaKompozicija.get(0);
      if (!prvaKompozicija.uloga.contains("P") || !minJedanVagon) {
        iterator.remove();
      }
    }
  }

  public void popuniEtapeSaStanicama() {
    List<Stanica> lista = null;

    for (VozniRedComponent vlakComponent : vozniRedComposite.getVlakovi()) {
      VlakComposite vlak = (VlakComposite) vlakComponent;

      for (VozniRedComponent etapaComponent : vlak.getEtape()) {
        EtapaComposite etapa = (EtapaComposite) etapaComponent;

        Stanica pocetnaS = null;
        Stanica zavrsnaS = null;

        for (var pruga : stanicePoPrugama.entrySet()) {
          if (pruga.getKey().equals(etapa.oznakaPruge)) {
            lista = pruga.getValue();
          }
        }
        for (var s : lista) {
          if (s.mjesto.equals(etapa.polaznaStanica)) {
            pocetnaS = s;
          }
          if (s.mjesto.equals(etapa.odredisnaStanica)) {
            zavrsnaS = s;
          }
        }
        int index1 = lista.indexOf(pocetnaS);
        int index2 = lista.indexOf(zavrsnaS);

        if (index1 < index2) {
          index2++;
          List<Stanica> podlista = lista.subList(index1, index2);
          for (var p : podlista) {
            StanicaLeaf novaStanica =
                new StanicaLeaf(p.mjesto, p.oznakaPruge, p.vrstaStanice, p.statusStanice,
                    p.aktivnostPutnici, p.aktivnostRoba, p.kategorijaPruge, p.brojPerona,
                    p.vrstaPruge, p.brojKolosjeka, p.doPoOsovini, p.doPoDuznomM, p.statusPruge,
                    p.duzina, p.vrijemeNormalniVlak, p.vrijemeUbrzaniVlak, p.vrijemeBrziVlak);
            etapa.add(novaStanica);
          }
        } else if (index1 > index2) {
          index1++;
          List<Stanica> podlista = lista.subList(index2, index1);
          for (int i = podlista.size() - 1; i >= 0; i--) {
            StanicaLeaf novaStanica = new StanicaLeaf(podlista.get(i).mjesto,
                podlista.get(i).oznakaPruge, podlista.get(i).vrstaStanice,
                podlista.get(i).statusStanice, podlista.get(i).aktivnostPutnici,
                podlista.get(i).aktivnostRoba, podlista.get(i).kategorijaPruge,
                podlista.get(i).brojPerona, podlista.get(i).vrstaPruge,
                podlista.get(i).brojKolosjeka, podlista.get(i).doPoOsovini,
                podlista.get(i).doPoDuznomM, podlista.get(i).statusPruge, podlista.get(i).duzina,
                podlista.get(i).vrijemeNormalniVlak, podlista.get(i).vrijemeUbrzaniVlak,
                podlista.get(i).vrijemeBrziVlak);
            etapa.add(novaStanica);
          }
        }
      }
    }
  }

}
