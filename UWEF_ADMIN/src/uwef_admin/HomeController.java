/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uwef_admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Harry
 */
public class HomeController implements Initializable {

    @FXML
    private Button btnLogout;
    @FXML
    private Label lblUsername;
    @FXML
    private Button btnWarnings;
    @FXML
    private Button btnBan;
    @FXML
    private Button btnUnban;
    @FXML
    private Button btnBanned;
    @FXML
    private TableView<?> tblStudents;
    @FXML
    private Label lblStudentTitle;
    @FXML
    private TextArea txtEditor;
    @FXML
    private MenuBar menuBar;
    @FXML
    private WebView webView;
    @FXML
    private Button btnShortSurvey;
    @FXML
    private Button btnLongSurvey;
    @FXML
    private ListView<?> lstLecturer;
    @FXML
    private ComboBox<?> comboModule;
    @FXML
    private PieChart chrtTimeBar;
    @FXML
    private LineChart<?, ?> chrtEngageLine;
    @FXML
    private PieChart chrtEngagePie;
    @FXML
    private PieChart chrtLecturePie;
    @FXML
    private LineChart<?, ?> chrtLectureLine;
    @FXML
    private LineChart<?, ?> chrtLecturerLine;
    @FXML
    private PieChart chrtLecturerPie;
    @FXML
    private ListView<?> lstOther;
    @FXML
    private TitledPane titleStudents;
    @FXML
    private Accordion accPane;
    @FXML
    private Button btnViewSelected;
    @FXML
    private Label lblLatest;
    @FXML
    private PieChart chrtMaterials;
    @FXML
    private PieChart chrtValue;
    //private String username = "Username";
    private ObservableList warnings = FXCollections.observableArrayList();
    private ObservableList banned = FXCollections.observableArrayList();
    private ObservableList staff = FXCollections.observableArrayList();
    private ObservableList modules = FXCollections.observableArrayList();
   

    String loc = "C:\\Users\\Harry\\Documents\\NetBeansProjects\\UWE Feedback\\web\\shortForm.jsp";

    private Staff selectedStaff = null;

    public void setUser(String s) {
        this.lblUsername.setText(s);
    }

    private void loadWarningList() throws SQLException {
        Connection con = DriverManager.getConnection(DBController.HOST, DBController.USER, DBController.PASS);
        String sql = "SELECT * FROM WARNINGS";
        PreparedStatement pstate = con.prepareStatement(sql);
        ResultSet result = pstate.executeQuery();

        while (result.next()) {
            Warning temp = new Warning(result.getString("STUDENTID"), result.getString("WARNING"), result.getString("DATE"));
            warnings.add(temp);
        }

        TableColumn IDCol = new TableColumn("Student ID");
        TableColumn WarCol = new TableColumn("Warning");
        TableColumn DateCol = new TableColumn("Date Issued");

        IDCol.setCellValueFactory(new PropertyValueFactory("ID"));
        WarCol.setCellValueFactory(new PropertyValueFactory("Warning"));
        DateCol.setCellValueFactory(new PropertyValueFactory("Date"));

        tblStudents.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        IDCol.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        WarCol.setMaxWidth(1f * Integer.MAX_VALUE * 60);
        DateCol.setMaxWidth(1f * Integer.MAX_VALUE * 20);

        tblStudents.getItems().clear();
        tblStudents.getColumns().clear();

        tblStudents.setItems(warnings);
        tblStudents.getColumns().addAll(IDCol, WarCol, DateCol);

        tblStudents.refresh();

        con.close();

    }

    private void loadBannedList() throws SQLException {
        Connection con = DriverManager.getConnection(DBController.HOST, DBController.USER, DBController.PASS);
        String sql = "SELECT * FROM STUDENTS WHERE BANNED='TRUE'";
        PreparedStatement pstate = con.prepareStatement(sql);
        ResultSet result = pstate.executeQuery();

        while (result.next()) {
            Student temp = new Student(result.getString("STUDENTID"), result.getString("FIRSTNAME"), result.getString("LASTNAME"), result.getBoolean("BANNED"));
            banned.add(temp);
        }

        TableColumn IDCol = new TableColumn("Student ID");
        TableColumn fnCol = new TableColumn("FIRSTNAME");
        TableColumn lnCol = new TableColumn("LASTNAME");
        TableColumn bCol = new TableColumn("BANNED");

        IDCol.setCellValueFactory(new PropertyValueFactory("ID"));
        fnCol.setCellValueFactory(new PropertyValueFactory("FirstName"));
        lnCol.setCellValueFactory(new PropertyValueFactory("LastName"));
        bCol.setCellValueFactory(new PropertyValueFactory("Banned"));

        tblStudents.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        IDCol.setMaxWidth(1f * Integer.MAX_VALUE * 25);
        fnCol.setMaxWidth(1f * Integer.MAX_VALUE * 25);
        lnCol.setMaxWidth(1f * Integer.MAX_VALUE * 25);
        bCol.setMaxWidth(1f * Integer.MAX_VALUE * 25);

        tblStudents.getItems().clear();
        tblStudents.getColumns().clear();

        tblStudents.setItems(banned);
        tblStudents.getColumns().addAll(IDCol, fnCol, lnCol, bCol);

        tblStudents.refresh();

        con.close();
    }

    @FXML
    private void saveSurvey(ActionEvent event) {

        try {
            PrintStream p = new PrintStream(new File(loc));
            p.print(txtEditor.getText());
            webView.getEngine().reload();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void loadShortSurvey() throws FileNotFoundException {

        txtEditor.clear();

        Scanner s = new Scanner(new File(loc));
        while (s.hasNextLine()) {
            txtEditor.appendText(s.nextLine() + "\n");

        }

        WebEngine we = webView.getEngine();
        // we.setUserAgent();
        we.executeScript("window.location = \"http://localhost:8080/UWE%20Feedback/shortForm.jsp\";");

    }

    void loadLongSurvey() throws FileNotFoundException {
        String loc = "C:\\Users\\Harry\\Documents\\NetBeansProjects\\UWE Feedback\\web\\longForm.jsp";

        txtEditor.clear();

        Scanner s = new Scanner(new File(loc));
        while (s.hasNextLine()) {
            txtEditor.appendText(s.nextLine() + "\n");

        }

        WebEngine we = webView.getEngine();
        // we.setUserAgent();
        we.executeScript("window.location = \"http://localhost:8080/UWE%20Feedback/longForm.jsp\";");
        txtEditor.selectPositionCaret(0);
    }

    void loadLecturers() throws SQLException {
        Connection con = DriverManager.getConnection(DBController.HOST, DBController.USER, DBController.PASS);
        String sql = "SELECT * FROM STAFF";
        PreparedStatement pstate = con.prepareStatement(sql);
        ResultSet result = pstate.executeQuery();

        while (result.next()) {
            Staff temp = new Staff(result.getString("STAFFID"), result.getString("FIRSTNAME"), result.getString("LASTNAME"));
            staff.add(temp);
        }
        con.close();
        lstLecturer.setItems(staff);
        System.out.println("staff loaded");
    }

    void fillComboModules() throws SQLException {
        Connection con = DriverManager.getConnection(DBController.HOST, DBController.USER, DBController.PASS);
        Staff temp = (Staff) lstLecturer.getSelectionModel().getSelectedItem();
        selectedStaff = temp;
        PreparedStatement pstate2;
        ResultSet result2;
        String sqlm = "SELECT * FROM MODULE WHERE STAFF1='" + temp.getStaffID() + "' OR STAFF2='" + temp.getStaffID() + "' OR STAFF3='" + temp.getStaffID() + "'";
        pstate2 = con.prepareStatement(sqlm);
        result2 = pstate2.executeQuery();

        while (result2.next()) {
            Module m = new Module(result2.getString("MODULEID"), result2.getString("TITLE"), result2.getString("Staff1"), result2.getString("Staff2"), result2.getString("Staff3"));

            System.out.println(m);
            modules.add(m);

        }
        comboModule.getItems().clear();
        comboModule.setItems(modules);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accPane.setExpandedPane(titleStudents);
        try {
            loadWarningList();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Could not load database");
        }

        try {
            loadShortSurvey();
        } catch (IOException ex) {
            System.out.println("Could not load survey");
        }

        try {
            loadLecturers();
        } catch (SQLException ex) {
            System.out.println("Could not load lecturers");
        }

        btnWarnings.setDisable(true);
        btnUnban.setDisable(true);
        btnBan.setDisable(false);
        btnBanned.setDisable(false);
    }

    @FXML
    private void viewWarnings(ActionEvent event) {
        try {
            loadWarningList();

        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        btnWarnings.setDisable(true);
        btnUnban.setDisable(true);
        btnBan.setDisable(false);
        btnBanned.setDisable(false);
    }

    @FXML
    private void banStudent(ActionEvent event) {
        try {
            if (tblStudents.getSelectionModel().getSelectedItem() != null) {
                Warning w = (Warning) tblStudents.getSelectionModel().getSelectedItem();

                String ID = w.getID();
                Connection con = DriverManager.getConnection(DBController.HOST, DBController.USER, DBController.PASS);
                String sql = "UPDATE STUDENTS SET BANNED='TRUE' WHERE STUDENTID='" + ID + "'";
                PreparedStatement pstate = con.prepareStatement(sql);
                pstate.executeUpdate();
                System.out.println("Banned");
                con.close();

                tblStudents.refresh();

            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void unBanStudent(ActionEvent event) {
        try {
            Student s = (Student) tblStudents.getSelectionModel().getSelectedItem();
            String ID = s.getID();
            Connection con = DriverManager.getConnection(DBController.HOST, DBController.USER, DBController.PASS);
            String sql = "UPDATE STUDENTS SET BANNED='FALSE' WHERE STUDENTID='" + ID + "'";
            PreparedStatement pstate = con.prepareStatement(sql);
            pstate.executeUpdate();
            System.out.println("Unbanned");
            con.close();
            loadBannedList();

        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void viewBanned(ActionEvent event) {
        try {
            loadBannedList();

        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        btnWarnings.setDisable(false);
        btnUnban.setDisable(false);
        btnBan.setDisable(true);
        btnBanned.setDisable(true);
    }

    @FXML
    private void shortSurvey(ActionEvent event) {
        try {
            loadShortSurvey();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomeController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void longSurvey(ActionEvent event) {
        try {
            loadLongSurvey();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomeController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent parent;
        try {
            parent = (Parent) loader.load();
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void populateComboBox(ActionEvent event) {

        try {
            fillComboModules();

        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fillGraphs(ActionEvent event) throws SQLException {
        ArrayList<Result> results = new ArrayList();
        int[] lateNum = new int[13];
        int total = 0;
        int yes = 0;
        int no = 0;
        int myes = 0;
        int mno = 0;
        
        Connection con = DriverManager.getConnection(DBController.HOST, DBController.USER, DBController.PASS);
        Module m = (Module) modules.get(comboModule.getSelectionModel().getSelectedIndex());
        String sql = "SELECT * FROM RESULTS_SH WHERE MODULEID='" + m.getID() + "' AND STAFFID='" + selectedStaff.getStaffID() + "'";
        PreparedStatement pstate = con.prepareStatement(sql);
        ResultSet rs = pstate.executeQuery();
        
        while (rs.next()) {
            Result r = new Result(rs.getString("MODULEID"), rs.getString("STAFFID"), rs.getInt("MINS_LATE"), rs.getBoolean("ENGAGING"), rs.getBoolean("GOOD_MATERIALS"), rs.getBoolean("L_VALUE"), rs.getInt("LECTURE_SCORE"), rs.getInt("LECTURER_SCORE"), rs.getDate("DATE"));
            results.add(r);
            int i = r.getMinsLate();
            if (i != 0) {
                lateNum[i / 5]++;
                total += i;
            } else {
                lateNum[0]++;
            }
            
            if(r.getLValue()){
                yes++;
            } else {
                no++;
            }

            if(r.getMaterials()){
                myes++;
            } else {
                mno++;
            }
        }

        if (!results.isEmpty()) {
            ObservableList<PieChart.Data> latelist = FXCollections.observableArrayList(
                    new PieChart.Data("0 mins", lateNum[0]),
                    new PieChart.Data("5 mins", lateNum[1]),
                    new PieChart.Data("10 mins", lateNum[2]),
                    new PieChart.Data("15 mins", lateNum[3]),
                    new PieChart.Data("20 mins", lateNum[4]),
                    new PieChart.Data("25 mins", lateNum[5]),
                    new PieChart.Data("30 mins", lateNum[6]),
                    new PieChart.Data("35 mins", lateNum[7]),
                    new PieChart.Data("40 mins", lateNum[8]),
                    new PieChart.Data("45 mins", lateNum[9]),
                    new PieChart.Data("50 mins", lateNum[10]),
                    new PieChart.Data("55 mins", lateNum[11]),
                    new PieChart.Data("60 mins", lateNum[12]));
            // MINS LATE
            chrtTimeBar.setData(latelist);
            double meanlate = total / results.size();
            lblLatest.setText(String.format("%.2f Minutes", meanlate));

            //VALUE ADDED
            ObservableList<PieChart.Data> pieValue = FXCollections.observableArrayList(
                    new PieChart.Data("Yes", yes),
                    new PieChart.Data("No", no)
            );
            chrtValue.setData(pieValue);
            
            //MATERIALS
            ObservableList<PieChart.Data> pieMaterial = FXCollections.observableArrayList(
                    new PieChart.Data("Yes", myes),
                    new PieChart.Data("No", mno)
            );
            chrtMaterials.setData(pieMaterial);
        }

        con.close();

    }

}
