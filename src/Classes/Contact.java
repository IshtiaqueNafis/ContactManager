package Classes;

import interfaces.UpperCapableLetter;
import interfaces.phoneNumberFormat;
import javafx.beans.property.SimpleStringProperty;

public class Contact implements UpperCapableLetter, phoneNumberFormat {
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty phone;
    private SimpleStringProperty email;
    private Address homeAddress; //passing reference of homeaddress
    private MyDate date;
    private SimpleStringProperty notes;

    // constructor
    public Contact(String firstName, String lastName, String phone, String email, Address homeAddress, MyDate date, String notes) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.homeAddress = homeAddress;
        this.date = date;
        this.notes = new SimpleStringProperty(notes);
    }

// setter and getting

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName); // changed to simple string property

    }



    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName); // chagned to simple string property
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set( returnedNUmberPhone(phone)); //changed phone number to canaadian format
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public MyDate getDate() {
        return date;
    }

    public void setDate(MyDate date) {
        this.date = date;
    }

    public String getNotes() {
        return notes.get();
    }

    public SimpleStringProperty notesProperty() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }


    // to string method this will return the
    @Override
    public String toString() {
        return getFirstName()+","+getLastName()+","+getEmail()+","+getPhone()+","+homeAddress+","+date+","+getNotes();
    }

//interfaces
    @Override
    public String uppercaseFirstLetter(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);

    }

    @Override // this will format the phone to 416-832-4619 format
    public String returnedNUmberPhone(String phone) {
        return phone.charAt(0)+phone.charAt(1)+phone.charAt(2)+"-"+phone.charAt(3)+phone.charAt(4)+phone.charAt(5)+"-"+phone.charAt(6)+phone.charAt(7)+phone.charAt(8)+phone.charAt(9);
    }


}
