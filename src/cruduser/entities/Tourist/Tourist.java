/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cruduser.entities.Tourist;


import cruduser.entities.User.User;
import java.util.Objects;


/**
 *
 * @author user
 */
public class Tourist extends User{
    
   private String nationality;
    private String langue; 

    public Tourist(int Id_User) {
        super(Id_User);
    }

    public Tourist( String User_FirstName, String User_LastName, String User_mail, int User_phone, String Username, String Password,String nationality, String langue) {
        super(User_FirstName, User_LastName, User_mail, User_phone, Username, Password);
        this.nationality = nationality;
        this.langue = langue;
    }

    public Tourist( String User_FirstName, String User_LastName, String User_mail, String Username, String Password, int User_phone, String role,String nationality, String langue) {
        super(User_FirstName, User_LastName, User_mail, User_phone, Username, Password, role);
        this.nationality = nationality;
        this.langue = langue;
    }

    public Tourist( int Id_User, String User_FirstName, String User_LastName, String User_mail, int User_phone, String Username, String Password,String nationality, String langue) {
        super(Id_User, User_FirstName, User_LastName, User_mail, User_phone, Username, Password);
        this.nationality = nationality;
        this.langue = langue;
    }

    public Tourist( int Id_User, String User_FirstName, String User_LastName, String User_mail, int User_phone, String Username, String Password, String role,String nationality, String langue) {
        super(Id_User, User_FirstName, User_LastName, User_mail, User_phone, Username, Password, role);
        this.nationality = nationality;
        this.langue = langue;
    }

   

    
    
     

   

  
   

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.nationality);
        hash = 11 * hash + Objects.hashCode(this.langue);
        return hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tourist other = (Tourist) obj;
        if (!Objects.equals(this.nationality, other.nationality)) {
            return false;
        }
        return Objects.equals(this.langue, other.langue);
    }

    

    public String toString() {
        return "Tourist{" + "nationality=" + nationality + ", langue=" + langue + '}';
    }


    
  

    
   

    
   
   

   

    
    
}
