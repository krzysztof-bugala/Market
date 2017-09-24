package com.shop.receipt.item.factory;

import com.shop.receipt.item.DefaultReceiptItem;
import com.shop.receipt.item.ReceiptItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by kb on 2017-09-19.
 */
@Component
public class DefaultReceiptItemFactory implements ReceiptItemFactory {

    @Autowired
    private ApplicationContext appContext;

    public ReceiptItem getInstance() {
        return (ReceiptItem) appContext.getBean("defaultReceiptItem");
    }
}
