package com.example.videojuegos.Service;

import java.util.List;
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
	
	public Map<String,Object> listarClientes(){
		ClienteRepository clienteRepository = new ClienteRepository(this.cliente);
		try {
			List <Cliente> clientes = clienteRepository.listarClientes();
			if(clientes.size() == 0) {
				return Utils.respuesta(false, "No existen registros", null);
			} else {
				return Utils.respuesta(true,"Registros encontrados", clientes);
			}
		}catch(Exception e) {
			return Utils.respuesta(false, "Error al obtener los registros", null);
		}
	}

}