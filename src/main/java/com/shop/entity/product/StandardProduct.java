package com.shop.entity.product;

import com.shop.entity.product.price.SpecialPrice;
import com.shop.entity.product.price.StandardPrice;

import javax.persistence.*;

/**
 * Created by kb on 2017-09-07.
 */
@Entity
@Table(name = "standard_product")
@Inheritance( strategy = InheritanceType.JOINED )
public class StandardProduct implements Product{

    @Id
    @SequenceGenerator(name = "productGenerator", sequenceName = "standard_product_sequence", allocationSize = 1)
    @GeneratedValue(generator = "productGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name="product_id")
    private long id;

    @Column(name="barcode")
    private long barcode;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Embedded
    private StandardPrice standardPrice;

    @Embedded
    private SpecialPrice specialPrice;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public long getBarcode() {
        return barcode;
    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StandardPrice getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(StandardPrice standardPrice) {
        this.standardPrice = standardPrice;
    }

    public SpecialPrice getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(SpecialPrice specialPrice) {
        this.specialPrice = specialPrice;
    }

}
