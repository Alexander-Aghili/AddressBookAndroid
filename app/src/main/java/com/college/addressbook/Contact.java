package com.college.addressbook;

import java.io.Serializable;

public class Contact implements Serializable {

    private String firstName;

    public Contact(String firstName){
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String toString() {
        return firstName;
    }
}
