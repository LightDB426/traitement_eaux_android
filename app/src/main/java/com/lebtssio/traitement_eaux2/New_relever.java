package com.lebtssio.traitement_eaux2;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lebtssio.traitement_eaux2.R;
import com.lebtssio.traitement_eaux2.metier.SignatureCapture;
import com.lebtssio.traitement_eaux2.metier.SignatureDessine;
import com.lebtssio.traitement_eaux2.passerelle.ReleverDAO;
import com.lebtssio.traitement_eaux2.passerelle.StationDAO;

import org.xmlpull.v1.XmlPullParser;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by LeonBr on 07/04/2017.
 */

public class New_relever extends AppCompatActivity{
    AutoCompleteTextView actv;
    AutoCompleteTextView actvCritere;
    LinearLayout l1;
    SignatureCapture drawSignature;
    String signature;
    EditText qte;
    EditText qts;
    String nomStation;
    String uniteCritere;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_relever);

        actv = (AutoCompleteTextView)findViewById(R.id.new_id_station);
        actvCritere = (AutoCompleteTextView)findViewById((R.id.type_critere));

        String[] stations;
        String[] critere;
        String[] unite;

        stations = StationDAO.getListeStation(this);
        critere = new String[] {"EAU", "DCO", "MES"};
        unite = new String[] {"m3", "mg/l"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, stations);
        actv.setAdapter(adapter);

        ArrayAdapter<String> adapterCritere = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, critere);
        actvCritere.setAdapter(adapterCritere);
        l1 = (LinearLayout)findViewById(R.id.id_draw_signature);
        drawSignature = new SignatureCapture(getBaseContext(), null);
        l1.addView(drawSignature);
    }

    @Override
    protected void onResume(){
        super.onResume();
        Button btValider = (Button)findViewById(R.id.btValider);
        qte = (EditText)findViewById(R.id.input_qte);
        qts = (EditText)findViewById(R.id.input_qtS);

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nomStation = (String)parent.getItemAtPosition(position);
            }
        });

        actvCritere.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                uniteCritere = (String)parent.getItemAtPosition(position);
            }
        });

        btValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signature = drawSignature.convertToString();
                //Toast.makeText(New_relever.this, signature , Toast.LENGTH_SHORT).show();

                String qteE = qte.getText().toString();
                String qteS = qts.getText().toString();

                Boolean msg = ReleverDAO.insertRelever(New_relever.this, nomStation, uniteCritere, qteE, qteS, signature);
                if (msg){
                    Toast.makeText(New_relever.this, "Insertion ok !" , Toast.LENGTH_LONG).show();
                    New_relever.this.finish();
                }
                else {
                    Toast.makeText(New_relever.this, "Veuillez remplir tous les champs svp !" , Toast.LENGTH_LONG).show();
                }


            }
        });

    }

}
