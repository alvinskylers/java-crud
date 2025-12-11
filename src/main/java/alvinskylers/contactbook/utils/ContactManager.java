package alvinskylers.contactbook.utils;

import alvinskylers.contactbook.entity.Contact;
import alvinskylers.contactbook.service.ContactService;

import java.util.List;
import java.util.stream.Collectors;

public class ContactManager {
    private final ContactService contactService;

    public ContactManager() {
        this.contactService = new ContactService();
    }

    public Contact getContact(int id) {
        return contactService.grabContact(id);
    }

    public List<Contact> getContacts() {
        return contactService.grabContacts();
    }

    public void addContact(Contact contact) {
        if (contact == null)
            return;
        contactService.saveContact(contact);
    }

    public void deleteContact(Contact contact) {
        if (contact == null)
            return;
        contactService.deleteContact(contact.getId());
    }

    public void updateContact(Contact contact) {
        if (contact == null)
            return;
        contactService.editContact(
                contact.getId(),
                contact.getName(),
                contact.getPhone(),
                contact.getEmail(),
                contact.getAddress()
        );
    }

    public List<Contact> filterContactsByName(String query) {
        return getContacts().stream()
                .filter(contact -> contact.getName().toLowerCase().contains(query))
                .collect(Collectors.toList());
    }

    public List<Contact> filterContactsByPhone(String query) {
        return getContacts().stream()
                .filter(contact -> contact.getPhone().contains(query))
                .collect(Collectors.toList());
    }

    public List<Contact> filterContactsByEmail(String query) {
        return getContacts().stream()
                .filter(contact -> contact.getEmail().toLowerCase().contains(query))
                .collect(Collectors.toList());
    }

    public  List<Contact> filterContactsByAddress(String query) {
        return getContacts().stream()
                .filter(contact -> contact.getAddress().toLowerCase().contains(query))
                .collect(Collectors.toList());
    }

    public boolean doesEmailExist(String email){
        return getContacts().stream().anyMatch(contact -> contact.getEmail().toLowerCase().contains(email.toLowerCase()));
    }

    public boolean doesPhoneExist(String phone){
        return getContacts().stream().anyMatch(contact -> contact.getPhone().toLowerCase().contains(phone.toLowerCase()));
    }

    public boolean doesContactIDExist(int id){
        return contactService.grabContacts().stream().anyMatch(contact -> contact.getId() == id);
    }


}
