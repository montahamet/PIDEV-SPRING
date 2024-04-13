package com.coconsult.pidevspring;


import com.coconsult.pidevspring.Services.User.Image.FilesStorageServiceImplTH;
import jakarta.annotation.Resource;

import com.coconsult.pidevspring.Services.ProjectModule.FilesStorageService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class PiDevSpringApplication implements CommandLineRunner {
    @Resource
    FilesStorageServiceImplTH storageServiceth;

    @Resource
    FilesStorageService storageService;
    public static void main(String[] args) {
        SpringApplication.run(PiDevSpringApplication.class, args);
    }
    @Override
    public void run(String... arg) throws Exception {
//    storageService.deleteAll();
        storageService.init();
      storageServiceth.init();
    }


}
