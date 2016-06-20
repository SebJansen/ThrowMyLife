package com.example.seb.throwmylife.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.LauncherApps;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import com.example.seb.throwmylife.models.PlainScore;
import com.example.seb.throwmylife.models.Score;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

// new imports


public class LeaderboardHelper extends SQLiteOpenHelper {

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LeaderboardDB";

    private static final String TABLE_SCORES = "scores";

    protected static final String KEY_ID = "id";
    protected static final String KEY_PLAYER = "player";
    protected static final String KEY_SCORE = "score";
    protected static final String KEY_DATE = "date";

    private static final String[] COLUMNS = {KEY_ID, KEY_PLAYER, KEY_SCORE, KEY_DATE};

    public LeaderboardHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private List<PlainScore> allHighscores;

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SCORES_TABLE = "CREATE TABLE " + TABLE_SCORES + " ( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_PLAYER + " TEXT, " +
                KEY_SCORE + " INTEGER, " +
                KEY_DATE + " INTEGER )";
        db.execSQL(CREATE_SCORES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
        this.onCreate(db);
    }

    public void addScore(Score score) {
        Log.d("Add score", score.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_PLAYER, score.getPlayer());
        values.put(KEY_SCORE, score.getScore());
        values.put(KEY_DATE, System.currentTimeMillis());

        db.insert(TABLE_SCORES,
                null,
                values);

        db.close();
        Log.d("Add score of " + score.getPlayer(), score.toString());
    }


//    public Score getScore(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_SCORES,
//                COLUMNS,
//                " id = ?",
//                new String[]{String.valueOf(id)},
//                null,
//                null,
//                null,
//                null);
//        if (cursor != null) {
//            cursor.moveToFirst();
//        }
//
//        Score score = new Score();
//        score.setId(cursor.getInt(0));
//        score.setPlayer(cursor.getString(1));
//        score.setScore(cursor.getInt(2));
//        score.setDate(cursor.getInt(3));
//
//        Log.d("Get Score " + id + "", score.toString());
//
////        db.close();
////        cursor.close();
//
//        return score;
//
//    }

    public List<Score> getAllScores() {
        List<Score> scores = new LinkedList<Score>();

        String query = "SELECT * FROM " + TABLE_SCORES;
        Log.d("QUERY", query.toString());

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Score score = null;

        if (cursor.moveToFirst()) {
            do {
                score = new Score();
                score.setId(cursor.getInt(0));
                score.setPlayer(cursor.getString(1));
                score.setScore(cursor.getInt(2));
                score.setDate(cursor.getInt(3));

                scores.add(score);

            } while (cursor.moveToNext());
        }

        Log.d("Get all scores", scores.toString());

        db.close(); //

        return scores;

    }

    //alt
    public Cursor getAllScoresCursor() {
        List<Score> scores = new LinkedList<Score>();

        String query = "SELECT " + KEY_ID + " AS _id, " +
                KEY_PLAYER + ", " +
                KEY_SCORE + ", " +
                KEY_DATE + " " +
                " FROM " + TABLE_SCORES +
                " ORDER BY " + KEY_SCORE +
                " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Score score = null;

        if (cursor.moveToFirst()) {
            do {
                score = new Score();
                score.setId(cursor.getInt(0));
                score.setPlayer(cursor.getString(1));
                score.setScore(cursor.getInt(2));
                score.setDate(cursor.getInt(3));

                scores.add(score);

            } while (cursor.moveToNext());
        }

        Log.d("Get all scores cursor", scores.toString());

//        cursor.close();
//        db.close();

        return cursor;
    }

//    public int updateScore(Score score) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put("player", score.getPlayer());
//        values.put("score", score.getScore());
//        values.put("date", score.getDate());
//
//        int i = db.update(TABLE_SCORES,
//                values,
//                KEY_ID + " = ?",
//                new String[]{String.valueOf(score.getId())});
//
//        db.close();
//
//        return i;
//    }

    public void deleteScore(long id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_SCORES,
                KEY_ID + " = ?",
                new String[]{String.valueOf(id)});

        db.close();

    }

    public void deleteAllScores() {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_SCORES, null, null);

        db.close();

        Log.d("Delete all scores", "from table");
    }

    public static String[] GetColumns() {
        return COLUMNS;
    }

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }

    public static String getKeyDate() {
        return KEY_DATE;
    }

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getKeyPlayer() {
        return KEY_PLAYER;
    }

    public static String getKeyScore() {
        return KEY_SCORE;
    }

    public static String getTableScores() {
        return TABLE_SCORES;
    }

//    public void run() throws Exception {
//        Request request = new Request.Builder()
//                .url("http://publicobject.com/helloworld.txt")
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//                Headers responseHeaders = response.headers();
//                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
//                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                }
//
//                System.out.println(response.body().string());
//            }
//        });
//    }

    public void acquireAllHighscores() throws Exception {
        Request request = new Request.Builder()
                .url("http://192.168.0.11:2403/highscores")
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                String jsonArray = response.body().string();

                Type listType = new TypeToken<ArrayList<PlainScore>>() {
                }.getType();

                List<PlainScore> yourClassList = new Gson().fromJson(jsonArray, listType);

                System.out.println("Poseidon test: " + yourClassList.get(0).getPlayerName());

                allHighscores = new Gson().fromJson(jsonArray, listType);

                System.out.println("Criterion test: " + allHighscores.get(0).getPlayerName());
            }

        });
    }

    public List<PlainScore> getAllHighscores() throws Exception {

        if (allHighscores != null) {
            return allHighscores;
        } else {
            acquireAllHighscores();
            return allHighscores;
        }
    }
}

