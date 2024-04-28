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
        HttpResponse<String> response = Unirest.get("https://api.scrapin.io/enrichment/profile?apikey=sk_live_66273763631cab061a37da4d_key_axftok2aern&linkedinUrl=" + linkedinUrl)
                .asString();

        // Handle the response if needed
        String responseBody = response.getBody();

        // Return the API response
        return responseBody;
    }
}
