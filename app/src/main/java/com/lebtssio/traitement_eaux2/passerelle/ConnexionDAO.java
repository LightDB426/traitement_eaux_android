package com.lebtssio.traitement_eaux2.passerelle;

import android.content.Context;

/**
 * Created by LeonBr on 15/03/2017.
 */

public class ConnexionDAO {
    private static BdSQLiteOpenHelper accesBD;
    private static String base = "BDExemple";
    private static int version = 1;

    /**
     * initialise une instance de la classe BdSQLiteOpenHelper
     *
     * @param ct un objet de la classe Context
     */
    private ConnexionDAO(Context ct) {
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    /**
     *
     * @param ct un objet de la classe Context
     * @return un objet permettant l'accès à la BD "BDExemple"
     */
    public static BdSQLiteOpenHelper getAccessBD(Context ct) {
        if (accesBD == null) {
            accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
        }
        return accesBD;
    }
}
