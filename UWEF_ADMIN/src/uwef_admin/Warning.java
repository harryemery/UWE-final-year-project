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
public class Warning {
    String ID;
    String Warning;
    String Date;

    public Warning(String ID, String Warning, String Date) {
        this.ID = ID;
        this.Warning = Warning;
        this.Date = Date;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getWarning() {
        return Warning;
    }

    public void setWarning(String Warning) {
        this.Warning = Warning;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    @Override
    public String toString() {
        return ID + "   " + Warning + "   " + Date;
    }
    
    
}
