package com.shop.features;

import com.shop.Market;
import org.junit.Test;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"})
public class CucumberRunner {

}