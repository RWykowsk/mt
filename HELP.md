Match-trade technical task Spring Boot Application

Installation

Requirements:
Java 11+
Apache Maven 3.6.3+

To start application with default instruments subscription for ticker channel invoke:

mvn spring-boot:run

To start application with configured instruments subscription for ticker channel pass comma seperated instruments in spring-boot.run.arguments:

mvn spring-boot:run -Dspring-boot.run.arguments=--ticker-instruments=BTC-EUR,BTC-USD

To view recent ticker instruments call this endpoint GET http://localhost:8080/coinbase/ticker/{instrumentId}

example:

http://localhost:8080/coinbase/ticker/BTC-EUR

