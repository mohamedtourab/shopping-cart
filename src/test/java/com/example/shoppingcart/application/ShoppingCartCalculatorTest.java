package com.example.shoppingcart.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.example.shoppingcart.repository.CartRepository;
import com.example.shoppingcart.repository.CreditCardType;
import com.example.shoppingcart.repository.Product;
import org.junit.jupiter.api.Test;

class ShoppingCartCalculatorTest {

  private final CartRepository cartRepository = new FakeCartRepository();
  private final ShoppingCartCalculator shoppingCartCalculator = new ShoppingCartCalculator(
      cartRepository);

  @Test
  void shouldGetDiscountedTotalForGoldCreditCard() {
    cartRepository.addProduct(1L, new Product("product1", BigDecimal.TEN), 2);
    cartRepository.addProduct(1L, new Product("product2", BigDecimal.TEN), 2);
    cartRepository.addProduct(1L, new Product("product3", BigDecimal.TEN), 2);
    BigDecimal total = shoppingCartCalculator.getCartTotal(1L, CreditCardType.GOLD);
    assertEquals(new BigDecimal("48.00"), total);
  }

  @Test
  void shouldGetDiscountedTotalForSilverCreditCard() {
    cartRepository.addProduct(1L, new Product("product1", BigDecimal.TEN), 2);
    cartRepository.addProduct(1L, new Product("product2", BigDecimal.TEN), 2);
    cartRepository.addProduct(1L, new Product("product3", BigDecimal.TEN), 2);
    BigDecimal total = shoppingCartCalculator.getCartTotal(1L, CreditCardType.SILVER);
    assertEquals(new BigDecimal("54.00"), total);
  }

  @Test
  void shouldGetDiscountedTotalForNormalCreditCard() {
    cartRepository.addProduct(1L, new Product("product1", BigDecimal.TEN), 2);
    cartRepository.addProduct(1L, new Product("product2", BigDecimal.TEN), 2);
    cartRepository.addProduct(1L, new Product("product3", BigDecimal.TEN), 2);
    BigDecimal total = shoppingCartCalculator.getCartTotal(1L, CreditCardType.NORMAL);
    assertEquals(new BigDecimal("60.00"), total);
  }
}

class FakeCartRepository implements CartRepository {

  private final Map<Long, Map<Product, Integer>> cart = new HashMap<>();

  @Override
  public void addProduct(Long cartId, Product product, Integer amount) {
    if (cart.containsKey(cartId)) {
      cart.get(cartId).put(product, amount);
    } else {
      cart.put(cartId, new HashMap<>(Map.of(product, amount)));
    }
  }

  @Override
  public void removeProduct(Long cartId, Product product) {
    if (cart.containsKey(cartId)) {
      cart.get(cartId).remove(product);
    }
  }

  @Override
  public void reduceProductAmount(Long cartId, Product product) {
    if (cart.containsKey(cartId)) {
      Map<Product, Integer> productAmountMap = cart.get(cartId);
      if (cart.get(cartId).containsKey(product)) {
        Integer amount = productAmountMap.get(product);
        productAmountMap.put(product, --amount);
      }
    }
  }

  @Override
  public Map<Product, Integer> getCartDetails(Long cartId) {
    if (cart.containsKey(cartId)) {
      return cart.get(cartId);
    } else {
      throw new RuntimeException("Cart is not found");
    }
  }
}