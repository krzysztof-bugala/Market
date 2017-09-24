package com.shop.entity.product;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;

/**
 * Created by kb on 2017-09-08.
 */
@Entity
@Table(name = "composite_product")
@PrimaryKeyJoinColumn(referencedColumnName="product_id")
public class CompositeProduct extends StandardProduct {

    @OneToOne(targetEntity = StandardProduct.class, cascade = CascadeType.ALL)
    @JoinColumn(name="first_product")
    private Product firstStandardProduct;

    @OneToOne(targetEntity = StandardProduct.class , cascade = CascadeType.ALL)
    @JoinColumn(name="second_product")
    private Product secondStandardProduct;

    public Product getFirstStandardProduct() {
        return firstStandardProduct;
    }

    public void setFirstStandardProduct(Product firstStandardProduct) {
        this.firstStandardProduct = firstStandardProduct;
    }

    public Product getSecondStandardProduct() {
        return secondStandardProduct;
    }

    public void setSecondStandardProduct(Product secondStandardProduct) {
        this.secondStandardProduct = secondStandardProduct;
    }
}
