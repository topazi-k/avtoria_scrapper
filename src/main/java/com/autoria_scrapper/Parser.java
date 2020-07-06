package com.autoria_scrapper;

import java.util.List;

public interface Parser {
    List<String> getTopBrandsUrls(int brandsAmount, String pageUrl);
    List<String> getAdsUrlsOnPage();
    AutoData getAuto(String url);
}
