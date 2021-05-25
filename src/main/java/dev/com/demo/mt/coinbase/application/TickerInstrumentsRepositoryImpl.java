package dev.com.demo.mt.coinbase.application;

import java.util.*;
import java.util.stream.Collectors;

class TickerInstrumentsRepositoryImpl implements TickerInstrumentsRepository
{
  private final Map<String, LinkedList<CoinbaseInstrument>> deque = new HashMap<>();

  @Override
  public void add(final CoinbaseInstrument coinbaseInstrument)
  {
    deque.computeIfAbsent(coinbaseInstrument.getProductId(), k -> new LinkedList<>())
         .addFirst(coinbaseInstrument);
  }

  @Override
  public List<CoinbaseInstrument> getLastInstruments(final String instrumentId,
                                                     int instrumentsNumber)
  {
    return Optional.ofNullable(deque.get(instrumentId))
                   .stream()
                   .flatMap(Collection::stream)
                   .limit(instrumentsNumber)
                   .collect(Collectors.toList());
  }
}
