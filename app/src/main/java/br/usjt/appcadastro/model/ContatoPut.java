package br.usjt.appcadastro.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContatoPut {
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

    public ContatoPut(String nome,
                      String email,
                      String telefone,
                      String tipoContato,
                      boolean privado,
                      String imagem) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.tipoContato = tipoContato;
        this.privado = privado;
        this.imagem = imagem;
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
}
