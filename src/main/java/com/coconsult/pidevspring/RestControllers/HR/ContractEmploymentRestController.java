package com.coconsult.pidevspring.RestControllers.HR;

import com.coconsult.pidevspring.DAO.Entities.ContractEmployment;
import com.coconsult.pidevspring.Services.HR.CVStorage.FileHRInfo;
import com.coconsult.pidevspring.Services.HR.CVStorage.FilesHRController;
import com.coconsult.pidevspring.Services.HR.CVStorage.FilesStorageServiceHR;
import com.coconsult.pidevspring.Services.HR.CVStorage.ResponseMessageHR;
import com.coconsult.pidevspring.Services.HR.IContractEmploymentService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/hr/contracts")
public class ContractEmploymentRestController {
    @Autowired
    private IContractEmploymentService contractEmploymentService;
    FilesStorageServiceHR storageService;

//    @PostMapping("/add/{candidacyId}")
//    public ResponseEntity<?> addContractByCandidacyId(@PathVariable Long candidacyId, @RequestBody ContractEmployment contract) {
//        try {
//            ContractEmployment createdContract = contractEmploymentService.addContractByCandidacyId(candidacyId, contract);
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdContract);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (IllegalStateException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
//        }
//    }
//@PostMapping("/addContractByInterviewId/{interviewId}")
//public ResponseEntity<ContractEmployment> addContractByInterviewId(
//        @PathVariable Long interviewId, @RequestBody ContractEmployment contractBody) {
//    try {
//        ContractEmployment contract = contractEmploymentService.addContractByInterviewId(interviewId, contractBody);
//        return new ResponseEntity<>(contract, HttpStatus.CREATED);
//    } catch (IllegalArgumentException e) {
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    } catch (IllegalStateException e) {
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//}
    @GetMapping("/all")
    public ResponseEntity<List<ContractEmployment>> getAllContractEmployments() {
        List<ContractEmployment> contractEmployments = contractEmploymentService.findAllContractEmployments();
        return new ResponseEntity<>(contractEmployments, HttpStatus.OK);
    }
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

    @GetMapping("/files")
    public ResponseEntity<List<FileHRInfo>> getListFiles() {
        List<FileHRInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesHRController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileHRInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    @Qualifier("csvJob")
    private Job job;
    @GetMapping(value = "/startBatch")
    public ResponseEntity<String> startBatch() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();

        JobExecution run = null;
        try {
            run = jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException e) {
            throw new RuntimeException(e);
        } catch (JobRestartException e) {
            throw new RuntimeException(e);
        } catch (JobInstanceAlreadyCompleteException e) {
            throw new RuntimeException(e);
        } catch (JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(run.getStatus().toString());

    }
}
