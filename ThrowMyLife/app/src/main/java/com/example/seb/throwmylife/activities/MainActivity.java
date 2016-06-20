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

        System.out.println("Got here!");
//        try {
//            db = new LeaderboardHelper(this);
//            List<PlainScore> scores = db.getAllHighscores();
//            System.out.println("Chaos test: " + scores.get(0).getScore());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        final String BASE_URL = "http://192.168.0.11:2403/highscores/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EndpointsInterface apiService = retrofit.create(EndpointsInterface.class);

        PlainScore score1 = new PlainScore("Nick", 7331, 2013);
        Call<PlainScore> call = apiService.addHighscore(score1);

        call.enqueue(new Callback<PlainScore>() {
            @Override
            public void onResponse(Call<PlainScore> call, Response<PlainScore> response) {
                System.out.println("Posted some " + response.toString());
            }

            @Override
            public void onFailure(Call<PlainScore> call, Throwable t) {


            }
        });
    }
}
