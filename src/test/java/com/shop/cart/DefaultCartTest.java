package com.shop.cart;

import com.shop.cart.item.CartItem;
import com.shop.cart.item.DefaultCartItem;
import com.shop.cart.item.visitor.CartVisitor;
import com.shop.entity.product.Product;
import com.shop.entity.product.StandardProduct;
import com.shop.receipt.DefaultReceipt;
import com.shop.receipt.Receipt;
import com.shop.receipt.builder.ReceiptBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kb on 2017-09-17.
 */
@RunWith(SpringRunner.class)
public class DefaultCartTest {

    @MockBean
    @Qualifier("defaultReceiptBuilder")
    private ReceiptBuilder receiptBuilder;

    @Autowired
    @Qualifier("defaultCartTest")
    private DefaultCart cart;

    @TestConfiguration
    static class DefaultCartTestContextConfiguration {

        @Bean
        public Cart defaultCartTest() {
            return new DefaultCart();
        }
    }

    @Test
    public void whenAddCartItemAndCartItemNotExists_theAddCartItemToCart() {
        long barcode = 4344L;
        Product product = new StandardProduct();
        CartItem cartItem = new DefaultCartItem(product, 30);
        CartItem spiedCartItem = Mockito.spy(cartItem);
        Set<CartItem> cartItems = new HashSet<>();
        cartItems = Mockito.spy(cartItems);
        cart.setCartItems(cartItems);

        cart.addCartItem(cartItem);

        Mockito.verify(cartItems).add(cartItem);
    }

    @Test
    public void whenAddCartItemAndCartItemExists_theAddCartItemJoin() {
        long barcode = 4344L;
        Product product = new StandardProduct();
        CartItem cartItem = new DefaultCartItem(product, 30);
        CartItem spiedCartItem = Mockito.spy(cartItem);
        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(spiedCartItem);
        cart.setCartItems(cartItems);

        cart.addCartItem(cartItem);

        Mockito.verify(spiedCartItem).join(cartItem);
    }

    @Test
    public void whenRemoveCartItem_ThenCartItems_Remove() {
        long barcode = 6565;

        ArgumentCaptor<CartItem> argument = ArgumentCaptor.forClass(CartItem.class);
        Set<CartItem> cartItems = Mockito.mock(Set.class);
        cart.setCartItems(cartItems);

        cart.removeCartItem(barcode);

        Mockito.verify(cartItems).remove(argument.capture());

        Assert.assertEquals(argument.getValue().getProductBarcode(), barcode);

    }

    @Test
    public void whenGetTotalPrice_thenReturnedTotalPrice() {
        BigDecimal totalPrice = new BigDecimal("454");
        Receipt receipt = new DefaultReceipt();
        receipt.setTotalPrice(totalPrice);

        Mockito.when(receiptBuilder.build()).thenReturn(receipt);

        BigDecimal returnedTotalPrice = cart.getTotalPrice();

        Assert.assertEquals(totalPrice, returnedTotalPrice);


    }

    @Test
    public void whenCheckout_thenReturnReceipt() {
        Receipt receipt = new DefaultReceipt();
        Set<CartItem> cartItems = Mockito.mock(Set.class);
        cart.setCartItems(cartItems);

        Mockito.when(receiptBuilder.build()).thenReturn(receipt);

        Receipt returnedReceipt = cart.checkout();

        Mockito.verify(cartItems).clear();

        Assert.assertEquals(receipt, returnedReceipt);

    }

    @Test
    public void whenAccept_thenCarVisitorVisit() {
        CartVisitor cartVisitor = Mockito.mock(CartVisitor.class);

        cart.accept(cartVisitor);

        Mockito.verify(cartVisitor).visit(cart);
    }

}
