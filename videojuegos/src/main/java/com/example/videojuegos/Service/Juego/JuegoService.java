package com.example.videojuegos.Service.Juego;

import java.util.Map;

import com.example.videojuegos.Model.Juego;
import com.example.videojuegos.Repository.Juego.JuegoRepositoryDao;
import com.example.videojuegos.Repository.Juego.JuegoRespository;

public class JuegoService {
	
	private JuegoRepositoryDao juegorepo;

	public JuegoService(JuegoRepositoryDao juegorepo) {
		super();
		this.juegorepo = juegorepo;
	}
	
	
	public  Map<String, Object> registrarJuego(Juego juego){
		JuegoRespository juegoRespository = new JuegoRespository(juegorepo); 
		juego.setDisponible(true);
		return juegoRespository.registrarJuego(juego);
	}

}
