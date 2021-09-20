package br.com.caipiracreative.android.unicamp_w3_spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner)findViewById(R.id.spinner);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View selectedItemView, int position, long id) {
                String txt = ((TextView)selectedItemView).getText().toString();
                Toast.makeText(MainActivity.this, "Você selecionou a " + txt + " de índice " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }

    /* Chamado quando o button é clicado */
    public void onClick(View view) {
        String string = String.valueOf(spinner.getSelectedItem());
        int position = spinner.getSelectedItemPosition();

        Toast.makeText(this, "Você selecionou a " + string + " de índice " + position, Toast.LENGTH_SHORT).show();
    }
}
