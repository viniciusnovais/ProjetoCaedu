package pdasolucoes.com.br.projetocaedu.mobile.Principal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pdasolucoes.com.br.projetocaedu.R;
import pdasolucoes.com.br.projetocaedu.mobile.Util.Metodo;

/**
 * Created by PDA on 25/09/2017.
 */

public class ConfiguracaoActivity extends AppCompatActivity {

    private EditText editServidor, editDiretorio, editFilial;
    private Button btCancelar, btConfirmar;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_activity_configuracao);

        editServidor = (EditText) findViewById(R.id.etServer);
        editDiretorio = (EditText) findViewById(R.id.etDiretorio);
        editFilial = (EditText) findViewById(R.id.etFilial);
        btCancelar = (Button) findViewById(R.id.btnCancelar);
        btConfirmar = (Button) findViewById(R.id.btnOkConfig);

        preferences = getSharedPreferences("Configuracoes", MODE_PRIVATE);
        editServidor.setText(preferences.getString("servidor", ""));
        editDiretorio.setText(preferences.getString("diretorio", ""));
        editFilial.setText(Integer.toString(preferences.getInt("filial", -1)));


        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Metodo.verificaCamposNulos(new EditText[]{editDiretorio, editServidor, editFilial})) {

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("servidor", editServidor.getText().toString());
                    editor.putString("diretorio", editDiretorio.getText().toString());
                    editor.putInt("filial", Integer.parseInt(editFilial.getText().toString()));

                    editor.commit();
                    finish();

                } else {
                    Metodo.toastCamposObrigatorios(ConfiguracaoActivity.this);
                }
            }
        });


    }
}
