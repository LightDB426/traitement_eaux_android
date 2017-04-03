package com.lebtssio.traitement_eaux2.passerelle;

import android.content.Context;

/**
 * Created by LeonBr on 15/03/2017.
 */

public class ConnexionDAO {
    private static BdSQLiteOpenHelper accesDB;
    private static String base = "BDExemple";
    private static int version = 1;

    private ConnexionDAO(Context ct){accesDB = new BdSQLiteOpenHelper(ct, base, null, version);}

    public static BdSQLiteOpenHelper getAccesDB(Context ct){
        if (accesDB == null){
            accesDB = new BdSQLiteOpenHelper(ct, base, null, version);
        }

        return accesDB;
    }
}
