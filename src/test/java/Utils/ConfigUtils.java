package Utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ConfigUtils {
    public static String getGenericValue (String configFile, String property, String defaultValue) { //properties from config
        String outValue = defaultValue;
        try {
            Properties appProp = new Properties();
            appProp.load(Files.newInputStream(Path.of(configFile)));
            outValue = appProp.getProperty(property);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return outValue;

    }



}
