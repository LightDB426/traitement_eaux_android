package com.lebtssio.traitement_eaux2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ImageView releveStation;
    Intent intentSuite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listeStation);
        Cursor listeStation;
    }

    protected void onResume(){
        super.onResume();

        releveStation = (ImageView)this.findViewById(R.id.btStation);

        releveStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentSuite = new Intent(MainActivity.this, ListeStation.class);
                MainActivity.this.startActivityForResult(intentSuite, 0);
            }
        });
    }
}
