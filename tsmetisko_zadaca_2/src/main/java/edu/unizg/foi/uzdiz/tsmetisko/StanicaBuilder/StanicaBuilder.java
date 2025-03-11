package edu.unizg.foi.uzdiz.tsmetisko.StanicaBuilder;

import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Stanica;

public interface StanicaBuilder {
  Stanica build();

  StanicaBuilder setMjesto(final String mjesto);

  StanicaBuilder setOznakaPruge(final String oznakaPruge);

  StanicaBuilder setVrstaStanice(final String vrstaStanice);

  StanicaBuilder setStatusStanice(final String statusStanice);

  StanicaBuilder setAktivnostPutnici(final String aktivnostPutnici);

  StanicaBuilder setAktivnostRoba(final String aktivnostRoba);

  StanicaBuilder setKategorijaPruge(final String kategorijaPruge);

  StanicaBuilder setBrojPerona(final int brojPerona);

  StanicaBuilder setVrstaPruge(final String vrstaPruge);

  StanicaBuilder setBrojKolosjeka(final int brojKolosjeka);

  StanicaBuilder setDoPoOsovini(final double doPoOsovini);

  StanicaBuilder setDoPoDuznomM(final double doPoDuznomM);

  StanicaBuilder setStatusPruge(final String statusPruge);

  StanicaBuilder setDuzina(final int duzina);

  StanicaBuilder setVrijemeNormalniVlak(final int vrijemeNormalniVlak);

  StanicaBuilder setVrijemeUbrzaniVlak(final int vrijemeUbrzaniVlak);

  StanicaBuilder setVrijemeBrziVlak(final int vrijemeBrziVlak);
}
