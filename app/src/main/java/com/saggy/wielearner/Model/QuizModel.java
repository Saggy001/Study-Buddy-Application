package com.saggy.wielearner.Model;

public class QuizModel {
    String question;
    String optionA;
    String optionB;
    String optionC;
    String optionD;
    String correctanswer;
    String explaination;

    public QuizModel() {
    }

    public QuizModel(String question, String optionA, String optionB, String optionC, String optionD, String correctanswer, String explaination) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctanswer = correctanswer;
        this.explaination = explaination;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }


    public void setCorrectanswer(String correctanswer) {
        this.correctanswer = correctanswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCorrectanswer() {
        return correctanswer;
    }

    public String getExplaination() {
        return explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }
}
