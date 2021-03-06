package com.example.joakes.xbox_sidekick.presenters_old;

import android.view.View;
import android.widget.TextView;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.models.Game;
import com.example.joakes.xbox_sidekick.custom_views.CircularProgressBar;
import com.example.joakes.xbox_sidekick.custom_views.ImageTextView;

/**
 * Created by joakes on 6/9/15.
 */
public class GameInfoPresenter {
    private Game mGame;

    public GameInfoPresenter(Game game){
        mGame = game;
    }

    //TODO refactor one method per
    public void present(TextView gameNameTextView, ImageTextView gameAchievementsImageTextview,
                        ImageTextView gamerscoreImageTextview, CircularProgressBar gamerscoreProgressBar) {
        gameNameTextView.setText(mGame.getName());
        gameAchievementsImageTextview.setImageAndTextIfValid(mGame.getEarnedAchievements(), mGame.getTotalAchivements(), R.drawable.ic_trophy);
        gamerscoreImageTextview.setImageAndTextIfValid(mGame.getEarnedGamerscore(), mGame.getTotalGamerscore(), R.drawable.ic_gamerscore);
        presentGamerscoreProgress(gamerscoreProgressBar);
    }

    private void presentGamerscoreProgress(CircularProgressBar gamerscoreProgressBar) {
        if(mGame.getEarnedGamerscore() < 0 || mGame.getTotalGamerscore() < 0){
            gamerscoreProgressBar.setVisibility(View.GONE);
        } else {
            int progress = (int)(100f * mGame.getEarnedGamerscore() / mGame.getTotalGamerscore());
            gamerscoreProgressBar.setProgress(progress);
        }
    }
}
