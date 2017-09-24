package com.shop.features.checkCartTotalPrice.steps;

import com.shop.entity.product.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MvcResult;

/**
 * Created by kb on 2017-09-24.
 */
@Component
@Scope("cucumber-glue")
public class CucumberScenarioData {

    private MockHttpSession mockSession;

    public MockHttpSession getMockSession() {
        return mockSession;
    }

    public void setMockSession(MockHttpSession mockSession) {
        this.mockSession = mockSession;
    }
}
