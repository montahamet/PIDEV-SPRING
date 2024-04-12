package com.coconsult.pidevspring.RestControllers.HR;

import com.coconsult.pidevspring.Services.HR.ILinkedInScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
@RequestMapping("/linkedin-scraper")
public class LinkedInScraperRestController {
    private final ILinkedInScraperService linkedInScraperService;

    @Autowired
    public LinkedInScraperRestController(ILinkedInScraperService linkedInScraperService) {
        this.linkedInScraperService = linkedInScraperService;
    }

    @GetMapping("/scrape")
    public ResponseEntity<String> scrapeLinkedIn(@RequestParam String linkedinUrl) {
        try {
            String responseJson = linkedInScraperService.enrichCandidacyWithLinkedInData(linkedinUrl);
            return ResponseEntity.ok(responseJson);
        } catch (UnirestException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to scrape LinkedIn data");
        }
    }
}
