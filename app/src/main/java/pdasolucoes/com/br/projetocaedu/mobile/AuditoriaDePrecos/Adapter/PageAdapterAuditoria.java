package pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Fragment.ConfReposicaoFragment;
import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Fragment.DetalhamentoFragment;
import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Fragment.MPFragment;
import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Fragment.MovimentoFragment;
import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Fragment.PrecoFragment;
import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Fragment.ReposicaoFragment;
import pdasolucoes.com.br.projetocaedu.mobile.Model.Produto;

/**
 * Created by PDA on 25/09/2017.
 */

public class PageAdapterAuditoria extends FragmentStatePagerAdapter {

    private String[] titulosFragmentsTabs;
    List<Produto> lista;

    public PageAdapterAuditoria(FragmentManager fm, String[] titulosFragmentsTabs, List<Produto> lista) {
        super(fm);
        this.lista = lista;
        this.titulosFragmentsTabs = titulosFragmentsTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PrecoFragment().novaInstancia(lista);
            case 1:
                return new ReposicaoFragment().novaInstancia(lista);
            case 2:
                return new MPFragment().novaInstancia(lista);
            case 3:
                return new ConfReposicaoFragment();
            case 4:
                return new DetalhamentoFragment();
            case 5:
                return new MovimentoFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.titulosFragmentsTabs.length;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return this.titulosFragmentsTabs[position];
    }
}
