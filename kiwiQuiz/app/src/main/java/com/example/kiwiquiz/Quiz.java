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

    private final int QUIZLENGTH = 60;
    //this keeps track of which questions were answered correctly and incorrectly
    private int[] answered = new int[10];
    //the array to store each completed question
    private Question[] questions = new Question[10];
    //array to store each image
    private int[] imagesArray = {R.drawable.coronet, R.drawable.cardrona,R.drawable.whakapapa,
            R.drawable.manganui,R.drawable.rainbow,R.drawable.treble,R.drawable.hamner,R.drawable.temple,R.drawable.broken,R.drawable.porters};
    //the temporary array to load all of the txt data into before passing into a question
    private String[] cityNameArray;

    private int questionNumber = 0;

    //Loading the gui elements
    private RadioButton A;
    private RadioButton B;
    private RadioButton C;
    private RadioButton D;
    private RadioGroup group;
    private TextView resortName;
    private String Answer;
    private ImageView image;


    public void readFile() throws IOException {
        //intialising the array
        cityNameArray = new String[QUIZLENGTH];
        //The code to load in the .txt file
        String assetFileName = "Answers.txt";
        AssetManager am = getAssets();
        InputStream is = am.open(assetFileName);
        InputStreamReader ir = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(ir);
        String newCity;
        //needed this int to increment through the array as the while loop has no loop number
        int i = 0;
        while ((newCity = br.readLine()) != null) {
            cityNameArray[i] = newCity;
            i++;
        }
        i=0;
        //Loading the questions with data
        for (int j = 0; j < QUIZLENGTH; j+=6) {
            questions[i] = new Question(cityNameArray[0 + j],cityNameArray[1 + j],cityNameArray[2 + j],
                    cityNameArray[3 + j], cityNameArray[4 + j],cityNameArray[5 + j]);
            i++;
        }
        br.close();
    }
    //the method to update the text on screen and set all radiobuttons to unclicked
    public void populateQuestions() {
        A.setText(questions[questionNumber].getQuestionA());
        B.setText(questions[questionNumber].getQuestionB());
        C.setText(questions[questionNumber].getQuestionC());
        D.setText(questions[questionNumber].getQuestionD());
        resortName.setText("Which of these is the closest to " + questions[questionNumber].getNameOfResort());
        image.setBackground(getResources().getDrawable(imagesArray[questionNumber]));

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
        //getting the ids
         A = findViewById(R.id.radioButtonA);
         B = findViewById(R.id.radioButtonB);
         C = findViewById(R.id.radioButtonC);
         D = findViewById(R.id.radioButtonD);
         group = findViewById(R.id.radioGroup);
         resortName = findViewById(R.id.questionText);
         image = findViewById(R.id.PictureOfPlace);
         //creating onclick listeners
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

    //This is the method that pops up the alerts once a user has answered a question
    public void alertBuilder(String input, Boolean next) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Quiz.this);
        //set the text on the top of the dialog box
        dialog.setMessage(input);
        //checks that the final question hasnt been answered yet
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
        //if final question has been answered, will display this text box
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

    //the code for once a radio button has been clicked
    @Override
    public void onClick(View v) {
        //checks that the quiz isnt finished
        if(questionNumber<questions.length) {
            //checks which id has been pressed (cant use previously defined as It wants a constant id)
            switch (group.getCheckedRadioButtonId()) {

                case R.id.radioButtonA:
                    //checks if the strings are equal
                    if (A.getText().equals(Answer)) {
                        //displays the alert, with the approriate information
                        alertBuilder("Correct, " + questions[questionNumber].getNameOfResort() + " is found close to " + A.getText(), Boolean.TRUE);
                        //sets the current questions position in the answered array to one, allows for question tracking
                        answered[questionNumber] = 1;
                        break;
                    } else
                        //displays the alert providing feedback to the user
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
                    answered[questionNumber] = 0;
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
            //increments the question number
            questionNumber++;
            //checks that the quiz isnt finished, before populating next question on screen
            if(questionNumber != questions.length) {
                populateQuestions();
            }
        }
    }
}
