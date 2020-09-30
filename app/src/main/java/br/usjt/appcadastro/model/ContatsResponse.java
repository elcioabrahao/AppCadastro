package br.usjt.appcadastro.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContatsResponse {

    @SerializedName("contatos")
    @Expose
    List<Contato> contatos = null;

}
