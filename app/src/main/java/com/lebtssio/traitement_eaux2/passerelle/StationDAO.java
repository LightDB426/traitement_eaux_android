package com.lebtssio.traitement_eaux2.passerelle;

import android.content.Context;
import android.database.Cursor;

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
}
