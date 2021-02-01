package Classes;

public class ContactManager {

    private static int IDGenarator=0; // ths will be used as key to modify, delete or edit data
    private Contact contact;
    private int contactID;


    public ContactManager(Contact contact) {
        IDGenarator++; // each time object is created it will go up by one
        this.contact = contact;
        this.contactID=IDGenarator;
    }
    // getter and setter
    public int getContactID() {
        return contactID;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return  contact.toString();
    }
}
