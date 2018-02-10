package com.example.jason.agenda;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class CyM extends AppCompatActivity implements View.OnClickListener{

    Intent itn;
    EditText etNombre, etaPaterno, etaMaterno, ettelefono;
    RadioButton rbhombre, rbmujer;
    CheckBox cbPelicula, cbJuegos, cbMuscia, cbLeer;
    Button bOk;
    Bundle bdl;
    SQLiteDatabase db = (SQLiteDatabase)conectorDB.getDataBase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cy_m);
        bdl = getIntent().getExtras();
        etNombre = (EditText)findViewById(R.id.xetNombreCM);
        etNombre.append(bdl.getString("nombre"));
        etaPaterno = (EditText)findViewById(R.id.xetApellidoPaternoCM);
        etaPaterno.append(bdl.getString("aPaterno"));
        etaMaterno = (EditText)findViewById(R.id.xetApellidoMaternoCM);
        etaMaterno.append(bdl.getString("aMaterno"));
        ettelefono = (EditText)findViewById(R.id.xetNumeroCM);
        ettelefono.append(bdl.getString("telefono"));
        rbhombre = (RadioButton)findViewById(R.id.xrbMasculinoCM);
        rbmujer = (RadioButton)findViewById(R.id.xrbFemeninoCM);
        cbJuegos = (CheckBox)findViewById(R.id.xcbJugarVideojuegosCM);
        cbLeer = (CheckBox)findViewById(R.id.xcbLeerCM);
        cbPelicula = (CheckBox)findViewById(R.id.xcbVerPeliculasCM);
        cbMuscia = (CheckBox)findViewById(R.id.xcbEscucharMusicaCM);
        bOk = (Button)findViewById(R.id.xbAgregarCM);
        bOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String hobbies = "";
        ContentValues cv = new ContentValues();
        cv.put("nombre", etNombre.getText().toString());
        cv.put("aPaterno", etaPaterno.getText().toString());
        cv.put("aMaterno", etaMaterno.getText().toString());
        cv.put("telefono", ettelefono.getText().toString());
        if(rbhombre.isChecked())
            cv.put("sexo", "Masculino");
        else
            cv.put("sexo", "Femenino");
        if(cbLeer.isChecked())
            hobbies += "Leer \t";
        if(cbMuscia.isChecked())
            hobbies += "Escuchar Musica \t";
        if(cbJuegos.isChecked())
            hobbies += "Jugar Videojuegos \t";
        if(cbPelicula.isChecked())
            hobbies += "Ver Peliculas \t";
        cv.put("hobbies", hobbies);
        db.update("Contactos", cv, "nombre = '" + bdl.getString("nombreViejo") + "'", null);
        Toast t = Toast.makeText(getApplicationContext(), "Se ha modificado con éxito", Toast.LENGTH_LONG);
        t.show();
        itn = new Intent(CyM.this, MainActivity.class);
        startActivity(itn);
    }
}
