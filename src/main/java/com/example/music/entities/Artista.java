package com.example.music.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "artistas")
public class Artista {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoArtista tipo;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas = new ArrayList<>();

    @Override
    public String toString() {
        return "Artista='" + nome + '\'' + ", tipo=" + tipo + ", musicas=" + musicas;
    }

    public Artista(String nome, TipoArtista tipoArtista) {
        this.nome = nome;
        this.tipo = tipo;
    }
    
}
