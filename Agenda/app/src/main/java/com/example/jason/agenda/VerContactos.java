package com.example.jason.agenda;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class VerContactos extends AppCompatActivity {

    TextView jtvVerContactos;
    SQLiteDatabase db = (SQLiteDatabase)conectorDB.getDataBase();
    String nombre, aPaterno, aMaterno, telefono, sexo, hobbies;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_contactos);
        jtvVerContactos = (TextView)findViewById(R.id.xtvVerContactos);
        c = db.rawQuery("SELECT * from Contactos", null);
        if(c.moveToFirst()) {
            do {
                nombre = c.getString(0);
                aPaterno = c.getString(1);
                aMaterno = c.getString(2);
                telefono = c.getString(3);
                sexo = c.getString(4);
                hobbies = c.getString(5);
                jtvVerContactos.append(" \n Nombre: " + nombre + "\n Apellido Paterno: " + aPaterno + "\n Apellido Materno: " + aMaterno + "\n Telefono: " + telefono + "\n Sexo: " + sexo + "\n Hobbies: " + hobbies + "\n\n\n");
            } while (c.moveToNext());
        }
    }
}
