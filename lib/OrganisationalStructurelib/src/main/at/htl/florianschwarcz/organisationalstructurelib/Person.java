/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htl.florianschwarcz.organisationalstructurelib;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Florian Schwarcz
 */
public class Person{

    private SimpleStringProperty lastName;
    private SimpleStringProperty firstName;
    private Date birthDate;

    private SimpleStringProperty birthCity;
    private SimpleStringProperty street;
    private SimpleStringProperty number;

    private SimpleStringProperty city;
    private SimpleStringProperty zipCode;

    private SimpleStringProperty email;
    private SimpleStringProperty socialSecurityNumber;
    private Profile profile;

    private Position position;

    public Person(String lastName, String firstName, Date birthDate, String birthCity, String street, String number, String city, String zipCode, String email, String socialSecurityNumber, Profile profile) {
        this(lastName, firstName, birthDate, birthCity, street, number, city, zipCode, email, socialSecurityNumber);
        this.profile = profile;
    }

    public Person(String lastName, String firstName, Date birthDate, String birthCity, String street, String number, String city, String zipCode, String email, String socialSecurityNumber) {
        this.lastName = new SimpleStringProperty(lastName);
        this.firstName = new SimpleStringProperty(firstName);
        this.birthDate = birthDate;
        this.birthCity = new SimpleStringProperty(birthCity);
        this.street = new SimpleStringProperty(street);
        this.number = new SimpleStringProperty(number);
        this.city = new SimpleStringProperty(city);
        this.zipCode = new SimpleStringProperty(zipCode);
        this.email = new SimpleStringProperty(email);
        this.socialSecurityNumber = new SimpleStringProperty(socialSecurityNumber);
    }

    public Position getPosition() {
        return position;
    }

    void setPosition(Position position) {
        this.position = position;
    }

    public SimpleStringProperty birthCityProperty() {
        return birthCity;
    }

    public SimpleStringProperty cityProperty() {
        return city;
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public SimpleStringProperty socialSecurityNumberProperty() {
        return socialSecurityNumber;
    }

    public SimpleStringProperty streetProperty() {
        return street;
    }

    public SimpleStringProperty zipCodeProperty() {
        return zipCode;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getBirthCity() {
        return birthCity.get();
    }

    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getNumber() {
        return number.get();
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.setValue(city);
    }

    public String getZipCode() {
        return zipCode.get();
    }

    public void setZipCode(String zipCode) {
        this.zipCode.setValue(zipCode);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber.get();
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber.set(socialSecurityNumber);
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public int getDBKey() {

        return Objects.hash(getLastName(), getFirstName(), getBirthDate(), getBirthCity(), getStreet(), getNumber(), getCity(), getZipCode(), getEmail(), getSocialSecurityNumber());
    }
}
