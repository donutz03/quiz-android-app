package eu.ase.ro.demo2app;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import eu.ase.ro.demo2app.adapters.QuestionAdapter;
import eu.ase.ro.demo2app.generator.QuestionGenerator;
import eu.ase.ro.demo2app.model.Answer;
import eu.ase.ro.demo2app.model.Question;

public class QuizEthicsMoralityActivity extends AppCompatActivity {

    public static final String LATEST_SCORE = "latestScore";
    private ListView listViewQuestions;
    private List<Question> questionList;
    private QuestionAdapter questionAdapter;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz_ethics_morality);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.hodoroaga_ionut_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listViewQuestions = findViewById(R.id.hodoroaga_ionut_listViewQ);
        QuestionGenerator questionGenerator;
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open(getString(R.string.data_txt));
           questionGenerator = new QuestionGenerator(new InputStreamReader(inputStream));
            questionList = List.copyOf(questionGenerator.questionList);
            questionAdapter = new QuestionAdapter(
                    getApplicationContext(),
                    R.layout.lv_item,
                    questionList,
                    getLayoutInflater()
            );
            listViewQuestions.setAdapter(questionAdapter);

        } catch (FileNotFoundException e) {
            Log.e(getString(R.string.quizethicsmoralityactivity), e.getMessage());
        } catch (IOException e) {
            Log.e(getString(R.string.quizethicsmoralityactivity), e.getMessage());
        }

        button = findViewById(R.id.hodoroaga_ionut_button2);
        button.setOnClickListener(getSaveEvent());

    }


    private View.OnClickListener getSaveEvent() {
        return v -> {
            int totalScore = 0;

            HashMap<Integer, Boolean[]> userSelections = questionAdapter.getUserSelections();

            for (int i = 0; i < questionList.size(); i++) {
                Question question = questionList.get(i);
                int[] correctAnswers = question.getAnswer().getCorrectAnswers();

                int totalCorrect = 0;
                int correctSelected = 0;
                int incorrectSelected = 0;

                for (int correct : correctAnswers) {
                    if (correct == Answer.CORRECT_ANSWER) {
                        totalCorrect++;
                    }
                }

                Boolean[] selections = userSelections.get(i);

                if (selections != null) {
                    for (int j = 0; j < selections.length; j++) {
                        if (selections[j]) {
                            if (correctAnswers[j] == Answer.CORRECT_ANSWER) {
                                correctSelected++;
                            } else {
                                incorrectSelected++;
                            }
                        }
                    }
                }

                if (correctSelected > 0) {
                    int totalUnselectedCorrect = totalCorrect - correctSelected;
                    int netCorrect = correctSelected - incorrectSelected - totalUnselectedCorrect;

                    if (netCorrect == totalCorrect) {
                        totalScore++;
                    }
                }
            }

            Toast.makeText(this, getString(R.string.your_score) + totalScore, Toast.LENGTH_SHORT).show();
            Intent resultIntent = new Intent();
            resultIntent.putExtra(LATEST_SCORE, totalScore);
            setResult(RESULT_OK, resultIntent);
            finish();
        };


    }




}