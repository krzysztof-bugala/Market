package com.shop.receipt.item.builder;

import com.shop.cart.item.CartItem;
import com.shop.entity.product.Product;
import com.shop.entity.product.price.Price;
import com.shop.entity.product.price.SpecialPrice;
import com.shop.receipt.item.calculator.ReceiptItemCalculator;
import com.shop.receipt.item.factory.ReceiptItemFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by kb on 2017-09-20.
 */
@RunWith(SpringRunner.class)
public class SpecialReceiptItemBuilderTest {

    @Autowired
    @Qualifier("specialReceiptItemBuilderTest")
    private SpecialReceiptItemBuilder specialReceiptItemBuilder;

    @MockBean
    @Qualifier("defaultReceiptItemFactory")
    private ReceiptItemFactory receiptItemFactory;

    @Mock
    private static ReceiptItemCalculator receiptItemCalculator;

    @Mock
    private CartItem cartItem;

    @Mock
    private Product product;

    @Mock
    private SpecialPrice specialPrice;

    @Mock
    private BigDecimal price;


    @TestConfiguration
    static class SpecialReceiptItemsBuilderTestContextConfiguration {

        @Bean
        public ReceiptItemBuilder specialReceiptItemBuilderTest() {
            return new SpecialReceiptItemBuilder(receiptItemCalculator);
        }

    }

    @Before
    public void init() {
        specialReceiptItemBuilder.setCartItem(cartItem);
    }

    @Test
    public void whenGetProductPrice() {
        Mockito.when(cartItem.getProduct()).thenReturn(product);
        Mockito.when(product.getSpecialPrice()).thenReturn(specialPrice);
        Mockito.when(specialPrice.getPrice()).thenReturn(price);

        BigDecimal returnedProductPrice = specialReceiptItemBuilder.getProductPrice();

        Assert.assertEquals(price, returnedProductPrice);
    }

}