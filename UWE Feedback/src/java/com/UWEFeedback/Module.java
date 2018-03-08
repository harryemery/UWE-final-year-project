/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UWEFeedback;

import java.util.ArrayList;

/**
 *
 * @author Harry
 */
public class Module {
    String ID;
    String title;
    ArrayList<String> Staff = new ArrayList();

    public Module(String ID, String title) {
        this.ID = ID;
        this.title = title;
        
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getStaff() {
        return Staff;
    }

    public void setStaff(ArrayList<String> Staff) {
        this.Staff = Staff;
    }
    
    public void addStaff(String staffMember){
        Staff.add(staffMember);
    }
    
}
