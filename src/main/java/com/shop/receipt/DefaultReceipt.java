package com.shop.receipt;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shop.receipt.item.ReceiptItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kb on 2017-09-10.
 */
@Component
@Scope(value = "prototype")
public class DefaultReceipt implements Receipt {

    private List<ReceiptItem> receiptItems = new ArrayList<>();

    private BigDecimal totalPrice;

    public DefaultReceipt() {
    }

    public DefaultReceipt(List<ReceiptItem> receiptItems, BigDecimal totalPrice) {
        this.receiptItems = receiptItems;
        this.totalPrice = totalPrice;
    }


    public List<ReceiptItem> getReceiptItems() {
        return new ArrayList<ReceiptItem>(receiptItems);
    }

    public void setReceiptItems(List<ReceiptItem> receiptItems) {
        this.receiptItems = receiptItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addReceiptItem(ReceiptItem receiptItem) {
        if (receiptItem.getTotalPrice().doubleValue() > 0) {
            receiptItems.add(receiptItem);
        }
    }

}
