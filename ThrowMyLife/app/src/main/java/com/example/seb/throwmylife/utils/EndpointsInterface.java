package com.example.seb.throwmylife.utils;

import com.example.seb.throwmylife.models.PlainScore;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EndpointsInterface {

    @GET("highscores/")
    Call<List<PlainScore>> getHighscores();

    @POST("/highscores/")
    Call<PlainScore> addHighscore(@Body PlainScore plainScore);
}
