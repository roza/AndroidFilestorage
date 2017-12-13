package com.example.android.roza.androidfilestorage;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.JsonWriter;
import android.util.JsonReader;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

public class MainActivity extends Activity {

    EditText textmsg;
    TextView jsonmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textmsg = findViewById(R.id.editText1);
        jsonmsg = findViewById(R.id.textView2);
    }

    /** Ecrire le texte vers le fichier */
    public void writeText(View v) {
        try {
            FileOutputStream fileout=openFileOutput("monfichier.txt", MODE_PRIVATE);
            OutputStreamWriter writer=new OutputStreamWriter(fileout);
            writer.write(textmsg.getText().toString());
            writer.close();
            //affichage message
            Toast.makeText(getBaseContext(), R.string.Write_Success,
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Effacer l'EditText */
    public void clearEdit(View v) {
        textmsg.setText("");
    }

    /** Lire le texte à partir du fichier */
    public void readText(View v) {
        try {
            FileInputStream fis = openFileInput("monfichier.txt");
            InputStreamReader reader= new InputStreamReader(fis);

            int charRead = fis.available(); // taille du fichier
            char[] inputBuffer= new char[charRead];
            final int read = reader.read(inputBuffer, 0, charRead);
            if (read>0) {
                textmsg.setText(new String(inputBuffer));
            } else {
                textmsg.setText(R.string.Read_Warn);
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void writeJson(View view) throws IOException {
        FileOutputStream fileout=openFileOutput("monfichier.json", MODE_PRIVATE);
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(fileout, "UTF-8"));
        writer.setIndent("  ");
        writer.beginObject();
        writer.name("date").value(new Date().toString());
        writer.name("text").value(textmsg.getText().toString());
        writer.endObject();
        writer.close();
    }

    public void readJson(View v) {
        try {
            FileInputStream fis = openFileInput("monfichier.json");
            InputStreamReader reader= new InputStreamReader(fis);

            int charRead = fis.available(); // taille du fichier
            char[] inputBuffer= new char[charRead];
            final int read = reader.read(inputBuffer, 0, charRead);
            if (read>0) {
                jsonmsg.setText(new String(inputBuffer));
            } else {
                jsonmsg.setText(R.string.Read_Warn);
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void readJsonObject(View view) throws IOException {
     String date = null;
     String text = null;
     FileInputStream fis = openFileInput("monfichier.json");
     JsonReader reader= new JsonReader(new InputStreamReader(fis, "UTF-8"));
     reader.beginObject();
     while (reader.hasNext()) {
       String name = reader.nextName();
       if (name.equals("date")) {
         date = reader.nextString();
       } else if (name.equals("text")) {
         text = reader.nextString();
       }
       else {
         reader.skipValue();
       }
     }
     reader.endObject();
     reader.close();
     jsonmsg.setText("date:"+date+", text:"+text);
    }
}