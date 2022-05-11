package com.example.videojuegos.Service;

import java.util.Map;

import com.example.videojuegos.Utils;
import com.example.videojuegos.Model.Cliente;
import com.example.videojuegos.Repository.Cliente.ClienteRepository;
import com.example.videojuegos.Repository.Cliente.ClienteRepositoryDao;

public class ClienteService {
	
	private ClienteRepositoryDao cliente;
	
	public ClienteService(ClienteRepositoryDao cliente) {
		super();
		this.cliente = cliente;
	}

	public Map<String, Object> registrarCliente(Cliente cliente){
		ClienteRepository clienteRepository = new ClienteRepository(this.cliente);
		if(cliente.getNombre() == null) {
			return Utils.respuesta(true,"Fallo el registro", null);
		}
		return clienteRepository.registrarCliente(cliente);
		
	}

}
