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
    }

    // fiz uma modificação nesse método !

    public void enviarNome(View view){
        // aqui dentro
        EditText editTextNome = (EditText)findViewById(R.id.nomeEditText);
        Log.d("LOGAPP",editTextNome.getText().toString());
        Intent intent = new Intent(this, SegundaActivity.class);
        String nome = editTextNome.getText().toString();
        intent.putExtra("NOMEUSUARIO",nome);
        startActivity(intent);
    }

}