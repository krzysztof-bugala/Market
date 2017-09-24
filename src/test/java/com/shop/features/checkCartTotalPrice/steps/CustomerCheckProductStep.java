package com.shop.features.checkCartTotalPrice.steps;

import com.shop.Market;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.runtime.java.StepDefAnnotation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by kb on 2017-09-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Market.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
public class CustomerCheckProductStep {

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
        mockSession = new MockHttpSession(context.getServletContext(), UUID.randomUUID().toString());
        cucumberScenarioData.setMockSession(mockSession);
    }

    @Given("^The customer check product with barcode (\\d+) using (.*) rest$")
    public void givenProductWithBarcode(long barcode, String productRest) throws Throwable {
        mockMvc.perform(get(productRest)
                .contentType(contentType)
                .session(mockSession))
                .andExpect(status().isOk());
    }


}
