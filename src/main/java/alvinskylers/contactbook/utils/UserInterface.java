package alvinskylers.contactbook.utils;

import alvinskylers.contactbook.entity.Contact;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner userInput;
    private final ContactManager contactManager;

    public UserInterface() {
        userInput = new Scanner(System.in);
        contactManager = new ContactManager();
    }

    public int printMainMenu() {
        while (true){
            System.out.println();
            System.out.println("*** ========== Contact Management System ========== ***");
            System.out.println("1. Add Contact");
            System.out.println("2. Update Contact");
            System.out.println("3. View Contacts");
            System.out.println("4. Search Contacts");
            System.out.println("5. Delete Contacts");
            System.out.println("0. exit.");
            System.out.println();
            System.out.print("Enter your choice: ");
            try {
                int choice = userInput.nextInt();
                userInput.nextLine();
                if (choice <= 5 && choice >= 0) {
                    return choice;
                }
                System.out.println("Invalid choice.");
            } catch (Exception e) {
                System.out.println("input was not a number.");
                userInput.nextLine();
            }
        }
    }

    public Contact printAddContact() {
        System.out.println();
        System.out.println("** ========== Add a Contact =========== **");
        while (true) {
            String name = insertName();
            String phone = insertPhone();
            String email = insertEmail();
            String address = insertAddress();
            System.out.println("*** Contact Preview ***");
            System.out.println("Name: " + name);
            System.out.println("Phone: " + phone);
            System.out.println("Email: " + email);
            System.out.println("Address: " + address);
            while (true) {
                System.out.print("Save this contact? y/n: ");
                String answer = userInput.next();
                switch (answer.toLowerCase()) {
                    case "y": return new Contact(name, phone, email, address);
                    case "n": System.out.println("Contact was not added"); break;
                    default:
                        System.out.println("Invalid choice"); continue;
                }
                break;
            }
        }
    }

    public Contact printUpdateContact() {
        String newName;
        String newPhone;
        String newEmail;
        String newAddress;

        System.out.println();
        System.out.println("** ========== View Contacts =========== **");
        System.out.print("Contact ID to update: ");
        try {
            int contactId =  userInput.nextInt();
            userInput.nextLine();
            Contact currentContact = contactManager.getContact(contactId);
            String name = currentContact.getName();
            String phone = currentContact.getPhone();
            String email = currentContact.getEmail();
            String address = currentContact.getAddress();

            System.out.println("leave field empty to preserve current data");
            System.out.print("Name: ");
            newName = userInput.nextLine();
            System.out.print("Phone: ");
            newPhone = userInput.nextLine();
            System.out.print("Email: ");
            newEmail = userInput.nextLine();
            System.out.print("Address: ");
            newAddress = userInput.nextLine();

            if (newName.isEmpty()) {
                newName = name;
            }

            if (newPhone.isEmpty()) {
                newPhone = phone;
            }

            if (newEmail.isEmpty()) {
                newEmail = email;
            }

            if (newAddress.isEmpty()) {
                newAddress = address;
            }

            System.out.println("Update preview");
            System.out.println("Name: " + newName);
            System.out.println("Phone: " + newPhone);
            System.out.println("Email: " + newEmail);
            System.out.println("Address: " + newAddress);
            System.out.print("Save this contact? y/n: ");
            String answer = userInput.next();
            userInput.nextLine();
            switch (answer.toLowerCase()) {
                case "y" :  return new Contact(contactId, newName, newPhone, newEmail, newAddress);
                case "n" : System.out.println("Contact was not updated!"); break;
                default: System.out.println("Invalid choice");
            }
        } catch (Exception e) {
            System.out.println("That is not a valid contact ID!");
            userInput.nextLine();
        }

        return null;
    }

    public Contact printDeleteContact() {
        int id;
        while (true) {
            System.out.println("** ========== Delete Contact =========== **");
            System.out.println("Check contact id at \"the View Contacts\" menu");
            System.out.print("Delete contact by id: ");
            try {
                id = userInput.nextInt();
                userInput.nextLine();
                if (contactManager.doesContactIDExist(id)) {
                    return contactManager.getContact(id);
                } else {
                    System.out.println("a contact with that id does not exist!");
                    userInput.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Invalid contact id!");
                userInput.nextLine();
            }
        }
    }

    public List<Contact> printSearchContact() {
        System.out.println();
        System.out.println("** ========== Search Contacts =========== **");
        System.out.println("Search contact via: ");
        System.out.println("1. Name");
        System.out.println("2. Email");
        System.out.println("3. Phone");
        System.out.println("4. Address");
        System.out.print("Enter your choice: ");
        try {
            int choice = userInput.nextInt();
            userInput.nextLine();
            switch (choice) {
                case 1:  return contactManager.filterContactsByName(insertQuery());
                case 2:  return contactManager.filterContactsByEmail(insertQuery());
                case 3:  return contactManager.filterContactsByPhone(insertQuery());
                case 4:  return contactManager.filterContactsByAddress(insertQuery());
                default:System.out.println("Invalid choice"); break;
            }
        } catch (Exception e) {
            System.out.println("Not a valid choice");
            userInput.nextLine();
        }
        return null;
    }

    public void printViewContacts(List<Contact> contacts) {

        System.out.println("** ========== View Contacts =========== **");
        if (contacts.isEmpty() || contacts == null) {
            System.out.println("No contacts found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int pageSize = 5;
        int totalPages = (int) Math.ceil((double) contacts.size() / pageSize);
        int currentPage = 0;

        while (true) {
            System.out.println("\n=== Page " + (currentPage + 1) + " of " + totalPages + " ===");

            contacts.stream()
                    .skip(currentPage * pageSize)
                    .limit(pageSize)
                    .forEach(System.out::println);

           System.out.print("\n");
            if (currentPage > 0 && currentPage < totalPages - 1) {
                System.out.print("[p] Previous | [n] Next | [q] Quit: ");
            } else if (currentPage == 0 && totalPages > 1) {
                System.out.print("[n] Next | [q] Quit: ");
            } else if (currentPage == totalPages - 1 && totalPages > 1) {
                System.out.print("[p] Previous | [q] Quit: ");
            } else {
                System.out.print("[q] Quit: ");
            }

            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("n") && currentPage < totalPages - 1) {
                currentPage++;
            } else if (input.equals("p") && currentPage > 0) {
                currentPage--;
            } else if (input.equals("q")) {
                break;
            } else {
                System.out.println("Invalid input!");
            }
        }
    }

    private String insertQuery() {
        System.out.print("Insert query: ");
        return userInput.nextLine();
    }

    private String insertName() {
        System.out.print("Insert name: ");
        return userInput.nextLine();
    }

    private String insertAddress() {
        System.out.print("Insert address: ");
        return userInput.nextLine();
    }

    private String insertPhone() {
        String input;
        String phoneRegex = "^\\d{8,}$";
        while (true) {
            System.out.print("insert phone number: ");
            input = this.userInput.nextLine();
            if (!input.matches(phoneRegex)) {
                System.out.println("invalid phone number!");
            } else {
                if (contactManager.doesPhoneExist(input)) {
                    System.out.println("Contact with such phone number already exists!");
                }
            }

            if (!contactManager.doesPhoneExist(input) && input.matches(phoneRegex)) {
                return input;
            }
        }
    }

    private String insertEmail() {
        String input;
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        while (true) {
            System.out.print("insert email: ");
            input = this.userInput.nextLine();
            if (!input.matches(emailRegex)) {
                System.out.println("invalid email!");
            } else {
                if (contactManager.doesEmailExist(input)) {
                    System.out.println("Contact with such email already exists!");
                }
            }

            if (!contactManager.doesEmailExist(input) && input.matches(emailRegex)) {
                return input;
            }
        }
    }

}
