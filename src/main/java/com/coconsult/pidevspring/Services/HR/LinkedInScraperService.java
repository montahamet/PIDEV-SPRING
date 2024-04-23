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
        HttpResponse<String> response = Unirest.get("https://api.scrapin.io/enrichment/profile?apikey=sk_live_661829b95158970618b3e285_key_lkth86lyaur&linkedinUrl=" + linkedinUrl)
                .asString();

        // Handle the response if needed
        String responseBody = response.getBody();

        // Return the API response
        return responseBody;
    }
}
