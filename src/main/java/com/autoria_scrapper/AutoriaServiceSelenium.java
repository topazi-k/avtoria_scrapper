package com.autoria_scrapper;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class AutoriaServiceSelenium {
    AutoRiaParseOptions options;
    FirefoxDriver driver = new FirefoxDriver();
    Parser parser = new SelenuimParser(driver);
    XlsxWriter writer = new XlsxWriter();

    public AutoriaServiceSelenium(AutoRiaParseOptions options) {
        this.options = options;
    }

    public List<AutoData> scrapData() {
        List<AutoData> autoData = new LinkedList<>();
        log.info("getting top brands from main page: " + options.autoRiaUrl);
        List<String> topBrandsUrls = parser.getTopBrandsUrls(options.brandsAmount, options.autoRiaUrl);
        int brandCounter = 1;
        for (String brandUrl : topBrandsUrls) {
            log.info("finding autos by brand: " + brandUrl);
            List<AutoData> autosByBrand = getAutoDataByBrand(brandUrl);
            autoData.addAll(autosByBrand);

            log.info("write data for current brand to xslx");
            writer.writeAutoData(autosByBrand, options.xlsxPath, Integer.toString(brandCounter));
            brandCounter++;
        }
        log.info("closing web driver");
        driver.close();
        return autoData;
    }

    private List<AutoData> getAutoDataByBrand(String brandUrl) {
        List<AutoData> autoDataByBrand = new LinkedList<>();
        driver.get(brandUrl);
        for (int i = 0, pageCounter = 1; i < options.pagesAmount;i++ ) {
            log.info("finding ads urls on current page: " + brandUrl + "/?page=" + pageCounter);
            List<String> adsUrlsOnPage = parser.getAdsUrlsOnPage();
            for (String adUrl : adsUrlsOnPage) {
                log.info("getting data for auto by url: " + adUrl);
                driver.get(adUrl);
                autoDataByBrand.add(parser.getAuto(adUrl));
                pageCounter++;
            }
            log.info("changing page on: "+brandUrl + "/?page=" + pageCounter);
            driver.get(brandUrl + "/?page=" + pageCounter);
        }
        return autoDataByBrand;
    }
}
