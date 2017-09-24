package com.shop.cart.item;

import com.shop.cart.item.visitor.Visitable;
import com.shop.cart.item.visitor.CartVisitor;
import com.shop.entity.product.Product;

/**
 * Created by kb on 2017-09-09.
 */
public class DefaultCartItem implements CartItem, Visitable {

    protected long productBarcode;

    private Product product;

    protected int quantity;

    public DefaultCartItem(){

    }

    public DefaultCartItem(Product product, int quantity)
    {
        this.productBarcode = product.getBarcode();
        this.product = product;
        this.quantity = quantity;
    }

    public DefaultCartItem(long productBarcode)
    {
        this.productBarcode = productBarcode;
    }

    public DefaultCartItem(int quantity)
    {
        this.quantity = quantity;
    }

    public void join(CartItem cartItem) {
        if (this.productBarcode == cartItem.getProductBarcode()) {
            this.quantity += cartItem.getQuantity();
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public long getProductBarcode() {
        return productBarcode;
    }

    public boolean equals(Object object) {
        return this.productBarcode == productBarcode;
    }

    public int hashCode() {
        return new Long(productBarcode).hashCode();
    }

    public Product getProduct() {
        return product;
    }

    void setProduct(Product product) {
        this.product = product;
    }

    public void accept(CartVisitor cartVisitor) {
        cartVisitor.visit(this);
    }
}
