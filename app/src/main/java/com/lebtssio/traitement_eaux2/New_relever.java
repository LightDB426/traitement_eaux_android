package com.lebtssio.traitement_eaux2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.lebtssio.traitement_eaux2.R;
import com.lebtssio.traitement_eaux2.passerelle.StationDAO;

/**
 * Created by LeonBr on 07/04/2017.
 */

public class New_relever extends AppCompatActivity{
    AutoCompleteTextView actv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_relever);

        actv = (AutoCompleteTextView)findViewById(R.id.new_id_station);

        String[] stations; //new String[StationDAO.getNbrStation(this)];
        stations = StationDAO.getListeStation(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, stations);
        actv.setAdapter(adapter);
    }

}
