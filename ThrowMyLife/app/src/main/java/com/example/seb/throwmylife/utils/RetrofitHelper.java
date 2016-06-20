package com.example.seb.throwmylife.utils;

import com.example.seb.throwmylife.models.PlainScore;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Seb on 20/06/16.
 */
public class RetrofitHelper {

    public static void postNewScore(PlainScore plainScore) {

        final String BASE_URL = "http://192.168.0.11:2403/highscores/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EndpointsInterface apiService = retrofit.create(EndpointsInterface.class);

//        PlainScore score1 = new PlainScore("Nick", 7331, 2013);
        Call<PlainScore> call = apiService.addHighscore(plainScore);

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


    public static void getAllScores() {

        final String BASE_URL = "http://192.168.0.11:2403/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EndpointsInterface apiService = retrofit.create(EndpointsInterface.class);

        Call<List<PlainScore>> call = apiService.getHighscores();

        call.enqueue(new Callback<List<PlainScore>>() {
            @Override
            public void onResponse(Call<List<PlainScore>> call, Response<List<PlainScore>> response) {
                List<PlainScore> scores = response.body();

                System.out.println("Got all scores: \n ");

                for (PlainScore score : scores) {
                    System.out.println(score);
                }
            }

            @Override
            public void onFailure(Call<List<PlainScore>> call, Throwable t) {


            }
        });
    }

}
