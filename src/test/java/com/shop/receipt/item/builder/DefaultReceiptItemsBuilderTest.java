package com.shop.receipt.item.builder;

import com.shop.cart.item.CartItem;
import com.shop.receipt.item.ReceiptItem;
import org.junit.Assert;
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

import java.util.List;

/**
 * Created by kb on 2017-09-20.
 */
@RunWith(SpringRunner.class)
public class DefaultReceiptItemsBuilderTest {

    @Autowired
    @Qualifier("defaultReceiptItemsBuilderTest")
    private DefaultReceiptItemsBuilder defaultReceiptItemsBuilder;

    @Mock
    CartItem cartItem;

    @MockBean
    @Qualifier("standardReceiptItemBuilder")
    private ReceiptItemBuilder standardReceiptItemBuilder;

    @MockBean
    @Qualifier("specialReceiptItemBuilder")
    private ReceiptItemBuilder specialReceiptItemBuilder;

    @Mock
    ReceiptItem standardReceiptItem;

    @Mock
    ReceiptItem specialReceiptItem;

    @Mock
    List<ReceiptItem> receiptItems;

    @TestConfiguration
    static class DefaultReceiptItemsBuilderTestContextConfiguration {

        @Bean
        public ReceiptItemsBuilder defaultReceiptItemsBuilderTest() {
            return new DefaultReceiptItemsBuilder();
        }

    }

    @Test
    public void whenBuild() {
        defaultReceiptItemsBuilder.setReceiptItems(receiptItems);
        defaultReceiptItemsBuilder.setCartItem(cartItem);

        Mockito.when(standardReceiptItemBuilder.build()).thenReturn(standardReceiptItem);
        Mockito.when(specialReceiptItemBuilder.build()).thenReturn(specialReceiptItem);

        List<ReceiptItem> buildReceiptItems = defaultReceiptItemsBuilder.build();

        Mockito.verify(standardReceiptItemBuilder).setCartItem(cartItem);
        Mockito.verify(standardReceiptItem).calculate();
        Mockito.verify(receiptItems).add(standardReceiptItem);

        Mockito.verify(specialReceiptItemBuilder).setCartItem(cartItem);
        Mockito.verify(specialReceiptItem).calculate();
        Mockito.verify(receiptItems).add(specialReceiptItem);


        Assert.assertEquals(receiptItems, buildReceiptItems);
    }
}