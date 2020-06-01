package dns;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesSettings {
    private Properties properties = new Properties();
    private static PropertiesSettings INSTANCE = null;

    private PropertiesSettings() {
        try {
            properties.load(new FileInputStream("src//test//resources//.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertiesSettings getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PropertiesSettings();
        }
        return INSTANCE;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public Properties getProperties() {
        return properties;
    }
}
