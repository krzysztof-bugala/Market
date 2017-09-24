package com.shop.receipt;

import com.shop.receipt.item.ReceiptItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by kb on 2017-09-10.
 */
public interface Receipt {

    public List<ReceiptItem> getReceiptItems();

    public void setReceiptItems(List<ReceiptItem> receiptItems);

    public BigDecimal getTotalPrice();

    public void setTotalPrice(BigDecimal totalPrice);

    public void addReceiptItem(ReceiptItem receiptItem);
}
