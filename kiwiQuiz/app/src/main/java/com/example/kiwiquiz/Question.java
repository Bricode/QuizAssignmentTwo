package com.example.kiwiquiz;

public class Question {
    //creating a questions fields
    private String questionA;
    private String questionB;
    private String questionC;
    private String questionD;
    private String nameOfResort;
    private String answer;

    //defining each questions fields
    public Question(String questionA, String questionB, String questionC, String questionD, String nameOfResort, String answer ) {
    this.questionA = questionA;
    this.questionB = questionB;
    this.questionC = questionC;
    this.questionD = questionD;
    this.nameOfResort = nameOfResort;
    this.answer = answer;
    }

    //getters and setters for the question class
    public String getQuestionA() {
        return questionA;
    }

    public void setQuestionA(String questionA) {
        this.questionA = questionA;
    }

    public String getQuestionB() {
        return questionB;
    }

    public void setQuestionB(String questionB) {
        this.questionB = questionB;
    }

    public String getQuestionC() {
        return questionC;
    }

    public void setQuestionC(String questionC) {
        this.questionC = questionC;
    }

    public String getQuestionD() {
        return questionD;
    }

    public void setQuestionD(String questionD) {
        this.questionD = questionD;
    }

    public String getNameOfResort() {
        return nameOfResort;
    }

    public void setNameOfResort(String nameOfResort) {
        this.nameOfResort = nameOfResort;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
