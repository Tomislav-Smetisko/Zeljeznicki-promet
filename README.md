# Zeljeznicki-promet
Aplikacija koja nudi uvid u željeznički promet s fokusom na korištenje uzoraka dizajna.


## Upute za pokretanje

Program je pisan u programskom jeziku Java te je podešena konfiguracija projekta (maven) tako da se kreira Java izvršna .jar datoteka.
Potrebno je napraviti „mvn clean package“ te kod pokretanja unijeti izvršnu .jar datoteku i odgovarajuće nazive datoteka, npr. 

java -jar /home/NWTiS_3/tsmetisko/tsmetisko_zadaca_2/target/tsmetisko_zadaca_2-1.0.0.jar --zs DZ_2_stanice.csv --zps DZ_2_vozila.csv --zk DZ_2_kompozicije.csv --zvr DZ_2_vozni_red.csv --zod DZ_2_oznake_dana.csv


### Komande za izvršavanje aktivnosti:

● Pregled pruga

○ Sintaksa:

■ IP

○ Primjer:

■ IP

○ Opis primjera:

■ Ispis tablice s prugama (oznaka, početna i završna željeznička stanica, ukupan broj kilometara).


● Pregled željezničkih stanica za odabranoj pruzi

○ Sintaksa:

■ ISP oznakaPruge redoslijed

○ Primjer:

■ ISP M501 N

○ Opis primjera:

■ Ispis tablice sa željezničkim stanicama na odabranoj pruzi (naziv željezničke stanice, vrsta, broj kilometara od početne željezničke stanice) prema normalnom redoslijedu. Npr. kod M501 ide od Kotoriba do Macinec.

○ Primjer:

■ ISP M501 O

○ Opis primjera:

■ Ispis tablice sa željezničkim stanicama na odabranoj pruzi (naziv željezničke stanice, vrsta, broj kilometara od početne željezničke stanice) prema obrnutom redoslijedu. Npr. kod M501 ide od Macinec do Kotoriba.


● Pregled željezničkih stanica između dviju željezničkih stanica

○ Sintaksa:

■ ISI2S polaznaStanica - odredišnaStanica

○ Primjer: 
■ ISI2S Donji Kraljevec - Čakovec

○ Opis primjera:

■ Ispis tablice sa željezničkim stanicama između dviju željezničke stanica (naziv željezničke stanice, vrsta, broj kilometara od početne željezničke stanice). U primjeru su stanice koje su na istoj pruzi.

○ Primjer:

■ ISI2S Donji Kraljevec - Zagreb glavni kolodvor

○ Opis primjera:

■ Ispis tablice sa željezničkim stanicama između dviju željezničke stanica (naziv željezničke stanice, vrsta, broj kilometara od početne željezničke stanice) U primjeru su željezničke stanice koje su na različitim prugama.


● Pregled kompozicije

○ Sintaksa:

■ IK oznaka

○ Primjer:

■ IK 8001

○ Opis primjera:

■ Ispis tablice sa prijevoznim sredstvima u kompoziciji (oznaka, uloga, opis, godina, namjena, vrsta pogona, maks. brzina).


● Pregled vlakova

○ Sintaksa:

■ IV

○ Primjer:

■ IV

○ Opis primjera:

■ Ispis tablice sa vlakovima (oznaka vlaka, polazna željeznička stanica, odredišna željeznička stanica, vrijeme polaska, vrijeme dolaska u odredišnu stanicu, ukupan broj km od polazne željezničke stanice do odredišne željezničke stanice vlaka).


● Pregled etapa vlaka

○ Sintaksa:

■ IEV oznaka

○ Primjer:

■ IEV 3609

○ Opis primjera:

■ Ispis tablice sa etapama vlaka (oznaka vlaka, oznaka pruge, polazna željeznička stanica etape, odredišna željeznička stanica etape, vrijeme polaska s polazne željezničke stanice etape, vrijeme dolaska u odredišnu stanicu etape, ukupan broj km od polazne željezničke stanice etape do odredišne željezničke stanice vlaka etape, daniUTjednu za etapu).


● Pregled vlakova koji voze sve etape na određene dane u tjednu

○ Sintaksa:

■ IEVD dani

○ Primjer:

■ IEVD PoSrPeN

○ Opis primjera:

■ Ispis tablice sa vlakovima i njihovim etapama koje voze na određene dane u tjednu (oznaka vlaka, oznaka pruge, polazna željeznička stanica etape, odredišna željeznička stanica etape, vrijeme polaska s polazne željezničke stanice etape, vrijeme dolaska u odredišnu željezničke stanicu etape daniUTjednu za etapu).


● Pregled voznog reda vlaka

○ Sintaksa:

■ IVRV oznaka

○ Primjer:

■ IVRV 3609

○ Opis primjera:

■ Ispis tablice sa svim željezničkim stanicama na kojima staje vlak (oznaka vlaka, oznaka pruge, željeznička stanica, vrijeme polaska sa željezničke stanice, broj km od polazne stanice vlaka).


● Dodavanje korisnika u registar korisnika

○ Sintaksa:

■ DK ime prezime

○ Primjer:

■ DK Pero Kos

○ Opis primjera:

■ Dodaje se korisnik


● Pregled korisnika iz registra korisnika

○ Sintaksa:

■ PK

○ Primjer:

■ PK

○ Opis primjera:

■ Ispis korisnika


● Dodavanje korisnika za praćenja putovanja vlaka ili dolaska u određenu željezničku stanicu

○ Sintaksa:

■ DPK ime prezime - oznakaVlaka [- stanica]

○ Primjer:

■ DPK Pero Kos - 3301

■ DPK Mato Medved - 3309 - Donji Kraljevec

○ Opis primjera:

■ Dodavanje korisnika Pero Kos za praćenje vlaka s oznakom 3301

■ Dodavanje korisnika Mato Medved za praćenje vlaka s oznakom 3309 za željezničku stanicu Donji Kraljevec


● Prekid rada programa

○ Sintaksa:

■ Q


### Korišteni uzorci dizajna

![image](https://github.com/user-attachments/assets/f8c601c1-52a8-4f1a-b2a0-502e84dc7fbb)


### Dijagram klasa

![Untitled Diagram drawio](https://github.com/user-attachments/assets/2c4526fb-aaf4-4157-870b-ce6cc4ee9b62)
