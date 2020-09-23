package br.usjt.appcadastro.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ContatoViewModel extends AndroidViewModel {
    private ContatoRepository contatoRepository;
    private LiveData<ContatoResponse> contatoResponseLiveData;

    public ContatoViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        contatoRepository = new ContatoRepository();
        contatoResponseLiveData = contatoRepository.getContatosResponseLiveData();
    }


    public LiveData<ContatoResponse> getContatoResponseLiveData() {
        return contatoResponseLiveData;
    }
}
