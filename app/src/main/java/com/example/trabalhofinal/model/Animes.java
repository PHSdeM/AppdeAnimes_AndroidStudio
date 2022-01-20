package com.example.trabalhofinal.model;
import java.io.Serializable;

public class Animes implements Serializable {

    private int id;
    private String nome;
    private String genero;
    private int temporadas;

    public Animes() {
    }

    public Animes(int id, String nome, String genero, int temporadas) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.temporadas = temporadas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }


    @Override
    public String toString() {
        return "table_animes {" +
                "id= , " + id +
                ", nome='"  + nome + "" +
        ", temporada" + temporadas + "" +
        ", genero=" + genero;
    }
}

