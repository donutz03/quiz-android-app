package eu.ase.ro.demo2app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import eu.ase.ro.demo2app.R;
import eu.ase.ro.demo2app.adapters.ScoresAdapter;

public class HistoryOfTestScoresFragment extends Fragment {

    private static final String SCORES_KEY = "scores_key";
    private EditText editTextLatestScore;
    private RecyclerView recyclerViewScores;
    private ScoresAdapter scoresAdapter;
    private ArrayList<Integer> scoresList;

    public static HistoryOfTestScoresFragment getInstance(ArrayList<Integer> scores) {
        HistoryOfTestScoresFragment fragment = new HistoryOfTestScoresFragment();
        Bundle args = new Bundle();
        args.putIntegerArrayList(SCORES_KEY, scores);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_of_test_scores, container, false);

        editTextLatestScore = view.findViewById(R.id.hodoroaga_ionut_editTextLatestScore);
        recyclerViewScores = view.findViewById(R.id.hodoroaga_ionut_recyclerViewScores);

        if (getArguments() != null) {
            scoresList = getArguments().getIntegerArrayList(SCORES_KEY);
        } else {
            scoresList = new ArrayList<>();
        }

        scoresAdapter = new ScoresAdapter(scoresList);
        recyclerViewScores.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewScores.setAdapter(scoresAdapter);

        if (!scoresList.isEmpty()) {
            int latestScore = scoresList.get(0);
            editTextLatestScore.setText(String.valueOf(latestScore));
        }

        return view;
    }

    public void addScore(int score) {
        scoresList.add(0, score);
        editTextLatestScore.setText(String.valueOf(score));
        scoresAdapter.notifyDataSetChanged();
    }
}
