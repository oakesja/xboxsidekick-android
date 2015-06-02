package com.example.joakes.xbox_sidekick;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.views.ImageTextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by joakes on 5/11/15.
 */
public class XboxGameAdapter extends RecyclerView.Adapter<XboxGameAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<XboxGame> mGames;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.game_name_textview)
        TextView gameNameTextView;
        @InjectView(R.id.game_achievements_image_textview)
        ImageTextView acheivementsImageTextView;
        @InjectView(R.id.game_score_image_textview)
        ImageTextView scoreImageTextView;
        @InjectView(R.id.game_imageview)
        ImageView gameImageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }

    public XboxGameAdapter(Context context) {
        mContext = context;
        mGames = new ArrayList<>();
    }

    @Override
    public XboxGameAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        XboxGame game = gameAt(position);
        holder.gameNameTextView.setText(game.getName());
        String achievements = String.format("%d/%d", game.getEarnedAchievements(), game.getTotalAchivements());
        holder.acheivementsImageTextView.setImageAndTextIfValid(achievements, R.drawable.ic_trophy);
        String score = String.format("%d/%d", game.getEarnedGamerscore(), game.getTotalGamerscore());
        holder.scoreImageTextView.setImageAndTextIfValid(score, R.drawable.ic_gamerscore);
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

    public void addGames(ArrayList<XboxGame> games) {
        mGames.addAll(games);
        this.notifyDataSetChanged();
    }

    private XboxGame gameAt(int position) {
        return mGames.get(position);
    }
}