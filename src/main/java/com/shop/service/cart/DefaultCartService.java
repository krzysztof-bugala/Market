package com.shop.service.cart;

import com.shop.cart.Cart;
import com.shop.cart.item.CartItem;
import com.shop.cart.item.DefaultCartItem;
import com.shop.entity.product.Product;
import com.shop.entity.product.StandardProduct;
import com.shop.receipt.Receipt;
import com.shop.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by kb on 2017-09-09.
 */
@Service
public class DefaultCartService implements CartService {

    @Autowired
    @Qualifier("defaultProductService")
    private ProductService<StandardProduct> productService;

    @Autowired
    @Qualifier("defaultCart")
    private Cart cart;

    @Override
    public void addCartItem(SelectedItem selectedItem) {
        long productBarcode = selectedItem.getProductBarcode();
        Product product = productService.getProductByBarcode(productBarcode, StandardProduct.class);
        cart.addCartItem(new DefaultCartItem(product, selectedItem.getQuantity()));
    }

    @Override
    public Collection<CartItem> getCartItems() {
        return cart.getCartItems();
    }

    @Override
    public void removeCartItem(long barcode) {
        cart.removeCartItem(barcode);
    }

    @Override
    public BigDecimal getTotalPrice() {
        return cart.getTotalPrice();
    }


    @Override
    public Receipt checkout() {
        return cart.checkout();
    }
}
