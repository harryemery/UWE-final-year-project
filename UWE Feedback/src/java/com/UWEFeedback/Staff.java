/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UWEFeedback;

/**
 *
 * @author Harry
 */
public class Staff {
    String firstName;
    String lastName;
    String staffID;

    public Staff(String firstName, String lastName, String staffID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.staffID = staffID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
    
    
    
}
