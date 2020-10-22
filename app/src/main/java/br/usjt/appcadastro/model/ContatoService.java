package br.usjt.appcadastro.model;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContatoService {

    @GET("/api/9382fe2b93f748fba27fdf623e6718d3/contato")
    Call<List<Contato>> getAllContatos();

    @POST("/api/9382fe2b93f748fba27fdf623e6718d3/contato")
    Call<ResponseBody> salvarContato(
            @Body
            Contato contato);

    @PUT("/api/9382fe2b93f748fba27fdf623e6718d3/contato/{id}")
    Call<ResponseBody> alterarContato(
            @Path("id") String id,
            @Body ContatoPut contatoPut);

    @DELETE("/api/9382fe2b93f748fba27fdf623e6718d3/contato/{id}")
    Call<ResponseBody> deletarContato(
            @Path("id") String id);
}
