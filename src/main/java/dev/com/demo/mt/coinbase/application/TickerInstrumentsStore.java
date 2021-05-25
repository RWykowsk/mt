package dev.com.demo.mt.coinbase.application;

import java.util.List;

public class TickerInstrumentsStore
{
  private List<String> instruments;

  public List<String> getInstruments()
  {
    return instruments;
  }

  public void setInstruments(final List<String> instruments)
  {
    this.instruments = instruments;
  }
}
