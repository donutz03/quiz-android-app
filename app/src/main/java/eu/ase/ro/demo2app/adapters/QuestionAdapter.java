package eu.ase.ro.demo2app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.List;

import eu.ase.ro.demo2app.R;
import eu.ase.ro.demo2app.model.Question;

public class QuestionAdapter extends ArrayAdapter {

    private HashMap<Integer, Boolean[]> userSelections;
    private Context applicationContext;
    private int resourceId;
    private List<Question> questionList;
    private LayoutInflater layoutInflater;
    public QuestionAdapter(@NonNull Context applicationContext, int resourceId,
                           @NonNull List<Question> questionList,
                           LayoutInflater layoutInflater) {
        super(applicationContext, resourceId, questionList);
        this.applicationContext = applicationContext;
        this.resourceId = resourceId;
        this.questionList = questionList;
        this.layoutInflater = layoutInflater;
        userSelections = new HashMap<>();
        for (int i = 0; i < questionList.size(); i++) {
            userSelections.put(i, new Boolean[]{false, false, false, false});
        }
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resourceId, parent, false);
        Question question = questionList.get(position);
        TextView textView = view.findViewById(R.id.hodoroaga_ionut_question_edit_text);
        textView.setText(question.getText());
        CheckBox checkBox1 = view.findViewById(R.id.hodoroaga_ionut_checkBox1);
        checkBox1.setText(question.getOptions().get(0));
        CheckBox checkBox2 = view.findViewById(R.id.hodoroaga_ionut_checkBox2);
        checkBox2.setText(question.getOptions().get(1));
        CheckBox checkBox3 = view.findViewById(R.id.hodoroaga_ionut_checkBox3);
        checkBox3.setText(question.getOptions().get(2));
        CheckBox checkBox4 = view.findViewById(R.id.hodoroaga_ionut_checkBox4);
        checkBox4.setText(question.getOptions().get(3));
        Boolean[] selections = userSelections.get(position);

        checkBox1.setChecked(selections[0]);
        checkBox2.setChecked(selections[1]);
        checkBox3.setChecked(selections[2]);
        checkBox4.setChecked(selections[3]);


        setCheckBoxListener(checkBox1, position, 0);
        setCheckBoxListener(checkBox2, position, 1);
        setCheckBoxListener(checkBox3, position, 2);
        setCheckBoxListener(checkBox4, position, 3);
        return view;
    }

    private void setCheckBoxListener(CheckBox checkBox, int questionPosition, int optionIndex) {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Boolean[] selections = userSelections.get(questionPosition);
            if (selections != null) {
                selections[optionIndex] = isChecked;
                userSelections.put(questionPosition, selections);
            }
        });
    }

    public HashMap<Integer, Boolean[]> getUserSelections() {
        return userSelections;
    }
}
