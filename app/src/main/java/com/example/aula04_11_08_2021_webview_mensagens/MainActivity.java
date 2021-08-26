package com.example.aula04_11_08_2021_webview_mensagens;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText edtBuscar;
    Button btnAbrir, btnSite;
    WebView webSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ligando os campos da tela (XML) com o do JAVA
        edtBuscar = findViewById(R.id.edtBusca);
        btnAbrir = findViewById(R.id.btnAbrir);
        webSite = findViewById(R.id.webSite);
        btnSite = findViewById(R.id.btnSite);

        //carregando o site da faculdade no webview
        webSite.loadUrl("https://www.alfaumuarama.edu.br/fau/");

        btnAbrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sBusca = edtBuscar.getText().toString();

                if (sBusca.isEmpty()) {
                    //criando a classe do alerta
                    AlertDialog.Builder alerta =
                        new AlertDialog.Builder(MainActivity.this);

                    //adicionando um titulo para o alerta
                    alerta.setTitle("Atenção");

                    //adicionando uma mensagem para o alerta
                    alerta.setMessage("Texto de busca não foi informado!");

                    //adicionar um botao de OK
                    alerta.setNeutralButton("OK", null);

                    alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this,
                                "Cliquei no SIM.",
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                    alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this,
                                    "Cliquei no NÃO.",
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                    //mostrardo a mensagem de alerta na tela
                    alerta.show();

                    return;
                }

                sBusca = codificaTextoURL(sBusca);
                webSite.loadUrl("https://www.bing.com/search?q=" + sBusca);
            }
        });

        btnSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webSite.loadUrl("http://www.alfaumuarama.com.br/home/");
            }
        });
    }

    private String codificaTextoURL(String texto) {
        try {
            return URLEncoder.encode(texto, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}