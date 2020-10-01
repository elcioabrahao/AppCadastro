package br.usjt.appcadastro.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import java.util.List;

import br.usjt.appcadastro.R;
import br.usjt.appcadastro.model.Contato;
import br.usjt.appcadastro.model.ContatoViewModel;
import br.usjt.appcadastro.model.Usuario;
import br.usjt.appcadastro.model.UsuarioViewModel;


public class ContatoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String CONTATO_FRAGMENT_TAG = "contato_fragment";
    private EditText editTextNomeContato;
    private EditText editTextEmailContato;
    private EditText editTextTelefoneContato;
    private Contato contatoCorrente;
    private Button buttonSalvarContato;
    private ContatoViewModel contatoViewModel;


    private String mParam1;
    private String mParam2;

    public ContatoFragment() {
    }

    public static ContatoFragment newInstance(String param1, String param2) {
        ContatoFragment fragment = new ContatoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        contatoViewModel = new ViewModelProvider(this).get(ContatoViewModel.class);
        contatoViewModel.getSalvoSucesso().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean sucesso) {
                String mensagem = "Criação de contato falhou!";
                if(sucesso){
                    mensagem = "Contato criado com sucesso!";
                }
                Toast.makeText(getActivity(),mensagem,Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contato, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        Hawk.init(getActivity()).build();

        contatoCorrente = new Contato();

        editTextNomeContato = getView().findViewById(R.id.nomeContatoEditText);
        editTextEmailContato = getView().findViewById(R.id.emailContatoEditText);
        editTextTelefoneContato = getView().findViewById(R.id.telefoneContatoEditText);
        buttonSalvarContato = getView().findViewById(R.id.enviarContatoButton);

        buttonSalvarContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

    }

    public void salvar(){
        contatoCorrente.setNome(editTextNomeContato.getText().toString());
        contatoCorrente.setEmail(editTextEmailContato.getText().toString());
        contatoCorrente.setTelefone(editTextTelefoneContato.getText().toString());
        contatoViewModel.salvarContato(contatoCorrente);
    }
}