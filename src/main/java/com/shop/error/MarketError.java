package com.shop.error;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kb on 2017-09-17.
 */
public class MarketError implements Error {

    private List<String> errorMessages = new ArrayList<>();

    public MarketError(String errorMessage){
        this.errorMessages.add(errorMessage);
    }

    public MarketError(List<String> errorMessages){
        this.setErrorMessages(errorMessages);
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
