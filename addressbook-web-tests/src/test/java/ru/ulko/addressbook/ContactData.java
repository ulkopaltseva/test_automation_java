package ru.ulko.addressbook;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String homePhone;
    private final String mobilePhone;
    private final String workPhone;
    private final String faxPhone;
    private final String email1;
    private final String email2;
    private final String email3;

    public ContactData(String firstName, String lastName, String address, String homePhone, String mobilePhone, String workPhone, String faxPhone, String email1, String email2, String email3) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.faxPhone = faxPhone;
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getFaxPhone() {
        return faxPhone;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }
}
