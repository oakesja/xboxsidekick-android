//package com.example.joakes.xbox_sidekick.activities;
//
//import android.test.ActivityInstrumentationTestCase2;
//import android.util.Log;
//
//import com.robotium.solo.Solo;
//
//import static org.assertj.android.api.Assertions.assertThat;
//
///**
// * Created by joakes on 5/17/15.
// */
//public class GameActivityTest extends ActivityInstrumentationTestCase2<GameActivity> {
//    private Solo solo;
//    private GameActivity activity;
//
//    public GameActivityTest() {
//        super(GameActivity.class);
//    }
//
//    @Override
//    protected void setUp() throws Exception {
//        activity = getActivity();
//        solo = new Solo(getInstrumentation(), activity);
//        solo.unlockScreen();
//        solo.assertCurrentActivity("Should start on GameActivity", GameActivity.class);
//        Log.i("setup", "setup");
//    }
//
//    public void testProfileVisible() {
////        String profileName = "PoizonOakes92";
////        Assert.assertTrue(solo.searchText(profileName));
////        assertTextView((TextView) solo.getView(R.id.game_score)).hasAnyText();
////        assertThat(solo.getView(R.id.gamer_picture)).isVisible();
//    }
//
//    public void testGamesVisible() {
////        assertTextView((TextView) solo.getView(R.id.game_name)).hasAnyText();
////        Assert.assertNotSame("", ((ImageTextView) solo.getView(R.id.game_score)).getText());
////        assertTextView((TextView) solo.getView(R.id.game_achievements)).hasAnyText();
//    }
//
//    public void testClickingOnGame(){
////        View v = solo.getView(R.id.game_name, 0);
////        solo.clickOnView(v);
////        solo.assertCurrentActivity("Should go to achievements activity", AchievementsActivity.class);
//    }
//}