package com.shop.entity.product;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shop.entity.product.price.SpecialPrice;
import com.shop.entity.product.price.StandardPrice;

/**
 * Created by kb on 2017-09-09.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = StandardProduct.class),
        @JsonSubTypes.Type(value = CompositeProduct.class)
})
public interface Product {

    public long getId();

    public void setId(long id);

    public long getBarcode();

    public void setBarcode(long barcode);

    public String getName();


    public void setName(String name);


    public String getDescription();

    public void setDescription(String description);

    public StandardPrice getStandardPrice();

    public void setStandardPrice(StandardPrice standardPrice);

    public SpecialPrice getSpecialPrice();

    public void setSpecialPrice(SpecialPrice specialPrice);
}
