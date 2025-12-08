package connectivity;

import alvinskylers.contactbook.config.DatabaseConfig;
import org.checkerframework.checker.signature.qual.DotSeparatedIdentifiers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DatabaseConfigurationTest {

    @Test
    @DisplayName("Grab database url")
    void grabDatabaseUrl() {
        String url = DatabaseConfig.getUrl();
        System.out.println("[FOUND] " + url);
        Assertions.assertNotNull(url);
    }

    @Test
    @DisplayName("Grab database username")
    void grabDatabaseName() {
        String name = DatabaseConfig.getUsername();
        System.out.println("[FOUND] " + name);
        Assertions.assertNotNull(name);
    }

    @Test
    @DisplayName("Grab database password")
    void grabDatabasePassword() {
        String password = DatabaseConfig.getPassword();
        System.out.println("[FOUND] " + password);
        Assertions.assertNotNull(password);
    }
}
