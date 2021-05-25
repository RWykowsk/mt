package dev.com.demo.mt.coinbase.application;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TickerInstrumentsRepositoryImplTest
{

  @Test
  void shallStoreInstrumentsOfSameProductIdInLifoQueue()
  {
    final TickerInstrumentsRepositoryImpl repository = new TickerInstrumentsRepositoryImpl();
    final CoinbaseInstrument instrument1 = createBTCUSDInstrument();
    final CoinbaseInstrument instrument2 = createBTCUSDInstrument();
    final CoinbaseInstrument instrument3 = createBTCUSDInstrument();
    repository.add(instrument1);
    repository.add(instrument2);
    repository.add(instrument3);
    final List<CoinbaseInstrument> lastInstruments = repository.getLastInstruments("BTC-USD", 3);
    assertEquals(3,lastInstruments.size());
    assertSame(instrument3, lastInstruments.get(0));
    assertSame(instrument2, lastInstruments.get(1));
    assertSame(instrument1, lastInstruments.get(2));
  }

  private CoinbaseInstrument createBTCUSDInstrument()
  {
    final CoinbaseInstrument ret = new CoinbaseInstrument();
    ret.setProductId("BTC-USD");
    return ret;
  }
}
