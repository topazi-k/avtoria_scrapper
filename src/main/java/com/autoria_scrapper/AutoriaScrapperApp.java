package com.autoria_scrapper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Properties;

public class AutoriaScrapperApp {

    public static void main(String[] args) {
        Properties properties = ConfigurationLoader.getConfigurations();
        System.setProperty("webdriver.gecko.driver", properties.getProperty("geckodriverPath"));

        WebDriver webDriver = new FirefoxDriver();
        AutoRiaParseOptions options = new AutoRiaParseOptions(properties);
        options.init();

        SilenuimParser parser = new SilenuimParser(webDriver, options);
        List<String> topBrandsUrls=parser.getTopBrandsUrls();
        webDriver.get(topBrandsUrls.get(0));
        List<String> adsUrls=parser.getAdsUrlsOnPage();

        webDriver.get(adsUrls.get(0));
        parser.getAuto(adsUrls.get(0));


        webDriver.close();


    }


}
