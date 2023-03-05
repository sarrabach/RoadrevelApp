/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Login;

import com.jfoenix.controls.JFXTextField;
import cruduser.entities.User.ServiceUser;
import cruduser.entities.User.User;
import cruduser.util.Util;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class LoginController implements Initializable {

    @FXML
    private Button login_btn;
    @FXML
    private Button signUp;
    @FXML
    private JFXTextField user_name;
    @FXML
    private JFXTextField user_pass;
    @FXML
    private Text error_password;
    @FXML
    private Button forg_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            error_password.setText("");
            ServiceUser su=new ServiceUser();
            List<User> touristList=su.findTourist();
            for(User u:touristList)
            {
                List<User> guideList=su.findGuides(u.getCityname(),u.getDateBegin(),u.getDateEnd());
                if(guideList.size()!=0)
                {
                su.autoMatch(u.getId_User(),guideList.get(0).getId_User());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void login(ActionEvent event) {
       String login = user_name.getText();
       String pass = user_pass.getText();
            if(login.equals("Admin") && pass.equals("Admin"))
           {
                Util.loadWindow(getClass().getResource("/cruduser/Tourist/DashbordAdmin.FXML"), "Admin Interface", null);   
           }    
           else
            {
       ServiceUser su = new ServiceUser();
       User user=su.existe(login);
       boolean etat=BCrypt.checkpw(pass,user.getPassword());
       System.out.println("pass hash =" +etat);
       
        if (login.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Stage) user_name.getScene().getWindow()), "Form Error!",
                "Please enter your UserName");
            return;
        }
        if (pass.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Stage) user_name.getScene().getWindow()), "Form Error!",
                "Please enter a password");
            return;
        } 
        
        System.out.println("pass =" +pass+" login = "+ login);

 
                
        if(user==null)
       {
           error_password.setText("Account Not Found");
       }
       else if(etat==true)
       {
           
           if(user.getRole().equals("Tourist"))
           {
               System.out.println(etat);
                Util.loadWindow(getClass().getResource("/cruduser/Tourist/InterfaceTourist.fxml"), "Tourist Interface", null);
           }
           else
           if(user.getRole()=="Quide")
           {
           //interface Quide   
           }
     
       }
            }
    }

    @FXML
    private void signUp(ActionEvent event) {
        Util.loadWindow(getClass().getResource("/cruduser/Tourist/RoleSelector.fxml"), "Code Mail", null);
    }


 
       private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static String validEmail;
    @FXML
    private void Email_forget_Code(ActionEvent event) {
          ServiceUser su = new ServiceUser();
          validEmail = user_name.getText();
         if (validEmail.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ((Stage) user_name.getScene().getWindow()), "Form Error!",
                "Please enter your UserName");
            return;
        }else if(su.existe(validEmail)!=null)
        {
            Util.loadWindow(getClass().getResource("/cruduser/Tourist/ForgetPassword.fxml"), "Code Mail", null);
        }    
         else
        {showAlert(Alert.AlertType.ERROR, ((Stage) user_name.getScene().getWindow()), "Form Error!",
                "Account does not exist");
         return;
        }
    }



}
