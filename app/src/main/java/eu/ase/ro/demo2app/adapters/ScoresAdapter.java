package eu.ase.ro.demo2app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eu.ase.ro.demo2app.R;

public class ScoresAdapter extends RecyclerView.Adapter<ScoresAdapter.ScoreViewHolder> {

    private List<Integer> scoresList;

    public ScoresAdapter(List<Integer> scoresList) {
        this.scoresList = scoresList;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        int score = scoresList.get(position);
        holder.textViewScore.setText(holder.textViewScore.getContext().getApplicationContext().getString(R.string.previous_score) + score);
    }

    @Override
    public int getItemCount() {
        return scoresList.size();
    }

    static class ScoreViewHolder extends RecyclerView.ViewHolder {
        TextView textViewScore;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewScore = itemView.findViewById(android.R.id.text1);
        }
    }
}
