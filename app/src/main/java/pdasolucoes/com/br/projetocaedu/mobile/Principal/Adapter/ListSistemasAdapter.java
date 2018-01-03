package pdasolucoes.com.br.projetocaedu.mobile.Principal.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import pdasolucoes.com.br.projetocaedu.R;
import pdasolucoes.com.br.projetocaedu.mobile.Model.Sistemas;

/**
 * Created by PDA on 03/10/2017.
 */

public class ListSistemasAdapter extends BaseAdapter {
    private List<Sistemas> lista;
    private Context context;

    public ListSistemasAdapter(List<Sistemas> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.principal_item_lista_sistemas, null);

        Sistemas s = lista.get(position);


        TextView tvSistema = (TextView) v.findViewById(R.id.sistema);

        tvSistema.setText(s.getNome());

        return v;
    }
}
