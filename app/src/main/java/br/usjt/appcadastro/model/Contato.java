package br.usjt.appcadastro.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contato {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("telefone")
    @Expose
    private String telefone;
    @SerializedName("email")
    @Expose
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
