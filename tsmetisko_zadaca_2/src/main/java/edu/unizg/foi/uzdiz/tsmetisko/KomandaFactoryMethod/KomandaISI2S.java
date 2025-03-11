package edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import edu.unizg.foi.uzdiz.tsmetisko.SustavTvrtke;
import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Stanica;

public class KomandaISI2S implements Komanda {
  SustavTvrtke sustavTvrtke = SustavTvrtke.getInstance();

  public KomandaISI2S(String komanda) {
    izvrsi(komanda);
  }

  @Override
  public void izvrsi(String komanda) {
    String[] dioKomande = komanda.split(" - ");
    dioKomande[0] = dioKomande[0].substring(6);

    Map<String, List<String>> graf = kreirajGraf();

    pronadiSvePuteve(graf, dioKomande[0], dioKomande[1]);
  }

  public Map<String, List<String>> kreirajGraf() {
    Map<String, List<String>> graf = new HashMap<>();

    for (Map.Entry<String, List<Stanica>> pruga : sustavTvrtke.stanicePoPrugama.entrySet()) {
      List<Stanica> stanice = pruga.getValue();

      for (int i = 0; i < stanice.size(); i++) {
        String trenutnaStanica = stanice.get(i).mjesto;
        graf.putIfAbsent(trenutnaStanica, new ArrayList<>());

        // Poveži s prethodnom stanicom
        if (i > 0) {
          String prethodnaStanica = stanice.get(i - 1).mjesto;
          if (!graf.get(trenutnaStanica).contains(prethodnaStanica)) {
            graf.get(trenutnaStanica).add(prethodnaStanica);
          }
          if (!graf.get(prethodnaStanica).contains(trenutnaStanica)) {
            graf.get(prethodnaStanica).add(trenutnaStanica);
          }
        }

        // Poveži sa sljedećom stanicom
        if (i < stanice.size() - 1) {
          String sljedecaStanica = stanice.get(i + 1).mjesto;
          graf.putIfAbsent(sljedecaStanica, new ArrayList<>());
          if (!graf.get(trenutnaStanica).contains(sljedecaStanica)) {
            graf.get(trenutnaStanica).add(sljedecaStanica);
          }
          if (!graf.get(sljedecaStanica).contains(trenutnaStanica)) {
            graf.get(sljedecaStanica).add(trenutnaStanica);
          }
        }
      }
    }

    return graf;
  }

  public static void ispisiGraf(Map<String, List<String>> graf) {
    for (Map.Entry<String, List<String>> ulaz : graf.entrySet()) {
      System.out.println(ulaz.getKey() + " -> " + ulaz.getValue());
    }
  }

  public static void pronadiSvePuteve(Map<String, List<String>> graf, String pocetna,
      String zavrsna) {

    List<String> trenutniPut = new ArrayList<>();
    List<List<String>> sviPutevi = new ArrayList<>();
    Set<String> posjeceni = new HashSet<>();

    dfs(graf, pocetna, zavrsna, trenutniPut, sviPutevi, posjeceni);

    // Ispis svih pronađenih puteva
    for (List<String> put : sviPutevi) {
      ispisiPut(put);
    }
  }

  private static void dfs(Map<String, List<String>> graf, String trenutni, String zavrsna,
      List<String> trenutniPut, List<List<String>> sviPutevi, Set<String> posjeceni) {

    // Dodaj trenutni čvor u trenutni put
    trenutniPut.add(trenutni);
    posjeceni.add(trenutni);

    // Ako je trenutni čvor završni, dodaj trenutni put u sve puteve
    if (trenutni.equals(zavrsna)) {
      sviPutevi.add(new ArrayList<>(trenutniPut));
    } else {
      // Istraži sve susjede
      for (String susjed : graf.getOrDefault(trenutni, new ArrayList<>())) {
        if (!posjeceni.contains(susjed)) {
          dfs(graf, susjed, zavrsna, trenutniPut, sviPutevi, posjeceni);
        }
      }
    }

    // Ukloni trenutni čvor iz puta i postavi ga kao neposjećen (backtrack)
    trenutniPut.remove(trenutniPut.size() - 1);
    posjeceni.remove(trenutni);
  }

  private static void ispisiPut(List<String> put) {
    SustavTvrtke sustavTvrtke = SustavTvrtke.getInstance();
    List<Stanica> putSaStanicama = new ArrayList<Stanica>();
    int ukupnaUdaljenost = 0;

    for (int i = 0; i < put.size(); i++) {
      for (var mapa : sustavTvrtke.stanicePoPrugama.entrySet()) {
        var pruga = mapa.getValue();
        for (int j = 0; j < pruga.size(); j++) {
          if (i > 0 && i < put.size() && j > 0) {
            if (put.get(i).equals(pruga.get(j).mjesto)
                && put.get(i - 1).equals(pruga.get(j - 1).mjesto)) {
              putSaStanicama.add(pruga.get(j));
            }
          } else if (i == 0 && j < pruga.size() - 1) {
            if (put.get(i).equals(pruga.get(j).mjesto)
                && put.get(i + 1).equals(pruga.get(j + 1).mjesto)) {
              putSaStanicama.add(pruga.get(j));
            }
          }
        }
      }
    }
    System.out.println("----------------------------------------------------");
    System.out.printf("| %-23s | %-2s | %-2s |%n", "NAZIV STANICE", "VRSTA", "UKUPAN BROJ KM");
    System.out.println("----------------------------------------------------");

    for (Stanica s : putSaStanicama) {
      System.out.printf("| %-23s | %-5s | %11s%3s |%n", s.mjesto, s.vrstaStanice, ukupnaUdaljenost,
          "km");
      ukupnaUdaljenost += s.duzina;
    }
    System.out.println("----------------------------------------------------");
    System.out.println();
  }

}
