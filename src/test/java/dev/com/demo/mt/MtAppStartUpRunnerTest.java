package dev.com.demo.mt;

import dev.com.demo.mt.coinbase.api.CoinbaseInstrumentResponse;
import dev.com.demo.mt.coinbase.application.CoinbaseInstrument;
import dev.com.demo.mt.coinbase.application.CoinbaseWSClient;
import dev.com.demo.mt.coinbase.application.TickerInstrumentsStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MtAppStartUpRunnerTest
{
  @InjectMocks
  private MtAppStartUpRunner mtAppStartUpRunner;
  @Mock
  private CoinbaseWSClient coinbaseWSClient;
  @Mock
  private TickerInstrumentsStore tickerInstrumentsStore;

  @Test
  void shallStartAppWithInstrumentsPassedFromApplicationArguments()
  {
    //given
    final ApplicationArguments applicationArguments = mock(ApplicationArguments.class);
    when(applicationArguments.getOptionValues(eq(MtAppStartUpRunner.TICKER_INSTRUMENTS))).thenReturn(List.of("BTC-EUR,BTC-USD"));
    //when
    mtAppStartUpRunner.run(applicationArguments);
    //then
    verify(coinbaseWSClient).init();
    ArgumentCaptor<List<String>> instrumentsCaptor = ArgumentCaptor.forClass(List.class);
    verify(tickerInstrumentsStore).setInstruments(instrumentsCaptor.capture());
    final List<String> capturedInstruments = instrumentsCaptor.getValue();
    assertEquals(2, capturedInstruments.size());
    assertThat(capturedInstruments).containsExactlyInAnyOrder("BTC-EUR","BTC-USD");
  }

  @Test
  void shallStartAppWithDefaultInstruments()
  {
    //given
    final ApplicationArguments applicationArguments = mock(ApplicationArguments.class);
    //when
    mtAppStartUpRunner.run(applicationArguments);
    //then
    verify(coinbaseWSClient).init();
    ArgumentCaptor<List<String>> instrumentsCaptor = ArgumentCaptor.forClass(List.class);
    verify(tickerInstrumentsStore).setInstruments(instrumentsCaptor.capture());
    final List<String> capturedInstruments = instrumentsCaptor.getValue();
    assertEquals(4, capturedInstruments.size());
    assertThat(capturedInstruments).containsExactlyInAnyOrder("BTC-USD", "BTC-EUR", "ETH-USD", "ETH-EUR");
  }
}
