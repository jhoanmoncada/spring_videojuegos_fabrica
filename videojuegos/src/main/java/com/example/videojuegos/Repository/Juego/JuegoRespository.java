package com.example.videojuegos.Repository.Juego;

import java.util.Map;
import com.example.videojuegos.Utils;
import com.example.videojuegos.Model.Juego;

public class JuegoRespository {
	
	private JuegoRepositoryDao juegorepo;

	public JuegoRespository(JuegoRepositoryDao juegorepo) {
		super();
		this.juegorepo = juegorepo;
	}

	public  Map<String, Object> registrarJuego(Juego juego){
		try {
			juegorepo.save(juego);
			return Utils.respuesta(true,"Registro exitoso", juego.getNombre()+ " ha sido registrado");
		}catch(Exception e) {
			System.err.println("ERROR:: " + e.getMessage());
			return Utils.respuesta(true,"Registro fallido", null);
		}
	}

}
