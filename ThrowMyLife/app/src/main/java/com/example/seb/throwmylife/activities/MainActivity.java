package com.example.seb.throwmylife.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.seb.throwmylife.R;
import com.example.seb.throwmylife.models.PlainScore;
import com.example.seb.throwmylife.utils.LeaderboardHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LeaderboardHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button playButton = (Button) findViewById(R.id.buttonPlay);
        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                startActivity(intent);
            }
        });

        final Button leaderboardButton = (Button) findViewById(R.id.buttonLeaderboard);
        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                startActivity(intent);
            }
        });

        System.out.println("Got here!");
        try {
            db = new LeaderboardHelper(this);
            List<PlainScore> scores = db.getAllHighscores();
            System.out.println("Chaos test: " + scores.get(0).getScore());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
