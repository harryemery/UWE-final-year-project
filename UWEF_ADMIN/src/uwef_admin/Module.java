/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uwef_admin;

import java.util.ArrayList;

/**
 *
 * @author Harry
 */
public class Module {
    String ID;
    String Title;
    String StaffA, StaffB, StaffC;

    public Module(String ID, String Title, String StaffA, String StaffB, String StaffC) {
        this.ID = ID;
        this.Title = Title;
        this.StaffA = StaffA;
        this.StaffB = StaffB;
        this.StaffC = StaffC;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getStaffA() {
        return StaffA;
    }

    public void setStaffA(String StaffA) {
        this.StaffA = StaffA;
    }

    public String getStaffB() {
        return StaffB;
    }

    public void setStaffB(String StaffB) {
        this.StaffB = StaffB;
    }

    public String getStaffC() {
        return StaffC;
    }

    public void setStaffC(String StaffC) {
        this.StaffC = StaffC;
    }

    @Override
    public String toString() {
        return ID + " " + Title;
    }

    

   
    
    

}
