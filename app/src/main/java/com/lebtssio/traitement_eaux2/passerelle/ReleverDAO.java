package com.lebtssio.traitement_eaux2.passerelle;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by LeonBr on 03/04/2017.
 */

public class ReleverDAO {
    public static final String relever_idR = "idR";
    public static final String relever_numA = "numA";
    public static final String relever_idS = "idS";
    public static final String relever_numM = "numM";
    public static final String relever_numJ = "numJ";
    public static final String relever_idC = "idC";
    public static final String RELEVER_libelleC = "libelleC";
    public static final String relever_qteEntreer = "qteEntreer";
    public static final String relever_qteSortir = "qteSortir";
    public static final String relever_uniteC = "uniteC";

    public static Cursor getReleverStation(Context ct, long idS){
        BdSQLiteOpenHelper accesDB = ConnexionDAO.getAccesDB(ct);
        Cursor curseur;
        String req = "select idR as _id, libelleC, qteEntreer, qteSortir, uniteC, numA, numM, numJ " +
                     "from relever inner join critere on relever.idC = critere.idC " +
                     "where idS = " + idS + " and " +
                     "numA = (select MAX(numA) from mois) and " +
                     "numM = (select MAX(numM) from mois where numA = (select MAX(numA) from mois)) and " +
                     "numJ = (select MAX(numJ) from relever)";
        curseur = accesDB.getReadableDatabase().rawQuery(req, null);

        return curseur;
    }

    public static String getDateDernierRelever(Context ctn, long idS){
        BdSQLiteOpenHelper accesDb = ConnexionDAO.getAccesDB(ctn);
        Cursor curseur;
        String nom = "";
        String req = "select numJ, numM, numA " +
                "from relever inner join critere on relever.idC = critere.idC " +
                "where idS = " + idS + " and " +
                "numA = (select MAX(numA) from mois) and " +
                "numM = (select MAX(numM) from mois where numA = (select MAX(numA) from mois)) and " +
                "numJ = (select MAX(numJ) from relever)";

        curseur = accesDb.getReadableDatabase().rawQuery(req, null);
        if (curseur.moveToFirst()){
            nom = curseur.getString(0) +  "/" + curseur.getString(1) + "/" + curseur.getString(2);
        }
        return nom;
    }
}
