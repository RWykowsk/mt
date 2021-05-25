package dev.com.demo.mt.coinbase.application;

import java.util.List;

interface TickerInstrumentsRepository
{
  void add(CoinbaseInstrument coinbaseInstrument);

  List<CoinbaseInstrument> getLastInstruments(String instrumentId,
                                              int instrumentsNumber);
}
