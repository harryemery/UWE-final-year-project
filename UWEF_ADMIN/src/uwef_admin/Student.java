/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uwef_admin;

/**
 *
 * @author Harry
 */
public class Student {
    String ID;
    String FirstName;
    String LastName;
    Boolean Banned;

    public Student(String ID, String FirstName, String LastName, Boolean Banned) {
        this.ID = ID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Banned = Banned;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public Boolean getBanned() {
        return Banned;
    }

    public void setBanned(Boolean Banned) {
        this.Banned = Banned;
    }

    @Override
    public String toString() {
        return "Student{" + "ID=" + ID + ", FirstName=" + FirstName + ", LastName=" + LastName + ", Banned=" + Banned + '}';
    }

   
    
    
    
}
