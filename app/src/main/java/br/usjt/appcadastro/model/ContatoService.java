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

    @GET("/api/a54241c45e264268bb3a8877f55c8d9b/contato")
    Call<List<Contato>> getAllContatos();

    @POST("/api/a54241c45e264268bb3a8877f55c8d9b/contato")
    Call<ResponseBody> salvarContato(
            @Body
            Contato contato);

    @PUT("/api/a54241c45e264268bb3a8877f55c8d9b/contato/{id}")
    Call<ResponseBody> alterarContato(
            @Path("id") String id,
            @Body Contato contato);

    @DELETE("/api/a54241c45e264268bb3a8877f55c8d9b/contato/{id}")
    Call<ResponseBody> deletarContato(
            @Path("id") String id);
}
