package br.usjt.appcadastro.ui;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import br.usjt.appcadastro.R;
import br.usjt.appcadastro.model.Usuario;
import br.usjt.appcadastro.model.UsuarioViewModel;


public class CadastroUsuarioActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        replaceFragment(R.id.frameLayoutCadastroUsuarioActivity,
                PerfilFragment.newInstance(true,""),
                PerfilFragment.PERFIL_FRAGMENT_TAG,
                "home");

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




    @Override
    protected void onStart(){
        super.onStart();
        Log.d("CICLO_DE_VIDA","MainActivity --> onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("CICLO_DE_VIDA","MainActivity --> onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("CICLO_DE_VIDA","MainActivity --> onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("CICLO_DE_VIDA","MainActivity --> onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("CICLO_DE_VIDA","MainActivity --> onDestroy");
    }

}