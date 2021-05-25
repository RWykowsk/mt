package dev.com.demo.mt.coinbase.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class CoinbaseConfiguration
{
  @Bean
  CoinbaseFacade coinbaseFacade(final TickerInstrumentsRepository tickerInstrumentsRepository){
    return new CoinbaseFacade(tickerInstrumentsRepository);
  }

  @Bean
  WebSocketClient webSocketClient(){
    return new StandardWebSocketClient();
  }

  @Bean
  TickerInstrumentsRepository tickerInstrumentsRepository(){
    return new TickerInstrumentsRepositoryImpl();
  }

  @Bean
  WebSocketHandler webSocketHandler(final CoinbaseFacade coinbaseFacade,
                                    final TickerInstrumentsStore tickerInstrumentsStore){
    return new CoinbaseWebSocketHandler(coinbaseFacade, tickerInstrumentsStore);
  }

  @Bean
  TickerInstrumentsStore tickerInstrumentsStore(){
    return new TickerInstrumentsStore();
  }

  @Bean
  CoinbaseWSClient coinbaseWSClient(final WebSocketClient webSocketClient,
                                    final WebSocketHandler webSocketHandler){
    return new CoinbaseWSClient(webSocketClient, webSocketHandler);
  }
}
