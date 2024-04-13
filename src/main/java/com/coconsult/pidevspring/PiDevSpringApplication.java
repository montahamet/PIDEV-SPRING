package com.coconsult.pidevspring;

import com.coconsult.pidevspring.Services.User.Image.FilesStorageServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PiDevSpringApplication implements CommandLineRunner {
    @Resource
    FilesStorageServiceImpl storageService;
    public static void main(String[] args) {
        SpringApplication.run(PiDevSpringApplication.class, args);
    }
    @Override
    public void run(String... arg) throws Exception {
//    storageService.deleteAll();
        storageService.init();
    }

}
