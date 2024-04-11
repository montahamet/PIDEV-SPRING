package com.coconsult.pidevspring.Services.HR;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

@Service

public class LinkedInScraperService implements ILinkedInScraperService{

    @Override
    public String enrichCandidacyWithLinkedInData(String linkedinUrl) throws UnirestException {
        // Make the API call to enrich candidacy with LinkedIn data
        HttpResponse<String> response = Unirest.get("https://api.scrapin.io/enrichment/profile?apikey=sk_live_660d4d710474e0ef72317654_key_omcwaeqkbz&linkedinUrl=" + linkedinUrl)
                .asString();

        // Handle the response if needed
        String responseBody = response.getBody();

        // Return the API response
        return responseBody;
    }
}
