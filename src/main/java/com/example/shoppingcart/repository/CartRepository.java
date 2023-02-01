package com.example.shoppingcart.repository;

import java.util.Map;

public interface CartRepository {

  void addProduct(Long cartId, Product product, Integer amount);

  void removeProduct(Long cartId, Product product);

  void reduceProductAmount(Long cartId, Product product);

  Map<Product, Integer> getCartDetails(Long cartId);

}
