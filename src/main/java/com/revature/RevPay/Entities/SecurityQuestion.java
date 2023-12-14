package com.revature.RevPay.Entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class SecurityQuestion {
    @Id
    @Column(name="question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionID;

    @Column(name="security_question")
    private String securityQuestion;

    @Column(name="security_answer")
    private String securityAnswer;

    @ManyToOne
    @JoinColumn(name="username", nullable = false)
    private User user;

    public SecurityQuestion() {
    }

    public SecurityQuestion(String securityQuestion, String securityAnswer, User user) {
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.user = user;
    }

    public SecurityQuestion(Integer questionID, String securityQuestion, String securityAnswer, User user) {
        this.questionID = questionID;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.user = user;
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SecurityQuestion that)) return false;
        return getQuestionID() == that.getQuestionID() && Objects.equals(getSecurityQuestion(), that.getSecurityQuestion()) && Objects.equals(getSecurityAnswer(), that.getSecurityAnswer()) && Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestionID(), getSecurityQuestion(), getSecurityAnswer(), getUser());
    }

    @Override
    public String toString() {
        return "SecurityQuestions{" +
                "questionID=" + questionID +
                ", securityQuestion='" + securityQuestion + '\'' +
                ", securityAnswer='" + securityAnswer + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
