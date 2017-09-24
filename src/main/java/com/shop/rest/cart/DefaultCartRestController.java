package com.shop.rest.cart;

import com.shop.cart.item.CartItem;
import com.shop.entity.product.StandardProduct;
import com.shop.service.cart.*;
import com.shop.receipt.Receipt;
import com.shop.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by kb on 2017-09-09.
 */
@RestController
@RequestMapping("market/api/v1/cart")
public class DefaultCartRestController implements CartRestController {
    @Autowired
    @Qualifier("defaultCartService")
    private CartService cartService;

    @Autowired
    @Qualifier("defaultProductService")
    private ProductService<StandardProduct> productService;

    @RequestMapping(value="/items", method = RequestMethod.POST)
    public ResponseEntity<?> addCartItem(@RequestBody DefaultSelectedItem selectedItem) {
        cartService.addCartItem (selectedItem);
        return new ResponseEntity<SelectedItem>(selectedItem, HttpStatus.CREATED);
    }

    @RequestMapping(value="/items", method = RequestMethod.GET)
    public Collection<CartItem> getCartItems() {
        return cartService.getCartItems();
    }

    @RequestMapping(value="/items/{barcode}", method = RequestMethod.DELETE)
    public void deleteCartItems(@PathVariable Long barcode) {
        cartService.removeCartItem(barcode);
    }

    @RequestMapping(value="/totalPrice", method = RequestMethod.GET)
    public BigDecimal calculateTotalPrice() {
        return cartService.getTotalPrice();
    }

    @RequestMapping(value="/checkout", method = RequestMethod.POST)
    public Receipt checkout() {
        Receipt receipt = cartService.checkout();
        return receipt;
    }

}
