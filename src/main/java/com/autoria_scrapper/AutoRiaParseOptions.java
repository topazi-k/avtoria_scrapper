package com.autoria_scrapper;

import java.util.Properties;

public class AutoRiaParseOptions {


    public static  String geckodriverPath;
    public static String xlsxPath;

    public static String autoRiaUrl;
    public static int brandsAmount;
    public static int pagesAmount;

    public AutoRiaParseOptions(Properties properties) {
        this.geckodriverPath=properties.getProperty("geckodriverPath");
        this.xlsxPath=properties.getProperty("xlsxPath");

        this.autoRiaUrl=properties.getProperty("autoRiaUrl");
        this.brandsAmount=Integer.parseInt(properties.getProperty("brandsAmount"));
        this.pagesAmount=Integer.parseInt(properties.getProperty("pagesAmount"));
    }


}
