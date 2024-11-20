package eu.ase.ro.demo2app.model;

import java.util.Arrays;

public class Answer {

    public static final int WRONG_ANSWER = -9999;
    public static final int CORRECT_ANSWER = 1;
    private int[] correctAnswers = {WRONG_ANSWER, WRONG_ANSWER, WRONG_ANSWER, WRONG_ANSWER};

    public Answer(int[] correctAnswers) {
        for (int i=0;i<correctAnswers.length;i++) {
            this.correctAnswers[correctAnswers[i]] = CORRECT_ANSWER;
        }
    }

    public int[] getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int[] correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "correctAnswers=" + Arrays.toString(correctAnswers) +
                '}';
    }
}
