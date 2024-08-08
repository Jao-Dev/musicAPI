package com.example.music.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "musicas")
public class Musica {   
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    @Override
    public String toString() {
        return "Musica='" + titulo + '\'' + ", artista=" + artista.getNome();
    }

    public Musica(String nomeMusica) {
        this.titulo = nomeMusica;
    }
    
}
