package pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pdasolucoes.com.br.projetocaedu.R;

/**
 * Created by PDA on 26/09/2017.
 */

public class DetalhamentoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.auditoriaprecos_fragment_detalhamento, container, false);

        return v;
    }
}
