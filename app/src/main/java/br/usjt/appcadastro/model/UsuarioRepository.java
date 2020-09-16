package br.usjt.appcadastro.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UsuarioRepository {
    private UsuarioDao usuarioDao;
    private LiveData<Usuario> usuario;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    UsuarioRepository(Application application) {
        AlunoDatabase db = AlunoDatabase.getDatabase(application);
        usuarioDao = db.usuarioDao();
        usuario = usuarioDao.getUsuario();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<Usuario> getUsuario() {
        return usuario;
    }

    public void insert(Usuario usuario) {
        AlunoDatabase.databaseWriteExecutor.execute(() -> {
            usuarioDao.insert(usuario);
        });
    }

    public void update(Usuario usuario){
        AlunoDatabase.databaseWriteExecutor.execute(() -> {
            usuarioDao.update(usuario);
        });
    }
}
