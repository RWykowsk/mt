package dev.com.demo.mt.coinbase.api;

import dev.com.demo.mt.coinbase.application.CoinbaseInstrument;

import java.time.*;
import java.time.temporal.ChronoUnit;

class CoinbaseInstrument2CoinbaseInstrumentResponseAdapter
{
  private CoinbaseInstrument2CoinbaseInstrumentResponseAdapter()
  {
  }

  public static CoinbaseInstrumentResponse toCoinbaseInstrumentResponse(final CoinbaseInstrument coinbaseInstrument)
  {
    final String instrument = HyphenFromStringRemover.removeHyphensFromString(coinbaseInstrument.getProductId());
    final String time = OffsetDateTime.parse(coinbaseInstrument.getTime())
                                      .toLocalTime()
                                      .truncatedTo(ChronoUnit.SECONDS)
                                      .toString();
    return new CoinbaseInstrumentResponse.Builder().withInstrument(instrument)
                                                   .withBid(coinbaseInstrument.getBestBid())
                                                   .withAsk(coinbaseInstrument.getBestAsk())
                                                   .withLast(coinbaseInstrument.getPrice())
                                                   .withTime(time)
                                                   .build();
  }
}
