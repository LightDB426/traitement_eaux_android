package com.lebtssio.traitement_eaux2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lebtssio.traitement_eaux2.metier.station;
import com.lebtssio.traitement_eaux2.passerelle.StationDAO;

/**
 * Created by LeonBr on 20/03/2017.
 */

public class GestionReleve extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_station);
        TextView txtNomStation = (TextView) findViewById(R.id.textView);
        int id = this.getIntent().getExtras().getInt("id");
        station s = StationDAO.getStation(id, this);
        txtNomStation.setText(String.valueOf(s.getNomS()));
    }


}
