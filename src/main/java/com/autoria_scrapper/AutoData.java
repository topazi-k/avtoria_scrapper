package com.autoria_scrapper;

import lombok.Data;

@Data
public class AutoData {

    private String adId;
    private String adName;
    private String seller;
    private int priceUSD;
    private int priceUAH;
    private String city;
    private String description;
    private String url;
    private String photosUrls;
}
