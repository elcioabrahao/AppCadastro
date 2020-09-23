package br.usjt.appcadastro.ui;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.usjt.appcadastro.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavegationView();
        replaceFragment(R.id.frameLayout,HomeFragment.newInstance("",""),HomeFragment.HOME_FRAGMENT_TAG,"home");
    }

    private void setupNavegationView(){
        navigationView = findViewById(R.id.bottomNav);
        navigationView.setOnNavigationItemSelectedListener(menuItem -> {
                    switch (menuItem.getItemId()) {
                        case R.id.home:
                            replaceFragment(R.id.frameLayout,
                                    HomeFragment.newInstance("",""),
                                    HomeFragment.HOME_FRAGMENT_TAG,
                                    "home");
                            break;
                        case R.id.contato:
                            replaceFragment(R.id.frameLayout,
                                    ContatoFragment.newInstance("",""),
                                    ContatoFragment.CONTATO_FRAGMENT_TAG,
                                    "contato");
                            break;
                        case R.id.perfil:
                            replaceFragment(R.id.frameLayout,
                                    PerfilFragment.newInstance("",""),
                                    PerfilFragment.PERFIL_FRAGMENT_TAG,
                                    "perfil");
                            break;
                        case R.id.configuracao:
                            replaceFragment(R.id.frameLayout,
                                    ConfiguracaoFragment.newInstance("",""),
                                    ConfiguracaoFragment.CONFIGURACAO_FRAGMENT_TAG,
                                    "configuracao");
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
            return false;
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case R.id.cadastro:
            Intent intent = new Intent(this, CadastroUsuarioActivity.class);
            startActivity(intent);
            return(true);
        case R.id.sair:
            finish();
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }
}