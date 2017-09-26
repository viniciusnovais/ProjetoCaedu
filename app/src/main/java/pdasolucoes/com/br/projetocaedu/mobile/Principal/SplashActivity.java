package pdasolucoes.com.br.projetocaedu.mobile.Principal;

import java.util.Timer;
import java.util.TimerTask;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.nfc.tech.NfcA;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import pdasolucoes.com.br.projetocaedu.R;

public class SplashActivity extends Activity {

    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ImageView pda = (ImageView) findViewById(R.id.splashmove);
        final Animation logoMoveAnimation = AnimationUtils.loadAnimation(this, R.anim.up);
        pda.startAnimation(logoMoveAnimation);


        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                finish();
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        }, 3000);
    }

}
