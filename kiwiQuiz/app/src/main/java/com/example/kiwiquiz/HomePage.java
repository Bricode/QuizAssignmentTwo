/* Name: Kiwi ski field quiz
   Author: Blake Chalmers
   Date: June 2020
   Using Android studio
   On java
 */
package com.example.kiwiquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating the onclick method for my button
        Button start = (Button) findViewById(R.id.button);
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent startQuiz = new Intent(HomePage.this, Quiz.class);
        startActivity(startQuiz);
    }
}
