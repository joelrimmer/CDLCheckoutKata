package org.example;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {

  public static void main(String[] args) throws Exception {

    try {
      PricingRule pricingRule = new PricingRule(CSVReader.getPricingInfo("prices.csv"));
      pricingRule.displayPricing();
      System.out.println("***");

      Checkout checkout = new Checkout(pricingRule, CSVReader.getOfferInfo("prices.csv"));
      checkout.displayOffers();
      System.out.println("***");

      checkout.runCheckout();
    } catch (IllegalArgumentException ex) {
      System.out.println("Error: " + ex.getMessage());
    } catch (IOException ioex) {
      ioex.printStackTrace();
    }

  }
}
