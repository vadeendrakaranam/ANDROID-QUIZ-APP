package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalQuestionsTextview;
    TextView questionTextview;
    Button ansA, ansB, ansC, ansD;
    Button sub;
    int score = 0;
    int totalQuestion = queandans.question.length;
    int index = 0;
    String selected = "";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalQuestionsTextview = findViewById(R.id.total_question);
        questionTextview = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        sub = findViewById(R.id.submit_butn);
        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        sub.setOnClickListener(this);
        totalQuestionsTextview.setText("        "+"  Total Questions:"+totalQuestion);
        loadQue();

    }

    @Override
    public void onClick(View view) {
        Button clicked = (Button) view;
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);
        if (clicked.getId() == R.id.submit_butn) {
            if(selected.equals(queandans.correctAnswers[index]))
            {
                score++;
            }
            index++;
            loadQue();

        }else {
            selected = clicked.getText().toString();
            clicked.setBackgroundColor(Color.GREEN);

        }
    }

    void loadQue() {
        if (index == totalQuestion) {
            finishQuiz();
            return;
        }
        questionTextview.setText(queandans.question[index]);
        ansA.setText(queandans.choices[index][0]);
        ansB.setText(queandans.choices[index][1]);
        ansC.setText(queandans.choices[index][2]);
        ansD.setText(queandans.choices[index][3]);

    }

    void finishQuiz() {
        String passStatus;
        if(score==totalQuestion)
        {
            passStatus="All Correct!!";
        }
        else if(score==totalQuestion-1)
        {
            passStatus="VeryGood";
        }
        else if(score==totalQuestion-2)
        {
            passStatus="Good";
        }
        else
        {
         passStatus="Do well";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+score+" out of "+totalQuestion)
                .setPositiveButton("Restart",(dialogInterface,i)->restartQuiz())
                .setCancelable(false)
                .show();

    }
void restartQuiz()
{
    score=0;
    index=0;
    loadQue();
}


}
