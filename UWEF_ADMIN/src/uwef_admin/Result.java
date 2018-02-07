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
public class Result {
    String ModuleID;
    String Staff;
    int MinsLate;
    Boolean Engaging;
    Boolean Materials;
    Boolean LValue;
    int LectureScore;
    int LecturerScore;
    Date date;

    public Result(String ModuleID, String Staff, int MinsLate, Boolean Engaging, Boolean Materials, Boolean LValue, int LectureScore, int LecturerScore, Date date) {
        this.ModuleID = ModuleID;
        this.Staff = Staff;
        this.MinsLate = MinsLate;
        this.Engaging = Engaging;
        this.Materials = Materials;
        this.LValue = LValue;
        this.LectureScore = LectureScore;
        this.LecturerScore = LecturerScore;
        this.date = date;
    }

    public String getModuleID() {
        return ModuleID;
    }

    public void setModuleID(String ModuleID) {
        this.ModuleID = ModuleID;
    }

    public String getStaff() {
        return Staff;
    }

    public void setStaff(String Staff) {
        this.Staff = Staff;
    }

    public int getMinsLate() {
        return MinsLate;
    }

    public void setMinsLate(int MinsLate) {
        this.MinsLate = MinsLate;
    }

    public Boolean getEngaging() {
        return Engaging;
    }

    public void setEngaging(Boolean Engaging) {
        this.Engaging = Engaging;
    }

    public Boolean getMaterials() {
        return Materials;
    }

    public void setMaterials(Boolean Materials) {
        this.Materials = Materials;
    }

    public Boolean getLValue() {
        return LValue;
    }

    public void setLValue(Boolean LValue) {
        this.LValue = LValue;
    }

    public int getLectureScore() {
        return LectureScore;
    }

    public void setLectureScore(int LectureScore) {
        this.LectureScore = LectureScore;
    }

    public int getLecturerScore() {
        return LecturerScore;
    }

    public void setLecturerScore(int LecturerScore) {
        this.LecturerScore = LecturerScore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Result{" + "ModuleID=" + ModuleID + ", Staff=" + Staff + ", MinsLate=" + MinsLate + ", Engaging=" + Engaging + ", Materials=" + Materials + ", LValue=" + LValue + ", LectureScore=" + LectureScore + ", LecturerScore=" + LecturerScore + ", date=" + date + '}';
    }

    
}