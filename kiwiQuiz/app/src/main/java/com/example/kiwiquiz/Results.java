package com.example.kiwiquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Results extends AppCompatActivity implements View.OnClickListener{

    private int[] scoresStorage = new int[10];
    private int scoreInt = 0;
    private String answeredCorrectly = "";
    private String answeredIncorrectly = "";

    private TextView score;
    private TextView incorrect;
    private TextView correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent scores = getIntent();
        Bundle extra = scores.getBundleExtra("test");
        scoresStorage = extra.getIntArray("score");
        scoreInt = 0;

        Button restart = (Button) findViewById(R.id.Restart);
        score = findViewById(R.id.ScoreText);
        incorrect = findViewById(R.id.AnsweredIncorrectly);
        correct =  findViewById(R.id.answeredCorrectly);

        calculateScore(scoresStorage);
        restart.setOnClickListener(this);
    }

    private void calculateScore(int[] total) {
        for(int i = 0; i < total.length; i++) {
            if(total[i] == 1) {
                scoreInt++;
                answeredCorrectly = answeredCorrectly.concat((i + 1) + ", ");
            }
            else {
                answeredIncorrectly = answeredIncorrectly.concat((i + 1) + ", ");
            }
        }
        answeredCorrectly = (answeredCorrectly.length() > 0)? answeredCorrectly.substring(0,answeredCorrectly.length() - 2): "no questions";
        answeredIncorrectly = (answeredIncorrectly.length() > 0)?answeredIncorrectly.substring(0,answeredIncorrectly.length() - 2):"no questions";

        correct.setText("You answered  " + answeredCorrectly + " correctly");
        incorrect.setText("You answered  " + answeredIncorrectly + " incorrectly");
        score.setText("You scored " + scoreInt + " out of " + total.length);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Restart:
                Intent restart = new Intent(Results.this, Quiz.class);
                startActivity(restart);
                break;
        }
    }
}

