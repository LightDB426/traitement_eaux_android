package com.lebtssio.traitement_eaux2.passerelle;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LeonBr on 15/03/2017.
 */

public class BdSQLiteOpenHelper extends SQLiteOpenHelper{
    private String station = "CREATE TABLE station ("
            + "idS INTEGER NOT NULL,"
            + "nomS TEXT NULL,"
            + "PRIMARY KEY(idS)"
            + ")";

    private String annee = "CREATE TABLE annee ("
            + "numA INTEGER NOT NULL,"
            + "idS INTEGER NOT NULL,"
            + "introA INTEGER,"
            + "conclusionA TEXT NULL,"
            + "PRIMARY KEY (numA, idS),"
            + "FOREIGN KEY (idS) REFERENCES station(idS)"
            + ")";

    private String mois = "CREATE TABLE mois ("
            + "numA INTEGER NULL,"
            + "idS INTEGER  NOT NULL,"
            + "numM INTEGER NOT NULL,"
            + "remarqueM TEXT NULL,"
            + "PRIMARY KEY (numA, idS, numM),"
            + "FOREIGN KEY (numA) REFERENCES annee(numA),"
            + "FOREIGN KEY (idS) REFERENCES annnee(idS)"
            + ")";

    private String relever = "CREATE TABLE relever ("
            + "numA INTEGER NOT NULL,"
            + "idS INTEGER NOT NULL,"
            + "numM INTEGER NOT NULL,"
            + "numJ INTEGER NOT NULL,"
            + "idC INTEGER NOT NULL,"
            + "qteEntreer INTEGER NULL,"
            + "qteSortir INTEGER NULL,"
            + "PRIMARY KEY (numA, idS, numM, numJ, idC),"
            + "FOREIGN KEY (numA) REFERENCES mois(numA),"
            + "FOREIGN KEY (idS) REFERENCES mois(idS),"
            + "FOREIGN KEY (numM) REFERENCES mois(numM),"
            + "FOREIGN KEY (idC) REFERENCES critere(idC)"
            + ")";

    private String critere = "CREATE TABLE critere (" +
            "idC NOT NULL,"
            + "libelleC TEXT NULL,"
            + "uniteC TEXT NULL,"
            + "PRIMARY KEY (idC)"
            + ")";

    public BdSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BdSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(station);
        db.execSQL(annee);
        db.execSQL(mois);
        db.execSQL(critere);
        db.execSQL(relever);

        //insertion de lignes
        db.execSQL("insert into station(idS, nomS) values(1, 'Canal de lalinde')");
        db.execSQL("insert into station(idS, nomS) values(2, 'Esat de Puch')");
        db.execSQL("insert into station(idS, nomS) values(3, 'Landerrouat')");
        db.execSQL("insert into station(idS, nomS) values(4, 'Station des vignes du Sud-Ouest')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
