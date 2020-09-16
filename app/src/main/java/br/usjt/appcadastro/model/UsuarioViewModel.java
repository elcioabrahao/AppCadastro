package br.usjt.appcadastro.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Database;

public class UsuarioViewModel extends AndroidViewModel {

    private UsuarioRepository mRepository;

    private LiveData<Usuario> usuario;

    public UsuarioViewModel(@NonNull Application application) {
        super(application);
        mRepository = new UsuarioRepository(application);
        usuario = mRepository.getUsuario();
    }

    public LiveData<Usuario> getUsuario(){
        return usuario;
    }

    public void insert(Usuario usuario){
        mRepository.insert(usuario);
    }

    public void update(Usuario usuario){
        mRepository.update(usuario);
    }

}
