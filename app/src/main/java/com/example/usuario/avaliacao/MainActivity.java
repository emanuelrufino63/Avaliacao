package com.example.usuario.avaliacao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends Activity {

    private EditText editTextPref;
    private Button btnSalvar;
    private Button btnRecuperar;
    private SharedPreferences prefs;
    private String prefName = "ESTRANGEIRO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // INÍCIO TÓPICO 10
        final EditText editText = (EditText) findViewById(R.id.editText);
        //ImageView imagem = (ImageView) findViewById(R.id.ivImagem);

        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(editText.getText().toString()));
            startActivity(Intent.createChooser(intent,"ENVIAR VIA:"));
            }
        });
        //FIM TÓPICO 10

        // INÍCIO TÓPICO 11
        Button btnInserir = (Button) findViewById(R.id.btnInserir);
        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent InserirContato = new Intent(Intent.ACTION_INSERT);
                InserirContato.setType(ContactsContract.Contacts.CONTENT_TYPE);

                if (InserirContato.resolveActivity(getPackageManager()) != null){
                    startActivity(InserirContato);
                }
            }
        });
        // FIM TÓPICO 11

        // INÍCIO TÓPICO 12
        String fileName = "MeuArquivo.txt";
        String content = "Arquivo";

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // FIM TÓPICO 12

        // INÍCIO TÓPICO 13
        // A ORIENTAÇÃO É QUE NO ARMAZENAMENTO EXTERNO É UM LOCAL ONDE QUALQUER UM PODE ACESSAR,
        //  PORTANTO DEVE-SE TER CUIDADO COM OS DADOS, JA QUE NO INTERNO, SÓ PODE SER ACESSO NO PRÓPRIO APP.
        // FIM TÓPICO 13

        // INÍCIO TÓPICO 15
        editTextPref = (EditText) findViewById(R.id.editTextPref);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        final SharedPreferences prefs = getSharedPreferences(prefName,Context.MODE_PRIVATE);

        //editTextPref.setText(prefs.getString("ESTRANGEIRO",null));

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("ESTRANGEIRO",editTextPref.getText().toString());
                editor.commit();

                Toast.makeText(getBaseContext(),"Dados Salvos",Toast.LENGTH_SHORT).show();
            }
        });
        // FIM TÓPICO 15

     }


}
