package com.lebtssio.traitement_eaux2.passerelle;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.lebtssio.traitement_eaux2.metier.Station;

import java.util.ArrayList;

/**
 * Created by LeonBr on 15/03/2017.
 */

public class StationDAO {
    public static final String STATION_KEY = "_id";
    public static final String STATION_nomS = "nomS";

    public static Station getStation(long id, Context ct) {

        BdSQLiteOpenHelper accessBD = ConnexionDAO.getAccessBD(ct);
        Station laStation = null;
        Cursor curseur;
        curseur = accessBD.getReadableDatabase().rawQuery("SELECT * from station where id=" + id + ";", null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            laStation = new Station (id, curseur.getString(1));
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
    public static ArrayList<Station> getArrayStation(Context ct) {
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
    private static ArrayList<Station> cursorToStationArrayList(Cursor curseur) {
        ArrayList<Station> listeStation = new ArrayList<Station>();
        long idC;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            idC = curseur.getLong(0);

            listeStation.add(new Station(idC, curseur.getString(1)));
            curseur.moveToNext();
        }
        return listeStation;
    }


}
