package com.shop.repository.product;

import com.shop.Market;
import com.shop.entity.product.CompositeProduct;
import com.shop.entity.product.StandardProduct;
import com.shop.entity.product.price.SpecialPrice;
import com.shop.entity.product.price.StandardPrice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Created by kb on 2017-09-07.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes=Market.class)
public class DefaultProductRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository<StandardProduct> productRepository;

    @Autowired
    private ProductRepository<CompositeProduct> compositeProductRepository;

    @Test
    public void whenGetProduct_thenReturnProduct() {
        // given
        long barcode = 7732478274L;
        StandardProduct standardProduct = new StandardProduct();
        standardProduct.setBarcode(barcode);
        standardProduct.setName("du");
        standardProduct.setDescription("sfdsfsdf");

        BigDecimal standardPriceValue = new BigDecimal("45.60");
        StandardPrice standardPrice = new StandardPrice();
        standardPrice.setPrice(standardPriceValue);

        standardProduct.setStandardPrice(standardPrice);

        BigDecimal specialPriceValue = new BigDecimal("60.70");
        SpecialPrice specialPrice = new SpecialPrice();
        specialPrice.setPrice(specialPriceValue);
        specialPrice.setPriceThreshold(120);

        standardProduct.setSpecialPrice(specialPrice);

        productRepository.persist(standardProduct);

        // when
        StandardProduct foundBasicStandardProduct = productRepository.getProductByBarcode(barcode, StandardProduct.class);

        // then
        assertEquals(foundBasicStandardProduct.getBarcode(), barcode);
        assertEquals(foundBasicStandardProduct.getName(), "du");
        assertEquals(foundBasicStandardProduct.getDescription(), "sfdsfsdf");
        assertEquals(foundBasicStandardProduct.getStandardPrice().getPrice(), standardPriceValue);
        assertEquals(foundBasicStandardProduct.getSpecialPrice().getPrice(), specialPriceValue);
        assertEquals(foundBasicStandardProduct.getSpecialPrice().getPriceThreshold(), 120);
    }

    @Test
    public void whenGetCompositeProduct_thenReturnCompositeProduct() {
        // given
        long barcode = 7732478274L;
        CompositeProduct compositeProduct = new CompositeProduct();
        compositeProduct.setBarcode(barcode);
        compositeProduct.setName("du");
        compositeProduct.setDescription("sfdsfsdf");

        StandardProduct firstStandardProduct = new StandardProduct();
        firstStandardProduct.setBarcode(66666);
        firstStandardProduct.setName("du");
        firstStandardProduct.setDescription("sfdsfsdf");

        compositeProduct.setFirstStandardProduct(firstStandardProduct);

        StandardProduct secondStandardProduct = new StandardProduct();
        secondStandardProduct.setBarcode(88888);
        secondStandardProduct.setName("du");
        secondStandardProduct.setDescription("sfdsfsdf");

        compositeProduct.setSecondStandardProduct(secondStandardProduct);

        compositeProductRepository.persist(compositeProduct);

        // when
        CompositeProduct foundCompositeProduct = compositeProductRepository.getProductByBarcode(compositeProduct.getBarcode(), CompositeProduct.class);



        // then
        assertEquals(foundCompositeProduct.getBarcode(), barcode);
        assertEquals(foundCompositeProduct.getName(), "du");
        assertEquals(foundCompositeProduct.getDescription(), "sfdsfsdf");
        assertEquals(foundCompositeProduct.getFirstStandardProduct().getBarcode(), 66666);
        assertEquals(foundCompositeProduct.getSecondStandardProduct().getBarcode(), 88888);
    }

    @Test
    public void whenFindAll_thenReturnProducts() {
        // given
        long barcode = 7732478274L;
        StandardProduct standardProduct = new StandardProduct();
        standardProduct.setBarcode(barcode);
        standardProduct.setName("du");
        standardProduct.setDescription("sfdsfsdf");

        productRepository.persist(standardProduct);

        standardProduct = new StandardProduct();
        standardProduct.setBarcode(7732478276L);
        standardProduct.setName("du");
        standardProduct.setDescription("sfdsfsdf");

        productRepository.persist(standardProduct);

        // when
        List<StandardProduct> foundBasicStandardProducts = productRepository.findAll(StandardProduct.class);

        // then
        assertEquals(foundBasicStandardProducts.size(), 2);
    }

    @Test
    public void whenFindAll_thenReturnCompositeProducts() {
        // given
        long barcode = 7732478274L;
        CompositeProduct compositeProduct = new CompositeProduct();
        compositeProduct.setBarcode(barcode);
        compositeProduct.setName("du");
        compositeProduct.setDescription("sfdsfsdf");

        compositeProductRepository.persist(compositeProduct);

        compositeProduct = new CompositeProduct();
        compositeProduct.setBarcode(7732478276L);
        compositeProduct.setName("du");
        compositeProduct.setDescription("sfdsfsdf");

        compositeProductRepository.persist(compositeProduct);

        // when
        List<CompositeProduct> foundBasicProducts = compositeProductRepository.findAll(CompositeProduct.class);

        // then
        assertEquals(foundBasicProducts.size(), 2);
    }

}

