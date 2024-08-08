package com.example.music.principal;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.music.entities.Artista;
import com.example.music.entities.Musica;
import com.example.music.entities.TipoArtista;
import com.example.music.repositories.ArtistaRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class Principal {

    private ArtistaRepository artistaRepository;
    
    private Scanner leitura = new Scanner(System.in);

    @Autowired
    public Principal(ArtistaRepository artistaRepository){
        this.artistaRepository = artistaRepository;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao!= 9) {
            var menu = """
                    *** Screen Sound Músicas ***                    
                                        
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas
                                    
                    9 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 9:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }


    private void cadastrarArtistas() {
        
        var cadastrarNovo = "S";

        while (cadastrarNovo.equalsIgnoreCase("s")) {
            System.out.println("Informe o nome do artista: ");
            var nome = leitura.nextLine();
            System.out.println("Informe o tipo desse artista: (solo, dupla ou banda)");
            var tipo = leitura.nextLine();
            
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nome, tipoArtista);
            artistaRepository.save(artista);

            System.out.println("Cadastrar novo artista? (S/N)");
            cadastrarNovo = leitura.nextLine();
        }
    }

    private void cadastrarMusicas() {
        
        System.out.println("Cadastra música de que artista? ");
        var nomeArtista = leitura.nextLine();
        
        Optional<Artista> artista = artistaRepository.findByNomeContainingIgnoreCase(nomeArtista);
        if (artista.isPresent()) {
            
            System.out.println("Informe o título da música: ");
            var nomeMusica = leitura.nextLine();
            Musica musica = new Musica(nomeMusica);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            artistaRepository.save(artista.get());
        }else{
            System.out.println("Artista não encontrado!");
        }
    }

    private void listarMusicas(){
        List<Artista> artistas = artistaRepository.findAll();
        artistas.forEach(a -> a.getMusicas().forEach(System.out::println));
    }

    private void buscarMusicasPorArtista(){
        System.out.println("Buscar músicas de que artista? ");
        var nomeArtista = leitura.nextLine();
        List<Musica> musicas = artistaRepository.buscaMusicasPorArtista(nomeArtista);
        musicas.forEach(System.out::println);
    }
}
