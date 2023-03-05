/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Api;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Exon
 */
public class Mail {
    
    private String username = "roadrevel123@gmail.com";
    private String password = "bbuscckegobdwdfp";
    

public void envoyer() {
// Etape 1 : CrÃ©ation de la session
    Properties props = new Properties();
    props.setProperty("mail.host", "smtp.gmail.com"); 
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", "true");  
    props.put("mail.smtp.port", "465");  
    props.put("mail.debug", "true");  
    props.put("mail.smtp.socketFactory.port", "465");  
    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
    props.put("mail.smtp.socketFactory.fallback", "false");
        Session session;
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
        // Etape 2 : CrÃ©ation de l'objet Message
            Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("ilyes.bettaieb@esprit.tn"));
        message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse("ilyesbettaieb5@gmail.com"));
        message.setSubject("Test email");
        message.setText("Bonjour,\n"
                + " Votre Offre a Ã©tÃ© ajoutÃ© avec succÃ©es.");
        // Etape 3 : Envoyer le message
            Transport.send(message);
        System.out.println("Message_envoye");
        } catch (MessagingException e) {
        throw new RuntimeException(e);
        } }



public void envoyerCode(String code,String to) {
// Etape 1 : CrÃ©ation de la session
Properties props = new Properties();
props.setProperty("mail.host", "smtp.gmail.com"); 
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", "true");  
    props.put("mail.smtp.port", "587");  
    props.put("mail.debug", "true");  
    props.put("mail.smtp.socketFactory.port", "465");  
    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
    props.put("mail.smtp.socketFactory.fallback", "false");
        Session session;
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
        // Etape 2 : CrÃ©ation de l'objet Message
            Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("workbottn@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(to));
        message.setSubject("Password reset");
        message.setText("Bonjour code="+code);
        // Etape 3 : Envoyer le message
            Transport.send(message);
        System.out.println("Message_envoye");
        } catch (MessagingException e) {
        throw new RuntimeException(e);
        } }
            }
    
