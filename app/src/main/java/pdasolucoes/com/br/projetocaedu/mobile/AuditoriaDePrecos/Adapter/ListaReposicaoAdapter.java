package pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pdasolucoes.com.br.projetocaedu.R;
import pdasolucoes.com.br.projetocaedu.mobile.Model.Produto;

/**
 * Created by PDA on 11/10/2017.
 */

public class ListaReposicaoAdapter extends RecyclerView.Adapter<ListaReposicaoAdapter.MyViewHolder> {

    private List<Produto> lista;
    private Context context;
    private LayoutInflater layoutInflater;
    private ItemClick itemClick;

    public interface ItemClick {
        void onClick(int position);
    }

    public void ItemClickListener(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public ListaReposicaoAdapter(List<Produto> lista, Context context) {
        this.lista = lista;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ListaReposicaoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = layoutInflater.inflate(R.layout.auditoriaprecos_adapter_list_reposicao, parent, false);

        MyViewHolder mv = new MyViewHolder(v);

        return mv;
    }

    @Override
    public void onBindViewHolder(ListaReposicaoAdapter.MyViewHolder holder, int position) {

        Produto p = lista.get(position);

        holder.tvCodigo.setText(p.getCodProduto());

//        holder.tvCodBarras.setText(p.getBarra());

        holder.tvTamanho.setText(p.getTamanho());

        holder.tvQtde.setText(String.format("%d", p.getQtde()));

        holder.tvCor.setText(p.getCor());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvCodigo, tvCodBarras, tvQtde, tvTamanho, tvCor;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvCodigo = (TextView) itemView.findViewById(R.id.codigoProduto);
//            tvCodBarras = (TextView) itemView.findViewById(R.id.codigoBarras);
            tvQtde = (TextView) itemView.findViewById(R.id.qtde);
            tvTamanho = (TextView) itemView.findViewById(R.id.tamanho);
            tvCor = (TextView) itemView.findViewById(R.id.cor);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemClick.onClick(getAdapterPosition());
        }
    }
}
