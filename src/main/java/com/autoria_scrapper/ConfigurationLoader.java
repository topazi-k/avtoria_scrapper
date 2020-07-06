package com.autoria_scrapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationLoader {

    public static Properties getConfigurations(){
        Properties properties= new Properties();

        try(FileInputStream fileIS = new FileInputStream("src/main/resources/scrapperConfigurations.properties");){
            properties.load(fileIS);
        }catch (IOException e){
            System.out.println("can not load properties");
        }
        return properties;
    }
}
