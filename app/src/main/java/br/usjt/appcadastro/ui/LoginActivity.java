package br.usjt.appcadastro.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import br.usjt.appcadastro.R;
import br.usjt.appcadastro.model.Usuario;
import br.usjt.appcadastro.model.UsuarioViewModel;

public class LoginActivity extends AppCompatActivity {

    private TextView textViewLinkCadastro;
    private Button buttonLogin;
    private UsuarioViewModel usuarioViewModel;
    Usuario usuario;
    EditText usuarioTextView;
    EditText senhaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioTextView = findViewById(R.id.usuarioEditText);
        senhaTextView = findViewById(R.id.senhaEditText);

        usuarioViewModel = new ViewModelProvider(this).get(UsuarioViewModel.class);

        usuarioViewModel.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable final Usuario usuario) {
                updateUsuario(usuario);
            }
        });

        Hawk.init(this).build();

        textViewLinkCadastro = findViewById(R.id.textViewLinkCadastro);
        buttonLogin = findViewById(R.id.buttonLogin);
    }

    private void updateUsuario(Usuario us){
        usuario = us;
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(Hawk.contains("tem_cadastro")){
            if(Hawk.get("tem_cadastro")){
                buttonLogin.setEnabled(true);
                buttonLogin.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                textViewLinkCadastro.setVisibility(View.GONE);
            }else{
                textViewLinkCadastro.setVisibility(View.VISIBLE);
                buttonLogin.setEnabled(false);
                buttonLogin.setBackgroundColor(Color.GRAY);
            }
        }else{
            Hawk.put("tem_cadastro",false);
            textViewLinkCadastro.setVisibility(View.VISIBLE);
            buttonLogin.setEnabled(false);
            buttonLogin.setBackgroundColor(Color.GRAY);

        }
    }

    public void logar(View view) {

        String us = usuarioTextView.getText().toString();
        String senha = senhaTextView.getText().toString();

        if(usuario !=null){
            if(usuario.getEmail().equalsIgnoreCase(us)
            && usuario.getSenha().equalsIgnoreCase(senha)){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Usuário ou senha inválidos!", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void fazerCadastro(View view) {
        Intent intent = new Intent(this, CadastroUsuarioActivity.class);
        startActivity(intent);
    }
}