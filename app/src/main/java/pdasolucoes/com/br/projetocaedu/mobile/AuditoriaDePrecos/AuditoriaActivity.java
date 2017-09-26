package pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pdasolucoes.com.br.projetocaedu.R;
import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Adapter.PageAdapterAuditoria;
import pdasolucoes.com.br.projetocaedu.mobile.Principal.LoginActivity;
import pdasolucoes.com.br.projetocaedu.mobile.Util.Metodo;

/**
 * Created by PDA on 25/09/2017.
 */

public class AuditoriaActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private EditText editCodProd;
    private String codProduto = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auditoriaprecos_activity_auditoria);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        editCodProd = (EditText) findViewById(R.id.editCodProd);

        //pegar click do icon lupa
        editCodProd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if ((event.getRawX() >= editCodProd.getRight() - editCodProd.getTotalPaddingRight())) {
                        if (!editCodProd.getText().toString().equals("")) {
                            codProduto = editCodProd.getText().toString();
                            editCodProd.setText("");
                            Metodo.fecharTeclado(AuditoriaActivity.this);
                        } else {
                            Metodo.toastCamposObrigatorios(AuditoriaActivity.this);
                        }
                        return true;
                    }
                }
                return false;
            }
        });

        //pegar o click do enter
        editCodProd.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        if (!editCodProd.getText().toString().equals("")) {
                            codProduto = editCodProd.getText().toString();
                            editCodProd.setText("");
                            Metodo.fecharTeclado(AuditoriaActivity.this);
                        } else {
                            Metodo.toastCamposObrigatorios(AuditoriaActivity.this);

                        }
                    }
                }
                return false;
            }
        });


        viewPager.setAdapter(new PageAdapterAuditoria(getSupportFragmentManager(), getResources().getStringArray(R.array.auditoria_titulos)));
        tabLayout.setupWithViewPager(viewPager);

    }

}
