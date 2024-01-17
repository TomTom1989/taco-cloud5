/*package tacos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class GmailIntegrationTest {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void testEmailSending() {
        // Setup the email message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("thomas.walciszewski@gmail.com"); // A test email address
        message.setSubject("Test Email");
        message.setText("This is a test email from Spring Boot.");

        // Send the email
        mailSender.send(message);

        // Assertions or further actions
        // Note: Receiving and verifying the email programmatically would be complex
        // as it involves checking the inbox of the recipient.
    }
}*/
