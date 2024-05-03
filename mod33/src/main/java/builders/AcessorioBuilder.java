package main.java.builders;

import main.java.domain.Acessorio;
import main.java.domain.Carro;

public class AcessorioBuilder {
    private String nome;
    private String descricao;
    private Carro carro;

    public AcessorioBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public AcessorioBuilder setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public AcessorioBuilder setCarro(Carro carro) {
        this.carro = carro;
        return this;
    }

    public Acessorio createAcessorio() {
        return new Acessorio(nome, descricao, carro);
    }
}