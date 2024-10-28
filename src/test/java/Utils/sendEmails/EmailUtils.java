package Utils.sendEmails;

import BaseClasses.TestBase;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
//public Properties testdata;

public class EmailUtils {
    private TestBase testBase;

    // Constructor to initialize TestBase
    public EmailUtils(TestBase testBase) {
        this.testBase = testBase;
    }


    public void sendEmail(String to, String subject, String body, String attachmentPath) {
        final String username = testBase.getTestData("gmailUser");
        final String password = testBase.getTestData("gmailAppPassword");


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        //587, 465, 25
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(testBase.getTestData("gmailUser")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);


            BodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setText(body);
//            messageBodyPart.setHeader("Content-Type", "text/html");
            messageBodyPart.setContent(body, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            if (attachmentPath != null) {
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachmentPath);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(attachmentPath);
                multipart.addBodyPart(messageBodyPart);
            }

            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Email sent successfully to:" + to);
        } catch (MessagingException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
            System.out.println("Unexpected error occurred: " + e.getMessage());

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Unexpected error occurred: " + e.getMessage());
 }}
}
