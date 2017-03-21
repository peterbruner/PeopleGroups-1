package com.theironyard.novauc.entities;

import javax.persistence.*;

/**
 * Created by Merlin on 3/17/17.
 */
@Entity
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String address;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String employment;

    @Column(nullable = false)
    String education;


    public Person() {
    }

    public Person(String name, String address, String email, String employment, String education) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.employment = employment;
        this.education = education;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getEmployment() {return employment;}

    public void setEmployment(String employment) {this.employment = employment;}

    public String getEducation() {return education;}

    public void setEducation(String education) {this.education = education;}
}
