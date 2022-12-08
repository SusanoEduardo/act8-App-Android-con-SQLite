package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;


import com.example.alumnos.DB.DbAlumnos;
import com.example.alumnos.adaptadores.ListaAlumnosAdapter;
import com.example.alumnos.entidades.Alumnos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView txtBuscar;
    RecyclerView listaAlumnos;
    FloatingActionButton fabNuevo;
    ArrayList<Alumnos> listaArrayAlumnos;
    ListaAlumnosAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBuscar = findViewById(R.id.txtBuscar);
        listaAlumnos = findViewById(R.id.listaAlumnos);

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            listaAlumnos.setLayoutManager(new GridLayoutManager(this, 1));
        }else {
            listaAlumnos.setLayoutManager(new GridLayoutManager(this, 2));
        }
        fabNuevo = findViewById(R.id.fabNuevo);

        listaAlumnos.setLayoutManager(new LinearLayoutManager(this));

        DbAlumnos dbAlumnos = new DbAlumnos(MainActivity.this);

        listaArrayAlumnos = new ArrayList<>();

        adapter= new ListaAlumnosAdapter(dbAlumnos.mostrarAlumnos());
        listaAlumnos.setAdapter(adapter);



        fabNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoRegistro();
            }
        });

        txtBuscar.setOnQueryTextListener(this);

    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, Insertar.class);
        startActivity(intent);
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtradro(s);
        return false;
    }
}