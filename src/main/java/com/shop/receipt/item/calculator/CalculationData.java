package com.shop.receipt.item.calculator;

import java.math.BigDecimal;

/**
 * Created by kb on 2017-09-18.
 */
public class CalculationData {

    private BigDecimal totalReceiptItemPrice;

    private int receiptItemQuantity;

    public CalculationData(BigDecimal totalReceiptItemPrice, int receiptItemQuantity) {
        this.totalReceiptItemPrice = totalReceiptItemPrice;
        this.receiptItemQuantity = receiptItemQuantity;
    }

    public BigDecimal getTotalReceiptItemPrice() {
        return this.totalReceiptItemPrice;
    }

    public int getReceiptItemQuantity() {
        return this.receiptItemQuantity;
    }
}
