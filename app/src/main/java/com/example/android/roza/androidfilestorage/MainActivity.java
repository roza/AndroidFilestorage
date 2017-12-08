package com.example.android.roza.androidfilestorage;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    EditText textmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textmsg=(EditText)findViewById(R.id.editText1);
    }

    /** Ecrire le texte vers le fichier */
    public void writeBtn(View v) {
        try {
            FileOutputStream fileout=openFileOutput("monfichier.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(textmsg.getText().toString());
            outputWriter.close();
            //affichage message
            Toast.makeText(getBaseContext(), R.string.Write_Success,
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Effacer l'EditText */
    public void clearBtn(View v) {
        textmsg.setText("");
    }
    /** Lire le texte à partir du fichier */
    public void readBtn(View v) {
        try {
            FileInputStream fis=openFileInput("monfichier.txt");
            InputStreamReader reader= new InputStreamReader(fis);

            int charRead = fis.available();
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
}