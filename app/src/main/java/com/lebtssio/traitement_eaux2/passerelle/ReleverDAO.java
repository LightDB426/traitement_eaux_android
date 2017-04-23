package com.lebtssio.traitement_eaux2.passerelle;

import android.content.Context;
import android.database.Cursor;

import java.util.Calendar;
import java.util.TimeZone;

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
    public static final String relever_signature = "signature";

    public static Cursor getReleverStation(Context ct, long idS){
        BdSQLiteOpenHelper accesDB = ConnexionDAO.getAccesDB(ct);
        Cursor curseur;
        String req = "select idR as _id, libelleC, qteEntreer, qteSortir, uniteC, numA, numM, numJ, signature " +
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
        curseur.close();
        return nom;
    }

    public static Boolean insertRelever(Context ct, String n, String c, String qte, String qts, String sign){
        Boolean insertMSG = false;
        String queryIdStation = "";
        String queryIDC = "";
        String countRelever = "";
        int ParseCountRelever = 0;

        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        BdSQLiteOpenHelper acces = ConnexionDAO.getAccesDB(ct);

        String getIDS = "select idS from Station where nomS = '" + n +"'";
        Cursor Cnom = acces.getReadableDatabase().rawQuery(getIDS, null);
        if (Cnom.moveToFirst()){
            queryIdStation = Cnom.getString(0);
        }
        Cnom.close();

        String getidC = "select idC from critere where libelleC = '" + c +"'";
        Cursor Cidc = acces.getReadableDatabase().rawQuery(getidC, null);
        if (Cidc.moveToFirst()){
            queryIDC = Cidc.getString(0);
        }
        Cidc.close();

        String getCountRel = "select COUNT(idR) from relever";
        Cursor CcountRel = acces.getReadableDatabase().rawQuery(getCountRel, null);
        if (CcountRel.moveToFirst()){
            countRelever = CcountRel.getString(0);
            ParseCountRelever = Integer.parseInt(countRelever);
            ParseCountRelever = ParseCountRelever + 1;
        }
        CcountRel.close();

        String sql = "insert into relever(idR, numA, idS, numM, numJ, idC, qteEntreer, qteSortir) values ("+ParseCountRelever+","+year+","+queryIdStation+","
                    +month+","+day+","+queryIDC+","+qte+","+qts+")";
        try {
            acces.getWritableDatabase().execSQL(sql);
            insertMSG = true;
        }
        catch (Exception e){
            insertMSG = false;
        }

        return insertMSG;
    }
}
