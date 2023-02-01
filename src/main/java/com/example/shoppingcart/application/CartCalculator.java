package com.example.shoppingcart.application;

import java.math.BigDecimal;

import com.example.shoppingcart.repository.CreditCardType;

public interface CartCalculator {

  BigDecimal getCartTotal(Long cartId, CreditCardType creditCardType);

}
