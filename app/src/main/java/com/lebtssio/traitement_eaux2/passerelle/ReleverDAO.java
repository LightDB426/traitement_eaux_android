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
        String req = "select idR as _id, libelleC, qteEntreer, qteSortir, uniteC, idS " +
                     "from relever inner join critere on relever.idC = critere.idC " +
                     "where idS = " + idS;
        curseur = accesDB.getReadableDatabase().rawQuery(req, null);

        return curseur;

    }
}
