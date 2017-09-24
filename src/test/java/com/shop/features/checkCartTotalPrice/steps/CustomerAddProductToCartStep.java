package com.shop.features.checkCartTotalPrice.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.Market;
import com.shop.service.cart.DefaultSelectedItem;
import com.shop.service.cart.SelectedItem;
import cucumber.api.java.Before;
import cucumber.api.java.en.When;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by kb on 2017-09-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Market.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
public class CustomerAddProductToCartStep {
    @Autowired
    private WebApplicationContext context;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    private MockHttpSession mockSession;

    @Autowired
    private CucumberScenarioData cucumberScenarioData;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @When("^The customer adds (\\d+) products with barcode (\\d+) as second to cart using (.*) rest$")
    public void whenSecondProductIsAddedToCart(int productQuantity, long productBarcode, String cartItemsRest) throws Throwable {
        mockSession = cucumberScenarioData.getMockSession();
        SelectedItem selectedItem = new DefaultSelectedItem(productBarcode, productQuantity);
        ObjectMapper mapper = new ObjectMapper();
        String jsonSelectedItem = mapper.writeValueAsString(selectedItem);
        mockMvc.perform(post(cartItemsRest)
                .contentType(contentType).content(jsonSelectedItem)
                .session(mockSession))
                .andExpect(status().isCreated());
    }
}
