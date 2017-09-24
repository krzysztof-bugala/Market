package com.shop.receipt.item.builder;

import com.shop.cart.item.CartItem;
import com.shop.entity.product.Product;
import com.shop.receipt.item.ReceiptItem;
import com.shop.receipt.item.calculator.ReceiptItemCalculator;
import com.shop.receipt.item.factory.ReceiptItemFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


/**
 * Created by kb on 2017-09-20.
 */
@RunWith(SpringRunner.class)
public class DefaultReceiptItemBuilderTest {

    private class TestableReceiptItemBuilderTest extends  DefaultReceiptItemBuilder {

        @Override
        protected BigDecimal getProductPrice() {
            return null;
        }

    }

    private TestableReceiptItemBuilderTest testableReceiptItemBuilderTest;

    @Mock
    private ReceiptItemFactory receiptItemFactory;

    @Mock
    private ReceiptItemCalculator receiptItemCalculator;

    @Mock
    private CartItem cartItem;

    @Mock
    private ReceiptItem receiptItem;

    @Mock
    private Product product;


    @Before
    public void init() {
        testableReceiptItemBuilderTest = new TestableReceiptItemBuilderTest();
        testableReceiptItemBuilderTest.setReceiptItemCalculator(receiptItemCalculator);
        testableReceiptItemBuilderTest.setCartItem(cartItem);
        testableReceiptItemBuilderTest.setReceiptItemFactory(receiptItemFactory);
    }

    @Test
    public void whenBuild() {
        Mockito.when(receiptItemFactory.getInstance()).thenReturn(receiptItem);
        Integer quantity = 6;
        Mockito.when(cartItem.getQuantity()).thenReturn(quantity);
        Mockito.when(cartItem.getProduct()).thenReturn(product);

        ReceiptItem builtReceiptItem = testableReceiptItemBuilderTest.build();

        Mockito.verify(receiptItem).setCartItem(cartItem);
        Mockito.verify(receiptItem).setQuantity(quantity);
        Mockito.verify(receiptItem).setProduct(product);
        Mockito.verify(receiptItem).setProductPrice(testableReceiptItemBuilderTest.getProductPrice());
        Mockito.verify(receiptItem).setReceiptItemCalculator(receiptItemCalculator);

        Assert.assertEquals(receiptItem, builtReceiptItem);

    }

}