package com.shop.exception.handler;


import com.shop.error.MarketError;
import com.shop.error.factory.ErrorFactory;
import com.shop.exception.MarketException;
import com.shop.error.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kb on 2017-09-16.
 */
@EnableWebMvc
@ControllerAdvice
@RestController
public class CustomRestExceptionHandler  extends ResponseEntityExceptionHandler {

    @Autowired
    private ErrorFactory errorFactory;

    @ExceptionHandler({MarketException.class})
    @ResponseBody
    public ResponseEntity<Object> handleMarketException(Exception exception, WebRequest request){
        MarketException marketException = (MarketException)exception;
        String errorMessage = marketException.getErrorMessage();
        Error error = errorFactory.getInstance(errorMessage);
        HttpStatus httpStatus = marketException.getHttpStatus();
        return new ResponseEntity<>(error, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            errors.add(error);
        }

        Error marketError = new MarketError(errors);

        return new ResponseEntity<>(marketError, headers, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
