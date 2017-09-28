package pdasolucoes.com.br.projetocaedu.mobile.Principal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

import pdasolucoes.com.br.projetocaedu.R;
import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.AuditoriaActivity;
import pdasolucoes.com.br.projetocaedu.mobile.Model.Filial;
import pdasolucoes.com.br.projetocaedu.mobile.Services.PrincipalListaSistemasService;

/**
 * Created by PDA on 25/09/2017.
 */

public class ListaSistemasActivity extends AppCompatActivity {

    private Button btConfirmar, btCancelar;
    private Spinner spinnerFilial;
    private SharedPreferences preferences;
    private List<Filial> lista;
    private  ArrayAdapter<Filial> adapterFilial;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_activity_lista_sistemas);

        preferences = getSharedPreferences("usuario", MODE_PRIVATE);

        btCancelar = (Button) findViewById(R.id.btnCancelar);
        btConfirmar = (Button) findViewById(R.id.btnConfirmar);
        spinnerFilial = (Spinner) findViewById(R.id.spinnerFilial);

        AsyncFiliais task = new AsyncFiliais();
        task.execute(preferences.getString("CodigoFilial", "0"));


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

    public class AsyncFiliais extends AsyncTask {


        @Override
        protected Object doInBackground(Object[] params) {
            lista = PrincipalListaSistemasService.listaSistemasWS(params[0].toString());

            //Criar uma lista de strings para montar o array adapter do spinner

            adapterFilial = new ArrayAdapter<>(ListaSistemasActivity.this, R.layout.custom_spinner, lista);
            adapterFilial.setDropDownViewResource(R.layout.spinner_row);

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            spinnerFilial.setAdapter(adapterFilial);
        }
    }
}
