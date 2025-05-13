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
@EnableWebSecurity
public class App {//implements CommandLineRunner {

    @Autowired
    TaskExecutorService taskExecutorService;
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        taskExecutorService.executeTask(() -> {
//            System.out.println("Task 1 executed");
//        });
//
//        taskExecutorService.executeTask(() -> {
//            System.out.println("Task 2 executed");
//        });
//    }
}
