package pdasolucoes.com.br.projetocaedu.mobile.Util;

import android.app.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import pdasolucoes.com.br.projetocaedu.R;
import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.AuditoriaActivity;

/**
 * Created by PDA on 26/09/2017.
 */

public class Metodo {

    public static void fecharTeclado(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void abrirTeclado(Activity activity, EditText editText) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        editText.requestFocus();
    }

    public static void toastCamposObrigatorios(Activity activity) {
        Toast.makeText(activity, activity.getString(R.string.campo_embranco), Toast.LENGTH_SHORT).show();
    }

    public static void limparCampos(EditText[] editTexts) {
        for (int i = 0; i < editTexts.length; i++) {
            editTexts[i].setText("");
        }
    }

    public static boolean verificaCamposNulos(EditText[] editTexts) {
        for (int i = 0; i < editTexts.length; i++) {
            if (editTexts[i].getText().toString().equals("")) {
                return false;
            }
        }
        return true;
    }

    public static ProgressDialog progressDialogCarregamento(Activity activity) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(activity.getString(R.string.carregando));
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.setCancelable(false);

        return progressDialog;
    }
}
