package com.example.shoppingcart.repository;

public enum CreditCardType {
  GOLD(20), SILVER(10), NORMAL(0);

  private final Integer discountRate;

  CreditCardType(int discountRate) {
    this.discountRate = discountRate;
  }

  public Integer getDiscountRate() {
    return discountRate;
  }
}
