package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {

  public static Map<String, Double> getPricingInfo(String filename) throws IOException {

    Map<String, Double> pricing = new HashMap<>();
    List<String> lines = Files.readAllLines(Paths.get(filename));
    for (String line : lines.subList(1, lines.size())) {
      //assume no empty lines
      if (line.trim().isEmpty()) {
        throw new IllegalArgumentException(
            "Price data file is invalid. There must not be empty lines");
      }
      pricing.put(convertLineToProduct(line), convertLineToPrice(line));
    }
    return pricing;
  }

  public static Map<String, OfferRule> getOfferInfo(String filename) throws IOException {

    Map<String, OfferRule> offers = new HashMap<>();
    List<String> lines = Files.readAllLines(Paths.get(filename));
    for (String line: lines.subList(1, lines.size())) {
      // assume no empty lines
      if (line.trim().isEmpty()) {
        throw new IllegalArgumentException("Price data file is invalid. There must not be empty lines");
      }
      OfferRule offerRule = convertLineToOffer(line);
      if (null != offerRule) {
        offers.put(convertLineToProduct(line), offerRule);
      }
    }
    return offers;
  }

  private static String convertLineToProduct(String line) {

    String[] parts = line.split(",");

    return parts[0];
  }

  private static Double convertLineToPrice(String line) {

    String[] parts = line.split(",");
    return Double.valueOf(parts[1]);
  }

  private static OfferRule convertLineToOffer(String line) {

    String[] parts = line.split(",");
    if (parts.length == 3) {
      String[] offerDetails = parts[2].split("for");

      int quantityOnOffer = Integer.valueOf(offerDetails[0].trim());
      double priceOfOffer = Double.valueOf(offerDetails[1].trim());
      return new OfferRule(quantityOnOffer, priceOfOffer);
    }
    return null;
  }



}
