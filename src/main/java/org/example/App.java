package org.example;

import org.example.executor.TaskExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableAsync
public class App {//implements CommandLineRunner {
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }

}
