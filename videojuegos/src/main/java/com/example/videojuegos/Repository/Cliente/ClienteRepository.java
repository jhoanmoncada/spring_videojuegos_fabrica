package com.example.videojuegos.Repository.Cliente;

import java.util.Map;

import org.springframework.stereotype.Component;
import com.example.videojuegos.Utils;
import com.example.videojuegos.Model.Cliente;

@Component
public class ClienteRepository {
	
	private ClienteRepositoryDao cliente;
	
	public ClienteRepository(ClienteRepositoryDao cliente) {
		super();
		this.cliente = cliente;
	}

	public Map<String, Object> registrarCliente(Cliente cli){
		try {
			cliente.save(cli);
			return Utils.respuesta(true,"Registro exitoso", cli);
		}catch(Exception e) {
			System.err.println("ERROR:: " + e.getMessage());
			return Utils.respuesta(true,"Fallo el registro", null);
		}
	}
	

}
