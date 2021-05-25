package dev.com.demo.mt.coinbase.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HyphenFromStringRemoverTest
{

  @Test
  void shallremoveHyphensFromStringCorrectly()
  {
    //when
    final String actual = HyphenFromStringRemover.removeHyphensFromString("BTC-USD");
    final String actual2 = HyphenFromStringRemover.removeHyphensFromString("BTCUSD");
    final String actual3 = HyphenFromStringRemover.removeHyphensFromString("BTCUSD-");
    //then
    assertEquals("BTCUSD", actual);
    assertEquals("BTCUSD", actual2);
    assertEquals("BTCUSD", actual3);
  }
}
