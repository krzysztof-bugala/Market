package com.shop.receipt.item.calculator;

import com.shop.receipt.item.ReceiptItem;

import javax.swing.plaf.basic.BasicIconFactory;
import java.math.BigDecimal;

/**
 * Created by kb on 2017-09-18.
 */
public interface ReceiptItemCalculator {
    public CalculationData calculate(ReceiptItem receiptItem);
}
