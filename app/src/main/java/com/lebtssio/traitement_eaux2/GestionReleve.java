package com.lebtssio.traitement_eaux2;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lebtssio.traitement_eaux2.passerelle.ReleverDAO;
import com.lebtssio.traitement_eaux2.passerelle.StationDAO;

/**
 * Created by LeonBr on 20/03/2017.
 */

public class GestionReleve extends AppCompatActivity {
    TextView x;
    Cursor listeRelever;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.les_relever);
    }

    @Override
    protected void onResume(){
        super.onResume();
        long choix = this.getIntent().getExtras().getLong("id");

        listeRelever = ReleverDAO.getReleverStation(this, choix);
        if (listeRelever.getCount() != 0){
            Toast.makeText(GestionReleve.this, "des resultats", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(GestionReleve.this, "Rien", Toast.LENGTH_SHORT).show();
        }
        TextView info_last_date = (TextView)findViewById(R.id.info_last_date);
        info_last_date.setText("Liste des derniers relevés de la station " + StationDAO.getNomStation(this, choix));
        ListView listView = (ListView) findViewById(R.id.relever_list);

        String[] listeChamps = new String[]{ReleverDAO.RELEVER_libelleC, ReleverDAO.relever_qteEntreer, ReleverDAO.relever_qteSortir, ReleverDAO.relever_uniteC};
        int[] vuiID = new int[]{R.id.libelle, R.id.qteE, R.id.qteS, R.id.unite};

        SimpleCursorAdapter monC = new SimpleCursorAdapter(
                getBaseContext(),
                R.layout.liste_relever_station,
                listeRelever,
                listeChamps,
                vuiID,
                0
        );
        listView.setAdapter(monC);
    }
}
