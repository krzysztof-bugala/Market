package com.shop.cart.item.visitor;

import com.shop.cart.Cart;
import com.shop.cart.item.CartItem;
import com.shop.receipt.Receipt;
import com.shop.receipt.item.ReceiptItem;
import com.shop.receipt.item.builder.ReceiptItemsBuilder;
import com.shop.receipt.item.builder.factory.ReceiptItemsBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kb on 2017-09-10.
 */
@Component
@Scope("prototype")
public class DefaultCartVisitor implements CartVisitor {

    @Autowired
    @Qualifier("defaultReceipt")
    private Receipt receipt;

    private BigDecimal receiptTotalPrice = new BigDecimal(0);

    private List<ReceiptItem> receiptItems = new ArrayList<>();

    @Autowired
    @Qualifier("defaultReceiptItemsBuilderFactory")
    private ReceiptItemsBuilderFactory receiptItemsBuilderFactory;

    public void visit(Cart cart) {
        for (Visitable visitable : cart.getCartItems()) {
            visitable.accept(this);
        }

        receipt.setTotalPrice(receiptTotalPrice);
    }

    void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Receipt getReceipt() {
        return this.receipt;
    }

    public void visit(CartItem cartItem) {
        ReceiptItemsBuilder receiptItemsBuilder = receiptItemsBuilderFactory.getInstance();
        receiptItemsBuilder.setCartItem(cartItem);
        receiptItems = receiptItemsBuilder.build();

        for (ReceiptItem receiptItem: receiptItems) {
            BigDecimal receiptItemTotalPrice = receiptItem.getTotalPrice();
            BigDecimal receiptTotalPrice = getReceiptTotalPrice();
            BigDecimal newReceiptTotalPrice = receiptTotalPrice.add(receiptItemTotalPrice);
            setReceiptTotalPrice(newReceiptTotalPrice);
            receipt.addReceiptItem(receiptItem);
        }
    }

    BigDecimal getReceiptTotalPrice() {
        return receiptTotalPrice;
    }

    void setReceiptTotalPrice(BigDecimal receiptTotalPrice) {
        this.receiptTotalPrice = receiptTotalPrice;
    }
}
