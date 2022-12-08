package com.example.alumnos.DB;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.alumnos.entidades.Alumnos;

import java.util.ArrayList;

public class DbAlumnos extends bdHelper {

    Context context;

    public DbAlumnos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertAlumnos(String matriculaA,String nombre, String apellidos, String sexo, String fecha)
    {
        long matricula = 0;

        try {
            bdHelper bdHelper = new bdHelper(context);
            SQLiteDatabase db = bdHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("matriculaA", matriculaA);
            values.put("nombre", nombre);
            values.put("apellidos", apellidos);
            values.put("sexo", sexo);
            values.put("fechaNa",fecha);

            matricula = db.insert(TABLE_ALUMNOS, null, values);

        }catch (Exception ex){
            ex.toString();
        }


        return matricula;
    }

    public ArrayList<Alumnos> mostrarAlumnos(){
        bdHelper dbHelper = new bdHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Alumnos> listaAlumnos = new ArrayList<>();
        Alumnos alumnos = null;
        Cursor cursorAlumnos = null;

        cursorAlumnos = db.rawQuery("SELECT * FROM " + TABLE_ALUMNOS, null);

        if (cursorAlumnos.moveToFirst()){
            do{
                alumnos = new Alumnos();
                alumnos.setMatricula(cursorAlumnos.getInt(0));
                alumnos.setMatriculaA(cursorAlumnos.getString(1));
                alumnos.setNombre(cursorAlumnos.getString(2));
                alumnos.setApellidos(cursorAlumnos.getString(3));
                alumnos.setSexo(cursorAlumnos.getString(4));
                alumnos.setFecha(cursorAlumnos.getString(5));

                listaAlumnos.add(alumnos);
            } while (cursorAlumnos.moveToNext());
        }
        cursorAlumnos.close();
        return listaAlumnos;
    }

    //ver Alumnos

    public Alumnos verAlumno(int matricula){
        bdHelper dbHelper = new bdHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Alumnos> listaAlumno = new ArrayList<>();
        Alumnos alumnos = null;
        Cursor cursorAlumnos;

        cursorAlumnos = db.rawQuery("SELECT * FROM " + TABLE_ALUMNOS + " WHERE matricula = " + matricula + " LIMIT 1 ", null);

        if (cursorAlumnos.moveToFirst()){

            alumnos = new Alumnos();
            alumnos.setMatricula(cursorAlumnos.getInt(0));
            alumnos.setMatriculaA(cursorAlumnos.getString(1));
            alumnos.setNombre(cursorAlumnos.getString(2));
            alumnos.setApellidos(cursorAlumnos.getString(3));
            alumnos.setSexo(cursorAlumnos.getString(4));
            alumnos.setFecha(cursorAlumnos.getString(5));



        }
        cursorAlumnos.close();
        return alumnos;
    }

    //editar
    public boolean editarAlumnos(int matricula, String matriculaA, String nombre, String apellidos, String sexo, String fecha)
    {
        boolean correcto = false;

        bdHelper bdHelper = new bdHelper(context);
        SQLiteDatabase db = bdHelper.getWritableDatabase();

        try {
            db.execSQL(" UPDATE " + TABLE_ALUMNOS + " SET nombre = '" + nombre + "',matriculaA = '" + matriculaA + "', apellidos = '" + apellidos + "', sexo = '" + sexo + "' , fechaNa = '" + fecha + "' WHERE matricula = '" + matricula +"' ");
            correcto = true;

        }catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }


        return correcto;
    }

    public boolean eliminarAlumno(int matricula)
    {
        boolean correcto = false;

        bdHelper bdHelper = new bdHelper(context);
        SQLiteDatabase db = bdHelper.getWritableDatabase();

        try {
            db.execSQL(" DELETE FROM " + TABLE_ALUMNOS + " WHERE matricula = '" + matricula + "' ");
            correcto = true;

        }catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }


        return correcto;
    }

}
