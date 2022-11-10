package com.example.manipulardocumentos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);


        Toast.makeText(this,Environment.getExternalStorageState(),Toast.LENGTH_LONG).show();


    }

    public void leerMemoriaInterna(View view) {

        String nombre=et1.getText().toString();

        et2.setText("");
        try {
            InputStreamReader isr=new InputStreamReader(openFileInput(nombre));
            BufferedReader br=new BufferedReader(isr);

            String contenido="";
            String linea=br.readLine();

            while(linea!=null){
                contenido+=linea;
                linea=br.readLine();

            }

            et2.setText(contenido);

            br.close();
            isr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribirMemoriaInterna(View view) {
        String nombre=et1.getText().toString();
        String contenido=et2.getText().toString();
        try {
            OutputStreamWriter flujo=new OutputStreamWriter(openFileOutput(nombre, Context.MODE_PRIVATE));

            flujo.write(contenido);
            flujo.close();

            et1.setText("");
            et2.setText("");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribirMemoriaExterna(View view) {
        String nombre=et1.getText().toString();
        String contenido=et2.getText().toString();
        try {
            File f=new File(getExternalFilesDir(null),nombre);
            FileOutputStream fos=new FileOutputStream(f);
            OutputStreamWriter flujo=new OutputStreamWriter(fos);

            flujo.write(contenido);
            flujo.close();

            et1.setText("");
            et2.setText("");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leerMemoriaExterna(View view) {
        String nombre=et1.getText().toString();

        et2.setText("");
        try {
            File f=new File(getExternalFilesDir(null),nombre);
            FileInputStream fis=new FileInputStream(f);
            InputStreamReader isr=new InputStreamReader(fis);
            BufferedReader br=new BufferedReader(isr);

            String contenido="";
            String linea=br.readLine();

            while(linea!=null){
                contenido+=linea;
                linea=br.readLine();

            }

            et2.setText(contenido);

            br.close();
            isr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}