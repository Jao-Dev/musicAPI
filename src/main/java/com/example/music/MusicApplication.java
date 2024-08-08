package com.example.music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.music.principal.Principal;
import com.example.music.repositories.ArtistaRepository;

@SpringBootApplication
public class MusicApplication implements CommandLineRunner{

	@Autowired
	private ArtistaRepository artistaRepository;

	@Autowired
	private Principal principal;

	public static void main(String[] args) {
		SpringApplication.run(MusicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		principal.exibeMenu();
	}

	

}
