package com.shop.receipt.item.builder;

import com.shop.cart.item.CartItem;
import com.shop.receipt.item.ReceiptItem;
import com.shop.receipt.item.calculator.ReceiptItemCalculator;

import java.math.BigDecimal;

/**
 * Created by kb on 2017-09-19.
 */
public interface ReceiptItemBuilder {

    public ReceiptItem build();

    public ReceiptItemCalculator getReceiptItemCalculator();

    public void setReceiptItemCalculator(ReceiptItemCalculator receiptItemCalculator);

    public CartItem getCartItem();

    public void setCartItem(CartItem cartItem);
}
