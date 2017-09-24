package com.shop.service.product;

import com.shop.entity.product.StandardProduct;

import java.util.List;

/**
 * Created by kb on 2017-09-07.
 */
public interface ProductService<T extends StandardProduct> {

    public T getProduct(long barcode, Class <T> productClass);

    public T getProductByBarcode(Long barCode, Class <T> productClass);

    public void update(T product);

    public void persist(T product);

    public List<T> findAll(Class<T> productClass);
}
