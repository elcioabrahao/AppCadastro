package br.usjt.appcadastro.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContatoRepository {
    private static final String BOOK_SEARCH_SERVICE_BASE_URL = "https://www.googleapis.com/";

    private ContatoService contatoService;
    private MutableLiveData<ContatoResponse> contatosResponseLiveData;

    public ContatoRepository() {
        contatosResponseLiveData = new MutableLiveData<>();


        OkHttpClient client = new OkHttpClient.Builder().build();

        contatoService = new retrofit2.Retrofit.Builder()
                .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ContatoService.class);

    }


    public LiveData<ContatoResponse> getContatosResponseLiveData() {
        return contatosResponseLiveData;
    }
}
