package dev.com.demo.mt.coinbase.application;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinbaseInstrument
{
  private String type;
  private Long sequence;
  @JsonProperty("product_id")
  private String productId;
  private Double price;
  @JsonProperty("open_24h")
  private Double open24h;
  @JsonProperty("volume_24h")
  private Double volume24h;
  @JsonProperty("low_24h")
  private Double low24h;
  @JsonProperty("high_24h")
  private Double high24h;
  @JsonProperty("volume_30d")
  private Double volume30d;
  @JsonProperty("best_bid")
  private Double bestBid;
  @JsonProperty("best_ask")
  private Double bestAsk;
  private String side;
  private String time;
  @JsonProperty("trade_id")
  private Long tradeId;
  @JsonProperty("last_size")
  private Double lastSize;

  public String getType()
  {
    return type;
  }

  public void setType(final String type)
  {
    this.type = type;
  }

  public Long getSequence()
  {
    return sequence;
  }

  public void setSequence(final Long sequence)
  {
    this.sequence = sequence;
  }

  public String getProductId()
  {
    return productId;
  }

  public void setProductId(final String productId)
  {
    this.productId = productId;
  }

  public Double getPrice()
  {
    return price;
  }

  public void setPrice(final Double price)
  {
    this.price = price;
  }

  public Double getOpen24h()
  {
    return open24h;
  }

  public void setOpen24h(final Double open24h)
  {
    this.open24h = open24h;
  }

  public Double getVolume24h()
  {
    return volume24h;
  }

  public void setVolume24h(final Double volume24h)
  {
    this.volume24h = volume24h;
  }

  public Double getLow24h()
  {
    return low24h;
  }

  public void setLow24h(final Double low24h)
  {
    this.low24h = low24h;
  }

  public Double getHigh24h()
  {
    return high24h;
  }

  public void setHigh24h(final Double high24h)
  {
    this.high24h = high24h;
  }

  public Double getVolume30d()
  {
    return volume30d;
  }

  public void setVolume30d(final Double volume30d)
  {
    this.volume30d = volume30d;
  }

  public Double getBestBid()
  {
    return bestBid;
  }

  public void setBestBid(final Double bestBid)
  {
    this.bestBid = bestBid;
  }

  public Double getBestAsk()
  {
    return bestAsk;
  }

  public void setBestAsk(final Double bestAsk)
  {
    this.bestAsk = bestAsk;
  }

  public String getSide()
  {
    return side;
  }

  public void setSide(final String side)
  {
    this.side = side;
  }

  public String getTime()
  {
    return time;
  }

  public void setTime(final String time)
  {
    this.time = time;
  }

  public Long getTradeId()
  {
    return tradeId;
  }

  public void setTradeId(final Long tradeId)
  {
    this.tradeId = tradeId;
  }

  public Double getLastSize()
  {
    return lastSize;
  }

  public void setLastSize(final Double lastSize)
  {
    this.lastSize = lastSize;
  }
}
