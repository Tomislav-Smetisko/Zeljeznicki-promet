package edu.unizg.foi.uzdiz.tsmetisko.StanicaBuilder;

import edu.unizg.foi.uzdiz.tsmetisko.Podaci.Stanica;

public class StanicaBuildDirector {
  private StanicaBuilder builder;

  public StanicaBuildDirector(final StanicaBuilder builder) {
    this.builder = builder;
  }

  public Stanica construct(String[] podaci) {
    return builder.setMjesto(podaci[0]).setOznakaPruge(podaci[1]).setVrstaStanice(podaci[2])
        .setStatusStanice(podaci[3]).setAktivnostPutnici(podaci[4]).setAktivnostRoba(podaci[5])
        .setKategorijaPruge(podaci[6]).setBrojPerona(Integer.parseInt(podaci[7]))
        .setVrstaPruge(podaci[8]).setBrojKolosjeka(Integer.parseInt(podaci[9]))
        .setDoPoOsovini(Double.parseDouble(podaci[10].replace(",", ".")))
        .setDoPoDuznomM(Double.parseDouble(podaci[11].replace(",", "."))).setStatusPruge(podaci[12])
        .setDuzina(Integer.parseInt(podaci[13])).setVrijemeNormalniVlak(checkIfNull(podaci[14]))
        .setVrijemeUbrzaniVlak(checkIfNull(podaci[14])).setVrijemeBrziVlak(checkIfNull(podaci[14]))
        .build();
  }

  public Integer checkIfNull(String podatak) {
    if (podatak == null) {
      return null;
    }
    return Integer.parseInt(podatak);
  }
}
