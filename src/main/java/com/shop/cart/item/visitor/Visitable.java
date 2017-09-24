package com.shop.cart.item.visitor;

/**
 * Created by kb on 2017-09-10.
 */
public interface Visitable {
    public void accept(CartVisitor cartVisitor);
}
