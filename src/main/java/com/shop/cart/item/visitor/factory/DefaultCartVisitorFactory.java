package com.shop.cart.item.visitor.factory;

import com.shop.cart.item.visitor.CartVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by kb on 2017-09-10.
 */
@Component
public class DefaultCartVisitorFactory implements CartVisitorFactory {

    @Autowired
    private ApplicationContext appContext;

    public CartVisitor getInstance() {
        return (CartVisitor) appContext.getBean("defaultCartVisitor");
    }
}
