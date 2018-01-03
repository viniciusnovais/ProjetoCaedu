package pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pdasolucoes.com.br.projetocaedu.R;
import pdasolucoes.com.br.projetocaedu.mobile.Model.Produto;

/**
 * Created by PDA on 25/09/2017.
 */

public class PrecoFragment extends Fragment {

    private TextView tvCodProduo, tvDescricao, tvPreco, tvMargem, tvIdade, tvVendas, tvTipo, tvEstoqueLoja;
    private List<Produto> lista;

    public static final PrecoFragment novaInstancia(List<Produto> lista) {

        PrecoFragment p = new PrecoFragment();
        Bundle b = new Bundle(1);
        b.putSerializable("listaProduto", (Serializable) lista);
        p.setArguments(b);

        return p;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lista = (List<Produto>) getArguments().getSerializable("listaProduto");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.auditoriaprecos_fragment_precos, container, false);

        tvCodProduo = (TextView) v.findViewById(R.id.codProduto);
        tvDescricao = (TextView) v.findViewById(R.id.descProduto);
        tvPreco = (TextView) v.findViewById(R.id.preco);
        tvMargem = (TextView) v.findViewById(R.id.margem);
        tvIdade = (TextView) v.findViewById(R.id.idade);
        tvVendas = (TextView) v.findViewById(R.id.vendas);
        tvTipo = (TextView) v.findViewById(R.id.tipo);
        tvEstoqueLoja = (TextView) v.findViewById(R.id.estoqueLoja);

        try {
            Produto produto = lista.get(0);
            int soma = 0, somaVendas = 0;


            tvCodProduo.setText(produto.getCodProduto());

            tvDescricao.setText(produto.getDescricao());

            tvPreco.setText(String.format("%.2f", produto.getPreco()));

            tvMargem.setText(String.format("%.2f", produto.getMargem()) + "%");

            tvIdade.setText(String.format("%d", produto.getIdade()));

            for (Produto p : lista) {
                somaVendas = somaVendas + p.getVendas();
            }

            tvVendas.setText(String.format("%d", somaVendas));

            tvTipo.setText(produto.getTipo_produto());

            for (Produto p : lista) {
                soma = soma + p.getQtde();
            }
            tvEstoqueLoja.setText(String.format("%d", soma));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;
    }


}
