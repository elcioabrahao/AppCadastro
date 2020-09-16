package br.usjt.appcadastro.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Usuario.class}, version = 1, exportSchema = false)
public abstract class AlunoDatabase extends RoomDatabase {

    public abstract UsuarioDao usuarioDao();

    private static volatile AlunoDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AlunoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AlunoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AlunoDatabase.class, "aluno_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
