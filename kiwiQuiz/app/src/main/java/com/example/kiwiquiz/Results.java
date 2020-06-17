package com.example.kiwiquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Results extends AppCompatActivity implements View.OnClickListener{

    //creating the array that will be read into by the quiz intent
    private int[] scoresStorage = new int[10];
    //declaring the total score
    private int scoreInt = 0;
    //generating blank strings to read results into
    private String answeredCorrectly = "";
    private String answeredIncorrectly = "";

    //declaring the gui elements
    private TextView score;
    private TextView incorrect;
    private TextView correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //getting the data that is being passed through
        Intent scores = getIntent();
        //unpacking the bundle to get out the int array
        Bundle extra = scores.getBundleExtra("test");
        scoresStorage = extra.getIntArray("score");
        //clearing the score
        scoreInt = 0;

        //finding each view by id
        Button restart = (Button) findViewById(R.id.Restart);
        score = findViewById(R.id.ScoreText);
        incorrect = findViewById(R.id.AnsweredIncorrectly);
        correct =  findViewById(R.id.answeredCorrectly);

        calculateScore(scoresStorage);

        restart.setOnClickListener(this);
    }

    private void calculateScore(int[] total) {
        for(int i = 0; i < total.length; i++) {
            //checking if question was answered correctly
            if(total[i] == 1) {
                //incrementing score
                scoreInt++;
                //concatenating the string to look nice
                answeredCorrectly = answeredCorrectly.concat((i + 1) + ", ");
            }
            else {
                //concatenating the string to look nice
                answeredIncorrectly = answeredIncorrectly.concat((i + 1) + ", ");
            }
        }
        //cleaning up the comma left at the end of each score, and checking that at least one question was answered in/correctly
        answeredCorrectly = (answeredCorrectly.length() > 0)? answeredCorrectly.substring(0,answeredCorrectly.length() - 2): "no questions";
        answeredIncorrectly = (answeredIncorrectly.length() > 0)?answeredIncorrectly.substring(0,answeredIncorrectly.length() - 2):"no questions";

        //applying each string to screen
        correct.setText("You answered  " + answeredCorrectly + " correctly");
        incorrect.setText("You answered  " + answeredIncorrectly + " incorrectly");
        score.setText("You scored " + scoreInt + " out of " + total.length);
    }

    //code to go to the start of the quiz
    @Override
    public void onClick(View v) {
        Intent restart = new Intent(Results.this, Quiz.class);
        startActivity(restart);


    }
}

