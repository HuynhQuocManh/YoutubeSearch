package com.huynhquocmanh.youtubesearch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class playvideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    int REQUEST_VIDEO = 123;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playvideo);
        youTubePlayerView = findViewById(R.id.myYoutube);
        youTubePlayerView.initialize(MainActivity.API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        youTubePlayer.cueVideo(MainActivity.idvideo);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(playvideoActivity.this, REQUEST_VIDEO);
        } else {
            Toast.makeText(this, "ERROR!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO) {
            youTubePlayerView.initialize(MainActivity.API_KEY, playvideoActivity.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
