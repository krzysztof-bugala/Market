package com.shop.cart.item.visitor.factory;

import com.shop.cart.item.visitor.CartVisitor;

/**
 * Created by kb on 2017-09-10.
 */
public interface CartVisitorFactory {
    public CartVisitor getInstance();
}
