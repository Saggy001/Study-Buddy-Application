package com.saggy.wielearner.Model;

public class UserInfoModel {
    private String username;
    private String board;
    private String course;
    private String sub1;
    private String sub2;
    private String sub3;
    private String sub4;
    private String sub5;
    private String sub6;

    public UserInfoModel(String username, String board, String course, String sub1,
                         String sub2, String sub3, String sub4, String sub5, String sub6) {
        this.username = username;
        this.board = board;
        this.course = course;
        this.sub1 = sub1;
        this.sub2 = sub2;
        this.sub3 = sub3;
        this.sub4 = sub4;
        this.sub5 = sub5;
        this.sub6 = sub6;
    }

    public UserInfoModel() {
    }

    public String getUsername() {
        return username;
    }

    public String getBoard() {
        return board;
    }

    public String getCourse() {
        return course;
    }

    public String getSub1() {
        return sub1;
    }

    public String getSub2() {
        return sub2;
    }

    public String getSub3() {
        return sub3;
    }

    public String getSub4() {
        return sub4;
    }

    public String getSub5() {
        return sub5;
    }

    public String getSub6() {
        return sub6;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setSub1(String sub1) {
        this.sub1 = sub1;
    }

    public void setSub2(String sub2) {
        this.sub2 = sub2;
    }

    public void setSub3(String sub3) {
        this.sub3 = sub3;
    }

    public void setSub4(String sub4) {
        this.sub4 = sub4;
    }

    public void setSub5(String sub5) {
        this.sub5 = sub5;
    }

    public void setSub6(String sub6) {
        this.sub6 = sub6;
    }
}
