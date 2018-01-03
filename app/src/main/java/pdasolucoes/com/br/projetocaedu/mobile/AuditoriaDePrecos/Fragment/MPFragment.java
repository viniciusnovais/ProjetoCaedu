package pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import pdasolucoes.com.br.projetocaedu.R;
import pdasolucoes.com.br.projetocaedu.mobile.Model.Produto;
import pdasolucoes.com.br.projetocaedu.mobile.Services.AuditoriaDePrecosProdutoService;
import pdasolucoes.com.br.projetocaedu.mobile.Services.AuditoriaPrecosMelhoresPioresService;

/**
 * Created by PDA on 26/09/2017.
 */

public class MPFragment extends Fragment {

    private List<Produto> lista;
    private String[] status = new String[3];
    private TextView tvDescricaoProduto, tvEstoqueLoja, tvStatusLoja, tvStatusRegional, tvStatusEmpresa;

    public static final MPFragment novaInstancia(List<Produto> lista) {

        MPFragment f = new MPFragment();
        Bundle b = new Bundle();
        b.putSerializable("listaProduto", (Serializable) lista);
        f.setArguments(b);

        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lista = (List<Produto>) getArguments().getSerializable("listaProduto");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.auditoriaprecos_fragment_mp, container, false);
        tvDescricaoProduto = (TextView) v.findViewById(R.id.descProduto);
        tvEstoqueLoja = (TextView) v.findViewById(R.id.estoqueLoja);
        tvStatusLoja = (TextView) v.findViewById(R.id.statusLoja);
        tvStatusRegional = (TextView) v.findViewById(R.id.statusRegional);
        tvStatusEmpresa = (TextView) v.findViewById(R.id.statusEmpres);

        async();


        return v;

    }

    public void async() {
        AsynMP task = new AsynMP();
        task.execute();
    }

    private class AsynMP extends AsyncTask {
        int soma = 0;

        @Override
        protected Object doInBackground(Object[] params) {

            status = AuditoriaPrecosMelhoresPioresService.ConsultaMelhoresPiores("VILA FORMOSA", lista.get(0).getCodProduto().trim());
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            Produto produto = lista.get(0);

            tvDescricaoProduto.setText(produto.getDescricao());

            for (Produto p : lista) {
                soma = soma + p.getQtde();
            }
            tvEstoqueLoja.setText(String.format("%d", soma));

            tvStatusLoja.setText(status[0]);

            tvStatusRegional.setText(status[1]);

            tvStatusEmpresa.setText(status[2]);
        }
    }
}
