package dev.com.demo.mt.coinbase.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CoinbaseWebSocketHandlerTest
{

  @InjectMocks
  private CoinbaseWebSocketHandler coinbaseWebSocketHandler;
  @Mock
  private CoinbaseFacade coinbaseFacade;
  @Mock
  private TickerInstrumentsStore tickerInstrumentsStore;
  @Test
  void shallSendProperMessageAfterConnectionEstablished() throws
                                                          IOException
  {
    //given
    final WebSocketSession webSocketSession = mock(WebSocketSession.class);
    when(tickerInstrumentsStore.getInstruments()).thenReturn(List.of("BTC-EUR","BTC-USD"));

    //when
    coinbaseWebSocketHandler.afterConnectionEstablished(webSocketSession);
    //then
    ArgumentCaptor<WebSocketMessage<?>> webSocketMessageArgumentCaptor = ArgumentCaptor.forClass(WebSocketMessage.class);
    verify(webSocketSession).sendMessage(webSocketMessageArgumentCaptor.capture());
    final WebSocketMessage<?> captureMessage = webSocketMessageArgumentCaptor.getValue();
    assert captureMessage instanceof TextMessage;
    assertEquals("{\"type\":\"subscribe\",\"channels\":[{\"name\":\"ticker\",\"product_ids\":[\"BTC-EUR\",\"BTC-USD\"]}]}", ((TextMessage) captureMessage).getPayload());
  }

  @Test
  void shallhandleTickerTypeMessageCorrectly() throws
                       IOException
  {
    //given
    final WebSocketSession webSocketSession = mock(WebSocketSession.class);
    String wsMessagePayload = "{\"type\":\"ticker\",\"sequence\":11513619090,\"price\":31039.33,\"side\":\"sell\",\"time\":\"2021-05-25T09:46:04.103024Z\",\"product_id\":\"BTC-EUR\",\"open_24h\":29721.45,\"volume_24h\":5023.57848742,\"low_24h\":29458.91,\"high_24h\":32750.0,\"volume_30d\":95972.9729726,\"best_bid\":31039.33,\"best_ask\":31046.95,\"trade_id\":44317645,\"last_size\":6.4434E-4}";

    //when
    coinbaseWebSocketHandler.handleMessage(webSocketSession, new TextMessage(wsMessagePayload));
    //then
    ArgumentCaptor<CoinbaseInstrument> coinbaseInstrumentArgumentCaptor = ArgumentCaptor.forClass(CoinbaseInstrument.class);
    verify(coinbaseFacade).add(coinbaseInstrumentArgumentCaptor.capture());

    final CoinbaseInstrument value = coinbaseInstrumentArgumentCaptor.getValue();
    assertEquals("ticker",value.getType());
    assertEquals(11513619090L,value.getSequence());
    assertEquals(31039.33,value.getPrice());
    assertEquals("sell",value.getSide());
    assertEquals("2021-05-25T09:46:04.103024Z",value.getTime());
    assertEquals("BTC-EUR",value.getProductId());
    assertEquals(29721.45,value.getOpen24h());
    assertEquals(5023.57848742,value.getVolume24h());
    assertEquals(29458.91,value.getLow24h());
    assertEquals(32750.0,value.getHigh24h());
    assertEquals(95972.9729726,value.getVolume30d());
    assertEquals(31039.33,value.getBestBid());
    assertEquals(31046.95,value.getBestAsk());
    assertEquals(44317645L,value.getTradeId());
    assertEquals(6.4434E-4,value.getLastSize());
  }
}
