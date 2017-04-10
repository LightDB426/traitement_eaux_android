package com.lebtssio.traitement_eaux2.passerelle;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by LeonBr on 15/03/2017.
 */

public class StationDAO {
    public static final String STATION_id = "idS";
    public static final String STATION_nomS = "nomS";

    public static Cursor getCursorStation(Context ct){
        BdSQLiteOpenHelper accesDB = ConnexionDAO.getAccesDB(ct);
        Cursor curseur;
        String req = "select idS as _id, nomS from Station";
        curseur = accesDB.getReadableDatabase().rawQuery(req, null);
        return curseur;
    }

    public static String getNomStation(Context ct, long id){
        BdSQLiteOpenHelper accesdb = ConnexionDAO.getAccesDB(ct);
        String sql = "select nomS from Station where idS = " + id;
        Cursor res = accesdb.getReadableDatabase().rawQuery(sql, null);
        String nomS = "";
        if(res.moveToFirst()){
            nomS = res.getString(0);
        }
        res.close();
        return nomS;
    }

    public static int getNbrStation(Context ct){
        BdSQLiteOpenHelper acces = ConnexionDAO.getAccesDB(ct);
        String sql = "select nomS from Station";
        Cursor res = acces.getReadableDatabase().rawQuery(sql, null);
        res.moveToFirst();
        int i = 0;
        while (!res.isAfterLast()){
            i = i + 1;
            res.moveToNext();
        }
        res.close();
        return i;
    }

    public static String[] getListeStation(Context ct){
        BdSQLiteOpenHelper accesDB = ConnexionDAO.getAccesDB(ct);
        String sql = "select nomS from Station";
        Cursor res = accesDB.getReadableDatabase().rawQuery(sql, null);
        int i = 0;
        int i2 = 0;
        i = getNbrStation(ct);
        String[] mArrayList = new String[i];
        res.moveToFirst();
        while (!res.isAfterLast()){
            mArrayList[i2] = res.getString(0);
            res.moveToNext();
            i2++;
        }
        res.close();

        return mArrayList;
    }
}
