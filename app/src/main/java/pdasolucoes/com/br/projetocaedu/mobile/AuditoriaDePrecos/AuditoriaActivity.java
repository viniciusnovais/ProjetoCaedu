package pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import pdasolucoes.com.br.projetocaedu.R;
import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Adapter.PageAdapterAuditoria;
import pdasolucoes.com.br.projetocaedu.mobile.Model.Produto;
import pdasolucoes.com.br.projetocaedu.mobile.Services.AuditoriaDePrecosProdutoService;
import pdasolucoes.com.br.projetocaedu.mobile.Util.Metodo;

/**
 * Created by PDA on 25/09/2017.
 */

public class AuditoriaActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String codProduto = "";
    //private ImageView imageBarcode;
    private String barcode = "";
    private String[] arrayString = new String[3];

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auditoriaprecos_activity_auditoria);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        final EditText editCodProd = (EditText) findViewById(R.id.editCodProd);
//        imageBarcode = (ImageView) findViewById(R.id.barcode);

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
                            arrayString[1] = codProduto;
                            AsynGetProduto task = new AsynGetProduto();
                            task.execute(arrayString);
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
                            if (codProduto.contains("\n")) {
                                codProduto = codProduto.replaceAll("[\n]", "");
                            }
                            editCodProd.setText("");
                            Metodo.fecharTeclado(AuditoriaActivity.this);
                            AsynGetProduto task = new AsynGetProduto();
                            arrayString[1] = codProduto;
                            task.execute(arrayString);
                        } else {
                            Metodo.toastCamposObrigatorios(AuditoriaActivity.this);
                        }

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (editCodProd != null) {
                                    editCodProd.requestFocus();
                                }
                            }
                        }, 10);
                    }
                }
                return false;
            }
        });

//        imageBarcode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                IntentIntegrator integrator = new IntentIntegrator(AuditoriaActivity.this);
//                integrator.initiateScan();
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (scanResult != null) {
            barcode = scanResult.getContents();
            if (barcode != null && !"".equals(barcode)) {
                arrayString[1] = barcode;

                AsynGetProduto task2 = new AsynGetProduto();
                task2.execute(arrayString);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }


    public class AsynGetProduto extends AsyncTask<String[], Void, List<Produto>> {

        @Override
        protected List<Produto> doInBackground(String[]... params) {


            List<Produto> lista = AuditoriaDePrecosProdutoService.getProdutoWS("VILA FORMOSA", params[0][1], "1");
            return lista;
        }

        @Override
        protected void onPostExecute(List<Produto> lista) {
            super.onPostExecute(lista);

            if (lista.size() != 0) {
                viewPager.setAdapter(new PageAdapterAuditoria(getSupportFragmentManager(), getResources().getStringArray(R.array.auditoria_titulos), lista));
                tabLayout.setupWithViewPager(viewPager);
            } else {
                Toast.makeText(AuditoriaActivity.this, "Produto não encontrado", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
