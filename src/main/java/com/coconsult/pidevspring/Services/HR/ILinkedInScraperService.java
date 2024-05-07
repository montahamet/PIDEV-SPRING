package com.coconsult.pidevspring.Services.HR;
import com.mashape.unirest.http.exceptions.UnirestException;


public interface ILinkedInScraperService {
    String enrichCandidacyWithLinkedInData(String linkedinUrl) throws UnirestException;

}
