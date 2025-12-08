package connectivity;

import alvinskylers.contactbook.config.Database;
import alvinskylers.contactbook.config.DatabaseConfig;
import org.checkerframework.checker.signature.qual.DotSeparatedIdentifiers;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

public class DatabaseTest {


    @BeforeAll
    static void setUp() throws SQLException, ClassNotFoundException {
        Database.dropContactsTable();
    }


    @Test
    @DisplayName("Create contact table")
    public void createDatabaseTest() {
        boolean status = false;
        try {
            Database.generateContactsTable();
            status = true;
            System.out.println("[SUCCESS] contact table created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(status);
    }

    @Test
    @DisplayName("Verify contact table")
    public void verifyDatabaseTest() {
        boolean status;
       try {
           status = Database.doesTableExist("contacts");
           System.out.println("[SUCCESS] contact table exists!");
       } catch (SQLException e) {
           e.printStackTrace();
           status = false;
       }
       Assertions.assertTrue(status);
    }

    @Test
    @DisplayName("Drop contact table")
    public void dropDatabaseTest() {
        boolean status = false;
        try {
            Database.dropContactsTable();
            status = true;
            System.out.println("[SUCCESS] contact table dropped!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(status);
    }

}
