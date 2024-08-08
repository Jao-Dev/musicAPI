package com.example.music.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.music.entities.Artista;
import com.example.music.entities.Musica;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    Optional<Artista> findByNomeContainingIgnoreCase(String nome);
    
    //@Query("SELECT m FROM Artista a JOIN a.musica m WHERE a.nome ILIKE %:nome%")
    @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE a.nome ILIKE :nome")
    List<Musica> buscaMusicasPorArtista(@Param("nome") String nome);
}
