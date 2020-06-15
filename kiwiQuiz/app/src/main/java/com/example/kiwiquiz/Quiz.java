package com.example.kiwiquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;

public class Quiz extends AppCompatActivity {

    private Question[] questions = new Question[10];
    private int score = 0;
    private int questionNumber=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

    }
}
