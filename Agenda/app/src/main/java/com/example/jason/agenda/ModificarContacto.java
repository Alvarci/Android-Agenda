package com.example.jason.agenda;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModificarContacto extends AppCompatActivity {

    TextView jtvTexto;
    String nombre;
    EditText jetNombre;
    Button jbBuscar;
    SQLiteDatabase db = (SQLiteDatabase)conectorDB.getDataBase();
    Cursor c;
    Intent itn;
    Bundle bdl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_contacto);
        jtvTexto = (TextView)findViewById(R.id.xtvBusquedaM);
        jetNombre = (EditText)findViewById(R.id.xetBusquedaM);
        jbBuscar = (Button)findViewById(R.id.xbBusquedaM);
        jbBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = jetNombre.getText().toString();
                c = db.rawQuery("SELECT * FROM Contactos where nombre = '" + nombre + "'", null);
                if(c.getCount() == 0) {
                    Toast t = Toast.makeText(getApplicationContext(), "No existe ese nombre en la agenda", Toast.LENGTH_LONG);
                    t.show();
                }
                else {
                    itn = new Intent(ModificarContacto.this, CyM.class);
                    bdl = new Bundle();
                    c.moveToFirst();
                    bdl.putString("nombre", c.getString(0));
                    bdl.putString("aPaterno", c.getString(1));
                    bdl.putString("aMaterno", c.getString(2));
                    bdl.putString("telefono", c.getString(3));
                    bdl.putString("sexo", c.getString(4));
                    bdl.putString("hobbies", c.getString(5));
                    bdl.putString("nombreViejo", nombre);
                    itn.putExtras(bdl);
                    startActivity(itn);
                }
            }
        });
    }
}
