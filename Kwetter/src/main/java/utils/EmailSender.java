package utils;

import domain.User;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Stateless
public class EmailSender {

    @Resource(name = "java/mail/swhp")
    Session mailSession;

    public void sendMail(User user) throws MessagingException, UnsupportedEncodingException {
        Message message = new MimeMessage(mailSession);

        message.setSubject("Confirm email Kwetter");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail(), user.getName()));
        ((MimeMessage) message).setText("Hallo");

        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
