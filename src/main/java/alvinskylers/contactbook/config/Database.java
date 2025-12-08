package alvinskylers.contactbook.config;

import jdk.jfr.DataAmount;

import java.sql.*;

public class Database {

    public static Connection connect() {
        try {
            String url = DatabaseConfig.getUrl();
            String username = DatabaseConfig.getUsername();
            String password = DatabaseConfig.getPassword();
            return DriverManager.getConnection(url, username, password);

        } catch (SQLException eSQL) {
            eSQL.printStackTrace();
            return null;
        }
    }

    public static boolean doesTableExist(String tableName) throws SQLException {
        try (Connection connection = connect();
             PreparedStatement pstmt = connection.prepareStatement("SELECT to_regclass(?)")) {
            pstmt.setString(1, tableName);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getString(1) != null;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public static void generateContactsTable() throws SQLException {
        String sql = "CREATE TABLE contacts (" +
                "   id SERIAL PRIMARY KEY," +
                "   name VARCHAR(255) NOT NULL," +
                "   phone VARCHAR(255) NOT NULL," +
                "   email VARCHAR(255) NOT NULL," +
                "   address VARCHAR(255) NOT NULL" +
                ");";

        try (Connection connection = Database.connect()) {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void dropContactsTable() throws SQLException {
        String sql = "DROP TABLE contacts;";

        try (Connection connection = Database.connect()) {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
