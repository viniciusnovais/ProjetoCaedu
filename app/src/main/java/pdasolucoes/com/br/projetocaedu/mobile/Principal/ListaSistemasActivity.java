package pdasolucoes.com.br.projetocaedu.mobile.Principal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import pdasolucoes.com.br.projetocaedu.R;
import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.AuditoriaActivity;
import pdasolucoes.com.br.projetocaedu.mobile.Model.Filial;
import pdasolucoes.com.br.projetocaedu.mobile.Model.Sistemas;
import pdasolucoes.com.br.projetocaedu.mobile.Principal.Adapter.ListSistemasAdapter;
import pdasolucoes.com.br.projetocaedu.mobile.Services.PrincipalListaFilialService;
import pdasolucoes.com.br.projetocaedu.mobile.Services.PrincipalListaSistemasService;

/**
 * Created by PDA on 25/09/2017.
 */

public class ListaSistemasActivity extends AppCompatActivity {

    private Button btConfirmar, btCancelar;
    private Spinner spinnerFilial;
    private SharedPreferences preferences;
    private List<Filial> lista;
    private ListView listView;
    private List<Sistemas> listaSistemas;
    private ListSistemasAdapter listSistemasAdapter;
    private ArrayAdapter<Filial> adapterFilial;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_activity_lista_sistemas);

        preferences = getSharedPreferences("usuario", MODE_PRIVATE);

        btCancelar = (Button) findViewById(R.id.btnCancelar);
        btConfirmar = (Button) findViewById(R.id.btnConfirmar);
        spinnerFilial = (Spinner) findViewById(R.id.spinnerFilial);
        listView = (ListView) findViewById(R.id.listSistemas);

        AsyncFiliais task = new AsyncFiliais();
        task.execute(preferences.getString("CodigoFilial", "0"));

        AsyncSistemas task2 = new AsyncSistemas();
        task2.execute(preferences.getString("CodigoPerfil", "0"));


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

    public class AsyncFiliais extends AsyncTask<Object, Void, List<Filial>> {


        @Override
        protected List<Filial> doInBackground(Object... params) {
            lista = PrincipalListaFilialService.listaFilialWS(params[0].toString());

            return lista;
        }

        @Override
        protected void onPostExecute(List<Filial> filials) {
            super.onPostExecute(filials);
            adapterFilial = new ArrayAdapter<>(ListaSistemasActivity.this, R.layout.custom_spinner, lista);
            adapterFilial.setDropDownViewResource(R.layout.spinner_row);
            spinnerFilial.setAdapter(adapterFilial);
        }
    }

    public class AsyncSistemas extends AsyncTask<Object, Void, List<Sistemas>> {


        @Override
        protected List<Sistemas> doInBackground(Object... params) {
            listaSistemas = PrincipalListaSistemasService.listaSistemasWS(
                    params[0].toString());

            return listaSistemas;
        }

        @Override
        protected void onPostExecute(List<Sistemas> sistemas) {
            super.onPostExecute(sistemas);

            listSistemasAdapter = new ListSistemasAdapter(sistemas, ListaSistemasActivity.this);
            listView.setAdapter(listSistemasAdapter);

        }
    }
}
