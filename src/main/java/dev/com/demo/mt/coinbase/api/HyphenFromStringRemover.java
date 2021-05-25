package dev.com.demo.mt.coinbase.api;

import java.util.Arrays;
import java.util.stream.Collectors;

class HyphenFromStringRemover
{
  private HyphenFromStringRemover()
  {
  }

  public static String removeHyphensFromString(final String string)
  {
    return String.join("", string.split("-"));
  }
}
