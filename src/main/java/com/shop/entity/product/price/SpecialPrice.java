package com.shop.entity.product.price;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * Created by kb on 2017-09-09.
 */
@Embeddable
public class SpecialPrice implements Price {

    @Column(name="special_price")
    private BigDecimal price;

    @Column(name="price_Threshold")
    private int priceThreshold;

    public int getPriceThreshold() {
        return priceThreshold;
    }

    public void setPriceThreshold(int priceThreshold) {
        this.priceThreshold = priceThreshold;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
