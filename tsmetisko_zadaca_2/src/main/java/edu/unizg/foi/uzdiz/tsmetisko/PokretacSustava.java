package edu.unizg.foi.uzdiz.tsmetisko;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.unizg.foi.uzdiz.tsmetisko.ChainOfResponsibility.CitacHandler;
import edu.unizg.foi.uzdiz.tsmetisko.ChainOfResponsibility.PopisOznakaDanaHandler;
import edu.unizg.foi.uzdiz.tsmetisko.ChainOfResponsibility.VozniRedHandler;
import edu.unizg.foi.uzdiz.tsmetisko.CitacFactoryMethod.CitacCreator;
import edu.unizg.foi.uzdiz.tsmetisko.CitacFactoryMethod.CreatorKompozicija;
import edu.unizg.foi.uzdiz.tsmetisko.CitacFactoryMethod.CreatorStanica;
import edu.unizg.foi.uzdiz.tsmetisko.CitacFactoryMethod.CreatorVozila;
import edu.unizg.foi.uzdiz.tsmetisko.CitacFactoryMethod.DatotekaCitac;
import edu.unizg.foi.uzdiz.tsmetisko.Composite.VozniRedComponent;
import edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod.CreatorKomandaDK;
import edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod.CreatorKomandaDPK;
import edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod.CreatorKomandaIK;
import edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod.CreatorKomandaIP;
import edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod.CreatorKomandaISI2S;
import edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod.CreatorKomandaISPNormalno;
import edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod.CreatorKomandaISPObrnuto;
import edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod.CreatorKomandaPK;
import edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod.CreatorKomandaQ;
import edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod.Komanda;
import edu.unizg.foi.uzdiz.tsmetisko.KomandaFactoryMethod.KomandaCreator;

public class PokretacSustava {

  public static void main(String[] args) {
    SustavTvrtke sustavTvrtke = SustavTvrtke.getInstance();

    List<String> naziviDatoteka = new ArrayList<>();
    List<String> datotekeCOR = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    // String regex =
    // "(?=.*--zs\\s+\\S+\\.csv)(?=.*--zps\\s+\\S+\\.csv)(?=.*--zk\\s+\\S+\\.csv)^((--zs\\s+\\S+\\.csv\\s*)|(--zps\\s+\\S+\\.csv\\s*)|(--zk\\s+\\S+\\.csv\\s*)){3}$";
    String unos = "";
    for (String arg : args) {
      unos += arg + " ";
    }

    String regexStanice = "--zs\\s+\\S+\\.csv";
    Pattern patternStanica = Pattern.compile(regexStanice);
    Matcher matcherStanica = patternStanica.matcher(unos);

    if (matcherStanica.find()) {
      String unosStanice = matcherStanica.group();
      naziviDatoteka.add(unosStanice);
    } else {
      System.out.println("Neispravna zastavica za datoteku stanica.");
      scanner.close();
      return;
    }

    String regexVozila = "--zps\\s+\\S+\\.csv";
    Pattern patternVozila = Pattern.compile(regexVozila);
    Matcher matcherVozila = patternVozila.matcher(unos);

    if (matcherVozila.find()) {
      String unosVozila = matcherVozila.group();
      naziviDatoteka.add(unosVozila);
    } else {
      System.out.println("Neispravna zastavica za datoteku vozila.");
      scanner.close();
      return;
    }

    String regexKompozicije = "--zk\\s+\\S+\\.csv";
    Pattern patternKompozicije = Pattern.compile(regexKompozicije);
    Matcher matcherKompozicije = patternKompozicije.matcher(unos);

    if (matcherKompozicije.find()) {
      String unosKompozicije = matcherKompozicije.group();
      naziviDatoteka.add(unosKompozicije);
    } else {
      System.out.println("Neispravna zastavica za datoteku kompozicija.");
      scanner.close();
      return;
    }

    String regexVozniRed = "--zvr\\s+\\S+\\.csv";
    Pattern patternVozniRed = Pattern.compile(regexVozniRed);
    Matcher matcherVozniRed = patternVozniRed.matcher(unos);

    if (matcherVozniRed.find()) {
      String unosVozniRed = matcherVozniRed.group();
      // naziviDatoteka.add(unosVozniRed);
      datotekeCOR.add(unosVozniRed);
    } else {
      System.out.println("Neispravna zastavica za datoteku voznih redova.");
      scanner.close();
      return;
    }

    String regexPopisOznakaDana = "--zod\\s+\\S+\\.csv";
    Pattern patternPopisOznakaDana = Pattern.compile(regexPopisOznakaDana);
    Matcher matcherPopisOznakaDana = patternPopisOznakaDana.matcher(unos);

    if (matcherPopisOznakaDana.find()) {
      String unosPopisOznakaDana = matcherPopisOznakaDana.group();
      // naziviDatoteka.add(unosPopisOznakaDana);
      datotekeCOR.add(unosPopisOznakaDana);
    } else {
      System.out.println("Neispravna zastavica za datoteku popisa oznaka dana.");
      scanner.close();
      return;
    }

    CitacCreator citacCreator;
    DatotekaCitac datotekaCitac;
    for (String s : naziviDatoteka) {
      String[] nazivSplitano = s.split(" ");
      if (nazivSplitano[0].equals("--zs")) {
        citacCreator = new CreatorStanica();
        datotekaCitac = citacCreator.factoryMethod(nazivSplitano[1]);
        datotekaCitac.procitaj();
      } else if (nazivSplitano[0].equals("--zps")) {
        citacCreator = new CreatorVozila();
        datotekaCitac = citacCreator.factoryMethod(nazivSplitano[1]);
        datotekaCitac.procitaj();
      } else if (nazivSplitano[0].equals("--zk")) {
        citacCreator = new CreatorKompozicija();
        datotekaCitac = citacCreator.factoryMethod(nazivSplitano[1]);
        datotekaCitac.procitaj();
      } else if (nazivSplitano[0].equals("--zvr")) {
        /*
         * citacCreator = new CreatorVozniRed(); datotekaCitac =
         * citacCreator.factoryMethod(nazivSplitano[1]); datotekaCitac.procitaj(); } else if
         * (nazivSplitano[0].equals("--zod")) { citacCreator = new CreatorPopisOznakaDana();
         * datotekaCitac = citacCreator.factoryMethod(nazivSplitano[1]); datotekaCitac.procitaj();
         */
      }
    }

    CitacHandler citacVozniRed = new VozniRedHandler();
    CitacHandler citacOznakeDana = new PopisOznakaDanaHandler();
    citacVozniRed.setNextHandler(citacOznakeDana);

    for (String s : datotekeCOR) {
      citacVozniRed.procitaj(s);
    }

    sustavTvrtke.provjeriIspravnostKompozicije();
    sustavTvrtke.popuniEtapeSaStanicama();


    while (sustavTvrtke.radi) {
      String line = scanner.nextLine();
      String[] dioUnos = line.split(" ");

      KomandaCreator creator;
      Komanda komanda;
      if (line.equals("IP")) {
        creator = new CreatorKomandaIP();
        komanda = creator.factoryMethod(line);
      } else if (dioUnos[0].equals("ISP") && dioUnos[2].equals("N") && dioUnos.length == 3) {
        creator = new CreatorKomandaISPNormalno();
        komanda = creator.factoryMethod(line);
      } else if (dioUnos[0].equals("ISP") && dioUnos[2].equals("O") && dioUnos.length == 3) {
        creator = new CreatorKomandaISPObrnuto();
        komanda = creator.factoryMethod(line);
      } else if (line.equals("Q")) {
        creator = new CreatorKomandaQ();
        komanda = creator.factoryMethod(line);
      } else if (dioUnos[0].equals("IK") && dioUnos.length == 2) {
        creator = new CreatorKomandaIK();
        komanda = creator.factoryMethod(line);
      } else if (dioUnos[0].equals("ISI2S")) {
        creator = new CreatorKomandaISI2S();
        komanda = creator.factoryMethod(line);
      } else if (line.equals("IV")) {
        sustavTvrtke.vozniRedComposite.ispisi();
      } else if (dioUnos[0].equals("IEV") && dioUnos.length == 2) {
        if (sustavTvrtke.vozniRedComposite.dohvatiVlak(dioUnos[1]) == null) {
          sustavTvrtke.redniBrojPogreske++;
          System.out.println("Redni broj pogreške: " + sustavTvrtke.redniBrojPogreske
              + ". Neispravna oznaka vlaka.");
        } else {
          VozniRedComponent v = sustavTvrtke.vozniRedComposite.dohvatiVlak(dioUnos[1]);
          System.out.println(v.toString());
        }
      } else if (dioUnos[0].equals("IEVD") && dioUnos.length == 2) {
        sustavTvrtke.vozniRedComposite.komandaIEVD(line);
      } else if (dioUnos[0].equals("IVRV") && dioUnos.length == 2) {
        if (sustavTvrtke.vozniRedComposite.dohvatiVlak(dioUnos[1]) == null) {
          sustavTvrtke.redniBrojPogreske++;
          System.out.println("Redni broj pogreške: " + sustavTvrtke.redniBrojPogreske
              + ". Neispravna oznaka vlaka.");
        } else {
          sustavTvrtke.vozniRedComposite.dohvatiVlak(dioUnos[1]).komandaIVRV();
        }
      } else if (dioUnos[0].equals("DK") && dioUnos.length == 3) {
        creator = new CreatorKomandaDK();
        komanda = creator.factoryMethod(line);
      } else if (line.equals("PK")) {
        creator = new CreatorKomandaPK();
        komanda = creator.factoryMethod(line);
      } else if (dioUnos[0].equals("DPK")) {
        creator = new CreatorKomandaDPK();
        komanda = creator.factoryMethod(line);
      } else {
        sustavTvrtke.redniBrojPogreske++;
        System.out.println(
            "Redni broj greške: " + sustavTvrtke.redniBrojPogreske + ". Neispravna komanda");
      }

    }
    scanner.close();
  }

}
