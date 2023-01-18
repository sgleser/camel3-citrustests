package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setApplicationStartup(new BufferingApplicationStartup(2048));
        app.run(args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
