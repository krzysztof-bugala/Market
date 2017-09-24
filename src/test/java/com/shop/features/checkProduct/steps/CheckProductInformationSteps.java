package com.shop.features.checkProduct.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.Market;
import com.shop.entity.product.Product;
import com.shop.entity.product.StandardProduct;
import com.shop.service.product.ProductService;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by kb on 2017-09-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Market.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
public class CheckProductInformationSteps {

    @Autowired
    private WebApplicationContext context;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private
    MockMvc mockMvc;

    @Autowired
    @Qualifier("defaultProductService")
    private ProductService<StandardProduct> productService;

    private Product product;

    private String productRest;

    private MvcResult mvcResult;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Given("^The product with barcode (\\d+)$")
    public void givenProductWithBarcode(long barcode) throws Throwable {
        product = productService.getProductByBarcode(barcode, StandardProduct.class);
    }

    @When("^The product is checked using (.*) rest$")
    public void whenProductIsChecked(String productRest) throws Throwable {
        this.productRest = productRest;
        mvcResult = mockMvc.perform(get(productRest)
                .contentType(contentType))
                .andExpect(status().isOk()).andReturn();
    }

    @Then("^The information about the product is returned$")
    public void thenProductInformationIsReturned() throws Throwable {
        String response = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        String jsonProduct = mapper.writeValueAsString(product);

        Assert.assertEquals(jsonProduct, response);
    }
}
