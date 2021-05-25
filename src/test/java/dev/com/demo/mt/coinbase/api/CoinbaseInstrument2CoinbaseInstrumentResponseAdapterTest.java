package dev.com.demo.mt.coinbase.api;

import dev.com.demo.mt.coinbase.application.CoinbaseInstrument;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CoinbaseInstrument2CoinbaseInstrumentResponseAdapterTest
{

  @Test
  void shallConvert2CoinbaseInstrumentResponseCorrectly()
  {
    final CoinbaseInstrument coinbaseInstrument = new CoinbaseInstrument();
    coinbaseInstrument.setProductId("ETH-EUR");
    coinbaseInstrument.setBestBid(1234.15);
    coinbaseInstrument.setBestAsk(1235.15);
    coinbaseInstrument.setPrice(1234.14);
    coinbaseInstrument.setTime(OffsetDateTime.parse("2021-05-25T10:15:13.2321Z").toString());
    final CoinbaseInstrumentResponse coinbaseInstrumentResponse = CoinbaseInstrument2CoinbaseInstrumentResponseAdapter.toCoinbaseInstrumentResponse(
        coinbaseInstrument);
    assertEquals("ETHEUR",coinbaseInstrumentResponse.getInstrument());
    assertEquals(1234.15,coinbaseInstrumentResponse.getBid());
    assertEquals(1235.15,coinbaseInstrumentResponse.getAsk());
    assertEquals(1234.14,coinbaseInstrumentResponse.getLast());
    assertEquals("10:15:13", coinbaseInstrumentResponse.getTime());
  }
}
