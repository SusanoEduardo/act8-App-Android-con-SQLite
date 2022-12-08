package com.example.alumnos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.alumnos.DB.DbAlumnos;
import com.example.alumnos.entidades.Alumnos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText txtNombre, txtApellidos, txtSexo, txtFecha, txtMatriculaA;
    Button btnEditar, btnEliminar;
    FloatingActionButton fabGuardar;


    Alumnos alumno;
    int matricula = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtMatriculaA = (EditText)findViewById(R.id.txt_matricula);
        txtNombre = (EditText) findViewById(R.id.txt_nombre);
        txtApellidos = (EditText) findViewById(R.id.txt_Apellidos);
        txtSexo = (EditText) findViewById(R.id.txtSexo);
        txtFecha = (EditText) findViewById(R.id.txtFecha);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);


        fabGuardar = findViewById(R.id.fabGuardar);

        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                matricula = Integer.parseInt(null);
            }else{
                matricula = extras.getInt("MATRICULA");
            }
        }else {
            matricula = (int) savedInstanceState.getSerializable("MATRICULA");
        }

        DbAlumnos dbAlumnos = new DbAlumnos(VerActivity.this);
        alumno = dbAlumnos.verAlumno(matricula);

        if(alumno != null){

            txtMatriculaA.setText(alumno.getMatriculaA());
            txtNombre.setText(alumno.getNombre());
            txtApellidos.setText(alumno.getApellidos());
            txtSexo.setText(alumno.getSexo());
            txtFecha.setText(alumno.getFecha());
            fabGuardar.setVisibility(View.INVISIBLE);

            txtMatriculaA.setInputType(InputType.TYPE_NULL);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtApellidos.setInputType(InputType.TYPE_NULL);
            txtSexo.setInputType(InputType.TYPE_NULL);
            txtFecha.setInputType(InputType.TYPE_NULL);
        }

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent .putExtra("MATRICULA", matricula);
                startActivity(intent);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿Desea elimiar este alumno?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbAlumnos.eliminarAlumno(matricula)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }
    private void lista(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}