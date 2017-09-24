package com.shop.rest.product;

import com.shop.entity.product.StandardProduct;
import com.shop.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by kb on 2017-09-09.
 */
@RestController
@RequestMapping("market/api/v1/products")
public class DefaultProductRestController {

    @Autowired
    @Qualifier("defaultProductService")
    private ProductService<StandardProduct> productService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<StandardProduct> getProducts() {
       return productService.findAll(StandardProduct.class);
    }

    @RequestMapping(value="/{barcode}", method = RequestMethod.GET)
    public StandardProduct getProduct(@PathVariable Long barcode) {
        return productService.getProductByBarcode(barcode,StandardProduct.class);
    }
}
