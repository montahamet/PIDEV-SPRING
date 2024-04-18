package com.coconsult.pidevspring.RestControllers.ProjectModule;

import com.coconsult.pidevspring.DAO.Entities.FileInfodoc;
import com.coconsult.pidevspring.filesupload.message.ResponseMessagedoc;
import com.coconsult.pidevspring.Services.ProjectModule.FilesProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@AllArgsConstructor
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

@RequestMapping("/uploads")
public class FilesControllerdoc {


        @Autowired
        FilesProjectService storageService;

        @PostMapping("/upload")
        public ResponseEntity<ResponseMessagedoc> uploadFile(@RequestParam("file") MultipartFile file) {
            String message = "";
            try {
                storageService.save(file);


                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessagedoc(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessagedoc(message));
            }
        }

        @GetMapping("/files")
        public ResponseEntity<List<FileInfodoc>> getListFiles() {
            List<FileInfodoc> fileInfos = storageService.loadAll().map(path -> {
                String filename = path.getFileName().toString();
                String url = MvcUriComponentsBuilder
                        .fromMethodName(FilesControllerdoc.class, "getFile", path.getFileName().toString()).build().toString();

                return new FileInfodoc(filename, url);
            }).collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
        }

        @GetMapping("/files/{filename:.+}")
        @ResponseBody
        public ResponseEntity<Resource> getFile(@PathVariable String filename) {
            Resource file = storageService.load(filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }
    @DeleteMapping("/files/{filename:.+}")
    public ResponseEntity<ResponseMessagedoc> deleteFile(@PathVariable String filename) {
        String message = "";

        try {
            boolean existed = storageService.delete(filename);

            if (existed) {
                message = "Delete the file successfully: " + filename;
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessagedoc(message));
            }

            message = "The file does not exist!";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessagedoc(message));
        } catch (Exception e) {
            message = "Could not delete the file: " + filename + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessagedoc(message));
        }
    }
    }

