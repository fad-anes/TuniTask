/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author abdes
 */
import entite.Users;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
  


public class EnvoyerEmail {
private String Mail = "abdessalam.bahri@esprit.tn";
private String Password = "Irhab123&";
public void envoyer(String recepient) {
Properties properies = new Properties();
properies.put("mail.smtp.host", "smtp.gmail.com");
properies.put("mail.smtp.port", "465");
properies.put("mail.smtp.auth", "true");
properies.put("mail.smtp.starttls.enable", "true");
properies.put("mail.smtp.starttls.required", "true");
properies.put("mail.smtp.ssl.protocols", "TLSv1.2");
properies.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
Session session = Session.getInstance(properies,
new javax.mail.Authenticator() {
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(Mail, Password);
}
});
try {
    UserSession usersess= UserSession.getInstance();
    String code= usersess.getCode();
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("abdessalam.bahri@esprit.tn"));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse(recepient));
message.setSubject("Verification email");
message.setText("Bonjour, Monsieur veuiller confirmer votre compte! "
        + code);
// Etape 3 : Envoyer le message
Transport.send(message);
System.out.println("Message_envoye");
} catch (MessagingException e) {
      System.out.println("error" + e.toString());
} }

}
