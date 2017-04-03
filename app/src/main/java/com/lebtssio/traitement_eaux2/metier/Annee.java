package com.lebtssio.traitement_eaux2.metier;

import java.util.ArrayList;

/**
 * Created by dylan.brault on 27/03/2017.
 */
public class Annee {

    private long numA;
    private ArrayList<station> listStation = new ArrayList<>();
    private int introA;
    private String conclusionA;

    public Annee(long numA, int introA, String conclusionA) {
        this.numA = numA;
        this.introA = introA;
        this.conclusionA = conclusionA;
    }

    public long getNumA() {
        return numA;
    }

    public void setNumA(long numA) {
        this.numA = numA;
    }
/*
    public Station getListStation() {
        for (Station s : listStation){
            listStation.contains(s);
        }
    }

    public void setListStation(ArrayList<Station> listStation) {
        this.listStation = listStation;
    }
 */


    public int getIntroA() {
        return introA;
    }

    public void setIntroA(int introA) {
        this.introA = introA;
    }

    public String getConclusionA() {
        return conclusionA;
    }

    public void setConclusionA(String conclusionA) {
        this.conclusionA = conclusionA;
    }
}
