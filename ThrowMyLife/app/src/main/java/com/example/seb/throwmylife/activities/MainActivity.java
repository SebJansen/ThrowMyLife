package com.example.seb.throwmylife.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.seb.throwmylife.R;
import com.example.seb.throwmylife.models.PlainScore;
import com.example.seb.throwmylife.utils.EndpointsInterface;
import com.example.seb.throwmylife.utils.LeaderboardHelper;
import com.example.seb.throwmylife.utils.RetrofitHelper;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        RetrofitHelper rh = new RetrofitHelper();

        rh.postNewScore(new PlainScore("Larissa", 866123, Arrays.asList("+87.689060","-45.044636")));
//        rh.getAllScores();

    }
}
