package com.shop.rest.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.Market;
import com.shop.entity.product.StandardProduct;
import com.shop.error.Error;
import com.shop.exception.handler.CustomRestExceptionHandler;
import com.shop.repository.product.exception.ProductNotFoundException;
import com.shop.service.product.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by kb on 2017-09-20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Market.class)
@WebAppConfiguration
public class DefaultProductRestControllerTest {


    private MockMvc mockMvc;

    @MockBean(name="productService")
    private ProductService<StandardProduct> productService;



    @InjectMocks
    private DefaultProductRestController defaultProductRestController;

    @Autowired
    private CustomRestExceptionHandler customRestExceptionHandler;


    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(defaultProductRestController)
                .setControllerAdvice(customRestExceptionHandler)
                .build();
    }

    @Test
    public void getProductsResponse() throws Exception {
        //given
        StandardProduct product1 = new StandardProduct();
        Integer product1Barcode = 54545;
        product1.setBarcode(product1Barcode);

        StandardProduct product2 = new StandardProduct();
        Integer product2Barcode = 66565;
        product2.setBarcode(product2Barcode);

        List<StandardProduct> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        ObjectMapper mapper = new ObjectMapper();
        String jsonProducts = mapper.writeValueAsString(products);


        when(productService.findAll(StandardProduct.class)).thenReturn(products);

        //when & then
        mockMvc.perform(get("/market/api/v1/products")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonProducts));
    }

    @Test
    public void getProductBarcodeResponse_whenProductFound() throws Exception {
        //given
        StandardProduct product = new StandardProduct();
        Integer productBarcode = 24567;
        product.setBarcode(productBarcode);

        when(productService.getProductByBarcode(productBarcode.longValue(), StandardProduct.class)).thenReturn(product);

        mockMvc.perform(get("/market/api/v1/products/24567")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.barcode", is(productBarcode)));
    }

    @Test
    public void getProductBarcodeResponse_whenProductNotFound() throws Exception {

        String errorMessage = Error.PRODUCT_NOT_FOUND_EXCEPTION;

        StandardProduct product = new StandardProduct();
        Integer productBarcode = 24568;
        product.setBarcode(productBarcode);

        when(productService.getProductByBarcode(productBarcode.longValue(), StandardProduct.class)).thenThrow(new ProductNotFoundException());

        mockMvc.perform(get("/market/api/v1/products/24568")
                .contentType(contentType))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessages[0]", is(errorMessage)));
    }

}