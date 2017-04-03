package com.lebtssio.traitement_eaux2;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lebtssio.traitement_eaux2.passerelle.ReleverDAO;

/**
 * Created by LeonBr on 20/03/2017.
 */

public class GestionReleve extends AppCompatActivity {
    TextView x;
    Cursor listeRelever;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_relever_station);
    }

    @Override
    protected void onResume(){
        super.onResume();
        long choix = this.getIntent().getExtras().getLong("id");

        x = (TextView)this.findViewById(R.id.last_info_station);
        x.setText(String.valueOf(choix));

        listeRelever = ReleverDAO.getReleverStation(this, choix);
    }
}
