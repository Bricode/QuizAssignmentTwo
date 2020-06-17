package com.example.kiwiquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Quiz extends AppCompatActivity implements View.OnClickListener{

    private Integer[] answered = new Integer[10];

    private Question[] questions = new Question[2];
    private int score = 0;
    private int questionNumber = 0;
    private String[] cityNameArray;
    private RadioButton A;
    private RadioButton B;
    private RadioButton C;
    private RadioButton D;
    private RadioGroup group;
    private TextView resortName;
    private String Answer;

    public void readFile() throws IOException {
        cityNameArray = new String[12];
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
        i=0;
        for (int j = 0; j < 12; j+=6) {
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
        resortName.setText("What is the closest city to " + questions[questionNumber].getNameOfResort());
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
                    Intent finish = new Intent(Quiz.this, MainActivity.class);
                    startActivity(finish);
                }
            });
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        if(questionNumber<questions.length) {
            System.out.println(questionNumber);
            System.out.println(questions.length);
            switch (group.getCheckedRadioButtonId()) {
                case R.id.radioButtonA:
                    if (A.getText().equals(Answer)) {
                        Toast.makeText(this, "Correct," + questions[questionNumber].getNameOfResort() + " is found close to " + A.getText(), Toast.LENGTH_LONG).show();
                        alertBuilder("Correct," + questions[questionNumber].getNameOfResort() + " is found close to " + A.getText(), Boolean.TRUE);
                        score++;
                        answered[questionNumber] = 1;
                        break;
                    } else
                        Toast.makeText(this, "Wrong", Toast.LENGTH_LONG).show();
                        alertBuilder("Wrong" + questions[questionNumber].getNameOfResort() + " is found close to " + Answer, Boolean.TRUE);
                    answered[questionNumber] = 0;
                    break;
                case R.id.radioButtonB:
                    if (B.getText().equals(Answer)) {
                        alertBuilder("Correct," + questions[questionNumber].getNameOfResort() + " is found close to " + B.getText(), Boolean.TRUE);
                        Toast.makeText(this, "Correct", Toast.LENGTH_LONG).show();
                        score++;
                        alertBuilder("Correct", Boolean.TRUE);
                        answered[questionNumber] = 1;
                        break;
                    } else
                        Toast.makeText(this, "Wrong", Toast.LENGTH_LONG).show();
                        alertBuilder("Wrong" + questions[questionNumber].getNameOfResort() + " is found close to " + Answer, Boolean.TRUE);
                        answered[questionNumber] = 0;
                    break;
                case R.id.radioButtonC:
                    if (C.getText().equals(Answer)) {
                        alertBuilder("Correct," + questions[questionNumber].getNameOfResort() + " is found close to " + C.getText(), Boolean.TRUE);
                        Toast.makeText(this, "Correct", Toast.LENGTH_LONG).show();
                        score++;
                        answered[questionNumber] = 1;
                        break;
                    } else
                        Toast.makeText(this, "Wrong", Toast.LENGTH_LONG).show();
                        alertBuilder("Wrong" + questions[questionNumber].getNameOfResort() + " is found close to " + Answer, Boolean.TRUE);
                    break;
                case R.id.radioButtonD:
                    if (D.getText().equals(Answer)) {
                        alertBuilder("Correct," + questions[questionNumber].getNameOfResort() + " is found close to " + D.getText(), Boolean.TRUE);
                        Toast.makeText(this, "Correct", Toast.LENGTH_LONG).show();
                        score++;
                        answered[questionNumber] = 1;
                        break;
                    } else
                        Toast.makeText(this, "Wrong", Toast.LENGTH_LONG).show();
                    alertBuilder("Wrong" + questions[questionNumber].getNameOfResort() + " is found close to " + Answer, Boolean.TRUE);
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
