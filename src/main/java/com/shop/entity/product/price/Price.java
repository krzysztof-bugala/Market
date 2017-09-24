package com.shop.entity.product.price;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * Created by kb on 2017-09-09.
 */
public interface Price {

    public BigDecimal getPrice();

    public void setPrice(BigDecimal price);
}
