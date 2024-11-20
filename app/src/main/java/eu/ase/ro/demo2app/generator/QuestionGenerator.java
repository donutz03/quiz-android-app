package eu.ase.ro.demo2app.generator;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import eu.ase.ro.demo2app.model.Answer;
import eu.ase.ro.demo2app.model.Question;
import eu.ase.ro.demo2app.model.QuestionType;

public class QuestionGenerator {

    private static final int NUMBER_OF_QUESTIONS = 11;
    public static final String ETHICS = "ETHICS";
    public static final String REGEX = "~";
    public static final String REGEX1 = "-";
    public static List<Question> questionList;
    BufferedReader bufferedReader;

    public QuestionGenerator(InputStreamReader reader) throws FileNotFoundException {
        questionList = new ArrayList<>(NUMBER_OF_QUESTIONS);
        bufferedReader = new BufferedReader(reader);
        List<String> listOfQuestions = bufferedReader.lines().collect(Collectors.toList());
        for (String question: listOfQuestions) {
            String[] parts = question.split(REGEX);
            String text = parts[0];
            String topic = parts[1];
            QuestionType questionType;
            if (topic.equals(ETHICS)) {
                questionType = QuestionType.ETHICS;
            } else {
                questionType = QuestionType.MORALITY;
            }
            String options = parts[2];
            List<String> individualOptions = List.of(options.split(REGEX1));
            String correctAnswers = parts[3];
            String[] correctAnswersParts = correctAnswers.split(REGEX1);
            int[] correctAnswersArray = new int[correctAnswersParts.length];
            for (int i=0;i<correctAnswersParts.length;i++) {
                correctAnswersArray[i] = Integer.parseInt(correctAnswersParts[i]);
            }
            Answer currentAnswer = new Answer(correctAnswersArray);
            Question currentQuestion = new Question(text,questionType,
                    currentAnswer,individualOptions);
            questionList.add(currentQuestion);
        }

    }
}
