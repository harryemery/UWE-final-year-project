/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uwef_admin;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Harry
 */
public class Staff {

    String StaffID;
    String FirstName;
    String SecondName;
    //ArrayList<Module> modules = new ArrayList();
    ObservableList modules = FXCollections.observableArrayList();

    public Staff(String StaffID, String FirstName, String SecondName) {
        this.StaffID = StaffID;
        this.FirstName = FirstName;
        this.SecondName = SecondName;
    }

    public String getStaffID() {
        return StaffID;
    }

    public void setStaffID(String StaffID) {
        this.StaffID = StaffID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getSecondName() {
        return SecondName;
    }

    public void setSecondName(String SecondName) {
        this.SecondName = SecondName;
    }

    public ObservableList getModules() {
        return modules;
    }

    public void addModule(Module mod) {
        modules.add(mod);
    }

    public void setModules(ObservableList modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return StaffID + " " + FirstName + " " + SecondName;
    }

}
