package com.lebtssio.traitement_eaux2.metier;

/**
 * Created by dylan.brault on 27/03/2017.
 */
public class Critere {

    private long idC;
    private String libelleC;
    private String uniteC;

    public Critere(long idC, String libelleC, String uniteC) {
        this.idC = idC;
        this.libelleC = libelleC;
        this.uniteC = uniteC;
    }

    public String getLibelleC() {
        return libelleC;
    }

    public void setLibelleC(String libelleC) {
        this.libelleC = libelleC;
    }

    public String getUniteC() {
        return uniteC;
    }

    public void setUniteC(String uniteC) {
        this.uniteC = uniteC;
    }

    public long getIdC() {
        return idC;
    }

    public void setIdC(long idC) {
        this.idC = idC;
    }
}
