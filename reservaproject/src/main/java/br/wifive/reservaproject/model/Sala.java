package br.wifive.reservaproject.model;

public class Sala {
    private String id;
    private String nome;
    private int capacidade;

    public Sala() {}

    public Sala(String id, String nome, int capacidade) {
        this.id = id;
        this.nome = nome;
        this.capacidade = capacidade;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getCapacidade() { return capacidade; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }
}