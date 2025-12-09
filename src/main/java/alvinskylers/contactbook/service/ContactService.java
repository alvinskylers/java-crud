package alvinskylers.contactbook.service;

import alvinskylers.contactbook.config.Database;
import alvinskylers.contactbook.entity.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactService {

    public void saveContact(Contact contact) {
        String sql = "INSERT INTO contacts(name, phone, email, address) VALUES (?,?,?,?)";
        try  (Connection connection = Database.connect();
              PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getPhone());
            preparedStatement.setString(3, contact.getEmail());
            preparedStatement.setString(4, contact.getAddress());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editContact(int id, String name, String phone, String email, String address) {
        String sql = "UPDATE contacts" +
                        " SET name  = ?, phone = ?, email = ?, address = ? " +
                        " WHERE id = ?";
        try (Connection connection = Database.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, address);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(int id) {
        String sql = "DELETE FROM contacts WHERE id = ?";
        try (Connection connection = Database.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch  (SQLException e) {
            e.printStackTrace();
        }
    }

    public Contact grabContact(int id) {
        String sql = "SELECT * FROM contacts WHERE id = ?";
        try (Connection connection = Database.connect();
             PreparedStatement preparedStatement =  connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet =  preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Contact(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Contact> grabContacts() {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts ORDER BY name";
        try (Connection connection = Database.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet =  preparedStatement.executeQuery();
            while (resultSet.next()) {
                contacts.add(new Contact(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("address")
                ));
            }
            return contacts;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }
}
