package com.shop.cart.item;

import com.shop.cart.item.visitor.Visitable;
import com.shop.cart.item.visitor.CartVisitor;
import com.shop.entity.product.Product;

/**
 * Created by kb on 2017-09-09.
 */
public interface CartItem extends Visitable {

    public Product getProduct();

    public long getProductBarcode();

    public int getQuantity();

    public void join(CartItem cartItem);

    public void accept(CartVisitor cartVisitor);
}
