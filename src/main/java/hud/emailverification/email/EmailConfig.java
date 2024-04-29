//package hud.emailverification.email;
//
//
//import jakarta.mail.PasswordAuthentication;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//
//import jakarta.mail.Authenticator;
//import jakarta.mail.Session;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
//@Configuration
//public class EmailConfig {
//    @Value("${spring.mail.host}")
//    private String host;
//
//    @Value("${spring.mail.port}")
//    private String port;
//
//    @Value("${spring.mail.username}")
//    private String from;
//
//    @Value("${spring.mail.password}")
//    private String password;
//
////    @Bean
////    public Properties properties(){
////        Properties properties = new Properties();
////        properties.put("mail.smtp.host", host);
////        properties.put("mail.smtp.port", port);
////        properties.put("mail.smtp.auth", "true");
////        properties.put("mail.smtp.starttls.enable", "true");
////        return properties;
////    }
//
////    @Bean
////    public Authenticator authenticator(){
////        return new Authenticator() {
////            @Override
////            protected PasswordAuthentication getPasswordAuthentication() {
////                return new PasswordAuthentication(host,password);
////            }
////        };
////    }
////
////    @Bean
////    public Session getSession(){
////        return  Session.getInstance(properties(), authenticator());
////    }
////
////    @Bean
////    public JavaMailSenderImpl sender(){
////        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
////        mailSender.setSession(getSession());
////        return mailSender;
////    }
//
//}
