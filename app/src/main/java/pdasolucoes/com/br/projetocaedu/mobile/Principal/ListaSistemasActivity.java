package pdasolucoes.com.br.projetocaedu.mobile.Principal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import pdasolucoes.com.br.projetocaedu.R;
import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.AuditoriaActivity;

/**
 * Created by PDA on 25/09/2017.
 */

public class ListaSistemasActivity extends AppCompatActivity {

    private Button btConfirmar, btCancelar;
    private Spinner spinnerFilial;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_activity_lista_sistemas);

        btCancelar = (Button) findViewById(R.id.btnCancelar);
        btConfirmar = (Button) findViewById(R.id.btnConfirmar);
        spinnerFilial = (Spinner) findViewById(R.id.spinnerFilial);

        ArrayAdapter<String> adapterFilial = new ArrayAdapter<>(this, R.layout.custom_spinner, new String[]{"Selecione filial", "A", "B", "C"});
        adapterFilial.setDropDownViewResource(R.layout.spinner_row);
        spinnerFilial.setAdapter(adapterFilial);

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaSistemasActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaSistemasActivity.this, AuditoriaActivity.class);
                startActivity(i);
            }
        });
    }
}
