package com.saggy.wielearner.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class SubjectModel{
    String coursecode;
    String coursename;
    String topic;
    String subtopic;
    String subjectname;
    String semester;
    String boardname;
    String videolink;
    String noteslink;
    String quizlink;

    //changes to be done in
    //homefragment
    //topicselection
    //topicadapter
    //subtopicadapter
    //subtopicselection

    public SubjectModel(String coursecode, String coursename, String topic, String subtopic, String subjectname, String semester, String boardname, String videolink) {
        this.coursecode = coursecode;
        this.coursename = coursename;
        this.topic = topic;
        this.subtopic = subtopic;
        this.subjectname = subjectname;
        this.semester = semester;
        this.boardname = boardname;
        this.videolink = videolink;
    }

    public SubjectModel() {
    }

    public String getNoteslink() {
        return noteslink;
    }

    public void setNoteslink(String noteslink) {
        this.noteslink = noteslink;
    }

    public String getQuizlink() {
        return quizlink;
    }

    public void setQuizlink(String quizlink) {
        this.quizlink = quizlink;
    }

    public String getVideolink() {
        return videolink;
    }

    public void setVideolink(String videolink) {
        this.videolink = videolink;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public String getCoursename() {
        return coursename;
    }

    public String getTopic() {
        return topic;
    }

    public String getSubtopic() {
        return subtopic;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public String getSemester() {
        return semester;
    }

    public String getBoardname() {
        return boardname;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setSubtopic(String subtopic) {
        this.subtopic = subtopic;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setBoardname(String boardname) {
        this.boardname = boardname;
    }

}
