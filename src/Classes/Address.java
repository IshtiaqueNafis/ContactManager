package Classes;

import interfaces.UpperCapableLetter;
import javafx.beans.property.SimpleStringProperty;

public class Address {
    public SimpleStringProperty  streetinfo1;
    public  SimpleStringProperty getStreetinfo2;
    public SimpleStringProperty  city;
    public SimpleStringProperty  province;
    public SimpleStringProperty  country;

    public Address(String streetinfo1, String getStreetinfo2, String city, String province, String country) {
        this.streetinfo1 = new SimpleStringProperty(streetinfo1);
        this.getStreetinfo2 = new SimpleStringProperty(getStreetinfo2);
        this.city = new SimpleStringProperty(city);
        this.province = new SimpleStringProperty(province);
        this.country = new SimpleStringProperty(country);
    }

    //setter and getter
    public String getStreetinfo1() {
        return streetinfo1.get();
    }

    public SimpleStringProperty streetinfo1Property() {
        return streetinfo1;
    }

    public void setStreetinfo1(String streetinfo1) {

        this.streetinfo1 = new SimpleStringProperty(streetinfo1);
    }

    public String getGetStreetinfo2() {
        return getStreetinfo2.get();
    }

    public SimpleStringProperty getStreetinfo2Property() {
        return getStreetinfo2;
    }

    public void setGetStreetinfo2(String getStreetinfo2) {

        this.getStreetinfo2 = new SimpleStringProperty(getStreetinfo2);
    }

    public String getCity() {
        return city.get();
    }

    public SimpleStringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city = new SimpleStringProperty(city);
    }

    public String getProvince() {
        return province.get();
    }

    public SimpleStringProperty provinceProperty() {
        return province;
    }

    public void setProvince(String province) {
        this.province = new SimpleStringProperty(province);
    }

    public String getCountry() {
        return country.get();
    }

    public SimpleStringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country = new SimpleStringProperty(country);
    }

    @Override
    public String toString() {
     return getStreetinfo1()+","+getGetStreetinfo2()+","+getCity()+','+getProvince()+","+getCountry();

    }


}
