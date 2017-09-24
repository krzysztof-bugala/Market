package com.shop.error.factory;

import com.shop.error.MarketError;
import com.shop.error.Error;
import org.springframework.stereotype.Component;

/**
 * Created by kb on 2017-09-17.
 */
@Component
public class MarketErrorFactory implements ErrorFactory {

    public Error getInstance(String errorMessage) {
        return new MarketError(errorMessage);
    }
}
