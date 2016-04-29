package com.example.seb.throwmylife.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.seb.throwmylife.R;
import com.example.seb.throwmylife.models.Score;
import com.example.seb.throwmylife.utils.LeaderboardHelper;

import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    private SimpleCursorAdapter simpleCursorAdapter;
    private LeaderboardHelper db;
    private List<Score> scores;
    private ListView listScores;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        listScores = (ListView) findViewById(R.id.list_scores);

        db = new LeaderboardHelper(this);
        scores = db.getAllScores();

        String[] columns = new String[]{db.getKeyPlayer(), db.getKeyScore()};
        int[] to = new int[]{R.id.leaderboard_player, R.id.leaderboard_score};

        if (getIntent().hasExtra("player") && getIntent().hasExtra("score")) {

            String playerExtra = getIntent().getStringExtra("player");
            int scoreExtra = getIntent().getIntExtra("score", -1);

            Score score = new Score(playerExtra, scoreExtra);
            db.addScore(score);

        }

        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.leaderboard_row_item, db.getAllScoresCursor(), columns, to, 0);
        listScores.setAdapter(simpleCursorAdapter);

        final Button clearButton = (Button) findViewById(R.id.leaderboard_clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                db.deleteAllScores();
                simpleCursorAdapter.changeCursor(db.getAllScoresCursor());

                Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
