package com.shop.receipt.item.builder;

import com.shop.cart.item.CartItem;
import com.shop.receipt.item.ReceiptItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kb on 2017-09-18.
 */
@Component
@Scope(value = "prototype")
public class DefaultReceiptItemsBuilder implements ReceiptItemsBuilder {

    private CartItem cartItem;

    private List<ReceiptItem> receiptItems;

    @Autowired
    @Qualifier("standardReceiptItemBuilder")
    private ReceiptItemBuilder standardReceiptItemBuilder;

    @Autowired
    @Qualifier("specialReceiptItemBuilder")
    private ReceiptItemBuilder specialReceiptItemBuilder;

    public DefaultReceiptItemsBuilder() {
        this.setReceiptItems(new ArrayList<>());
    }

    public List<ReceiptItem> build() {
        CartItem cartItem = getCartItem();

        standardReceiptItemBuilder.setCartItem(cartItem);
        ReceiptItem standardReceiptItem = standardReceiptItemBuilder.build();
        standardReceiptItem.calculate();
        getReceiptItems().add(standardReceiptItem);

        specialReceiptItemBuilder.setCartItem(cartItem);
        ReceiptItem specialReceiptItem = specialReceiptItemBuilder.build();
        specialReceiptItem.calculate();
        getReceiptItems().add(specialReceiptItem);

        return getReceiptItems();
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }


    List<ReceiptItem> getReceiptItems() {
        return receiptItems;
    }

    void setReceiptItems(List<ReceiptItem> receiptItems) {
        this.receiptItems = receiptItems;
    }
}
