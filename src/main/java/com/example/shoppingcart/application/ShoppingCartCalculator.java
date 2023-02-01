package com.example.shoppingcart.application;

import java.math.BigDecimal;
import java.util.Map.Entry;

import com.example.shoppingcart.repository.CartRepository;
import com.example.shoppingcart.repository.CreditCardType;
import com.example.shoppingcart.repository.Product;

public class ShoppingCartCalculator implements CartCalculator {

  private final CartRepository repository;

  public ShoppingCartCalculator(CartRepository repository) {
    this.repository = repository;
  }

  @Override
  public BigDecimal getCartTotal(Long cartId, CreditCardType creditCardType) {
    return repository.getCartDetails(cartId)
        .entrySet()
        .stream()
        .map(this::getTotalCostPerProduct)
        .reduce(BigDecimal::add)
        .orElse(new BigDecimal(0))
        .multiply(getDiscountPercentage(creditCardType));
  }

  private BigDecimal getTotalCostPerProduct(Entry<Product, Integer> entry) {
    return getProductPrice(entry).multiply(getProductAmountInCart(entry));
  }

  private BigDecimal getDiscountPercentage(CreditCardType creditCardType) {
    return BigDecimal.ONE.subtract(getDiscountRate(creditCardType).movePointLeft(2));
  }

  private BigDecimal getProductPrice(Entry<Product, Integer> entry) {
    return entry.getKey().price();
  }

  private BigDecimal getProductAmountInCart(Entry<Product, Integer> entry) {
    return BigDecimal.valueOf(entry.getValue());
  }

  private BigDecimal getDiscountRate(CreditCardType creditCardType) {
    return new BigDecimal(creditCardType.getDiscountRate());
  }
}
