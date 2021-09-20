package br.com.caipiracreative.android.unicamp_w3_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listview);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listview, View view, int position, long id) {
                TextView textView = (TextView)view;
                Toast.makeText(MainActivity.this, "Você selecionou a " + textView.getText().toString() + " de índice " + position, Toast.LENGTH_SHORT).show();
            }
        };

        listView.setOnItemClickListener(itemClickListener);
    }
}
