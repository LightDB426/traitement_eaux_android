package com.lebtssio.traitement_eaux2.passerelle;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.lebtssio.traitement_eaux2.metier.station;

import java.util.ArrayList;

/**
 * Created by LeonBr on 15/03/2017.
 */

public class StationDAO {
    public static final String STATION_KEY = "_id";
    public static final String STATION_nomS = "nomS";

    public static station getStation(long id, Context ct) {

        BdSQLiteOpenHelper accessBD = ConnexionDAO.getAccessBD(ct);
        station laStation = null;
        Cursor curseur;
        curseur = accessBD.getReadableDatabase().rawQuery("SELECT * from station where id=" + id + ";", null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            laStation = new station (id, curseur.getString(1));
        }
        return laStation;
    }

    public static Cursor getCursorStation(Context ct) {
        BdSQLiteOpenHelper accesDB = ConnexionDAO.getAccessBD(ct);
        Cursor curseur;
        String req = "select idS as _id, nomS from station";
        curseur = accesDB.getReadableDatabase().rawQuery(req, null);

        return curseur;
    }



    /**
     * récupère tous les stations dans une collection de stations
     * @return ArrayList d'objets Station
     */
    public static ArrayList<station> getArrayStation(Context ct) {
        BdSQLiteOpenHelper accesBD = ConnexionDAO.getAccessBD(ct);
        Cursor curseur;
        String req = "select * from station;";
        curseur = accesBD.getReadableDatabase().rawQuery(req, null);
        return cursorToStationArrayList(curseur);
    }

    /**
     * Crée un ArrayList de stations à partir d'un curseur de stations
     * @param curseur le curseur de stations
     * @return ArrayList de station
     */
    private static ArrayList<station> cursorToStationArrayList(Cursor curseur) {
        ArrayList<station> listeStation = new ArrayList<station>();
        long idC;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            idC = curseur.getLong(0);

            listeStation.add(new station(idC, curseur.getString(1)));
            curseur.moveToNext();
        }
        return listeStation;
    }


}
