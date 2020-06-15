package com.example.kiwiquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = (Button) findViewById(R.id.button);
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent changeActivity = new Intent(MainActivity.this, Quiz.class);
        startActivity(changeActivity);
    }


}
