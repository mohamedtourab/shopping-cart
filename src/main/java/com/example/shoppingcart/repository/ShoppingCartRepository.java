package com.example.shoppingcart.repository;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartRepository implements CartRepository {

  private final Map<Long, Map<Product, Integer>> cart;

  public ShoppingCartRepository(Map<Long, Map<Product, Integer>> cart) {
    this.cart = cart;
  }

  public void addProduct(Long cartId, Product product, Integer amount) {
    if (cart.containsKey(cartId)) {
      cart.get(cartId).put(product, amount);
    } else {
      cart.put(cartId, new HashMap<>(Map.of(product, amount)));
    }
  }

  public void removeProduct(Long cartId, Product product) {
    if (cart.containsKey(cartId)) {
      cart.get(cartId).remove(product);
    }
  }

  public void reduceProductAmount(Long cartId, Product product) {
    if (cart.containsKey(cartId)) {
      Map<Product, Integer> productAmountMap = cart.get(cartId);
      if (cart.get(cartId).containsKey(product)) {
        Integer amount = productAmountMap.get(product);
        productAmountMap.put(product, --amount);
      }
    }
  }

  public Map<Product, Integer> getCartDetails(Long cartId) {
    if (cart.containsKey(cartId)) {
      return cart.get(cartId);
    } else {
      throw new RuntimeException("Cart is not found");
    }
  }

}
