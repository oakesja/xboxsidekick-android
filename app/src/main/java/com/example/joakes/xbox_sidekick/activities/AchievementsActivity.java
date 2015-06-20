package com.example.joakes.xbox_sidekick.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.joakes.xbox_sidekick.dagger.BaseApplication;
import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.adapters.AchievementAdapter;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.models.XboxGame;
import com.example.joakes.xbox_sidekick.presenters.GameInfoPresenter;
import com.example.joakes.xbox_sidekick.requests.utils.WebService;
import com.example.joakes.xbox_sidekick.views.CircularProgressBar;
import com.example.joakes.xbox_sidekick.views.ImageTextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

public class AchievementsActivity extends AppCompatActivity {
    @InjectView(R.id.achievement_list)
    RecyclerView achievementList;
    @Inject
    WebService mWebService;
    public static final String GAME = "com.example.joakes.xbox_sidekick.game";
    public TextView gameNameTextView;
    public ImageTextView gameAchievementsImageTextview;
    public ImageTextView gamerscoreImageTextview;
    public CircularProgressBar gamerscoreProgressBar;
    private static String REQUEST_TAG = "ACHIEVEMENT_ACTIVITY";
    private AchievementAdapter mAdapter;
    private EventBus eventBus;
    private XboxGame mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivity();
        setupRecyclerView();
        setRecyclerViewHeader();
        mWebService.getAchievementsFor(mGame, REQUEST_TAG);
    }

    private void setupActivity() {
        setContentView(R.layout.activity_achievements);
        ButterKnife.inject(this);
        ((BaseApplication) getApplication()).component().inject(this);
        mGame = getIntent().getParcelableExtra(GAME);
        eventBus = EventBus.getDefault();
        mWebService = new WebService(this);
    }

    private void setupRecyclerView() {
        achievementList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        achievementList.setLayoutManager(layoutManager);
        mAdapter = new AchievementAdapter(this);
        achievementList.setAdapter(mAdapter);
    }

    private void setRecyclerViewHeader() {
        RecyclerViewHeader header = RecyclerViewHeader.fromXml(this, R.layout.game_info_view);
        header.attachTo(achievementList);
        setGameInfoViews();
        new GameInfoPresenter(mGame).present(gameNameTextView, gameAchievementsImageTextview, gamerscoreImageTextview, gamerscoreProgressBar);
    }

    private void setGameInfoViews() {
        gameNameTextView = (TextView) findViewById(R.id.game_name_textview);
        gameAchievementsImageTextview = (ImageTextView) findViewById(R.id.game_achievements_image_textview);
        gamerscoreImageTextview = (ImageTextView) findViewById(R.id.gamerscore_image_textview);
        gamerscoreProgressBar = (CircularProgressBar) findViewById(R.id.gamerscore_progress_bar);
    }

    public void onEvent(ArrayList<Achievement> achievements) {
        mAdapter.addAchievements(achievements);
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
        mWebService.stop(getClass().toString());
    }
}