/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uwef_admin;

import java.sql.Date;

/**
 *
 * @author Harry
 */
public class DateCount {
    Date date;
    int count;

    public DateCount(Date date, int count) {
        this.date = date;
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "DateCount{" + "date=" + date + ", count=" + count + '}';
    }
    
    
}
