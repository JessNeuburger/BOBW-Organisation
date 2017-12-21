/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htl.florianschwarcz.organisationalstructurelib;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Florian Schwarcz
 */
public class Person {
    private String lastName;
    private String firstName;
    private Date birthDate;
    private String birthCity;
    private String street;
    private int number;
    private String city;
    private String zipCode;
    private String email;
    private String socialSecurityNumber;
    private Profile profile;

    public Person(String lastName, String firstName, Date birthDate, String birthCity, String street, int number, String city, String zipCode, String email, String socialSecurityNumber, Profile profile) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.birthCity = birthCity;
        this.street = street;
        this.number = number;
        this.city = city;
        this.zipCode = zipCode;
        this.email = email;
        this.socialSecurityNumber = socialSecurityNumber;
        this.profile = profile;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
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
