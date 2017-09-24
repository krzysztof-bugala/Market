package com.shop.rest.cart;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.Market;
import com.shop.cart.item.CartItem;
import com.shop.cart.item.DefaultCartItem;
import com.shop.entity.product.Product;
import com.shop.entity.product.StandardProduct;
import com.shop.exception.handler.CustomRestExceptionHandler;
import com.shop.receipt.DefaultReceipt;
import com.shop.receipt.Receipt;
import com.shop.receipt.item.DefaultReceiptItem;
import com.shop.service.cart.CartService;
import com.shop.service.cart.DefaultSelectedItem;
import com.shop.service.cart.SelectedItem;
import com.shop.service.product.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by kb on 2017-09-21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Market.class)
@WebAppConfiguration
public class DefaultCartRestControllerTest {

    private MockMvc mockMvc;

    @MockBean(name="productService")
    private ProductService<StandardProduct> productService;

    @MockBean(name="cartService")
    private CartService cartService;

    @InjectMocks
    private DefaultCartRestController defaultCartRestController;

    @Autowired
    private CustomRestExceptionHandler customRestExceptionHandler;


    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(defaultCartRestController)
                .setControllerAdvice(customRestExceptionHandler)
                .build();
    }

    @Test
    public void postItems_thenAddItemToCart() throws Exception {
        Integer productBarcode = 5656;
        Integer productQuantity = 5;
        SelectedItem selectedItem = new DefaultSelectedItem(productBarcode, productQuantity);

        ObjectMapper mapper = new ObjectMapper();
        String jsonSelectedItem = mapper.writeValueAsString(selectedItem);

        mockMvc.perform(post("/market/api/v1/cart/items")
                .contentType(contentType)
                .content(jsonSelectedItem))
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonSelectedItem));


        ArgumentCaptor<SelectedItem> argument = ArgumentCaptor.forClass(SelectedItem.class);
        Mockito.verify(cartService).addCartItem(argument.capture());
        assertEquals(selectedItem, argument.getValue());
    }

    @Test
    public void getItems_thenReturnCartItems() throws Exception {
        Integer productBarcode = 54555;
        Integer productQuantity = 6;
        Product product = new StandardProduct();
        product.setBarcode(productBarcode);
        DefaultCartItem cartItem = new DefaultCartItem(product, productQuantity);
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        Mockito.when(cartService.getCartItems()).thenReturn(cartItems);

        ObjectMapper mapper = new ObjectMapper();
        String jsonCartItems = mapper.writeValueAsString(cartItems);

        mockMvc.perform(get("/market/api/v1/cart/items")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonCartItems));
    }

    @Test
    public void deleteItems_thenRemoveItemFromCart() throws Exception {
        Integer productBarcode = 34343;

        ObjectMapper mapper = new ObjectMapper();
        String jsonProductBarcode = mapper.writeValueAsString(productBarcode);

        mockMvc.perform(delete("/market/api/v1/cart/items/34343")
                .contentType(contentType)
                .content(jsonProductBarcode))
                .andExpect(status().isOk());

        Mockito.verify(cartService).removeCartItem(productBarcode);
    }

    @Test
    public void getTotalPrice_thenReturnTotalPrice() throws Exception {
        BigDecimal totalPrice = new BigDecimal("456.67");

        Mockito.when(cartService.getTotalPrice()).thenReturn(totalPrice);

        mockMvc.perform(get("/market/api/v1/cart/totalPrice")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(totalPrice.doubleValue())));

    }

    @Test
    public void postCheckout_thenCartCheckout() throws Exception {
        Receipt receipt = new DefaultReceipt();
        DefaultReceiptItem receiptItem = new DefaultReceiptItem();
        BigDecimal receiptItemTotalPrice = new BigDecimal("45.34");
        receiptItem.setTotalPrice(receiptItemTotalPrice);
        receipt.addReceiptItem(receiptItem);
        BigDecimal totalPrice = new BigDecimal("456.33");

        receipt.setTotalPrice(totalPrice);

        ObjectMapper mapper = new ObjectMapper();
        String jsonReceipt = mapper.writeValueAsString(receipt);

        Mockito.when(cartService.checkout()).thenReturn(receipt);

        mockMvc.perform(post("/market/api/v1/cart/checkout")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonReceipt));
    }
}