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

    @GET("/api/b87d236ded1f425093e264a0bc1801c4/contato")
    Call<List<Contato>> getAllContatos();

    @POST("/api/b87d236ded1f425093e264a0bc1801c4/contato")
    Call<ResponseBody> salvarContato(
            @Body
            Contato contato);

    @PUT("/api/b87d236ded1f425093e264a0bc1801c4/contato/{id}")
    Call<ResponseBody> alterarContato(
            @Path("id") String id,
            @Body Contato contato);

    @DELETE("/api/b87d236ded1f425093e264a0bc1801c4/contato/{id}")
    Call<ResponseBody> deletarContato(
            @Path("id") String id);
}
