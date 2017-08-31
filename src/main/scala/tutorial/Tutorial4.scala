package tutorial

import ch.usi.inf.reveal.parsing.stormed.service._
import ch.usi.inf.reveal.parsing.model.Implicits._

object Tutorial4 extends App {
  
  val textToParse = """
    
    package com.vlclabs.adsops.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import com.vlclabs.adsops.service.SimpleRegistrationService;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

@Configuration
public class EmailConfiguration {

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("mail.csonth.gov.uk");
        return mailSender;
    }

    /* 
    @Bean
    public SimpleRegistrationService registrationService(JavaMailSenderImpl mailSender, VelocityEngineFactoryBean velocityEngine) {
        SimpleRegistrationService registrationService = new SimpleRegistrationService();
        registrationService.setMailSender(mailSender);
        registrationService.setVelocityEngine(velocityEngine);
        return registrationService;
    }
    */

    @Bean
    public VelocityEngineFactoryBean velocityEngine() {
        VelocityEngineFactoryBean velocityEngine = new VelocityEngineFactoryBean();
        velocityEngine.setVelocityProperties("resource.loader=class", "class.resource.loader.class=org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        return velocityEngine;
    }
}
    
    """.trim
  
  val resultOption = StormedService.parseOption(textToParse,TutorialData.key)
  
  resultOption foreach { nodes =>
      nodes.foreach { node =>
        println(node.getClass.getSimpleName)
      }
  }
}
