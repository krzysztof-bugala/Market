package com.shop.service.cart;

import com.shop.cart.item.CartItem;
import com.shop.receipt.Receipt;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by kb on 2017-09-09.
 */
public interface CartService {

    public void addCartItem(SelectedItem selectedItem);

    public Collection<CartItem> getCartItems();

    public void removeCartItem(long barcode);

    public BigDecimal getTotalPrice();

    public Receipt checkout();
}
