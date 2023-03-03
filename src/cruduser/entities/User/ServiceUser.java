
package cruduser.entities.User;

import cruduser.database.DatabaseHandler;
import cruduser.entities.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nasr
 */
public class ServiceUser implements IService<User>{
  
    private Connection cnx = DatabaseHandler.getInstance().getCnx();

    @Override
    public void ajouter(User p) {
        String req = "INSERT INTO user(User_FirstName , User_lastName ,User_mail,User_phone, Username, Password) VALUES(?, ? ,? ,? ,? ,? ,? ,?);";
        try {
            //Statement st = cnx.createStatement();
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, p.getUser_FirstName());
            pst.setString(2, p.getUser_LastName());
            pst.setString(3, p.getUser_mail());
            pst.setInt(4, p.getUser_phone());
            pst.setString(5, p.getUsername());
            pst.setString(6, p.getPassword());
            pst.executeUpdate();
            System.out.println("User ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(User p) {
        String req = "UPDATE  user SET User_FirstName=?, User_lastName= ? ,User_mail= ? ,User_phone=?,Username= ?, Password= ? WHERE id_User=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, p.getUser_FirstName());
            pst.setString(2, p.getUser_LastName());
            pst.setString(3, p.getUser_mail());
            pst.setInt(4, p.getUser_phone());
            pst.setString(5, p.getUsername());
            pst.setString(6, p.getPassword());
            pst.executeUpdate();
            System.out.println("User modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(User p) {
        String req = "DELETE FROM user WHERE id_User=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId_User());
            pst.executeUpdate();
            System.out.println("User supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> afficher() {
        List<User> list = new ArrayList<>();
        
        String req = "SELECT * FROM user";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while(result.next()) {
                list.add(new User(result.getInt("id_User"), result.getString("User_FirstName"), result.getString("User_lastName"),result.getString("User_mail"), result.getInt("User_phone"), result.getString("Username"), result.getString("Password")));
            }
            System.out.println("Users récupérées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }

    public boolean validate(String Uname , String Upassword) {
         
        String vd = "SELECT * FROM user WHERE Username= ? and Password= ?";
        try {
        PreparedStatement pst = cnx.prepareStatement(vd);
            pst.setString(1,Uname);
            pst.setString(2, Upassword);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());;
        }
        return false ;
    }
}

