package com.titzko.testingThings.stax.application.model;

import javax.persistence.*;


@Entity
public class Address {

    @SequenceGenerator(
            name="addresse_sequence",
            sequenceName = "addresse_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "addresse_sequence"
    )
    @Id
    Long id;
    String name;
    String street;
    String phoneNumber;


    public Address() {
    }

    public Address(String name, String street, String phoneNumber) {
        this.name = name;
        this.street = street;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumer) {
        this.phoneNumber = phoneNumer;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", phoneNumer=" + phoneNumber +
                '}';
    }
}
