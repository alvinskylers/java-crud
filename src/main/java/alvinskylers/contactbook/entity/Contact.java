package alvinskylers.contactbook.entity;

public class Contact {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;

    public Contact(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Contact(int id, String name, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString(){
        String header = "=== CONTACT DETAILS ===\n";
        String id = this.id < 10 ? "[0" + this.id + "] " : "[" + this.id + "] ";
        return header + id + this.name + "\nphone: " +this.phone + "\nemail: " +this.email + "\naddress: " +this. address;
    }

}
