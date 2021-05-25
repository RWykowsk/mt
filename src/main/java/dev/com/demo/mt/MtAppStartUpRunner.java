package dev.com.demo.mt;

import dev.com.demo.mt.coinbase.application.CoinbaseWSClient;
import dev.com.demo.mt.coinbase.application.TickerInstrumentsStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MtAppStartUpRunner implements ApplicationRunner
{
  private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup()
                                                                         .lookupClass());
  static final String TICKER_INSTRUMENTS = "ticker-instruments";
  private final CoinbaseWSClient coinbaseWSClient;
  private final TickerInstrumentsStore tickerInstrumentsStore;

  MtAppStartUpRunner(final CoinbaseWSClient coinbaseWSClient,
                     final TickerInstrumentsStore tickerInstrumentsStore)
  {
    this.coinbaseWSClient = coinbaseWSClient;
    this.tickerInstrumentsStore = tickerInstrumentsStore;
  }

  @Override
  public void run(final ApplicationArguments args)
  {
    LOG.info("init ticker instruments");
    final List<String> cmdtickerInstruments = Optional.ofNullable(args.getOptionValues(TICKER_INSTRUMENTS))
                                                      .stream()
                                                      .flatMap(Collection::stream)
                                                      .findFirst()
                                                      .map(s -> s.split(","))
                                                      .stream()
                                                      .flatMap(Arrays::stream)
                                                      .collect(Collectors.toList());
    List<String> tickerInstruments;
    if(!cmdtickerInstruments.isEmpty()){
      tickerInstruments = cmdtickerInstruments;
    }
    else{
      tickerInstruments = List.of("BTC-USD", "BTC-EUR", "ETH-USD", "ETH-EUR");
    }
    LOG.info("ticker instruments {}", tickerInstruments);
    tickerInstrumentsStore.setInstruments(tickerInstruments);
    coinbaseWSClient.init();
  }
}
