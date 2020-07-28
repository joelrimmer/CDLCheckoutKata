package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class CSVReaderTest {

  @Test
  public void populatePricingInfo() throws IOException {

    Map<String, Double> pricingMapTest = new HashMap<>();
    pricingMapTest.put("A", 0.50);
    pricingMapTest.put("B", 0.30);
    pricingMapTest.put("C", 0.20);
    pricingMapTest.put("D", 0.15);

    Map<String, Double> pricingMap = CSVReader.getPricingInfo("prices.csv");

    assertEquals(pricingMapTest, pricingMap);
    assertFalse(pricingMap.isEmpty());

  }

  @Test
  public void populateOfferInfo() throws IOException {

    Map<String, OfferRule> offerRuleMapTest = new HashMap<>();
    offerRuleMapTest.put("A", new OfferRule(3, 1.30));
    offerRuleMapTest.put("B", new OfferRule(2, 0.45));

    Map<String, OfferRule> offerRuleMap = CSVReader.getOfferInfo("prices.csv");

    assertEquals(offerRuleMapTest, offerRuleMap);
    assertTrue(offerRuleMap.containsKey("A"));
    assertTrue(offerRuleMap.containsKey("B"));
    assertFalse(offerRuleMap.containsKey("C"));
    assertFalse(offerRuleMap.containsKey("D"));

  }

  @Test
  public void throwExceptionWhenCSVHasEmptyLines() throws IllegalArgumentException {

    assertThrows(IllegalArgumentException.class, () -> {
      CSVReader.getPricingInfo("pricesInvalid.csv");
    });

  }


}