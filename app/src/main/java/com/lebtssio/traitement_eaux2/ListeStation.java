package com.lebtssio.traitement_eaux2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.lebtssio.traitement_eaux2.metier.Station;
import com.lebtssio.traitement_eaux2.passerelle.ReleverDAO;
import com.lebtssio.traitement_eaux2.passerelle.StationDAO;

public class ListeStation extends AppCompatActivity {
    Cursor listeRelever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_station);

        ListView listView = (ListView) findViewById(R.id.listeStation);

        Cursor listeStation;

        listeStation = StationDAO.getCursorStation(this);

        String[] listeChamps = new String[]{StationDAO.STATION_nomS};

        int[] vueID = new int[]{R.id.nom_station};

        SimpleCursorAdapter monCursorAdapter = new SimpleCursorAdapter(
                getBaseContext(),
                R.layout.vue_station_list,
                listeStation,
                listeChamps,
                vueID,
                0
        );
        listView.setAdapter(monCursorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //on instancie un objet de type Intent qui précisera l'activité à démarrer
                Intent intentSuite = new Intent(ListeStation.this, GestionReleve.class);
                //on passe l'id du client qui a été sélectionné
                intentSuite.putExtra("id", id);
                //on démarre l'activité qui affichera le détail du client sélectionné
                ListeStation.this.startActivity(intentSuite);

            }
        });

        listeRelever = ReleverDAO.getReleverStation(this, 3);
        if (listeRelever.getCount() != 0){
            Toast.makeText(ListeStation.this, "ok" , Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(ListeStation.this, "Rien", Toast.LENGTH_SHORT).show();
        }

    }
}
