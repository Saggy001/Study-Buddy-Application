package com.saggy.wielearner.Model;

public class ScoreModel {
    String username;
    String subjectcode;
    String topic;
    String score;

    public ScoreModel() {
    }

    public ScoreModel(String username, String subjectcode, String topic, String score) {
        this.username = username;
        this.subjectcode = subjectcode;
        this.topic = topic;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public String getSubjectcode() {
        return subjectcode;
    }

    public String getTopic() {
        return topic;
    }

    public String getScore() {
        return score;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSubjectcode(String subjectcode) {
        this.subjectcode = subjectcode;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
