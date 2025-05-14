package org.example.executor;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class TaskExecutorService {
    private ExecutorService executorService;

    @PostConstruct
    public void init(){
        this.executorService = Executors.newFixedThreadPool(5);
    }
}
