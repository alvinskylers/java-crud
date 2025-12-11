package alvinskylers.contactbook.utils;

import alvinskylers.contactbook.config.Database;
import java.sql.SQLException;

public class ContactBook {
    private final UserInterface ui;
    private final ContactManager contactManager;

    public ContactBook() {
        this.ui = new UserInterface();
        this.contactManager = new ContactManager();
    }

    private void startup() {
        try {
            if (!Database.doesTableExist("contacts")) {
                Database.generateContactsTable();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void start() {
        startup();
        while (true) {
            switch (ui.printMainMenu()) {
                case 1 : contactManager.addContact(ui.printAddContact()); break;
                case 2 : contactManager.updateContact(ui.printUpdateContact()); break;
                case 3 : ui.printViewContacts(contactManager.getContacts()); break;
                case 4 : ui.printViewContacts(ui.printSearchContact()); break;
                case 5 : contactManager.deleteContact(ui.printDeleteContact()); break;
                case 0: System.exit(0);
            }
        }
    }

}
