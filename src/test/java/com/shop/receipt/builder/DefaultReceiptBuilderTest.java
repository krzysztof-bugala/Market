package com.shop.receipt.builder;

import com.shop.cart.Cart;
import com.shop.cart.item.visitor.CartVisitor;
import com.shop.cart.item.visitor.factory.CartVisitorFactory;
import com.shop.receipt.DefaultReceipt;
import com.shop.receipt.Receipt;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Matchers.any;

/**
 * Created by kb on 2017-09-17.
 */
@RunWith(SpringRunner.class)
public class DefaultReceiptBuilderTest {

    @MockBean
    @Qualifier("defaultCart")
    private Cart cart;

    @MockBean
    @Qualifier("defaultCartVisitorFactory")
    private CartVisitorFactory cartVisitorFactory;

    @Autowired
    @Qualifier("defaultReceiptBuilderTest")
    ReceiptBuilder receiptBuilder;

    @TestConfiguration
    static class DefaultReceiptBuilderTestContextConfiguration {

        @Bean
        public ReceiptBuilder defaultReceiptBuilderTest() {
            return new DefaultReceiptBuilder();
        }
    }

    @Test
    public void whenBuild_thenBuildReceipt() {
        Receipt receipt = new DefaultReceipt();
        CartVisitor cartVisitor = Mockito.mock(CartVisitor.class);

        Mockito.when(cartVisitorFactory.getInstance()).thenReturn(cartVisitor);
        Mockito.when(cartVisitor.getReceipt()).thenReturn(receipt);

        Receipt builtReceipt = receiptBuilder.build();

        Mockito.verify(cartVisitor).visit(cart);

        Assert.assertEquals(receipt, builtReceipt);
    }

}
