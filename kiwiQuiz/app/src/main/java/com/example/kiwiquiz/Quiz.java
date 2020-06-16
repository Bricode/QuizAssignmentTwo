package com.example.kiwiquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Quiz extends AppCompatActivity implements View.OnClickListener{

    private Question[] questions = new Question[10];
    private int score = 0;
    private int questionNumber = 0;
    private String[] cityNameArray;
    private RadioButton A;
    private RadioButton B;
    private RadioButton C;
    private RadioButton D;
    private TextView resortName;
    private String Answer;

    public void readFile(RadioButton A, RadioButton B,RadioButton C,RadioButton D, TextView resortName, String Answer) throws IOException {
        cityNameArray = new String[6];
        String assetFileName = "Answers.txt";
        AssetManager am = getAssets();
        InputStream is = am.open(assetFileName);
        InputStreamReader ir = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(ir);
        String newCity;
        System.out.println(br.readLine());
        int i = 0;
        while ((newCity = br.readLine()) != null) {
            cityNameArray[i] = newCity;
            i++;
        }
        for (int j = 0; j < 6; j+=6) {
            A.setText(cityNameArray[0 + j]);
            B.setText(cityNameArray[1 + j]);
            C.setText(cityNameArray[2 + j]);
            D.setText(cityNameArray[3 + j]);
            resortName.setText("What is the closest city to " + cityNameArray[4 + j]);
            Answer = cityNameArray[5 + j];
        }
        System.out.println(cityNameArray[0]);
        System.out.println(cityNameArray[1]);
        System.out.println(cityNameArray[2]);
        System.out.println(cityNameArray[3]);
        System.out.println(cityNameArray[4]);
        System.out.println(cityNameArray[5]);
        br.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
         A = findViewById(R.id.radioButtonA);
         B = findViewById(R.id.radioButtonB);
         C = findViewById(R.id.radioButtonC);
         D = findViewById(R.id.radioButtonD);
         resortName = findViewById(R.id.questionText);
        A.setOnClickListener(this);
        B.setOnClickListener(this);
        C.setOnClickListener(this);
        D.setOnClickListener(this);
        try {
            readFile(A,B,C,D, resortName, Answer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        if(A.isChecked() == true) {
            System.out.println(A.getText() + " " + cityNameArray[5]);
            if (A.getText().equals(cityNameArray[5])) {
                System.out.println("correct answer given");
                Toast.makeText( this, "Correct", Toast.LENGTH_LONG).show();
            }
            else
            Toast.makeText( this, "Wrong", Toast.LENGTH_LONG).show();
        }
    }
}
