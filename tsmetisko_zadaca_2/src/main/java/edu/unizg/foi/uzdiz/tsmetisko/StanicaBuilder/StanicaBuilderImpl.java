package edu.unizg.foi.uzdiz.tsmetisko.StanicaBuilder;

import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Stanica;

public class StanicaBuilderImpl implements StanicaBuilder {
  private Stanica stanica;

  public StanicaBuilderImpl() {
    stanica = new Stanica();
  }

  @Override
  public Stanica build() {
    return stanica;
  }

  @Override
  public StanicaBuilder setMjesto(final String mjesto) {
    stanica.setMjesto(mjesto);
    return this;
  }

  @Override
  public StanicaBuilder setOznakaPruge(final String oznakaPruge) {
    stanica.setOznakaPruge(oznakaPruge);
    return this;
  }

  @Override
  public StanicaBuilder setVrstaStanice(final String vrstaStanice) {
    stanica.setVrstaStanice(vrstaStanice);
    return this;
  }

  @Override
  public StanicaBuilder setStatusStanice(final String statusStanice) {
    stanica.setStatusStanice(statusStanice);
    return this;
  }

  @Override
  public StanicaBuilder setAktivnostPutnici(final String aktivnostPutnici) {
    stanica.setAktivnostPutnici(aktivnostPutnici);
    return this;
  }

  @Override
  public StanicaBuilder setAktivnostRoba(final String aktivnostRoba) {
    stanica.setAktivnostRoba(aktivnostRoba);
    return this;
  }

  @Override
  public StanicaBuilder setKategorijaPruge(final String kategorijaPruge) {
    stanica.setKategorijaPruge(kategorijaPruge);
    return this;
  }

  @Override
  public StanicaBuilder setBrojPerona(final int brojPerona) {
    stanica.setBrojPerona(brojPerona);
    return this;
  }

  @Override
  public StanicaBuilder setVrstaPruge(final String vrstaPruge) {
    stanica.setVrstaPruge(vrstaPruge);
    return this;
  }

  @Override
  public StanicaBuilder setBrojKolosjeka(final int brojKolosjeka) {
    stanica.setBrojKolosjeka(brojKolosjeka);
    return this;
  }

  @Override
  public StanicaBuilder setDoPoOsovini(final double doPoOsovini) {
    stanica.setDoPoOsovini(doPoOsovini);
    return this;
  }

  @Override
  public StanicaBuilder setDoPoDuznomM(final double doPoDuznomM) {
    stanica.setDoPoDuznomM(doPoDuznomM);
    return this;
  }

  @Override
  public StanicaBuilder setStatusPruge(final String statusPruge) {
    stanica.setStatusPruge(statusPruge);
    return this;
  }

  @Override
  public StanicaBuilder setDuzina(final int duzina) {
    stanica.setDuzina(duzina);
    return this;
  }

  @Override
  public StanicaBuilder setVrijemeNormalniVlak(final int vrijemeNormalniVlak) {
    stanica.setVrijemeNormalniVlak(vrijemeNormalniVlak);
    return this;
  }

  @Override
  public StanicaBuilder setVrijemeUbrzaniVlak(final int vrijemeUbrzaniVlak) {
    stanica.setVrijemeUbrzaniVlak(vrijemeUbrzaniVlak);
    return this;
  }

  @Override
  public StanicaBuilder setVrijemeBrziVlak(final int vrijemeBrziVlak) {
    stanica.setVrijemeBrziVlak(vrijemeBrziVlak);
    return this;
  }
}
