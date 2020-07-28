package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import lombok.Data;

public class PricingRule {

  private final Map<String, Double> priceListMap;

  public PricingRule(Map<String, Double> prices) {
    this.priceListMap = prices;
  }

  public void displayPricing() {
    System.out.println("The pricing for this transaction is as follows: ");
    for (Map.Entry<String, Double> entry: this.priceListMap.entrySet()) {
      System.out.println("Price for product " + entry.getKey() + " is £" + entry.getValue());
    }
  }

  public Double getPriceForProduct(String productCode) {
    return priceListMap.get(productCode);
  }

  public String getProductCodeList() {
    StringJoiner joiner = new StringJoiner(",");
    this.priceListMap.keySet().stream().forEach(prodCode -> joiner.add(prodCode));
    return joiner.toString();
  }

}
