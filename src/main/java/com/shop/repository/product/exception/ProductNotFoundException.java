package com.shop.repository.product.exception;

import com.shop.exception.MarketException;
import com.shop.error.Error;
import org.springframework.http.HttpStatus;

/**
 * Created by kb on 2017-09-09.
 */
public class ProductNotFoundException extends MarketException {

    public ProductNotFoundException() {
        this.errorMessage = Error.PRODUCT_NOT_FOUND_EXCEPTION;
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

}
