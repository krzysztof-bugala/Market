package com.shop.service.product;

import com.shop.entity.product.StandardProduct;
import com.shop.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kb on 2017-09-09.
 */
@Transactional
@Service
public class DefaultProductService<T extends StandardProduct> implements ProductService<T> {

    @Autowired
    @Qualifier("defaultProductRepository")
    private ProductRepository<T> productRepository;

    @Override
    public T getProduct(long productId, Class <T> productClass)  {
        T product = productRepository.getProduct(productId, productClass);
        return product;
    }
    @Override
    public T getProductByBarcode(Long barCode, Class <T> productClass) {
        return productRepository.getProductByBarcode(barCode, productClass);
    }

    @Override
    public void update(T product) {
        productRepository.update(product);
    }

    @Override
    public void persist(T product) {
        productRepository.persist(product);
    }

    @Override
    public List<T> findAll(Class<T> productClass) {
        return productRepository.findAll(productClass);
    }

}
