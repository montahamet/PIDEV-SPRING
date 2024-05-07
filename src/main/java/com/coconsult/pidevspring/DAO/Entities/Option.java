package com.coconsult.pidevspring.DAO.Entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Option {
    private String answer;
    private boolean isCorrect;

    // Constructors, getters, and setters
    public Option() {}

    public Option(String answer, boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}
