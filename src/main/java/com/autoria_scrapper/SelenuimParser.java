package com.autoria_scrapper;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class SelenuimParser implements Parser {

    private WebDriver driver;


    public SelenuimParser(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getTopBrandsUrls(int brandsAmount, String pageUrl) {
        List<String> brandsUrls = new LinkedList<>();
        log.info("openning page: "+ pageUrl);
        driver.get(pageUrl);
        WebElement navBar = driver.findElement(By.xpath("//nav[contains(@class,\"catalog-brands\")]"));
        List<WebElement> topBrands = navBar.findElements(By.xpath("//a[contains(@class,\"item-brands\")]"));

        for (int i = 0; i < brandsAmount; i++) {
            String brandUrl = topBrands.get(i).getAttribute("href");
            brandsUrls.add(brandUrl);
        }
        return brandsUrls;
    }

    public List<String> getAdsUrlsOnPage() {
        List<WebElement> adsOnPage = driver.findElements(By.xpath("//section[contains(@class,\"ticket-item\")]"));
        List<String> adsUrls = new LinkedList<>();
        for (WebElement ad : adsOnPage) {
            WebElement linkElement = ad.findElement(By.className("address"));
            String adUrl = linkElement.getAttribute("href");
            if (adUrl != null) {
                adsUrls.add(adUrl);
            }
        }
        return adsUrls;
    }

    public AutoData getAuto(String adUrl) {
        AutoData autoData = new AutoData();
        autoData.setAdId(getId(adUrl));
        autoData.setAdName(getAdName(adUrl));
        autoData.setSeller(getSellerName(adUrl));
        autoData.setCity(getCity(adUrl));
        autoData.setPriceUSD(getPriceUSD(adUrl));
        autoData.setPriceUAH(getPriceUAH(adUrl));
        autoData.setUrl(adUrl);
        autoData.setDescription(getDescription(adUrl));
        autoData.setPhotosUrls(getPhotosUrls(adUrl));
        return autoData;
    }

    private String getPhotosUrls(String currentUrl) {
        log.info("getting photos urls");
        String photosUrls = "can not find";
        try {
            WebElement photosContainer = driver.findElement(By.className("preview-gallery"));
            List<WebElement> photosElements = photosContainer.findElements(By.tagName("img"));
            List<String> urls = new LinkedList<>();
            for (int i = 0; i < photosElements.size(); i++) {
                urls.add(photosElements.get(i).getAttribute("src"));
            }
            photosUrls = String.join(", ", urls);
        } catch (Exception e) {
            log.warn("Can not find photos url for url: " + currentUrl);
        }
        return photosUrls;
    }

    private String getId(String currentUrl) {
        log.info("getting id ");
        String adId = "can not find";
        try {
            adId = driver.findElement(By.xpath("//body")).getAttribute("data-auto-id");
        } catch (NoSuchElementException e) {
            log.warn("Can not find id for url: " + currentUrl);
        }
        return adId;
    }

    private String getAdName(String currentUrl) {
        log.info("getting ad name");
        String adName = "can not find";
        try {
            adName = driver.findElement(By.className("auto-content_title")).getText();
        } catch (NoSuchElementException e) {
            log.warn("Can not find ad name for url: " + currentUrl);
        }
        return adName;
    }

    private String getSellerName(String currentUrl) {
        log.info("getting seller name");
        String sellerName = "no name";
        try {
            sellerName = driver.findElement(By.className("seller_info_name")).getText();
        } catch (NoSuchElementException e) {
            log.warn("Can not find seller name for url: " + currentUrl);
        }
        return sellerName;
    }

    private String getCity(String currentUrl) {
        log.info("getting city");
        String city = "no info";
        try {
            city = driver.findElement(By.className("item_inner")).getText();
        } catch (NoSuchElementException e) {
            log.warn("Can not find city for url: " + currentUrl);
        }
        return city;
    }

    private int getPriceUSD(String currentUrl) {
        log.info("getting price USD");
        int priceUSD = 0;
        try {
            WebElement priceElementUSD = driver.findElement(By.xpath("//div[contains(@class,\"price_value\")]/strong"));
            priceUSD = Integer.parseInt(priceElementUSD.getText().replaceAll("[^\\d.]", ""));
        } catch (Exception e) {
            System.out.println("Can not find price USD for url: " + currentUrl);
        }
        return priceUSD;
    }

    private int getPriceUAH(String currentUrl) {
        log.info("getting price UAH");
        int priceUAH = 0;
        try {
            WebElement priceElementUAH = driver.findElement(By.xpath("//span[contains(@data-currency,\"UAH\")]"));
            priceUAH = Integer.parseInt(priceElementUAH.getText().replaceAll("[^\\d.]", ""));
        } catch (Exception e) {
            log.warn("Can not find price UAH for url: " + currentUrl);
        }
        return priceUAH;
    }

    private String getDescription(String currentUrl) {
        log.info("getting full description");
        String description = "no info";
        try {
            description = driver.findElement(By.id("full-description")).getText();
        } catch (NoSuchElementException e) {
           log.warn("Can not find description for url: " + currentUrl);
        }
        return description;
    }
}
