package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OfferRule {

  private int quantity;
  private double offerPrice;

}
