package com.saggy.wielearner.Model;

import java.io.Serializable;

public class Subject implements Serializable {
    String coursecode;
    String coursename;
    String subjectname;
    String semester;

    public Subject(String coursecode, String coursename, String subjectname, String semester) {
        this.coursecode = coursecode;
        this.coursename = coursename;
        this.subjectname = subjectname;
        this.semester = semester;
    }


    public Subject() {
    }

    public String getCoursecode() {
        return coursecode;
    }

    public String getCoursename() {
        return coursename;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public String getSemester() {
        return semester;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
