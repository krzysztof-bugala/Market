package com.shop.receipt.item.builder;

import com.shop.cart.item.CartItem;
import com.shop.receipt.item.ReceiptItem;

import java.util.List;

/**
 * Created by kb on 2017-09-18.
 */
public interface ReceiptItemsBuilder {

    public List<ReceiptItem> build();

    public CartItem getCartItem();

    public void setCartItem(CartItem cartItem);
}
