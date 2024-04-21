package com.coconsult.pidevspring.Services.HR;

import com.coconsult.pidevspring.DAO.Entities.Candidacy;
import com.coconsult.pidevspring.DAO.Repository.HR.CandidacyRepository;
import com.coconsult.pidevspring.DAO.Repository.HR.JobOfferRepository;
//import com.mashape.unirest.http.*;
//import com.mashape.unirest.http.exceptions.UnirestException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mashape.unirest.http.Unirest;
@Service
@AllArgsConstructor
public class CandidacyService implements ICandidacyService {
    @Autowired
    CandidacyRepository candidacyRepository;
    JobOfferRepository jobOfferRepository;
    private final LinkedInScraperService linkedInScraperService;


    @Override
    public Candidacy addOrUpdateCandidacy(Candidacy candidacy) {
        return candidacyRepository.save(candidacy);
    }
    @Override
    public Candidacy addCandidate(Candidacy candidacy) {
        return candidacyRepository.save(candidacy);
    }

    @Override
    public List<Candidacy> addAllCandidacies(List<Candidacy> Candidacys) {
        return candidacyRepository.saveAll(Candidacys);
    }
    @Override
    public List<Candidacy> findAllCandidacies() {
        return candidacyRepository.findAll();
    }

    @Override
    public Candidacy findCandidacyById(long id) {
        return candidacyRepository.findById(id).get();
    }

    @Override
    public void deleteCandidacy(Candidacy candidacy) {
        candidacyRepository.delete(candidacy);

    }
    @Override
    public void deleteCandidacyById(long id) {
        candidacyRepository.deleteById(id);
    }
    @Override
    public List<Candidacy> getCandidaciesByJobOfferId(Long jobOfferId) {
        // Implement the logic to retrieve candidacies by job offer ID from the repository
        return candidacyRepository.findByJobOfferId
                (jobOfferId);
    }
    public List<Candidacy> findAllByJobOfferId(Long jobOfferId) {
        return candidacyRepository.findByJobOfferId(jobOfferId);
    }
    @Override
    public int countCandidaciesByJobOfferId(Long jobOfferId) {
        return candidacyRepository.countByJobOfferId(jobOfferId);
    }
    public Candidacy updateCandidacyStatus(Candidacy updatedCandidacy) {
        // Fetch the existing Candidacy object from the database
        Optional<Candidacy> optionalCandidacy = candidacyRepository.findById(updatedCandidacy.getCandidacy_id());

        if (optionalCandidacy.isPresent()) {
            // Update the status of the existing Candidacy object
            Candidacy existingCandidacy = optionalCandidacy.get();
            existingCandidacy.setCandidacystatus(updatedCandidacy.getCandidacystatus());

            // If the status is for archiving (-1), set isArchived to true
            if (updatedCandidacy.getCandidacystatus() == -1) {
                existingCandidacy.setArchived(true);
            }

            // Save the updated Candidacy object back to the database
            return candidacyRepository.save(existingCandidacy);
        } else {
            // Handle the case where the Candidacy object is not found
            // You may choose to throw an exception or return null
            return null;
        }
    }

//    @Override
//    public String getCandidacyInfoFromLinkedIn(String linkedinUrl) {
//        try {
//            // Call the LinkedInScraperService to get information from LinkedIn API
//            String scrapedInfo = linkedInScraperService.enrichCandidacyWithLinkedInData(linkedinUrl);
//            // You can store the scraped information in a suitable data structure or return it directly
//            return scrapedInfo;
//        } catch (UnirestException e) {
//            e.printStackTrace(); // Handle the exception as needed
//            return null;
//        }
//    }

//    @Override
//    public void updateCandidaciesWithLinkedInData() {
//        // Retrieve all candidacies with LinkedIn links
//        List<Candidacy> candidaciesWithLinkedInLinks = candidacyRepository.findAll();
//
//        // Iterate over each candidacy
//        for (Candidacy candidacy : candidaciesWithLinkedInLinks) {
//            try {
//                // Retrieve LinkedIn data for the current candidacy
//                String linkedInData = linkedInScraperService.enrichCandidacyWithLinkedInData(candidacy.getLinkedin());
//
//                // Extract and store the country, skills, and geographic area
//                extractAndStoreLinkedInData(candidacy, linkedInData);
//
//                // Save the updated candidacy to the database
//                candidacyRepository.save(candidacy);
//            } catch (UnirestException e) {
//                e.printStackTrace(); // Handle the exception as needed
//            }
//        }
//    }
//    private void extractAndStoreLinkedInData(Candidacy candidacy, String linkedInData) {
//        try {
//            // Parse the JSON response
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode rootNode = objectMapper.readTree(linkedInData);
//
//            // Extract necessary information
//            JsonNode personNode = rootNode.path("person");
//            if (personNode != null) {
//                // Extract location
//                String location = personNode.path("location").asText();
//
//                // Extract skills
//                StringBuilder skillsBuilder = new StringBuilder();
//                JsonNode skillsNode = personNode.path("skills");
//                if (skillsNode.isArray()) {
//                    for (JsonNode skillNode : skillsNode) {
//                        skillsBuilder.append(skillNode.asText()).append(", ");
//                    }
//                    String skills = skillsBuilder.toString();
//                    if (skills.endsWith(", ")) {
//                        skills = skills.substring(0, skills.length() - 2); // Remove the trailing comma and space
//                    }
//
//                    // Extract education history
//                    StringBuilder schoolsBuilder = new StringBuilder();
//                    JsonNode educationHistoryNode = personNode.path("schools").path("educationHistory");
//                    if (educationHistoryNode.isArray()) {
//                        for (JsonNode educationNode : educationHistoryNode) {
//                            String schoolName = educationNode.path("schoolName").asText();
//                            schoolsBuilder.append(schoolName).append(", ");
//                        }
//                    }
//                    String educationHistory = schoolsBuilder.toString();
//                    if (educationHistory.endsWith(", ")) {
//                        educationHistory = educationHistory.substring(0, educationHistory.length() - 2); // Remove the trailing comma and space
//                    }
//
//                    // Update the candidacy attributes
//                    candidacy.setCountry(location); // Assuming location contains the country
//                    candidacy.setSkills(skills);
//                    candidacy.setEducationHistory(educationHistory);
//                }
//            }
//        } catch (IOException e) {
//            // Handle JSON parsing exception
//            e.printStackTrace();
//        }
//    }
//private void extractAndStoreLinkedInData(Candidacy candidacy, String linkedInData) {
//    try {
//        // Parse the JSON response
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode rootNode = objectMapper.readTree(linkedInData);
//
//        // Extract necessary information
//        JsonNode personNode = rootNode.path("person");
//        if (personNode != null) {
//            // Extract location
//            String location = personNode.path("location").asText();
//
//            // Extract skills
//            JsonNode skillsNode = personNode.path("skills");
//            if (skillsNode.isArray()) {
//                StringBuilder skillsBuilder = new StringBuilder();
//                for (JsonNode skillNode : skillsNode) {
//                    skillsBuilder.append(skillNode.asText()).append(", ");
//                }
//                String skills = skillsBuilder.toString();
//                if (!skills.isEmpty()) {
//                    skills = skills.substring(0, skills.length() - 2); // Remove the trailing comma and space
//                }
//
//                // Update the candidacy attributes
//                candidacy.setCountry(location); // Assuming location contains the country
//                candidacy.setSkills(skills);
//            }
//        }
//    } catch (IOException e) {
//        // Handle JSON parsing exception
//        e.printStackTrace();
//    }
//}


    @Override
    @Transactional // Ensure transactional behavior
    public List<Object[]> getCandidatesByCountryStatistics() {
        return candidacyRepository.getCandidatesByCountryStatistics();
    }
    @Override
    public List<Map<String, Object>> getMostQualifiedCandidatesStatistics() {
        // Retrieve all candidacies
        List<Candidacy> candidacies = candidacyRepository.findAll();

        // Create a list to store the result
        List<Map<String, Object>> result = new ArrayList<>();

        // Iterate over each candidacy
        for (Candidacy candidacy : candidacies) {
            // Check if the skills attribute is not null
            if (candidacy.getSkills() != null) {
                // Split the skills string by comma and count the number of skills
                String[] skillsArray = candidacy.getSkills().split(", ");
                int numberOfSkills = skillsArray.length;

                // Create a map to store the candidate's information
                Map<String, Object> candidateStatistics = new HashMap<>();
                candidateStatistics.put("candidateName", candidacy.getCandidateName());
                candidateStatistics.put("numberOfSkills", numberOfSkills);

                // Add the candidate's information to the result list
                result.add(candidateStatistics);
            }
        }

        // Sort the result list by the number of skills (descending order)
        result.sort((a, b) -> ((Integer) b.get("numberOfSkills")).compareTo((Integer) a.get("numberOfSkills")));

        return result;
    }



}
