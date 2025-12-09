package contactbook;

import alvinskylers.contactbook.config.Database;
import alvinskylers.contactbook.entity.Contact;
import alvinskylers.contactbook.service.ContactService;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactServiceTest {

    ContactService cs = new ContactService();

    Contact generateContact () {
        return new Contact(1,"Shorekeeper", "shorekeeper@mail.com",  "000000000", "Blackshores HQ");
    }

    List<Contact> generateContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(generateContact());
        contacts.add(generateContact());
        contacts.add(generateContact());
        return contacts;
    }

    @BeforeEach
    public void testSetup() {
        try {
            Database.generateContactsTable();
            System.out.println("[SUCCESS] Table contact generated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Insert contact to database")
    public void insertContactTest() {
        cs.saveContact(generateContact());
    }

    @Test
    @DisplayName("Grab contact from database")
    public void grabContactFromDatabaseTest() {
        Contact contact = generateContact();
        System.out.println(contact);
        cs.saveContact(contact);
        Contact grabbedContact = cs.grabContact(1);
        Assertions.assertNotNull(grabbedContact);
    }

    @Test
    @DisplayName("Grab multiple contacts from database")
    public void insertMultipleContactsTest() {
        List<Contact> contacts = generateContacts();
        for (Contact contact : contacts) {
            cs.saveContact(contact);
        }
        Assertions.assertNotEquals(0,  cs.grabContacts().size());
    }

    @Test
    @DisplayName("Update contact from database")
    public void updateContactFromDatabaseTest() {
        Contact contact = generateContact();
        cs.saveContact(contact);
        Contact updateContact = new Contact("Cartethiya", "cartethiya@mail", "0000000001", "Inverted Tower");
        cs.editContact(1, contact.getName(),  contact.getEmail(), contact.getPhone(), contact.getAddress());
        Assertions.assertNotEquals(contact.getName(), updateContact.getName() );
    }

    @Test
    @DisplayName("Delete contact from database")
    public void deleteContactFromDatabaseTest() {
        cs.saveContact(generateContact());
        cs.deleteContact(1);
        Assertions.assertNull(cs.grabContact(1));
    }

    @AfterEach
    public void testCleanUp() {
        try {
            Database.dropContactsTable();
            System.out.println("[SUCCESS] Table contact dropped successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
