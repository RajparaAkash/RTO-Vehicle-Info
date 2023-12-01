package com.rto.vehicle.numberplate.finder.information.Model;

import java.util.ArrayList;

public class BIKE_RTOQuestionModel {
    String Answer;
    String ImageUrl;
    String Isimage;
    String Question;
    String QuestionNum;
    String SelctedAnswer;
    String correctAnswer;
    ArrayList<String> QuestionOptions = new ArrayList<>();
    Boolean Attempt = false;
    Boolean AttemptMiss = false;

    public Boolean getAttemptMiss() {
        return this.AttemptMiss;
    }

    public void setAttemptMiss(Boolean bool) {
        this.AttemptMiss = bool;
    }

    public String getSelctedAnswer() {
        return this.SelctedAnswer;
    }

    public void setSelctedAnswer(String str) {
        this.SelctedAnswer = str;
    }

    public Boolean getAttempt() {
        return this.Attempt;
    }

    public void setAttempt(Boolean bool) {
        this.Attempt = bool;
    }

    public String getQuestionNum() {
        return this.QuestionNum;
    }

    public void setQuestionNum(String str) {
        this.QuestionNum = str;
    }

    public String getIsimage() {
        return this.Isimage;
    }

    public void setIsimage(String str) {
        this.Isimage = str;
    }

    public String getImageUrl() {
        return this.ImageUrl;
    }

    public void setImageUrl(String str) {
        this.ImageUrl = str;
    }

    public String getQuestion() {
        return this.Question;
    }

    public void setQuestion(String str) {
        this.Question = str;
    }

    public String getAnswer() {
        return this.Answer;
    }

    public void setAnswer(String str) {
        this.Answer = str;
    }

    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    public void setCorrectAnswer(String str) {
        this.correctAnswer = str;
    }

    public ArrayList<String> getQuestionOptions() {
        return this.QuestionOptions;
    }

    public void setQuestionOptions(ArrayList<String> arrayList) {
        this.QuestionOptions = arrayList;
    }
}
