package com.shop.error.factory;

import com.shop.error.Error;

/**
 * Created by kb on 2017-09-17.
 */
public interface ErrorFactory {
    public Error getInstance(String errorMessage);
}
