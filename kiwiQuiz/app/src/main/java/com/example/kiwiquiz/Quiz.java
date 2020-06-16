package com.example.kiwiquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.widget.RadioButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Quiz extends AppCompatActivity {

    //private Question[] questions = new Question[10];
    private int score = 0;
    private int questionNumber=0;
    private String[] cityNameArray;
    public Quiz() throws IOException {
        cityNameArray = new String[4];
        String assetFileName = "Answers.txt";
        AssetManager am = getAssets();
        InputStream is = am.open(assetFileName);
        InputStreamReader ir = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(ir);
        String newCity;
        System.out.println(br.readLine());
        while ((newCity = br.readLine()) != null) {
            for(int i = 0; i < 4; i++) {
                cityNameArray[i] = newCity;
            }
            System.out.println(newCity);
        }
        System.out.println(cityNameArray[0]);
        System.out.println(cityNameArray[1]);
       /* RadioButton A = (RadioButton) findViewById(R.id.radioButtonA);
        RadioButton B = (RadioButton) findViewById(R.id.radioButtonB);
        RadioButton C = (RadioButton) findViewById(R.id.radioButtonC);
        RadioButton D = (RadioButton) findViewById(R.id.radioButtonD);
        A.setText(cityNameArray[0]);
        B.setText(cityNameArray[1]);
        C.setText(cityNameArray[2]);
        D.setText(cityNameArray[3]);*/
        br.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

    }
}
