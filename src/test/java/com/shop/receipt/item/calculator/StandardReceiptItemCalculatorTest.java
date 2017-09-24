package com.shop.receipt.item.calculator;

import com.shop.entity.product.Product;
import com.shop.entity.product.price.SpecialPrice;
import com.shop.receipt.item.ReceiptItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created by kb on 2017-09-18.
 */
@RunWith(SpringRunner.class)
public class StandardReceiptItemCalculatorTest {
    private ReceiptItemCalculator standardReceiptItemCalculator;

    @Mock
    private ReceiptItem receiptItem;

    @Mock
    private Product product;

    @Mock
    private SpecialPrice specialPrice;

    @Before
    public void init() {
        standardReceiptItemCalculator = new StandardReceiptItemCalculator();
        Mockito.when(receiptItem.getProduct()).thenReturn(product);

        Mockito.when(receiptItem.getProductPrice()).thenReturn(new BigDecimal("24.46"));
        Mockito.when(product.getSpecialPrice()).thenReturn(specialPrice);
        Mockito.when(product.getSpecialPrice().getPriceThreshold()).thenReturn(60);
    }

    @Test
    public void whenProductQuantityEqualZero() {
        Mockito.when(receiptItem.getQuantity()).thenReturn(0);

        CalculationData calculationData = standardReceiptItemCalculator.calculate(receiptItem);

        Assert.assertEquals(0, calculationData.getReceiptItemQuantity());
        Assert.assertEquals(new BigDecimal(("0.00")), calculationData.getTotalReceiptItemPrice());
    }

    @Test
    public void whenProductQuantityBelowPriceThreshold() {
        Mockito.when(receiptItem.getQuantity()).thenReturn(30);

        CalculationData calculationData = standardReceiptItemCalculator.calculate(receiptItem);

        Assert.assertEquals(30, calculationData.getReceiptItemQuantity());
        Assert.assertEquals(new BigDecimal(("733.80")), calculationData.getTotalReceiptItemPrice());
    }

    @Test
    public void whenProductQuantityEqualPriceThreshold() {
        Mockito.when(receiptItem.getQuantity()).thenReturn(60);

        CalculationData calculationData = standardReceiptItemCalculator.calculate(receiptItem);

        Assert.assertEquals(0, calculationData.getReceiptItemQuantity());
        Assert.assertEquals(new BigDecimal(("0.00")), calculationData.getTotalReceiptItemPrice());
    }

    @Test
    public void whenProductQuantityGreaterThanPriceThreshold() {
        Mockito.when(receiptItem.getQuantity()).thenReturn(70);

        CalculationData calculationData = standardReceiptItemCalculator.calculate(receiptItem);

        Assert.assertEquals(10, calculationData.getReceiptItemQuantity());
        Assert.assertEquals(new BigDecimal(("244.60")), calculationData.getTotalReceiptItemPrice());
    }
}