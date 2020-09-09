package br.usjt.appcadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        Intent intent = getIntent();
        String nome = intent.getStringExtra("NOMEUSUARIO");
        String cpf = intent.getStringExtra("CPFUSUARIO");
        String email = intent.getStringExtra("EMAILUSUARIO");
        TextView textViewNome = (TextView)findViewById(R.id.nomeSegundaTelaTextView);
        textViewNome.setText("Olá "+nome+ " seu CPF é: "+cpf +" e seu email é "+email);

    }

    public void voltar(View view) {
        finish();
    }
}