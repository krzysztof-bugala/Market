package com.shop.receipt.item;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shop.cart.item.CartItem;
import com.shop.entity.product.Product;
import com.shop.receipt.item.calculator.ReceiptItemCalculator;

import java.math.BigDecimal;

/**
 * Created by kb on 2017-09-10.
 */
@JsonDeserialize(as=DefaultReceiptItem.class)
public interface ReceiptItem {
    public Product getProduct();

    public int getQuantity();

    public void setQuantity(int quantity);

    public BigDecimal getProductPrice();

    public BigDecimal getTotalPrice();

    public void calculate();

    public CartItem getCartItem();

    public void setCartItem(CartItem cartItem);

    public void setProduct(Product product);

    public void setProductPrice(BigDecimal productPrice);

    public ReceiptItemCalculator getReceiptItemCalculator();

    public void setReceiptItemCalculator(ReceiptItemCalculator receiptItemCalculator);
}
