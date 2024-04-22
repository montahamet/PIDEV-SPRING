package com.coconsult.pidevspring.Services.HR;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import java.util.Collections;

@Service
public class EmailVerificationService {
    private final String EMAIL_VALIDATION_API_URL = "https://emailvalidation.abstractapi.com/v1";
    private final String API_KEY = "f266c29f04394a5ba4719817d92f8047";

    public String verifyEmailAddress(String email) {
        // Construct the URL with the email to verify
        String apiUrl = EMAIL_VALIDATION_API_URL + "?api_key=" + API_KEY + "&email=" + email;

        // Create HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Create HttpEntity
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Create RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Make a GET request to the EmailValidation API
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        // Check if the request was successful
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            // Parse the response body and extract the necessary information
            String responseBody = responseEntity.getBody();
            // Process the response and extract the necessary information
            String verificationResult = extractVerificationResult(responseBody);
            return verificationResult;
        } else {
            // Handle the error response
            return "Error occurred: " + responseEntity.getStatusCodeValue();
        }
    }

    private String extractVerificationResult(String response) {
        try {
            // Parse the response JSON and extract the necessary information
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);

            // Extract the necessary information from the response
            String email = rootNode.get("email").asText();
            String deliverability = rootNode.get("deliverability").asText();
            double qualityScore = rootNode.get("quality_score").asDouble();

            // Build the response string
            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append("Email: ").append(email).append("\n");
            resultBuilder.append("Deliverability: ").append(deliverability).append("\n");
            resultBuilder.append("Quality Score: ").append(qualityScore).append("\n");

            // You can extract and include more information as needed

            return resultBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error parsing response";
        }
    }
}
