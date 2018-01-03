package pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;

import pdasolucoes.com.br.projetocaedu.R;
import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Adapter.ListaReposicaoAdapter;
import pdasolucoes.com.br.projetocaedu.mobile.Model.Produto;
import pdasolucoes.com.br.projetocaedu.mobile.Util.Metodo;

/**
 * Created by PDA on 25/09/2017.
 */

public class ReposicaoFragment extends Fragment {

    private TextView tvDescricaoProduto;
    private List<Produto> lista;
    private ListaReposicaoAdapter adapter;
    private RecyclerView recyclerView;
    private int qtde = 0;

    public static final ReposicaoFragment novaInstancia(List<Produto> lista) {

        ReposicaoFragment f = new ReposicaoFragment();

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
        View v = inflater.inflate(R.layout.auditoriaprecos_fragment_reposicao, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(container.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), llm.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);

        adapter = new ListaReposicaoAdapter(lista, getActivity());
        recyclerView.setAdapter(adapter);

        adapter.ItemClickListener(new ListaReposicaoAdapter.ItemClick() {
            @Override
            public void onClick(int position) {
                popupInsereQtde(position);
            }
        });

        tvDescricaoProduto = (TextView) v.findViewById(R.id.descProduto);

        Produto p = lista.get(0);

        tvDescricaoProduto.setText(p.getDescricao());

        return v;
    }

    public void popupInsereQtde(int position) {
        View v = View.inflate(getContext(), R.layout.popup_quantidade, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final AlertDialog dialog;
        builder.setView(v);
        dialog = builder.create();
        TextView tvDescricao = (TextView) v.findViewById(R.id.detalhe);
        final EditText editQtde = (EditText) v.findViewById(R.id.etQuantidade);
        Button btConfirmar = (Button) v.findViewById(R.id.btnConfirmar);
        Button btCancelar = (Button) v.findViewById(R.id.btnCancelar);

        dialog.show();

        tvDescricao.setText(lista.get(position).getCor().trim() + " - " + lista.get(position).getTamanho().trim());

        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Metodo.verificaCamposNulos(new EditText[]{editQtde})) {
                    qtde = Integer.parseInt(editQtde.getText().toString());
                    dialog.dismiss();
                } else {
                    Metodo.toastCamposObrigatorios((Activity) getContext());
                }
            }

        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
