package com.shop.receipt.builder;

import com.shop.cart.item.visitor.factory.CartVisitorFactory;
import com.shop.cart.item.visitor.CartVisitor;
import com.shop.cart.Cart;
import com.shop.receipt.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by kb on 2017-09-10.
 */
@Component
@Scope("prototype")
public class DefaultReceiptBuilder implements ReceiptBuilder {

    @Autowired
    @Qualifier("defaultCart")
    private Cart cart;

    @Autowired
    @Qualifier("defaultCartVisitorFactory")
    private CartVisitorFactory cartVisitorFactory;

    public DefaultReceiptBuilder() {

    }

    public DefaultReceiptBuilder(Cart cart) {
        this.cart =  cart;
    }

    public Receipt build() {
        CartVisitor cartVisitor = cartVisitorFactory.getInstance();
        cartVisitor.visit(cart);
        return cartVisitor.getReceipt();
    }

}
