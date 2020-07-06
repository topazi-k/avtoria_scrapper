package com.autoria_scrapper;

import java.util.Properties;

public class AutoRiaParseOptions {

    private Properties properties;
    public static String geckodriverPath;
    public static String autoRiaUrl;
    public static int brandsAmount;
    public static int pagesAmount;

    public AutoRiaParseOptions(Properties properties){
        this.properties=properties;
    }

    public void init(){
        this.geckodriverPath=properties.getProperty("geckodriverPath");
        this.autoRiaUrl=properties.getProperty("autoRiaUrl");
        this.brandsAmount=Integer.parseInt(properties.getProperty("brandsAmount"));
        this.pagesAmount=Integer.parseInt(properties.getProperty("pagesAmount"));
    }
}
