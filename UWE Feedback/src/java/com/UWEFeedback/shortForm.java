/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UWEFeedback;

import com.UWEFeedback.models.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Harry
 */
public class shortForm extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher view = request.getRequestDispatcher("shortForm.jsp");
        view.forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            Connection con = DriverManager.getConnection(DBConnection.HOST, DBConnection.USER, DBConnection.PASS);

            String sql = String.format("SELECT * FROM STUDENTS WHERE \"STUDENTID\" = '%s'", request.getSession().getAttribute("USERNAME").toString());
            System.out.println(request.getSession().getAttribute("USERNAME"));
            
            PreparedStatement pstate = con.prepareStatement(sql);
            ResultSet result = pstate.executeQuery();
            String mStr = null;
            while(result.next()){
               mStr = result.getString("MODULES"); 
            }
            
            String[] moduleids = mStr.split(",");
            ArrayList<Module> modules = new ArrayList();
            ArrayList<Staff> staff = new ArrayList();
            

            for (String m : moduleids) {
                sql = String.format("SELECT * FROM MODULE WHERE \"MODULEID\" = '%s'", m);
                pstate = con.prepareStatement(sql);
                result = pstate.executeQuery();
                Module temp = null;
                String listStaff = null;
                while(result.next()){
                    temp = new Module(m, result.getString("TITLE"));
                    listStaff = result.getString("STAFF");
                }
                
                
                String[] mStaff = listStaff.split(",");

                for (String i : mStaff) {
                    temp.addStaff(i);
                    String sqlStaff = String.format("SELECT * FROM STAFF WHERE \"STAFFID\" = '%s'", i);
                    pstate = con.prepareStatement(sqlStaff);
                    result = pstate.executeQuery();
                    while(result.next()){
                        staff.add(new Staff(result.getString("FIRSTNAME"), result.getString("LASTNAME"), result.getString("STAFFID")));
                    }
                    
                }

                modules.add(temp);

            }
            
            ArrayList<String> modulesString = new ArrayList();
            ArrayList<String> staffString = new ArrayList();
            
            for(Module m : modules){
                modulesString.add(m.getTitle());
            }
            for(Staff s : staff){
                staffString.add(s.getFirstName() + " " + s.getLastName());
            }

            Set<String> staffhash = new HashSet();
            staffhash.addAll(staffString);
            
            System.out.println(modules);

            request.setAttribute("moduleList", modulesString.toArray());
            request.setAttribute("staffList", staffhash.toArray());

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(shortForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
