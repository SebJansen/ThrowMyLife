package com.example.seb.throwmylife.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.seb.throwmylife.models.Score;

import java.util.LinkedList;
import java.util.List;

public class LeaderboardHelper extends SQLiteOpenHelper {

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
}

