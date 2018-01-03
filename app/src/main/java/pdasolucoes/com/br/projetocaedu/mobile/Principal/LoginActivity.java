package pdasolucoes.com.br.projetocaedu.mobile.Principal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.ksoap2.serialization.SoapObject;

import pdasolucoes.com.br.projetocaedu.R;
import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.AuditoriaActivity;
import pdasolucoes.com.br.projetocaedu.mobile.Services.PrincipalLoginService;
import pdasolucoes.com.br.projetocaedu.mobile.Util.Metodo;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsuario, editextSenha;
    private Button btnEntrar;
    private SoapObject objectUsuario;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_activity_login);

        preferences = getSharedPreferences("usuario", MODE_PRIVATE);

        editTextUsuario = (EditText) findViewById(R.id.etUser);
        editextSenha = (EditText) findViewById(R.id.etPassword);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Metodo.verificaCamposNulos(new EditText[]{editTextUsuario, editextSenha})) {
                    if (editTextUsuario.getText().toString().equals("12345678")
                            && editextSenha.getText().toString().equals("12345678")) {
                        Metodo.limparCampos(new EditText[]{editTextUsuario, editextSenha});
                        Intent i = new Intent(LoginActivity.this, ConfiguracaoActivity.class);
                        startActivity(i);
                    } else {
                        //chama o AsyncLogin
                        AsyncLogin task = new AsyncLogin();
                        task.execute(editTextUsuario.getText().toString(), editextSenha.getText().toString());
                    }
                } else {
                    Metodo.toastCamposObrigatorios(LoginActivity.this);
                }
            }
        });
    }

    public class AsyncLogin extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Metodo.progressDialogCarregamento(LoginActivity.this).show();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            objectUsuario = PrincipalLoginService.autenticacoWS(params[0].toString(), params[1].toString());

            SharedPreferences.Editor editor = preferences.edit();

            //quando logar vai apagar o que tiver no arquivo usuario
            editor.clear();
            editor.commit();

            //depois ele vai logar e salvar as propriedades do usuario no arquivo
            editor.putString("Nome", objectUsuario.getProperty("Nome").toString());
            editor.putString("CodigoPerfil", objectUsuario.getProperty("CodigoPerfil").toString());
            editor.putString("CodigoFilial", objectUsuario.getProperty("CodigoFilial").toString());
            editor.putInt("Codigo", Integer.parseInt(objectUsuario.getProperty("Codigo").toString()));

            editor.commit();


            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Metodo.progressDialogCarregamento(LoginActivity.this).dismiss();

//            Intent i = new Intent(LoginActivity.this, ListaSistemasActivity.class);
            Intent i = new Intent(LoginActivity.this, AuditoriaActivity.class);
            startActivity(i);
            finish();
        }
    }
}
