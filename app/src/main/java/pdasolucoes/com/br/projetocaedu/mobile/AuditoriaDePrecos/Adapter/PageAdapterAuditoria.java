package pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Fragment.ConfReposicaoFragment;
import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Fragment.DetalhamentoFragment;
import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Fragment.MPFragment;
import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Fragment.MovimentoFragment;
import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Fragment.PrecoFragment;
import pdasolucoes.com.br.projetocaedu.mobile.AuditoriaDePrecos.Fragment.ReposicaoFragment;

/**
 * Created by PDA on 25/09/2017.
 */

public class PageAdapterAuditoria extends FragmentPagerAdapter {

    private String[] titulosFragmentsTabs;

    public PageAdapterAuditoria(FragmentManager fm, String[] titulosFragmentsTabs) {
        super(fm);
        this.titulosFragmentsTabs = titulosFragmentsTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PrecoFragment();
            case 1:
                return new ReposicaoFragment();
            case 2:
                return new MPFragment();
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
    public CharSequence getPageTitle(int position) {
        return this.titulosFragmentsTabs[position];
    }
}
