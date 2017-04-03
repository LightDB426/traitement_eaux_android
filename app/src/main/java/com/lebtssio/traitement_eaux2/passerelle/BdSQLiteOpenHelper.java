package com.lebtssio.traitement_eaux2.passerelle;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LeonBr on 15/03/2017.
 */

public class BdSQLiteOpenHelper extends SQLiteOpenHelper{
    private String station = "CREATE TABLE Station ("
            + "idS INTEGER NOT NULL,"
            + "nomS TEXT NULL,"
            + "PRIMARY KEY(idS)"
            + ")";

    private String annee = "CREATE TABLE annee ("
            + "numA INTEGER NOT NULL,"
            + "idS INTEGER NOT NULL,"
            + "introA TEXT NULL,"
            + "conclusionA TEXT NULL,"
            + "PRIMARY KEY (numA, idS),"
            + "FOREIGN KEY (idS) REFERENCES Station(idS)"
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
            + "idR INTEGER NOT NULL,"
            + "numA INTEGER NOT NULL,"
            + "idS INTEGER NOT NULL,"
            + "numM INTEGER NOT NULL,"
            + "numJ INTEGER NOT NULL,"
            + "idC INTEGER NOT NULL,"
            + "qteEntreer INTEGER NULL,"
            + "qteSortir INTEGER NULL,"
            + "PRIMARY KEY (idR, numA, idS, numM, numJ, idC),"
            + "FOREIGN KEY (numA) REFERENCES mois(numA),"
            + "FOREIGN KEY (idS) REFERENCES mois(idS),"
            + "FOREIGN KEY (numM) REFERENCES mois(numM),"
            + "FOREIGN KEY (idC) REFERENCES critere(idC)"
            + ")";

    private String critere = "CREATE TABLE critere (" +
            "idC INTEGER NOT NULL,"
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
        db.execSQL("insert into Station(idS, nomS) values(1, 'Canal de lalinde')");
        db.execSQL("insert into Station(idS, nomS) values(2, 'Esat de Puch')");
        db.execSQL("insert into Station(idS, nomS) values(3, 'Landerrouat')");
        db.execSQL("insert into Station(idS, nomS) values(4, 'Station des vignes du Sud-Ouest')");

        db.execSQL("insert into annee(numA, idS, introA, conclusionA) values(2017, 3, 'Nouvelle an', 'Aucune idee')");

        db.execSQL("insert into mois (numA, idS, numM, remarqueM) values(1, 3, 1, 'Aucune')");

        db.execSQL("insert into critere (idC, libelleC, uniteC) values (1, 'Eau', 'm3')");
        db.execSQL("insert into critere (idC, libelleC, uniteC) values (2, 'DCO', 'mg/l')");
        db.execSQL("insert into critere (idC, libelleC, uniteC) values (3, 'MES', 'mg/l')");

        //table relever (numA, idS, numM, numJ, idC, qte, qts)
        db.execSQL("insert into relever(idR, numA, idS, numM, numJ, idC, qteEntreer, qteSortir) values (1, 1, 3, 1, 11, 1, 2408, 51509)");
        db.execSQL("insert into relever(idR, numA, idS, numM, numJ, idC, qteEntreer, qteSortir) values (2, 1, 3, 1, 11, 2, 7340, 73)");
        db.execSQL("insert into relever(idR, numA, idS, numM, numJ, idC, qteEntreer, qteSortir) values(3, 1, 3, 1, 11, 3, 581, 55)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
