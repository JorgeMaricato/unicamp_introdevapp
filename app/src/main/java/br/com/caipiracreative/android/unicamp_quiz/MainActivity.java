package br.com.caipiracreative.android.unicamp_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner level_selection;
    private Button start_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        level_selection = (Spinner) findViewById(R.id.difficulties);
        start_button = (Button) findViewById(R.id.start_button);

        start_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //LevelManagement lvlMan = new LevelManagement(this);
                //lvlMan.setChosenDifficulty(level_selection.getSelectedItem().toString());
                //String level_selection_number = level_selection.getSelectedItem().toString();

                Intent activityChangeIntent = new Intent(MainActivity.this, Question.class);
                activityChangeIntent.putExtra("level_selection_text", level_selection.getSelectedItem().toString());

                MainActivity.this.startActivity(activityChangeIntent);
            }
        });
    }


}
