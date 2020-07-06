package com.autoria_scrapper;

public class AutoriaScrapperApp {

    public static void main(String[] args) {
        AutoRiaParseOptions parseOptions = ConfigurationLoader.getConfigurations();
        System.setProperty("webdriver.gecko.driver", parseOptions.geckodriverPath);

        AutoriaServiceSelenium serviceSilenium=new AutoriaServiceSelenium(parseOptions);
        serviceSilenium.scrapData();





    }



}
