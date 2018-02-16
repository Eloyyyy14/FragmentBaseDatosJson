package com.example.eloyyyyyyy.fragmentbasedatosjson;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction=fragmentManager.beginTransaction();
        FragmentBD fragmentBD=new FragmentBD();

        transaction.add(R.id.relativeLayoutContenedor, fragmentBD);
        transaction.commit();
    }

    public void seleccionarFragmento(View v){
        android.support.v4.app.Fragment miFragmento;

        if(v==findViewById(R.id.tvLeerJson)){
            miFragmento=new FragmentLeerJson();
        }

        else {
            miFragmento=new FragmentBD();
        }

        android.support.v4.app.FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.relativeLayoutContenedor, miFragmento);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
