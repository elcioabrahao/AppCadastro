package br.usjt.appcadastro.model;

import java.util.List;

public class ContatoResponse {

    private List<Contato> contatos;

    public List<Contato> getContatos() {

        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
}
