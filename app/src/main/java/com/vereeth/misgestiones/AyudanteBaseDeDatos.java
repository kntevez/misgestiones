package com.vereeth.misgestiones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AyudanteBaseDeDatos extends SQLiteOpenHelper {
    private static final String REGISTROS_BD = "registros_bd",
            TABLA_REGISTROS = "registros";
    private static final int VERSION_BD = 1;

    public AyudanteBaseDeDatos(Context context) {
        super(context, REGISTROS_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, dia int, mes int, ano int, positivos int, negativos int, portas int, da int, horas int, cumplimiento int, rph int, efectividad int)", TABLA_REGISTROS));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
