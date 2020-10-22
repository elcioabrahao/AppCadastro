package br.usjt.appcadastro.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Contato implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("telefone")
    @Expose
    private String telefone;
    @SerializedName("tipo_contato")
    @Expose
    private String tipoContato;
    @SerializedName("privado")
    @Expose
    private boolean privado;
    @SerializedName("imagem")
    @Expose
    private String imagem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(String tipoContato) {
        this.tipoContato = tipoContato;
    }

    public boolean isPrivado() {
        return privado;
    }

    public void setPrivado(boolean privado) {
        this.privado = privado;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
