package com.shop.cart;

import com.shop.cart.item.CartItem;
import com.shop.cart.item.visitor.Visitable;
import com.shop.receipt.Receipt;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by kb on 2017-09-09.
 */
public interface Cart extends Visitable {
    public void addCartItem(CartItem cartItem);

    public void removeCartItem(long barcode);

    public  List<CartItem> getCartItems();

    public BigDecimal getTotalPrice();

    public Receipt checkout();
}
