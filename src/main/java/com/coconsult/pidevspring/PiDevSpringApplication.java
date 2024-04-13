package com.coconsult.pidevspring;

import com.coconsult.pidevspring.Services.ProjectModule.FilesProjectService;
import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PiDevSpringApplication implements CommandLineRunner{
    @Resource
    FilesProjectService storageService;
    public static void main(String[] args) {
        SpringApplication.run(PiDevSpringApplication.class, args);
    }

    public void run(String... arg) throws Exception {
//    storageService.deleteAll();
        storageService.init();
    }
}
