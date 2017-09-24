package com.shop.entity.product.price;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * Created by kb on 2017-09-09.
 */
@Embeddable
public class StandardPrice implements Price {

    @Column(name="price")
    private BigDecimal price;


    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
