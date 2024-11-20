package eu.ase.ro.demo2app.model;

import java.util.List;

public class Question {

    private String text;
    private QuestionType questionType;
    private Answer answer;
    private List<String> options;

    public Question(String text, QuestionType questionType, Answer answer, List<String> options) {
        this.text = text;
        this.questionType = questionType;
        this.answer = answer;
        this.options = options;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", questionType=" + questionType +
                ", answer=" + answer +
                ", options=" + options +
                '}';
    }


}
