package com.shop.cart;

import com.shop.cart.item.CartItem;
import com.shop.cart.item.DefaultCartItem;
import com.shop.cart.item.visitor.CartVisitor;
import com.shop.receipt.Receipt;
import com.shop.receipt.builder.ReceiptBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by kb on 2017-09-09.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class DefaultCart implements Cart {

    @Autowired
    @Qualifier("defaultReceiptBuilder")
    private ReceiptBuilder receiptBuilder;

    private Set<CartItem> cartItems = new LinkedHashSet<>();

    public synchronized void addCartItem(final CartItem cartItem) {

        Optional<CartItem> foundCartItem = cartItems.stream()
                .filter(p -> p.getProductBarcode() == cartItem.getProductBarcode()).findFirst();

        if (foundCartItem.isPresent()) {
            foundCartItem.get().join(cartItem);
        } else {
            cartItems.add(cartItem);
        }
    }

    public synchronized void removeCartItem(long barcode) {
        CartItem cartItem = new DefaultCartItem(barcode);
        cartItems.remove(cartItem);
    }

    public synchronized List<CartItem> getCartItems() {
        return new CopyOnWriteArrayList<CartItem>(cartItems);
    }

    synchronized void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return receiptBuilder.build().getTotalPrice();
    }

    public Receipt checkout() {
        Receipt receipt = receiptBuilder.build();
        cartItems.clear();
        return receipt;
    }

    public void accept(CartVisitor cartVisitor) {
        cartVisitor.visit(this);
    }

}
