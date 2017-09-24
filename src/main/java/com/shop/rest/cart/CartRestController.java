package com.shop.rest.cart;

import com.shop.cart.item.CartItem;
import com.shop.receipt.Receipt;
import com.shop.service.cart.DefaultSelectedItem;
import com.shop.service.cart.SelectedItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by kb on 2017-09-09.
 */
public interface CartRestController {

    public ResponseEntity<?> addCartItem(DefaultSelectedItem selectedItem);

    public Collection<CartItem> getCartItems();

    public void deleteCartItems(Long barcode);

    public BigDecimal calculateTotalPrice();

    public Receipt checkout();
}
