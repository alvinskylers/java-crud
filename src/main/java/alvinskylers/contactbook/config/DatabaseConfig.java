package alvinskylers.contactbook.config;

import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static final Properties properties = new Properties();

    static {
        try (InputStream config = DatabaseConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (config == null) {
                System.out.println("No config file found!");
                System.exit(0);
            }

            properties.load(config);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUrl() {
        return properties.getProperty("config.url");
    }

    public static String getUsername() {
        return properties.getProperty("config.username");
    }

    public static String getPassword() {
        return properties.getProperty("config.password");
    }

}
