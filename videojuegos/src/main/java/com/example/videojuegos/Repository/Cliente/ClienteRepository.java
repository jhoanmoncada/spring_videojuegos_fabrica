package com.example.videojuegos.Repository.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
			return Utils.respuesta(false,"Fallo el registro", null);
		}
	}
	
	public List<Cliente> listarClientes(){
		List <Cliente> clientes = new ArrayList<Cliente>();
		try {
			clientes = cliente.findAll();
		}catch(Exception e) {
			System.err.println("ERROR:: "+e.getMessage());
		}
		return clientes; 
	}
	
	public Optional<Cliente> buscarCliente(Integer id) {
		Optional<Cliente> clien = null;
		try {
			clien = cliente.findById(id);
			return clien;
		} catch (Exception e) {
			System.err.println("ERROR:: "+e.getMessage());
		}
		return clien;		
	}
	
}
