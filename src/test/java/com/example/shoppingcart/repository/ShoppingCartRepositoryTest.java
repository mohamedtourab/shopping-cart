package com.example.shoppingcart.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ShoppingCartRepositoryTest {

  public static final long CART_ID = 1L;
  private ShoppingCartRepository shoppingCartRepository;

  @Test
  void shouldSaveProductToCart() {
    shoppingCartRepository = new ShoppingCartRepository(new HashMap<>());
    shoppingCartRepository.addProduct(CART_ID, new Product("product 1", new BigDecimal("10.1")), 3);
    assertFalse(shoppingCartRepository.getCartDetails(CART_ID).keySet().isEmpty());
  }

  @Test
  void shouldGetCartDetails() {
    shoppingCartRepository = new ShoppingCartRepository(
        givenCart()
    );
    assertFalse(shoppingCartRepository.getCartDetails(CART_ID).keySet().isEmpty());
  }

  @Test
  void shouldRemoveProductFromCart() {
    shoppingCartRepository = new ShoppingCartRepository(givenCart());
    var product = new Product("product 1", new BigDecimal("10.1"));
    assertNotNull(shoppingCartRepository.getCartDetails(CART_ID).get(product));
    shoppingCartRepository.removeProduct(CART_ID, product);
    assertNull(shoppingCartRepository.getCartDetails(CART_ID).get(product));
  }

  private HashMap<Long, Map<Product, Integer>> givenCart() {
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