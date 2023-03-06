/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Login;

import com.jfoenix.controls.JFXTextField;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.autopilot.v1.assistant.Task;
import cruduser.entities.User.ServiceUser;
import cruduser.entities.User.User;
import cruduser.util.Util;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
    public static final String ACCOUNT_SID = "ACbcc7f8d34a41d899234eaee96ba4df31";
    public static final String AUTH_TOKEN = "622adcf13aa54bd8f813bd16af9606c4";
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            // String sendList[] = jTextFieldTo.getText().split(",")
            ServiceUser su=new ServiceUser();
            List<User> touristList=su.findTourist();
            for(User u:touristList)
            {
           
                List<User> guideList=su.findGuides(u.getCityname(),u.getDateBegin(),u.getDateEnd());
                if(!guideList.isEmpty())
                {
                    
                    su.autoMatch(u.getId_User(),guideList.get(0).getId_User());
                    createTask();
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

protected Task createTask()
{
    
Thread th = new Thread(() -> {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                   String messageBody = "Guide number :";
                    String to = "+21654494381";
                    Message message =Message.creator(
                    new com.twilio.type.PhoneNumber(to),                //Recipient(s)
                    new com.twilio.type.PhoneNumber("+15674093244"),    //Sender Phone No. - Find your Twilio phone number at https://www.twilio.com/console
                    messageBody)
                    .setMediaUrl(										//MMS, Comment out this and the next line if you don't want to attach picture to your message.
                            Arrays.asList(URI.create("http://oracleprofessor.com/LO.jpg")))	//MMS
                    .create();});
return null;
}

}
