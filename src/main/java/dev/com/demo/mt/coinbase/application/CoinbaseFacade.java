package dev.com.demo.mt.coinbase.application;

import dev.com.demo.mt.coinbase.api.CoinbaseInstrumentResponse;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class CoinbaseFacade
{

  private final TickerInstrumentsRepository tickerInstrumentsRepository;

  public CoinbaseFacade(final TickerInstrumentsRepository tickerInstrumentsRepository)
  {
    this.tickerInstrumentsRepository = tickerInstrumentsRepository;
  }

  public void add(final CoinbaseInstrument coinbaseInstrument)
  {
    tickerInstrumentsRepository.add(coinbaseInstrument);
  }

  public List<CoinbaseInstrument> getLastInstruments(final String instrumentId,
                                                     final int instrumentsNumber)
  {
    return tickerInstrumentsRepository.getLastInstruments(instrumentId, instrumentsNumber);
  }
}
