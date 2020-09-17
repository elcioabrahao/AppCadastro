package br.usjt.appcadastro.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

    private UsuarioViewModel usuarioViewModel;
    private EditText editTextNome;
    private EditText editTextCPF;
    private EditText editTextEmail;
    private EditText editTextSenha;
    private Usuario usuarioCorrente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        Hawk.init(this).build();

        usuarioCorrente = new Usuario();

        editTextNome = (EditText)findViewById(R.id.nomeEditText);
        editTextCPF = (EditText)findViewById(R.id.cpfEditText);
        editTextEmail = (EditText)findViewById(R.id.emailEditText);
        editTextSenha = (EditText)findViewById(R.id.senhaEditText);

        Log.d("CICLO_DE_VIDA","MainActivity --> onCreate");
        usuarioViewModel = new ViewModelProvider(this).get(UsuarioViewModel.class);

        usuarioViewModel.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable final Usuario usuario) {
                updateView(usuario);
            }
        });
    }


    private void updateView(Usuario usuario){
        if(usuario != null && usuario.getId() > 0){
            usuarioCorrente = usuario;
            editTextNome.setText(usuario.getNome());
            editTextCPF.setText(usuario.getCpf());
            editTextEmail.setText(usuario.getEmail());
            editTextSenha.setText(usuario.getSenha());
        }
    }

    public void salvar(View view){
        usuarioCorrente.setNome(editTextNome.getText().toString());
        usuarioCorrente.setCpf(editTextCPF.getText().toString());
        usuarioCorrente.setEmail(editTextEmail.getText().toString());
        usuarioCorrente.setSenha(editTextSenha.getText().toString());
        usuarioViewModel.insert(usuarioCorrente);
        Hawk.put("tem_cadastro",true);
        Toast.makeText(this,"Usuário salvo com sucesso!",Toast.LENGTH_SHORT).show();
        finish();
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