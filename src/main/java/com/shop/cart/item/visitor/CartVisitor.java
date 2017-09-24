package com.shop.cart.item.visitor;

import com.shop.cart.Cart;
import com.shop.cart.item.CartItem;
import com.shop.receipt.Receipt;

/**
 * Created by kb on 2017-09-10.
 */
public interface CartVisitor {
    public void visit(Cart cart);

    public void visit(CartItem cartItem);

    public Receipt getReceipt();
}
