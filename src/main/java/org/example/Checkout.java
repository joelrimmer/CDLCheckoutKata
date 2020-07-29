package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import lombok.Data;

@Data
public class Checkout {

  private final Map<String, OfferRule> offerRuleMap;
  private final PricingRule pricingRule;
  private Map<String, Integer> shoppingBasket = new HashMap<>();

  public Checkout(PricingRule pricingRule, Map<String, OfferRule> offerRuleMap) {
    this.offerRuleMap = offerRuleMap;
    this.pricingRule = pricingRule;
  }

  public void displayOffers() {
    System.out.println("The offers currently running are as follows: ");

    for (Entry<String, OfferRule> offer : offerRuleMap.entrySet()) {
      System.out.println(
          offer.getValue().getQuantity() + " of product " + offer.getKey() + " for £" + offer
              .getValue().getOfferPrice());
    }

  }

  public void runCheckout() {

    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter a product to scan: ");

    while (true) {

      String item = scanner.nextLine().toUpperCase();
      if (item.equals("CHECKOUT")) {
        break;
      }

      //check item exists in pricing first
      if (this.pricingRule.getPriceForProduct(item) == null) {
        // product does not exist, handle it
        System.out
            .println("Invalid entry, product has to be in " + pricingRule.getProductCodeList());
      } else {
        //if item exists in pricingRule but not already in basket then add it
        if (!shoppingBasket.containsKey(item)) {
          shoppingBasket.put(item, 1);
        } else {
          Integer currentQty = shoppingBasket.get(item);
          shoppingBasket.put(item, currentQty + 1);
        }

        printShoppingBasket();
        System.out.println("The current total is: £" + calculateTotal());
      }

      System.out.println("Enter another product to scan or enter 'checkout' to pay: ");

    }

    System.out.println("The final total is £" + calculateTotal());

  }

  private void printShoppingBasket() {

    System.out.println("Your basket current consists of: ");
    Set<Entry<String, Integer>> products = shoppingBasket.entrySet();
    products.forEach(
        product -> System.out
            .println(product.getKey().toUpperCase() + " - quantity: " + product.getValue()));

  }

  public Double calculateTotal() {

    double totalCost = 0.00;

    for (Entry<String, Integer> shoppingItem : this.shoppingBasket.entrySet()) {

      if (offerRuleMap.containsKey(shoppingItem.getKey())) {
        if (shoppingItem.getValue() >= offerRuleMap.get(shoppingItem.getKey()).getQuantity()) {
          totalCost +=
              (shoppingItem.getValue() % offerRuleMap.get(shoppingItem.getKey()).getQuantity())
                  * this.pricingRule.getPriceForProduct(shoppingItem.getKey()) +
                  (shoppingItem.getValue() / offerRuleMap.get(shoppingItem.getKey()).getQuantity())
                      * offerRuleMap.get(shoppingItem.getKey()).getOfferPrice();
        } else {
          totalCost +=
              shoppingItem.getValue() * this.pricingRule.getPriceForProduct(shoppingItem.getKey());
        }

      } else {
        totalCost +=
            shoppingItem.getValue() * this.pricingRule.getPriceForProduct(shoppingItem.getKey());
      }

    }
    return totalCost;
  }


}
