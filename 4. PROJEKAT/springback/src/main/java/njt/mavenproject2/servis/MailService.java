// njt.mavenproject2.servis.MailService
package njt.mavenproject2.servis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mail;
    @Value("${app.mail.from}") private String from;

    public MailService(JavaMailSender mail){ this.mail = mail; }

    public void send(String to, String subject, String text){
        SimpleMailMessage m = new SimpleMailMessage();
        m.setFrom(from);
        m.setTo(to);
        m.setSubject(subject);
        m.setText(text);
        mail.send(m);
    }
}
