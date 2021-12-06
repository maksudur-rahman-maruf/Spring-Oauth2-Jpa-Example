package com.maruftech.springoauth2jpaexample;

import com.maruftech.springoauth2jpaexample.entities.Role;
import com.maruftech.springoauth2jpaexample.entities.User;
import com.maruftech.springoauth2jpaexample.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringOauth2JpaExampleApplication {


    @Bean
    public CommandLineRunner setupDefaultUser(UserService service) {
        return args -> {
            service.save(new User(
                    "user", //username
                    "user", //password
                    Arrays.asList(new Role("USER"), new Role("ACTUATOR")),//roles
                    true//Active
            ));
            service.save(new User(
                    "admin", //username
                    "admin", //password
                    Arrays.asList(new Role("USER"), new Role("ADMIN"), new Role("ACTUATOR")),//roles
                    true//Active
            ));

        };
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringOauth2JpaExampleApplication.class, args);
    }

}
