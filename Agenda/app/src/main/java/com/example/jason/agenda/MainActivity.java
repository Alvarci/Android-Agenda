package com.example.jason.agenda;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton jibCrear, jibListar, jibEditar, jibEliminar;
    Intent itn;
    SQLiteDatabase sqld;
    DbmsSQLiteHelper dsqlh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jibCrear = (ImageButton) findViewById(R.id.xibAgregarContacto);
        jibListar = (ImageButton) findViewById(R.id.xibVerContactos);
        jibEditar = (ImageButton) findViewById(R.id.xibModificarContacto);
        jibEliminar = (ImageButton)findViewById(R.id.xibEliminarContacto);
        jibCrear.setOnClickListener(this);
        jibListar.setOnClickListener(this);
        jibEditar.setOnClickListener(this);
        jibEliminar.setOnClickListener(this);
        dsqlh = new DbmsSQLiteHelper(this, "DBContactos", null, 1);
        sqld = dsqlh.getWritableDatabase();
        conectorDB.setDataBase(sqld);
        conectorDB.setDbh(dsqlh);
    }

    @Override
    public void onClick(View v) {
        int opcion = v.getId();
        switch (opcion) {
            case R.id.xibAgregarContacto:
                itn = new Intent(MainActivity.this, CrearContacto.class);
                startActivity(itn);
                break;
            case R.id.xibVerContactos:
                itn = new Intent(MainActivity.this, VerContactos.class);
                startActivity(itn);
                break;
            case R.id.xibModificarContacto:
                itn = new Intent(MainActivity.this, ModificarContacto.class);
                startActivity(itn);
                break;
            case R.id.xibEliminarContacto:
                itn = new Intent(MainActivity.this, ELiminarContacto.class);
                startActivity(itn);
                break;
        }
    }
}
