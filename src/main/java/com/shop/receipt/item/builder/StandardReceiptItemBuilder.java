package com.shop.receipt.item.builder;

import com.shop.receipt.item.calculator.ReceiptItemCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by kb on 2017-09-19.
 */
@Component
@Scope(value="prototype")
public class StandardReceiptItemBuilder extends DefaultReceiptItemBuilder {

    @Autowired
    public StandardReceiptItemBuilder(@Qualifier("standardReceiptItemCalculator") ReceiptItemCalculator receiptItemCalculator) {
        super(receiptItemCalculator);
    }

    protected BigDecimal getProductPrice() {
        return getCartItem().getProduct().getStandardPrice().getPrice();
    }
}
