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

    @GET("api/d753435cfe984e8e9de5ba4681b27733/contato")
    Call<List<Contato>> getAllContatos();

    @POST("api/d753435cfe984e8e9de5ba4681b27733/contato")
    Call<ResponseBody> salvarContato(
            @Body
            Contato contato);

    @PUT("api/d753435cfe984e8e9de5ba4681b27733/contato/{id}")
    Call<ResponseBody> alterarContato(
            @Path("id") String id,
            @Body ContatoPut contatoPut);

    @DELETE("api/d753435cfe984e8e9de5ba4681b27733/contato/{id}")
    Call<ResponseBody> deletarContato(
            @Path("id") String id);
}
