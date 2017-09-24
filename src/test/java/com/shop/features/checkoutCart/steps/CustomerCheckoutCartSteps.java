package com.shop.features.checkoutCart.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.Market;
import com.shop.features.checkCartTotalPrice.steps.CucumberScenarioData;
import com.shop.receipt.DefaultReceipt;
import com.shop.receipt.Receipt;
import com.shop.service.cart.CartService;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by kb on 2017-09-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Market.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
public class CustomerCheckoutCartSteps {

    @Autowired
    private WebApplicationContext context;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    private MockHttpSession mockSession;

    private MvcResult mvcResult;

    @Autowired
    @Qualifier("defaultCartService")
    private CartService cartService;

    @Autowired
    private CucumberScenarioData cucumberScenarioData;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @When("^The customer checkouts cart using (.*) rest$")
    public void whenCartTotalPriceIsChecked(String checkoutCartRest) throws Throwable {
        mockSession = cucumberScenarioData.getMockSession();
        mvcResult = mockMvc.perform(post(checkoutCartRest)
                .contentType(contentType).session(mockSession))
                .andExpect(status().isOk()).andReturn();
    }

    @Then("^The customer verify that the receipt total price is (.*)$")
    public void thenCheckReceipt(String totalPriceValue) throws Throwable {

        String receiptJson = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Receipt  receipt = mapper.readValue(receiptJson, DefaultReceipt.class);
        BigDecimal totalPrice = new BigDecimal(totalPriceValue);

        Assert.assertEquals(totalPrice, receipt.getTotalPrice());
    }




}
