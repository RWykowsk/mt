package dev.com.demo.mt.coinbase.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketClient;

import java.lang.invoke.MethodHandles;

public class CoinbaseWSClient
{
  private final WebSocketClient webSocketClient;
  private final WebSocketHandler webSocketHandler;

  CoinbaseWSClient(final WebSocketClient webSocketClient,
                   final WebSocketHandler webSocketHandler)
  {
    this.webSocketClient = webSocketClient;
    this.webSocketHandler = webSocketHandler;
  }

  public void init(){
    webSocketClient.doHandshake(webSocketHandler, "wss://ws-feed.pro.coinbase.com");
  }
}
