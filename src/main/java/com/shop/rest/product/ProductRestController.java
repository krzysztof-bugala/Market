package com.shop.rest.product;

import com.shop.entity.product.StandardProduct;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

/**
 * Created by kb on 2017-09-09.
 */
public interface ProductRestController {

    public Collection<StandardProduct> getAvailableProducts();

    public StandardProduct getAvailableProduct(Long barcode);
}
