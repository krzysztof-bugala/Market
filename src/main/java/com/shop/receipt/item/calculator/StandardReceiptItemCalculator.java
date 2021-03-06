package com.shop.receipt.item.calculator;

import com.shop.entity.product.Product;
import com.shop.entity.product.price.Price;
import com.shop.entity.product.price.SpecialPrice;
import com.shop.receipt.item.ReceiptItem;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by kb on 2017-09-18.
 */
@Component
@Qualifier("standardReceiptItemCalculator")
public class StandardReceiptItemCalculator implements ReceiptItemCalculator {

    public CalculationData calculate(ReceiptItem receiptItem) {
        BigDecimal productPrice = receiptItem.getProductPrice();
        int quantity = receiptItem.getQuantity();
        Product product = receiptItem.getProduct();
        SpecialPrice specialPrice = product.getSpecialPrice();

        int priceThreshold = specialPrice.getPriceThreshold();

        int standardProductQuantity = quantity % priceThreshold;

        BigDecimal receiptItemTotalPrice = new BigDecimal(standardProductQuantity).multiply(productPrice);

        return new CalculationData(receiptItemTotalPrice, standardProductQuantity);
    }
}
