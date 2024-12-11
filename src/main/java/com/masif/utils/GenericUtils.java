package com.masif.utils;

import org.apache.commons.lang3.RandomStringUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class GenericUtils {

    //random String method
        public static String createRandomStringTwo(int stringLength) {
        return RandomStringUtils.randomAlphanumeric(stringLength);
    }

    public static String createRandomEmail(int stringLength) {
        return RandomStringUtils.randomAlphanumeric(stringLength) + "@yopmail.com";
    }

    public static String createBaseUrl (String configFile) { //url from config
        String urlBase = "";
        try {
            Properties appProps = new Properties();
            appProps.load(Files.newInputStream(Path.of(configFile)));
            urlBase = appProps.getProperty("protocol") + "://" + appProps.getProperty("hostname");
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
        return urlBase;
    }

    public static String getBrowserFromConfig (String configFile) { //browser from config

        System.out.println(ConfigUtils.getGenericValue(configFile,"browser", "FIREFOX"));
        return ConfigUtils.getGenericValue(configFile,"browser", "FIREFOX");
    }

    public static Boolean isDownloadDirectoryEnabled(String configFile) {
        System.out.println("Download directory enabled: " + Boolean.parseBoolean(ConfigUtils.getGenericValue(configFile, "downloadDirectoryEnabled", "false")));
        return Boolean.parseBoolean(ConfigUtils.getGenericValue(configFile, "downloadDirectoryEnabled", "false"));
    }
}
