package com.example.aval;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText cep;
    Button buscaCep;
    TextView resposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cep = findViewById(R.id.etCep);
        buscaCep = findViewById(R.id.btnBuscarCep);
        resposta = findViewById(R.id.etResposta);
        buscaCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    try {
                        Cep retorno = new Httpinfo(cep.getText().toString()).execute().get();
                        System.out.println(retorno.getLocalidade());
                        if (cep.length() == 8) {
                            if (retorno.getLocalidade() == null) {
                                resposta.setText("erro, cep invalido");
                            } else {
                                resposta.setText(retorno.toString());
                            }
                        } else {
                            resposta.setText("erro, CEP com menos números do que o necessário");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}