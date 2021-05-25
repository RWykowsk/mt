package dev.com.demo.mt.coinbase.api;

public class CoinbaseInstrumentResponse
{
  private String instrument;
  private Double bid;
  private Double ask;
  private Double last;
  private String time;

  private CoinbaseInstrumentResponse(final Builder builder)
  {
    this.instrument = builder.instrument;
    this.bid = builder.bid;
    this.ask = builder.ask;
    this.last = builder.last;
    this.time = builder.time;
  }

  public String getInstrument()
  {
    return instrument;
  }

  public Double getBid()
  {
    return bid;
  }

  public Double getAsk()
  {
    return ask;
  }

  public Double getLast()
  {
    return last;
  }

  public String getTime()
  {
    return time;
  }

  public static class Builder{

    private String instrument;
    private Double bid;
    private Double ask;
    private Double last;
    private String time;

    public Builder withInstrument(final String instrument)
    {
      this.instrument = instrument;
      return this;
    }

    public Builder withBid(final Double bid)
    {
      this.bid = bid;
      return this;
    }

    public Builder withAsk(final Double ask)
    {
      this.ask = ask;
      return this;
    }

    public Builder withLast(final Double last)
    {
      this.last = last;
      return this;
    }

    public Builder withTime(final String time)
    {
      this.time = time;
      return this;
    }

    public CoinbaseInstrumentResponse build()
    {
      return new CoinbaseInstrumentResponse(this);
    }
  }
}
