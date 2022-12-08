package com.example.alumnos.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class bdHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_ALUMNO = "alumnosD.db";
    public static final String TABLE_ALUMNOS = "t_alumnos";

    public bdHelper(@Nullable Context context) {
        super(context, DATABASE_ALUMNO, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ALUMNOS + "(" +
                "matricula INTEGER PRIMARY KEY AUTOINCREMENT," +
                "matriculaA TEXT NOT NULL UNIQUE," +
                "nombre TEXT NOT NULL," +
                "apellidos TEXT NOT NULL,"+
                "sexo TEXT,"+
                "fechaNa TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_ALUMNOS);
        onCreate(sqLiteDatabase);
    }
}
