package dev.com.demo.mt.coinbase.api;

import dev.com.demo.mt.coinbase.application.CoinbaseFacade;
import dev.com.demo.mt.coinbase.application.CoinbaseInstrument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/coinbase")
class CoinbaseController
{

  private final CoinbaseFacade coinbaseFacade;

  CoinbaseController(final CoinbaseFacade coinbaseFacade)
  {
    this.coinbaseFacade = coinbaseFacade;
  }

  @GetMapping(value = "ticker/{instrumentId}",produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<CoinbaseInstrumentResponse>> getInstruments(final @PathVariable String instrumentId)
  {
    final List<CoinbaseInstrumentResponse> lastInstruments = coinbaseFacade.getLastInstruments(instrumentId, 10)
                                                                           .stream()
                                                                           .map(
                                                                               CoinbaseInstrument2CoinbaseInstrumentResponseAdapter::toCoinbaseInstrumentResponse)
                                                                           .collect(Collectors.toList());
    return ResponseEntity.ok(lastInstruments);
  }
}
