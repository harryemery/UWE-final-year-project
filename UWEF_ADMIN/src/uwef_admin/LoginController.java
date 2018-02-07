package uwef_admin;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Harry
 */
public class LoginController implements Initializable {

    @FXML
    private PasswordField txtPass;
    @FXML
    private TextField txtUsername;
    @FXML
    private Button btnOk;
    @FXML
    private Label lblLogin;
    @FXML
    private Label lblInvalid;
    @FXML
    private Label lblMUser;
    @FXML
    private Label lblMPass;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtUsername.clear();
        txtPass.clear();
    }

    @FXML
    private void Login(ActionEvent event) {

        String username = txtUsername.getText();
        String password = txtPass.getText();
        Connection con = null;
        try {
            con = DriverManager.getConnection(DBController.HOST, DBController.USER, DBController.PASS);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Cannot connect to database");
        }

        PreparedStatement pstate = null;
        ResultSet result = null;

        String sql = "SELECT * FROM LOGIN WHERE ID = '" + username + "' AND PASSWORD= '" + password + "'";
        try {
            pstate = con.prepareStatement(sql);
            result = pstate.executeQuery();

            if (result.next()) {
                //found
                String status = result.getString("STATUS");
                if (status.equals("ADMIN")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                    Parent parent = (Parent)loader.load();
                    HomeController controller = loader.<HomeController>getController();
                    controller.setUser(username);
                    Scene scene = new Scene(parent);
                    
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    
                    stage.setScene(scene);
                    stage.setTitle("UWE FEEDBACK - Home");
                    
                } else {
                    JOptionPane.showMessageDialog(null, "User is not authorised");
                }

            } else {
                //not found
                lblInvalid.setVisible(true);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
