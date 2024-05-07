package com.coconsult.pidevspring.DAO.Entities;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import java.util.Properties;
import java.util.logging.Logger;

public class SentimentAnalyzer {
    private static final Logger logger = Logger.getLogger(SentimentAnalyzer.class.getName());
    private static StanfordCoreNLP englishPipeline;
    private static StanfordCoreNLP frenchPipeline;

    // Lazily initialize the English pipeline
    private static StanfordCoreNLP getEnglishPipeline() {
        if (englishPipeline == null) {
            try {
                Properties englishProps = new Properties();
                englishProps.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
                englishProps.setProperty("parse.model", "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
                englishPipeline = new StanfordCoreNLP(englishProps);
            } catch (Exception e) {
                logger.severe("Failed to initialize English pipeline: " + e.getMessage());
                return null; // return null to handle initialization failure
            }
        }
        return englishPipeline;
    }

    // Lazily initialize the French pipeline
    private static StanfordCoreNLP getFrenchPipeline() {
        if (frenchPipeline == null) {
            try {
                Properties frenchProps = new Properties();
                frenchProps.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
                frenchProps.setProperty("parse.model", "edu/stanford/nlp/models/lexparser/frenchFactored.ser.gz");
                frenchProps.setProperty("tokenize.language", "fr");
                frenchPipeline = new StanfordCoreNLP(frenchProps);
            } catch (Exception e) {
                logger.severe("Failed to initialize French pipeline: " + e.getMessage());
                return null; // return null to handle initialization failure
            }
        }
        return frenchPipeline;
    }

    /**
     * Determines the sentiment of a given text based on its language.
     * @param text the text to analyze.
     * @param language the language of the text (either "english" or "french").
     * @return the sentiment of the text, or a message indicating an error or no sentiment detected.
     */
    public static String findSentiment(String text, String language) {
        StanfordCoreNLP pipeline = language.equals("french") ? getFrenchPipeline() : getEnglishPipeline();
        if (pipeline == null) {
            return "Error initializing NLP pipeline";
        }
        CoreDocument document = new CoreDocument(text);
        try {
            pipeline.annotate(document);
            if (!document.sentences().isEmpty()) {
                return document.sentences().get(0).sentiment();
            } else {
                return "No sentiment detected"; // Handle case with no sentences detected
            }
        } catch (Exception e) {
            logger.severe("Error analyzing sentiment: " + e.getMessage());
            return "Error analyzing sentiment";
        }
    }
}
