package com.example.seb.throwmylife.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.seb.throwmylife.R;
import com.example.seb.throwmylife.activities.MainActivity;
import com.example.seb.throwmylife.activities.PlayActivity;
import com.example.seb.throwmylife.activities.ScoreActivity;

public class SaveScoreQuestionDialogFragment extends DialogFragment {

    private int score;
    public PlayActivity activity;

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Do you wish to save your score?")
                .setCancelable(false)

                .setPositiveButton("Alrighty", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        showInputDialog();
                    }
                })

                .setNegativeButton("Nah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });

        return builder.create();
    }

    public void setScore(int score) {
        this.score = score;
    }

    protected void showInputDialog() {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View promptView = layoutInflater.inflate(R.layout.save_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.save_player);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(activity, ScoreActivity.class);
                        intent.putExtra("player", editText.getText().toString());
                        intent.putExtra("score", score);
                        Log.d("Step F", Integer.toString(score));
                        activity.startActivity(intent);

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void setActivity(PlayActivity activity) {
        this.activity = activity;
    }
}
