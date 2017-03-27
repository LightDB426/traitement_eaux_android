package com.lebtssio.traitement_eaux2.metier;

/**
 * Created by LeonBr on 15/03/2017.
 */

public class station {
    private long idS;
    private String nomS;

    public station(long idS, String nomS) {
        this.idS = idS;
        this.nomS = nomS;
    }

    public long getIdS() {
        return idS;
    }

    public void setIdS(int idS) {
        this.idS = idS;
    }

    public String getNomS() {
        return nomS;
    }

    public void setNomS(String nomS) {
        this.nomS = nomS;
    }
}
