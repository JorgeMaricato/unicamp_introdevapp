package br.com.caipiracreative.android.unicamp_quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.logging.Level;

public class Question extends AppCompatActivity {

    private TextView header;
    private TextView question;
    private RadioGroup answersGroup;
    private Button check_answer;

    private TextView outputTerminal;


    private LevelManagement lvlManag;
    private Integer totalQuestion;
    private Integer currentQuestion;
    private Integer totalScore;

    private Integer questionId, answersId, correctAnswerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        outputTerminal = (TextView) findViewById(R.id.outputTerminal);

        header = (TextView) findViewById(R.id.header);
        question = (TextView) findViewById(R.id.question);
        answersGroup = (RadioGroup) findViewById(R.id.answers);
        check_answer = (Button) findViewById(R.id.check_answer);


        lvlManag = new LevelManagement(this);
        lvlManag.setChosenDifficulty(getIntent().getExtras().getString("level_selection_text"));

        this.setup();
        this.setQuestion();

        check_answer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Resources res = getResources();
                String isCorrectText;
                Boolean isCorrect = isTheAnswerCorrect();

                if(isCorrect)
                    isCorrectText = res.getString(R.string.answer_right);
                else
                    isCorrectText = res.getString(R.string.answer_wrong);

                String isCorrect_title = String.format(res.getString(R.string.question_title), currentQuestion, totalQuestion);

                new AlertDialog.Builder(Question.this)
                        .setTitle(isCorrect_title)
                        .setMessage(isCorrectText)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){

                            public void onClick(DialogInterface dialog, int whitchButton) {
                                Boolean isCorrect = isTheAnswerCorrect();

                                if(isCorrect)
                                    totalScore++;

                                if(currentQuestion == totalQuestion)
                                    openDialogScore();
                                else
                                    nextQuestion();
                            }
                        }).show();
                        //.setNegativeButton(android.R.string.no, null).show();

            }
        });
    }

    private void setup() {
        this.totalQuestion = lvlManag.getNQuestions();
        this.currentQuestion = 1;
        this.totalScore = 0;
    }

    private Boolean isTheAnswerCorrect() {
        Integer selectedId = answersGroup.getCheckedRadioButtonId();

        RadioButton selectedAnswer = (RadioButton) findViewById(selectedId);
        String selected_text = selectedAnswer.getText().toString();

        Resources res = getResources();
        String correct_text = res.getString(correctAnswerId);

        return selected_text.equals(correct_text);
    }

    private void nextQuestion(){
        resetAnswers();
        currentQuestion++;
        setQuestion();

        Resources res = getResources();
        String question_title = String.format(res.getString(R.string.question_title), currentQuestion, totalQuestion);
        Toast.makeText(Question.this,  question_title, Toast.LENGTH_SHORT).show();
    }

    private void setQuestion(){
        Resources res = getResources();
        questionId = res.getIdentifier("question_"+currentQuestion, "string", this.getPackageName());
        answersId = res.getIdentifier("answers_"+currentQuestion, "array", this.getPackageName());
        correctAnswerId = res.getIdentifier("correct_"+currentQuestion, "string", this.getPackageName());

        String question_title = String.format(res.getString(R.string.question_title), currentQuestion, totalQuestion);
        header.setText(question_title);

        String question_text = res.getString(questionId);
        String[] answers_text = res.getStringArray(answersId);

        question.setText(question_text);
        for (int i = 0; i < answersGroup .getChildCount(); i++) {
            ((RadioButton) answersGroup.getChildAt(i)).setText(answers_text[i]);
        }

    }

    private void resetAnswers(){
        answersGroup.clearCheck();
    }

    private void openDialogScore(){
        Resources res = getResources();
        String score_title = res.getString(R.string.final_score_title);
        String score_text = String.format(res.getString(R.string.final_score_text), totalScore, totalQuestion);

        new AlertDialog.Builder(Question.this)
                .setTitle(score_title)
                .setMessage(score_text)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton(R.string.ending_text, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int whitchButton) {
                        openActivityMain();
                    }
                }).show();
    }

    private void openActivityMain(){
        Intent activityChangeIntent = new Intent(Question.this, MainActivity.class);

        Question.this.startActivity(activityChangeIntent);
    }

    private void openActivityScore(){

        //Intent activityChangeIntent = new Intent(Question.this, Score.class);

        //Question.this.startActivity(activityChangeIntent);
    }

}
