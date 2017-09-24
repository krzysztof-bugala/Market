package com.shop.receipt.item.builder;

import com.shop.cart.item.CartItem;
import com.shop.receipt.item.ReceiptItem;
import com.shop.receipt.item.calculator.ReceiptItemCalculator;
import com.shop.receipt.item.factory.ReceiptItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;

/**
 * Created by kb on 2017-09-19.
 */
public abstract class DefaultReceiptItemBuilder implements ReceiptItemBuilder {

    @Autowired
    @Qualifier("defaultReceiptItemFactory")
    ReceiptItemFactory receiptItemFactory;

    private ReceiptItemCalculator receiptItemCalculator;

    private CartItem cartItem;

    public DefaultReceiptItemBuilder() {

    }

    public DefaultReceiptItemBuilder(ReceiptItemCalculator receiptItemCalculator) {
        this.receiptItemCalculator = receiptItemCalculator;
    }

    protected abstract BigDecimal getProductPrice();

    public ReceiptItem build() {
        ReceiptItem standardReceiptItem = getReceiptItemFactory().getInstance();
        standardReceiptItem.setCartItem(cartItem);
        standardReceiptItem.setQuantity(cartItem.getQuantity());
        standardReceiptItem.setProduct(cartItem.getProduct());
        standardReceiptItem.setProductPrice(getProductPrice());
        standardReceiptItem.setReceiptItemCalculator(receiptItemCalculator);
        return standardReceiptItem;
    }


    public ReceiptItemCalculator getReceiptItemCalculator() {
        return receiptItemCalculator;
    }

    public void setReceiptItemCalculator(ReceiptItemCalculator receiptItemCalculator) {
        this.receiptItemCalculator = receiptItemCalculator;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }


    ReceiptItemFactory getReceiptItemFactory() {
        return receiptItemFactory;
    }

    void setReceiptItemFactory(ReceiptItemFactory receiptItemFactory) {
        this.receiptItemFactory = receiptItemFactory;
    }
}
