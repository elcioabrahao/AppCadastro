package br.usjt.appcadastro.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ContatoService {

    @GET("/contatos")
    Call<ContatoResponse> getAllContatos();

}
