package Utils;

import org.apache.commons.lang3.RandomStringUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Random;

public class GenericUtils {
    //random String method
    public static String createRandomStringOne(int charCount) {
        StringBuilder sb = new StringBuilder();
        String charset = "abc123!@#";
        for (int i = 0; i < charCount; i++) {
            Random r = new Random();
            char x = charset.toCharArray()[r.nextInt(charset.length())]; //takes x as random char and append to sb
            sb.append(x);
        }
        System.out.println(sb);
        return sb.toString();
    }
    public static String createRandomStringTwo(int stringLength) {
        String rs = RandomStringUtils.randomAlphanumeric(stringLength);
        System.out.println(rs);
        return rs;
    }

    public static String createRandomEmail(int stringLength) {
        String randomEmail = RandomStringUtils.randomAlphanumeric(stringLength) + "@yopmail.com";
        System.out.println(randomEmail);
        return randomEmail;
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
