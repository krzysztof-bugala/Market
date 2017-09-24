package com.shop.service.product;

import com.shop.entity.product.CompositeProduct;
import com.shop.entity.product.StandardProduct;
import com.shop.repository.product.ProductRepository;
import com.shop.repository.product.exception.ProductNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by kb on 2017-09-09.
 */
@RunWith(SpringRunner.class)
public class DefaultProductServiceTest {

    @Autowired
    @Qualifier("productServiceTest")
    private ProductService<StandardProduct> productService;

    @Autowired
    @Qualifier("compositeProductServiceTest")
    private ProductService<CompositeProduct> compositeProductService;

    @MockBean(name="productRepository")
    @Qualifier("defaultProductRepository")
    private ProductRepository<StandardProduct> productRepository;

    @MockBean(name="productRepository")
    @Qualifier("defaultProductRepository")
    private ProductRepository<CompositeProduct> compositeProductRepository;

    @TestConfiguration
    static class DefaultProductServiceTestContextConfiguration {

        @Bean
        public ProductService<StandardProduct> productServiceTest() {
            return new DefaultProductService<StandardProduct>();
        }

        @Bean
        public ProductService<CompositeProduct> compositeProductServiceTest() {
            return new DefaultProductService<CompositeProduct>();
        }
    }

    @Test
    public void whenGetProduct_thenReturnProduct() {

        StandardProduct standardProduct = new StandardProduct();
        long productId = 4445;
        standardProduct.setId(productId);

        Mockito.when(productRepository.getProduct(productId, StandardProduct.class)).thenReturn(standardProduct);
        standardProduct = productService.getProduct(productId, StandardProduct.class);

        assertEquals(standardProduct.getId(), productId );

    }

    @Test
    public void whenGetProduct_thenReturnCompositeProduct() {

        CompositeProduct product = new CompositeProduct();
        long productId = 4448;
        product.setId(productId);

        Mockito.when(compositeProductRepository.getProduct(productId, CompositeProduct.class)).thenReturn(product);
        product = compositeProductService.getProduct(productId, CompositeProduct.class);

        assertEquals(product.getId(), productId );

    }

    @Test(expected = ProductNotFoundException.class)
    public void whenGetProduct_thenThrowProductNotFoundException() {

        StandardProduct standardProduct = new StandardProduct();
        long productId = 4445;
        standardProduct.setId(productId);

        Mockito.when(productRepository.getProduct(productId, StandardProduct.class)).thenThrow(ProductNotFoundException.class);
        standardProduct = productService.getProduct(productId, StandardProduct.class);

        assertEquals(standardProduct.getId(), productId );

    }

    @Test
    public void whenGetProductByBarcode_thenReturnStandardProduct() {

        StandardProduct standardProduct = new StandardProduct();
        long barcode = 4445;
        standardProduct.setBarcode(barcode);

        Mockito.when(productRepository.getProductByBarcode(barcode, StandardProduct.class)).thenReturn(standardProduct);
        standardProduct = productService.getProductByBarcode(barcode, StandardProduct.class);

        assertEquals(standardProduct.getBarcode(), barcode);

    }

    @Test
    public void whenGetProductByBarcode_thenReturnCompositeProduct() {

        CompositeProduct compositeProduct = new CompositeProduct();
        long barcode = 4445;
        compositeProduct.setBarcode(barcode);

        Mockito.when(compositeProductRepository.getProductByBarcode(barcode, CompositeProduct.class)).thenReturn(compositeProduct);
        compositeProduct = compositeProductService.getProductByBarcode(barcode, CompositeProduct.class);

        assertEquals(compositeProduct.getBarcode(), barcode);

    }

    @Test(expected = ProductNotFoundException.class)
    public void whenGetProductByBarcode_thenThrowProductNotFoundException() {

        StandardProduct standardProduct = new StandardProduct();
        long barcode = 4445;
        standardProduct.setBarcode(barcode);

        Mockito.when(productRepository.getProductByBarcode(barcode, StandardProduct.class)).thenThrow(ProductNotFoundException.class);
        standardProduct = productService.getProductByBarcode(barcode, StandardProduct.class);


    }

    @Test
    public void whenFindAll_TheReturnsStandardProducts() {

        StandardProduct firstStandardProduct = new StandardProduct();
        long firstBarcode = 4445;
        firstStandardProduct.setBarcode(firstBarcode);

        StandardProduct secondStandardProduct = new StandardProduct();
        long secondBarcode = 4445;
        secondStandardProduct.setBarcode(secondBarcode);

        List<StandardProduct> standardProducts = new ArrayList<>();
        standardProducts.add(firstStandardProduct);
        standardProducts.add(secondStandardProduct);

        Mockito.when(productRepository.findAll(StandardProduct.class)).thenReturn(standardProducts);
        List<StandardProduct> foundStandardProducts = productService.findAll(StandardProduct.class);

        Assert.assertEquals(foundStandardProducts, standardProducts);
    }

    @Test
    public void whenPersit_thenPersistCalled() {
        StandardProduct standardProduct = new StandardProduct();
        long firstBarcode = 4445;
        standardProduct.setBarcode(firstBarcode);

        productService.persist(standardProduct);

        Mockito.verify(productRepository).persist(standardProduct);
    }

    @Test
    public void whenUpdate_thenUpdateCalled() {
        StandardProduct standardProduct = new StandardProduct();
        long firstBarcode = 4445;
        standardProduct.setBarcode(firstBarcode);

        productService.update(standardProduct);

        Mockito.verify(productRepository).update(standardProduct);
    }
}
