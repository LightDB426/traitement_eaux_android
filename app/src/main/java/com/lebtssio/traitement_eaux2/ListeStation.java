package com.lebtssio.traitement_eaux2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.lebtssio.traitement_eaux2.passerelle.StationDAO;

public class  ListeStation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_station);

        ListView listView = (ListView) findViewById(R.id.listeStation);

        Cursor listeStation;

        listeStation = StationDAO.getCursorStation(this);

        String[] listeChamps = new String[]{StationDAO.STATION_nomS};

        int[] vueID = new int[]{R.id.TXT_nom_station};

        SimpleCursorAdapter monCursorAdapter = new SimpleCursorAdapter(
                getBaseContext(),
                R.layout.vue_station_list,
                listeStation,
                listeChamps,
                vueID,
                0
        );
        listView.setAdapter(monCursorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListeStation.this, DetailListeStation.class);
                intent.putExtra("id", id);
                ListeStation.this.startActivity(intent);

            }

        });

    }
}
