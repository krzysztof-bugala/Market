package com.shop.receipt.item.builder.factory;

import com.shop.receipt.item.builder.ReceiptItemsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by kb on 2017-09-23.
 */
@Component
public class DefaultReceiptItemsBuilderFactory implements ReceiptItemsBuilderFactory {

    @Autowired
    private ApplicationContext appContext;

    public ReceiptItemsBuilder getInstance() {
        return (ReceiptItemsBuilder)appContext.getBean("defaultReceiptItemsBuilder");
    }
}
