package tacos;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.mail.ImapMailReceiver;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.integration.mail.transformer.MailToStringTransformer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
//import jakarta.validation.constraints.Email;

import tacos.data.IngredientRepository;
import tacos.data.IngredientService;
import tacos.Ingredient.Type;
//import tacos.email.Email;
//import tacos.email.EmailTacoService;


@SpringBootApplication
//@EnableR2dbcRepositories(basePackages = "tacos.data")
public class TacoCloudApplication {
	


  
    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
 
    }
    
   
}

