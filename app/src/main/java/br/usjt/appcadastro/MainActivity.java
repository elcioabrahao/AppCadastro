package br.usjt.appcadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("CICLO_DE_VIDA","MainActivity --> onCreate");
    }

    public void enviarNome(View view){
        // aqui dentro
        EditText editTextNome = (EditText)findViewById(R.id.nomeEditText);
        EditText editTextCPF = (EditText)findViewById(R.id.cpfEditText);
        EditText editTextEmail = (EditText)findViewById(R.id.emailEditText);
        Log.d("LOGAPP",editTextNome.getText().toString());
        Intent intent = new Intent(this, SegundaActivity.class);
        String nome = editTextNome.getText().toString();
        String cpf = editTextCPF.getText().toString();
        String email = editTextEmail.getText().toString();
        intent.putExtra("NOMEUSUARIO",nome);
        intent.putExtra("CPFUSUARIO",cpf);
        intent.putExtra("EMAILUSUARIO",email);
        startActivity(intent);
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