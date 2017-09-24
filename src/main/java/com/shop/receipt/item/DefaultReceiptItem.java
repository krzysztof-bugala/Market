package com.shop.receipt.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shop.cart.item.CartItem;
import com.shop.entity.product.Product;
import com.shop.receipt.DefaultReceipt;
import com.shop.receipt.item.calculator.CalculationData;
import com.shop.receipt.item.calculator.ReceiptItemCalculator;
import com.shop.receipt.item.calculator.SpecialReceiptItemCalculator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by kb on 2017-09-10.
 */
@Component
@Scope(value = "prototype")
public class DefaultReceiptItem implements ReceiptItem {

    private Product product;

    protected int quantity;

    private BigDecimal productPrice;

    protected BigDecimal totalPrice;

    @JsonIgnore
    private ReceiptItemCalculator receiptItemCalculator;

    @JsonIgnore
    private CartItem cartItem;

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void calculate() {
        CalculationData calculationData = receiptItemCalculator.calculate(this);
        quantity = calculationData.getReceiptItemQuantity();
        totalPrice = calculationData.getTotalReceiptItemPrice();
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }


    public void setProduct(Product product) {
        this.product = product;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public ReceiptItemCalculator getReceiptItemCalculator() {
        return receiptItemCalculator;
    }

    public void setReceiptItemCalculator(ReceiptItemCalculator receiptItemCalculator) {
        this.receiptItemCalculator = receiptItemCalculator;
    }
}
