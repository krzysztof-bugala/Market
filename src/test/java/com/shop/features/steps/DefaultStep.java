package com.shop.features.steps;

import com.shop.features.checkCartTotalPrice.steps.CucumberScenarioData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

/**
 * Created by kb on 2017-09-24.
 */
public class DefaultStep {
    @Autowired
    protected WebApplicationContext context;

    protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    protected MockMvc mockMvc;

    protected MockHttpSession mockSession;

    protected MvcResult mvcResult;

    @Autowired
    protected CucumberScenarioData test;

    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
