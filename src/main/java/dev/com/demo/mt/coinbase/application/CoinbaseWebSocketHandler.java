package dev.com.demo.mt.coinbase.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.invoke.MethodHandles;

class CoinbaseWebSocketHandler implements WebSocketHandler
{
  private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup()
                                                                         .lookupClass());
  private final CoinbaseFacade coinbaseFacade;
  private final TickerInstrumentsStore tickerInstrumentsStore;

  public CoinbaseWebSocketHandler(final CoinbaseFacade coinbaseFacade,
                                  final TickerInstrumentsStore tickerInstrumentsStore)
  {
    this.coinbaseFacade = coinbaseFacade;
    this.tickerInstrumentsStore = tickerInstrumentsStore;
  }

  @Override
  public void afterConnectionEstablished(final WebSocketSession webSocketSession) throws
                                                                                  IOException
  {
    final ObjectMapper objectMapper = new ObjectMapper();
    final ObjectNode objectNode = objectMapper.createObjectNode();
    objectNode.put("type", "subscribe");
    final ArrayNode channels = objectNode.putArray("channels");
    final ObjectNode tickerNode = objectMapper.createObjectNode();
    tickerNode.put("name", "ticker");
    final ArrayNode instruments = tickerNode.putArray("product_ids");
    tickerInstrumentsStore.getInstruments().forEach(instruments::add);
    channels.add(tickerNode);
    final String subscription = objectNode.toString();
      webSocketSession.sendMessage(new TextMessage(subscription));
  }

  @Override
  public void handleMessage(final WebSocketSession webSocketSession,
                            final WebSocketMessage<?> webSocketMessage) throws
                                                                        JsonProcessingException
  {
      String msg = (String) webSocketMessage.getPayload();
      final ObjectMapper objectMapper = new ObjectMapper();
      final String type = objectMapper.readTree(msg)
                                      .get("type").asText("");
      switch (type){
        case "ticker":
          final CoinbaseInstrument coinbaseInstrument = objectMapper.readValue(msg, CoinbaseInstrument.class);
          coinbaseFacade.add(coinbaseInstrument);
          break;
        default:
          LOG.info("Received : " + msg);
          break;
      }
  }

  @Override
  public void handleTransportError(final WebSocketSession webSocketSession,
                                   final Throwable throwable)
  {
    LOG.error("Exception in coinbase web socket handler" ,throwable);
  }

  @Override
  public void afterConnectionClosed(final WebSocketSession webSocketSession,
                                    final CloseStatus closeStatus)
  {
    LOG.info(closeStatus.getReason());
  }

  @Override
  public boolean supportsPartialMessages()
  {
    return false;
  }
}
