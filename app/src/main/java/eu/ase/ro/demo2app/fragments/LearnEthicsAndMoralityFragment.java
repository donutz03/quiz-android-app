package eu.ase.ro.demo2app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import eu.ase.ro.demo2app.R;


public class LearnEthicsAndMoralityFragment extends Fragment {

    LinearLayout linearLayout;
    LinearLayout linearLayout2;

    public LearnEthicsAndMoralityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_learn_ethics_and_morality,
                container, false);
        if (getContext() != null) {
            linearLayout = view.findViewById(R.id.hodoroaga_ionut_linearLayoutInsideSV);
            String largeText =
                    getString(R.string.long_text_lorem_ips);

            TextView textView = new TextView(getContext().getApplicationContext());
            textView.setText(largeText);
            linearLayout.addView(textView);
            linearLayout2 = view.findViewById(R.id.hodoroaga_ionut_lin_lay2);
            TextView textViewHSW = new TextView(getContext().getApplicationContext());
            textViewHSW.setText(largeText);
            linearLayout2.addView(textViewHSW);
        }
        return view;
    }


}