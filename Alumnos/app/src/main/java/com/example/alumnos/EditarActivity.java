package com.example.alumnos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alumnos.DB.DbAlumnos;
import com.example.alumnos.entidades.Alumnos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombre, txtApellidos, txtSexo, txtFecha, txtMatriculaA;
    Button btnEditar, btnEliminar;
    FloatingActionButton fabGuardar;
    boolean correcto = false;
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
        btnEditar.setVisibility(View.INVISIBLE);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnEliminar.setVisibility(View.INVISIBLE);
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

        final DbAlumnos dbAlumnos = new DbAlumnos(EditarActivity.this);
        alumno = dbAlumnos.verAlumno(matricula);

        if(alumno != null){
            txtMatriculaA.setText(alumno.getMatriculaA());
            txtNombre.setText(alumno.getNombre());
            txtApellidos.setText(alumno.getApellidos());
            txtSexo.setText(alumno.getSexo());
            txtFecha.setText(alumno.getFecha());

        }
        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") && !txtApellidos.getText().toString().equals("")){
                    correcto = dbAlumnos.editarAlumnos(matricula, txtMatriculaA.getText().toString(),txtNombre.getText().toString(),txtApellidos.getText().toString(),txtSexo.getText().toString(),txtFecha.getText().toString());

                    if (correcto ){
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_SHORT).show();
                        verRegistro();
                    }else{
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(EditarActivity.this, "LLENE TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("MATRICULA", matricula);
        startActivity(intent);
    }
}