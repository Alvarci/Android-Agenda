package com.example.jason.agenda;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class CrearContacto extends AppCompatActivity implements View.OnClickListener {

    EditText jetNombre, jetAMaterno, jetAPaterno, jetTelefono;
    RadioButton jrbHombre, jrbMujer;
    CheckBox jcbVideoJuegos, jcbLeer, jcbMusica, jcbPeliculas;
    Button jbAgregar;
    SQLiteDatabase sqld = (SQLiteDatabase)conectorDB.getDataBase();
    Intent itn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_contacto);
        jbAgregar = (Button)findViewById(R.id.xbAgregar);
        jbAgregar.setOnClickListener(this);
        jetNombre = (EditText)findViewById(R.id.xetNombre);
        jetAPaterno = (EditText)findViewById(R.id.xetApellidoPaterno);
        jetAMaterno = (EditText)findViewById(R.id.xetApellidoMaterno);
        jetTelefono = (EditText)findViewById(R.id.xetNumero);
        jrbHombre = (RadioButton)findViewById(R.id.xrbMasculino);
        jrbMujer = (RadioButton)findViewById(R.id.xrbFemenino);
        jcbVideoJuegos = (CheckBox)findViewById(R.id.xcbJugarVideojuegos);
        jcbLeer = (CheckBox)findViewById(R.id.xcbLeer);
        jcbMusica = (CheckBox)findViewById(R.id.xcbEscucharMusica);
        jcbPeliculas = (CheckBox)findViewById(R.id.xcbVerPeliculas);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.xbAgregar) {
            String nombre, aPaterno, aMaterno, telefono, sexo, hobbies = "";
            nombre = jetNombre.getText().toString();
            aPaterno = jetAPaterno.getText().toString();
            aMaterno = jetAMaterno.getText().toString();
            Cursor c = sqld.rawQuery("SELECT nombre from Contactos where nombre = '" + nombre + "' and aPaterno = '" + aPaterno +"' and aMaterno = '" + aMaterno + "'", null);
            if(c.moveToFirst()) {
                Toast t = Toast.makeText(getApplicationContext(), "Ese nombre ya existe en la agenda", Toast.LENGTH_LONG);
                t.show();
            }
            else {
                telefono = jetTelefono.getText().toString();
                if (jrbHombre.isChecked())
                    sexo = "Masculino";
                else
                    sexo = "Femenino";
                if (jcbPeliculas.isChecked())
                    hobbies += "Ver Peliculas \t";
                if (jcbMusica.isChecked())
                    hobbies += "Escuchar Musica \t";
                if (jcbLeer.isChecked())
                    hobbies += "Leer \t";
                if (jcbVideoJuegos.isChecked())
                    hobbies += "Jugar Videojuegos \t";
                ContentValues cv = new ContentValues();
                cv.put("nombre", nombre);
                cv.put("aPaterno", aPaterno);
                cv.put("aMaterno", aMaterno);
                cv.put("telefono", telefono);
                cv.put("sexo", sexo);
                cv.put("hobbies", hobbies);
                sqld.insert("Contactos", null, cv);
                Toast t = Toast.makeText(getApplicationContext(), "Se ha agregado exitosamente un contacto", Toast.LENGTH_LONG);
                t.show();
                itn = new Intent(CrearContacto.this, MainActivity.class);
                startActivity(itn);
            }
        }
    }
}
