package com.autoria_scrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

public class SilenuimParser {

    private WebDriver driver;
    private AutoRiaParseOptions options;

    public SilenuimParser(WebDriver driver, AutoRiaParseOptions options) {
        this.driver = driver;
        this.options = options;
    }

    public List<String> getTopBrandsUrls() {
        List<String> brandsUrls = new LinkedList<>();
        driver.get(options.autoRiaUrl);
        WebElement navBar = driver.findElement(By.xpath("//nav[contains(@class,\"catalog-brands\")]"));
        List<WebElement> topBrands = navBar.findElements(By.xpath("//a[contains(@class,\"item-brands\")]"));

        for (int i = 0; i < options.brandsAmount; i++) {
            String brandUrl=topBrands.get(i).getAttribute("href");
            brandsUrls.add(brandUrl);
        }
        return brandsUrls;
    }

    public List<String> getAdsUrlsOnPage(){
        List<WebElement> adsOnPage=driver.findElements(By.xpath("//section[contains(@class,\"ticket-item\")]"));
        List<String> adsUrls=new LinkedList<>();
        for(WebElement ad:adsOnPage){
            WebElement linkElement=ad.findElement(By.className("m-link-ticket"));
            String adUrl=linkElement.getAttribute("href");
            adsUrls.add(adUrl);
        }
        return adsUrls;
    }
    public AutoData getAuto(String adUrl){
        AutoData autoData=new AutoData();
        String id=driver.findElement(By.xpath("//body")).getAttribute("data-auto-id");
        String adName=driver.findElement(By.className("auto-content_title")).getText();
        String sellerName=driver.findElement(By.className("seller_info_name")).getText();
        WebElement priceElementUSD=driver.findElement(By.xpath("//div[contains(@class,\"price_value\")]/strong"));
        int priceUSD=Integer.parseInt(priceElementUSD.getText().replaceAll("[^\\d.]", ""));
        WebElement priceElementUAH=driver.findElement(By.xpath("//span[contains(@data-currency,\"UAH\")]"));
        int priceUAH=Integer.parseInt(priceElementUAH.getText().replaceAll("[^\\d.]", ""));
        String city=driver.findElement(By.className("item_inner")).getText();
        autoData.setUrl(adUrl);
        String description=driver.findElement(By.id("full-description")).getText();
        getPhotosUrls();
       // System.out.println(description);
        return autoData;
    }

    private String getPhotosUrls(){
        String photosUrls;
        WebElement photosContainer=driver.findElement(By.xpath("//div[@data-megaphoto]"));
        System.out.println(photosContainer.getAttribute("class"));
        return null;
    }
}
