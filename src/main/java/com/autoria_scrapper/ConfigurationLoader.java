package com.autoria_scrapper;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class ConfigurationLoader {

    public static AutoRiaParseOptions getConfigurations() {
        Properties properties = new Properties();
        log.info("Reading configuration data for scrapping from --src/main/resources/scrapperConfigurations.properties");
        try (FileInputStream fileIS = new FileInputStream("src/main/resources/scrapperConfigurations.properties");) {
            properties.load(fileIS);
        } catch (IOException e) {
            log.error("can not load properties file");
        }
        return createOptionsObject(properties);
    }

    private static AutoRiaParseOptions createOptionsObject(Properties props) {
        log.info("define ParseOptins object");
        AutoRiaParseOptions options = new AutoRiaParseOptions(props);
        return options;
    }
}
