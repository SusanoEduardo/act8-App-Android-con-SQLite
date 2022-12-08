package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alumnos.DB.DbAlumnos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class Insertar extends AppCompatActivity implements View.OnClickListener {

    EditText txtNombre, txtApellidos, txtSexo,txtFecha, txtMatriculaA;
    FloatingActionButton fabGuardar;
    private int dia, mes, ano;
    FloatingActionButton flbCalendario;

    private CheckBox c1,c2;
    private String ch1="", ch2="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        txtMatriculaA= (EditText)findViewById(R.id.txt_matricula);
        txtNombre = (EditText)findViewById(R.id.txt_nombre);
        txtApellidos = (EditText)findViewById(R.id.txt_Apellidos);
        txtSexo = (EditText)findViewById(R.id.txtSexo);
        txtFecha= (EditText)findViewById(R.id.txtFecha);
        fabGuardar= findViewById(R.id.fabGuardar);
        c1= (CheckBox) findViewById(R.id.checkbox1);
        c2= (CheckBox) findViewById(R.id.checkbox2);

        flbCalendario= findViewById(R.id.flbCalendario);
        flbCalendario.setOnClickListener(this);

        txtFecha.setInputType(InputType.TYPE_NULL);
        txtSexo.setVisibility(View.INVISIBLE);

        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbAlumnos dbAlumnos = new DbAlumnos(Insertar.this);
                long matri = dbAlumnos.insertAlumnos(
                        txtMatriculaA.getText().toString(),
                        txtNombre.getText().toString(),
                        txtApellidos.getText().toString(),
                        txtSexo.getText().toString(),
                        txtFecha.getText().toString());


                if (matri > 0){
                    Toast.makeText(Insertar.this, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
                    limpiar();
                }else{
                    Toast.makeText(Insertar.this, "LA MATRICULA YA EXISTE", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void c1(View view){
        if (c1.isChecked()==true)
            ch1="Masculino";
        else
            ch1=" ";
        txtSexo.setText(ch1);
    }

    public void c2(View view){
        if (c2.isChecked()==true)
            ch2="Femenino";
        else
            ch2=" ";

        txtSexo.setText(ch2);
    }
    private void limpiar(){
        txtNombre.setText("");
        txtApellidos.setText("");
        txtSexo.setText("");
        txtFecha.setText("");
        txtMatriculaA.setText("");
    }

    @Override
    public void onClick(View view) {
        if(view == flbCalendario){
            final Calendar c = Calendar.getInstance();
            dia= c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    txtFecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            }
                    ,dia, mes,ano);
            datePickerDialog.show();
        }
    }
}