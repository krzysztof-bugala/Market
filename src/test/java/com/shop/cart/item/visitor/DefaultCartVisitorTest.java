package com.shop.cart.item.visitor;

import com.shop.cart.Cart;
import com.shop.cart.item.CartItem;
import com.shop.receipt.Receipt;
import com.shop.receipt.item.ReceiptItem;
import com.shop.receipt.item.builder.ReceiptItemsBuilder;
import com.shop.receipt.item.builder.factory.ReceiptItemsBuilderFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by kb on 2017-09-17.
 */
@RunWith(SpringRunner.class)
public class DefaultCartVisitorTest {

    @Autowired
    @Qualifier("defaultCartVisitorTest")
    private DefaultCartVisitor cartVisitor;

    @Mock
    private Cart cart;

    @Mock
    private CartItem cartItem;

    @MockBean
    @Qualifier("defaultReceipt")
    private Receipt receipt;

    @MockBean
    @Qualifier("defaultReceiptItemsBuilderFactory")
    private ReceiptItemsBuilderFactory receiptItemsBuilderFactory;

    @TestConfiguration
    static class DefaultCartVisitorContextConfiguration {

        @Bean
        public CartVisitor defaultCartVisitorTest() {
            return new DefaultCartVisitor();
        }
    }

    @Test
    public void whenVisitCart() {
        CartItem cartItem1 = Mockito.mock(CartItem.class);
        CartItem cartItem2 = Mockito.mock(CartItem.class);
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);

        Mockito.when(cart.getCartItems()).thenReturn(cartItems);

        cartVisitor.visit(cart);

        Mockito.verify(cartItem1).accept(cartVisitor);
        Mockito.verify(cartItem2).accept(cartVisitor);
        BigDecimal totalPrice = cartVisitor.getReceiptTotalPrice();
        Mockito.verify(receipt).setTotalPrice(totalPrice);
    }

    @Test
    public void whenVisitCartItem() {
        ReceiptItemsBuilder receiptItemsBuilder = Mockito.mock(ReceiptItemsBuilder.class);
        ReceiptItem receiptItem = Mockito.mock(ReceiptItem.class);
        BigDecimal receiptItemTotalPrice = Mockito.mock(BigDecimal.class);
        BigDecimal receiptTotalPrice = Mockito.mock(BigDecimal.class);

        cartVisitor.setReceiptTotalPrice(receiptTotalPrice);

        Mockito.when(receiptItemsBuilderFactory.getInstance()).thenReturn(receiptItemsBuilder);
        Mockito.when(receiptItem.getTotalPrice()).thenReturn(receiptItemTotalPrice);
        Mockito.when(receiptTotalPrice.add(receiptItemTotalPrice)).thenReturn(receiptTotalPrice);


        List<ReceiptItem> receiptItems = new ArrayList<>();
        receiptItems.add(receiptItem);
        receiptItems.add(receiptItem);
        Mockito.when(receiptItemsBuilder.build()).thenReturn(receiptItems);

        cartVisitor = Mockito.spy(cartVisitor);

        cartVisitor.visit(cartItem);

        Mockito.verify(receiptItem, Mockito.times(2)).getTotalPrice();
        Mockito.verify(receipt, Mockito.times(2)).addReceiptItem(receiptItem);
        Mockito.verify(cartVisitor, Mockito.times(2)).setReceiptTotalPrice(receiptTotalPrice);
        Mockito.verify(receiptTotalPrice, Mockito.times(2)).add(receiptItemTotalPrice);
    }

}