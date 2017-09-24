package com.shop.service.cart;

import com.shop.cart.Cart;
import com.shop.cart.item.CartItem;
import com.shop.entity.product.StandardProduct;
import com.shop.receipt.DefaultReceipt;
import com.shop.receipt.Receipt;
import com.shop.service.product.ProductService;
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
import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by kb on 2017-09-17.
 */
@RunWith(SpringRunner.class)
public class DefaultCartServiceTest {

    @MockBean(name="productService")
    @Qualifier("defaultProductService")
    private ProductService<StandardProduct> productService;

    @MockBean
    @Qualifier("defaultCart")
    private Cart cart;

    @Autowired
    @Qualifier("cartServiceTest")
    private CartService cartService;


    @TestConfiguration
    static class DefaultCartServiceTestContextConfiguration {

        @Bean
        public CartService cartServiceTest() {
            return new DefaultCartService();
        }
    }

    @Test
    public void whenAddCartItem_thenCart_AddCartItem() {
        StandardProduct product = new StandardProduct();
        long barcode = 54554;
        product.setBarcode(barcode);
        int quantity = 100;
        Mockito.when(productService.getProductByBarcode(barcode, StandardProduct.class)).thenReturn(product);

        SelectedItem selectedItem = new DefaultSelectedItem(barcode, quantity);
        cartService.addCartItem(selectedItem);

        ArgumentCaptor<CartItem> argument = ArgumentCaptor.forClass(CartItem.class);

        Mockito.verify(cart).addCartItem(argument.capture());

        Assert.assertEquals(argument.getValue().getProduct(), product);
        Assert.assertEquals(argument.getValue().getQuantity(), quantity);
    }

    @Test
    public void whenGetCartItems_thenCart_getCartItems() {
        Collection<CartItem> cartItems = new ArrayList<>();
        Collection<CartItem> returnedCartItems;

        Mockito.when(cartService.getCartItems()).thenReturn(cartItems);

        returnedCartItems = cartService.getCartItems();

        Assert.assertEquals(cartItems, returnedCartItems);
    }

    @Test
    public void whenRemoveCartItem_thenCart_removeCartItem() {
        long barcode = 3434;
        cartService.removeCartItem(barcode);

        Mockito.verify(cart).removeCartItem(barcode);
    }

    @Test
    public void whenGetTotalPrice_thenCart_getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal("567.45");
        BigDecimal returnedTotalPrice = cartService.getTotalPrice();

        Mockito.when(cart.getTotalPrice()).thenReturn(totalPrice);

        returnedTotalPrice = cartService.getTotalPrice();

        Assert.assertEquals(totalPrice, returnedTotalPrice);

    }


    @Test
    public void whenCheckout_thenCart_Checkout() {
        Receipt receipt = new DefaultReceipt();

        Mockito.when(cart.checkout()).thenReturn(receipt);
        Receipt returnedReceipt = cartService.checkout();

        Assert.assertEquals(receipt, returnedReceipt);

    }
}
