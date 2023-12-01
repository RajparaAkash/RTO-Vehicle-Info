package com.rto.vehicle.numberplate.finder.information.Model;

public class BIKE_RTOExamResultModel {
    String AttendQuestion;
    String CorrectAnswer;
    String ExamDate;
    Boolean Result;
    String ResultMessage;
    String Score;
    String WrongAnswer;

    public String getScore() {
        return this.Score;
    }

    public void setScore(String str) {
        this.Score = str;
    }

    public String getResultMessage() {
        return this.ResultMessage;
    }

    public void setResultMessage(String str) {
        this.ResultMessage = str;
    }

    public String getExamDate() {
        return this.ExamDate;
    }

    public void setExamDate(String str) {
        this.ExamDate = str;
    }

    public String getAttendQuestion() {
        return this.AttendQuestion;
    }

    public void setAttendQuestion(String str) {
        this.AttendQuestion = str;
    }

    public String getCorrectAnswer() {
        return this.CorrectAnswer;
    }

    public void setCorrectAnswer(String str) {
        this.CorrectAnswer = str;
    }

    public String getWrongAnswer() {
        return this.WrongAnswer;
    }

    public void setWrongAnswer(String str) {
        this.WrongAnswer = str;
    }

    public Boolean getResult() {
        return this.Result;
    }

    public void setResult(Boolean bool) {
        this.Result = bool;
    }
}
