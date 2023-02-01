package com.example.shoppingcart;

import static com.example.shoppingcart.repository.CreditCardType.GOLD;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.example.shoppingcart.application.ShoppingCartCalculator;
import com.example.shoppingcart.repository.Product;
import com.example.shoppingcart.repository.ShoppingCartRepository;

public class ShoppingCartApplication {

  public static final long CART_ID = 1L;

  public static void main(String[] args) {
    ShoppingCartRepository shoppingCartRepository = new ShoppingCartRepository(givenCart());
    ShoppingCartCalculator shoppingCartCalculator = new ShoppingCartCalculator(
        shoppingCartRepository);
    System.out.println(shoppingCartCalculator.getCartTotal(CART_ID, GOLD));

  }

  private static HashMap<Long, Map<Product, Integer>> givenCart() {
    return new HashMap<>(
        Map.of(CART_ID,
            new HashMap<>(
                Map.of(
                    new Product("product 1", new BigDecimal("10.1")), 3,
                    new Product("product 2", new BigDecimal("10.1")), 3,
                    new Product("product 3", new BigDecimal("10.1")), 3
                ))
        ));
  }
}
