package com.example.jason.agenda;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ELiminarContacto extends AppCompatActivity implements View.OnClickListener{

    String nombre;
    EditText jetNombre;
    Button jbBorrar;
    SQLiteDatabase db = (SQLiteDatabase)conectorDB.getDataBase();
    Intent itn;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_contacto);
        jetNombre = (EditText)findViewById(R.id.xetBusquedaE);
        jbBorrar = (Button)findViewById(R.id.xbBorrarE);
        jbBorrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        nombre = jetNombre.getText().toString();
        c = db.rawQuery("SELECT nombre from Contactos where nombre = '" + nombre + "'", null);
        if(!c.moveToFirst()) {
            Toast t = Toast.makeText(getApplicationContext(), "Este contacto no existe", Toast.LENGTH_LONG);
            t.show();
        }
        else {
            db.delete("Contactos", "nombre = '" + nombre + "'", null);
            Toast t = Toast.makeText(getApplicationContext(), "Se ha borrado exitósamente", Toast.LENGTH_LONG);
            t.show();
            itn = new Intent(ELiminarContacto.this, MainActivity.class);
            startActivity(itn);
        }
    }
}
