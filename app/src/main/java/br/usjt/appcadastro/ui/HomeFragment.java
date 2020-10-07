package br.usjt.appcadastro.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import java.util.List;

import br.usjt.appcadastro.R;
import br.usjt.appcadastro.model.Contato;
import br.usjt.appcadastro.model.ContatoViewModel;
import br.usjt.appcadastro.model.ContatosResponse;
import br.usjt.appcadastro.model.Usuario;
import br.usjt.appcadastro.model.UsuarioViewModel;


public class HomeFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String HOME_FRAGMENT_TAG = "home_fragment";
    private ContatoViewModel contatoViewModel;
    private List<Contato> contatos;
    private TextView conteudo;
    private Button buttonAtualizar;
    private ContatoAdapter contatoAdapter;

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contatoAdapter = new ContatoAdapter();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        contatoViewModel = new ViewModelProvider(this).get(ContatoViewModel.class);
        contatoViewModel.getContatosResponseLiveData().observe(this, new Observer<List<Contato>>() {
            @Override
            public void onChanged(List<Contato> contatosList) {
                Log.d("RESPOSTA","NULLO DO RESPOSE");
                if (contatosList != null) {
                    contatoAdapter.setResults(contatosList);
                }
            }
        });

        contatoAdapter.setOnItemClickListener(new ContatoAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v, Contato contato) {
                Log.d("FRAGMENTHOME", "onItemClick contato: " + contato.toString());
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayoutMainActivity,
                                ContatoFragment.newInstance("",contato),
                                ContatoFragment.CONTATO_FRAGMENT_TAG)
                        .addToBackStack("contato_click")
                        .commit();

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewContatos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(contatoAdapter);



        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        //contatoViewModel.getContatos();
    }

    @Override
    public void onResume(){
        super.onResume();
        contatoViewModel.getContatos();
    }


}