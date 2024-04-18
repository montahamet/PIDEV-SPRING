package com.coconsult.pidevspring.Services.User.Image;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

<<<<<<<< HEAD:src/main/java/com/coconsult/pidevspring/Services/HR/CVStorage/FilesHRController.java
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class FilesHRController {
    @Autowired
    FilesStorageServiceHR storageService;
========

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("user/files")
public class FilesControllers {

    @Autowired

    FilesStorageServiceImplTH storageService ;
>>>>>>>> 340bb1611de4d28d73c923a57941f8b1cd8d1183:src/main/java/com/coconsult/pidevspring/Services/User/Image/FilesControllers.java

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessageHR> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.save(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageHR(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessageHR(message));
        }
    }

<<<<<<<< HEAD:src/main/java/com/coconsult/pidevspring/Services/HR/CVStorage/FilesHRController.java
    @GetMapping("/files")
    public ResponseEntity<List<FileHRInfo>> getListFiles() {
        List<FileHRInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesHRController.class, "getFile", path.getFileName().toString()).build().toString();
========
    @GetMapping("")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesControllers.class, "getFile", path.getFileName().toString()).build().toString();
>>>>>>>> 340bb1611de4d28d73c923a57941f8b1cd8d1183:src/main/java/com/coconsult/pidevspring/Services/User/Image/FilesControllers.java

            return new FileHRInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}