package com.example.eloyyyyyyy.fragmentbasedatosjson;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentBD extends Fragment {

    EditText etTelefono, etNombre;
    TextView tvListar;
    Button btnAnadir, btnBorrar, btnModificar, btnListar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_fragment_bd, container, false);

        etTelefono=(EditText)v.findViewById(R.id.etTelefono);
        etNombre=(EditText)v.findViewById(R.id.etNombre);
        tvListar=(TextView)v.findViewById(R.id.tvListar);
        btnAnadir=(Button)v.findViewById(R.id.btnAnadir);
        btnBorrar=(Button)v.findViewById(R.id.btnBorrar);
        btnModificar=(Button)v.findViewById(R.id.btnModificar);
        btnListar=(Button)v.findViewById(R.id.btnListar);

        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "contactos", null, 1);
                SQLiteDatabase bd=admin.getWritableDatabase();

                String miNumero=etTelefono.getText().toString();
                String miNombre=etNombre.getText().toString();

                ContentValues registro=new ContentValues();
                registro.put("numero", miNumero);
                registro.put("nombre", miNombre);

                try {
                    bd.insert("contactos", null, registro);
                }catch (Exception e){
                    e.getMessage();
                }

                bd.close();

                etTelefono.setText("");
                etNombre.setText("");
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "contactos", null, 1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                String miNumero= etTelefono.getText().toString();
                int cantidadElementosBorrados = bd.delete("contactos", "numero=" + miNumero, null);
                bd.close();
                etTelefono.setText("");
                etNombre.setText("");
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "contactos", null, 1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                String miNumero = etTelefono.getText().toString();
                String miNombre = etNombre.getText().toString();

                ContentValues registro = new ContentValues();
                registro.put("numero", miNumero);
                registro.put("nombre", miNombre);

                int cantidadElementosActualizados = bd.update("contactos", registro, "numero=" + miNumero, null);
                bd.close();
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvListar.setText("");
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "contactos", null, 1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                Cursor fila=bd.rawQuery("select numero, nombre from contactos", null);

                if(fila.moveToFirst()){
                    do{
                        int numero=fila.getInt(0);
                        String nombre=fila.getString(1);
                        tvListar.append("Número: "+numero+" Nombre: "+nombre+"\n");
                    }while(fila.moveToNext());

                    etTelefono.setText("");
                    etNombre.setText("");
                }

                bd.close();
            }
        });

        return v;
    }
}
