package br.usjt.appcadastro.model;

import androidx.room.Query;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ContatoService {

    @GET("/contatos")
    Call<ContatsResponse> getAllContatos();

}
