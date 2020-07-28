package org.example;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class CheckoutTest {

  private PricingRule pricingRule;
  private Map<String, Integer> shoppingBasket = new HashMap<>();
  private Map<String, OfferRule> offerRuleMap = new HashMap<>();

  @Before
  public void populateCheckoutItems() {
    Map<String, Double> pricingRuleMap = new HashMap<>();
    pricingRuleMap.put("A", 0.50);
    pricingRuleMap.put("B", 0.30);
    pricingRuleMap.put("C", 0.20);
    pricingRuleMap.put("D", 0.15);

    pricingRule = new PricingRule(pricingRuleMap);
    offerRuleMap.put("A", new OfferRule(3, 1.30));
    offerRuleMap.put("B", new OfferRule(2, 0.45));
  }

  @Test
  public void calculateOneA() {
    shoppingBasket.put("A", 1);
    Checkout checkout = new Checkout(pricingRule, offerRuleMap);
    Double total = checkout.calculateTotal();
    assertEquals(Double.valueOf(0.5), total);
  }


  @Test
  public void calculateThreeAs() {
    shoppingBasket.put("A", 3);
    Checkout checkout = new Checkout(pricingRule, offerRuleMap);
    Double total = checkout.calculateTotal();
    assertEquals(Double.valueOf(1.30), total);
  }

  @Test
  public void calculateOneB() {
    shoppingBasket.put("B", 1);
    Checkout checkout = new Checkout(pricingRule, offerRuleMap);
    Double total = checkout.calculateTotal();
    assertEquals(Double.valueOf(0.30), total);
  }

  @Test
  public void calculateTwoBs() {
    shoppingBasket.put("B", 2);
    Checkout checkout = new Checkout(pricingRule, offerRuleMap);
    Double total = checkout.calculateTotal();
    assertEquals(Double.valueOf(0.45), total);
  }

  @Test
  public void calculateOneC() {
    shoppingBasket.put("C", 1);
    Checkout checkout = new Checkout(pricingRule, offerRuleMap);
    Double total = checkout.calculateTotal();
    assertEquals(Double.valueOf(0.20), total);
  }

  @Test
  public void calculateComplexBasket() {
    shoppingBasket.put("A", 7);
    shoppingBasket.put("B", 4);
    shoppingBasket.put("C", 1);
    shoppingBasket.put("D", 2);
    Checkout checkout = new Checkout(pricingRule, offerRuleMap);
    Double total = checkout.calculateTotal();
    assertEquals(Double.valueOf(4.50), total);
  }


}