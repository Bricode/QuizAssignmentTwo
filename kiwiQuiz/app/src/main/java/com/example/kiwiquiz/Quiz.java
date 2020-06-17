package com.example.kiwiquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Quiz extends AppCompatActivity implements View.OnClickListener{

    private int[] answered = new int[10];
    private Question[] questions = new Question[10];
    private int questionNumber = 0;
    private String[] cityNameArray;
    private RadioButton A;
    private RadioButton B;
    private RadioButton C;
    private RadioButton D;
    private RadioGroup group;
    private TextView resortName;
    private String Answer;
    private ImageView image;



    public void readFile() throws IOException {
        cityNameArray = new String[60];
        String assetFileName = "Answers.txt";
        AssetManager am = getAssets();
        InputStream is = am.open(assetFileName);
        InputStreamReader ir = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(ir);
        String newCity;
        int i = 0;
        while ((newCity = br.readLine()) != null) {
            cityNameArray[i] = newCity;

            System.out.println(i);
            System.out.println(cityNameArray[i]);
            i++;
        }
        i=0;
        for (int j = 0; j < 60; j+=6) {
            questions[i] = new Question(cityNameArray[0 + j],cityNameArray[1 + j],cityNameArray[2 + j],cityNameArray[3 + j], cityNameArray[4 + j],cityNameArray[5 + j]);
            i++;
        }
        br.close();
    }
    public void populateQuestions() {
        A.setText(questions[questionNumber].getQuestionA());
        B.setText(questions[questionNumber].getQuestionB());
        C.setText(questions[questionNumber].getQuestionC());
        D.setText(questions[questionNumber].getQuestionD());
        resortName.setText("Which of these is the closest to " + questions[questionNumber].getNameOfResort());
        Answer = questions[questionNumber].getAnswer();
        A.setChecked(false);
        B.setChecked(false);
        C.setChecked(false);
        D.setChecked(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
         A = findViewById(R.id.radioButtonA);
         B = findViewById(R.id.radioButtonB);
         C = findViewById(R.id.radioButtonC);
         D = findViewById(R.id.radioButtonD);
         group = findViewById(R.id.radioGroup);
         resortName = findViewById(R.id.questionText);
         image = findViewById(R.id.PictureOfPlace);
        A.setOnClickListener(this);
        B.setOnClickListener(this);
        C.setOnClickListener(this);
        D.setOnClickListener(this);
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        populateQuestions();
    }

    public void alertBuilder(String input, Boolean next) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Quiz.this);
        dialog.setMessage(input);
        if(next == true) {
            dialog.setPositiveButton(("Next Question"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    if(questionNumber == questions.length) {
                        alertBuilder("Finished", false);
                    }
                }
            });
        }
        else
            dialog.setPositiveButton(("Finish quiz"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    Intent finish = new Intent(Quiz.this, Results.class);
                    Bundle extra = new Bundle();
                    extra.putIntArray("score",answered);
                    finish.putExtra("test", extra);
                    startActivity(finish);
                }
            });
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        if(questionNumber<questions.length) {
            switch (group.getCheckedRadioButtonId()) {
                case R.id.radioButtonA:
                    if (A.getText().equals(Answer)) {
                        alertBuilder("Correct, " + questions[questionNumber].getNameOfResort() + " is found close to " + A.getText(), Boolean.TRUE);
                        answered[questionNumber] = 1;
                        break;
                    } else
                        alertBuilder("Wrong, " + questions[questionNumber].getNameOfResort() + " is found close to " + Answer, Boolean.TRUE);
                    answered[questionNumber] = 0;
                    break;
                case R.id.radioButtonB:
                    if (B.getText().equals(Answer)) {
                        alertBuilder("Correct, " + questions[questionNumber].getNameOfResort() + " is found close to " + B.getText(), Boolean.TRUE);
                        answered[questionNumber] = 1;
                        break;
                    } else
                        alertBuilder("Wrong, " + questions[questionNumber].getNameOfResort() + " is found close to " + Answer, Boolean.TRUE);
                        answered[questionNumber] = 0;
                    break;
                case R.id.radioButtonC:
                    if (C.getText().equals(Answer)) {
                        alertBuilder("Correct, " + questions[questionNumber].getNameOfResort() + " is found close to " + C.getText(), Boolean.TRUE);
                        answered[questionNumber] = 1;
                        break;
                    } else
                        alertBuilder("Wrong, " + questions[questionNumber].getNameOfResort() + " is found close to " + Answer, Boolean.TRUE);
                    break;
                case R.id.radioButtonD:
                    if (D.getText().equals(Answer)) {
                        alertBuilder("Correct, " + questions[questionNumber].getNameOfResort() + " is found close to " + D.getText(), Boolean.TRUE);
                        answered[questionNumber] = 1;
                        break;
                    } else
                    alertBuilder("Wrong, " + questions[questionNumber].getNameOfResort() + " is found close to " + Answer, Boolean.TRUE);
                    answered[questionNumber] = 0;
                    break;
            }
            questionNumber++;
            if(questionNumber != questions.length) {
                populateQuestions();
            }
        }
    }
}
