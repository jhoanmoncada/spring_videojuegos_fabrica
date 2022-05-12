package com.example.videojuegos.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.videojuegos.Utils;
import com.example.videojuegos.Dto.ClienteDTO;
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
		return clienteRepository.registrarActualizarCliente(cliente);
		
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
	
	public Map<String,Object> buscarCliente(Integer id){
		ClienteRepository clienteRepository = new ClienteRepository(this.cliente);
		try {
			Optional<Cliente> cliente = clienteRepository.buscarCliente(id);
			if(cliente != null) {
				if(cliente.isPresent()) {
					return Utils.respuesta(true, "Registro encontrado", cliente);
				} else {
					return Utils.respuesta(false, "Registro no encontrado", null);
				}
			} else {
				return Utils.respuesta(false, "Registro no encontrado", null);
			}
		} catch (Exception e) {
			return Utils.respuesta(false, "Error al obtener el registro", null);
		}
	}
	
	public Map<String, Object> eliminarCliente(Integer id){
		ClienteRepository clienteRepository = new ClienteRepository(this.cliente);
		Optional<Cliente> cliente = clienteRepository.buscarCliente(id);
		if(cliente != null) {
			if(cliente.isPresent()) {
				return clienteRepository.eliminarCliente(id);
			} 
		}
		return Utils.respuesta(false, "Cliente no encontrado", null);
	}
	
	public Map<String, Object> actualizarCliente(ClienteDTO cl){
		ClienteRepository clienteRepository = new ClienteRepository(this.cliente);
		Optional<Cliente> clien = clienteRepository.buscarCliente(cl.getIdCliente());
		if(clien.isPresent()) {
			Cliente cl2=clien.get();
			if(!cl.getNombre().isBlank()) {
				cl2.setNombre(cl.getNombre());
			}
			if(!cl.getCorreo().isBlank()) {
				cl2.setCorreo(cl.getCorreo());
			}
			if(!cl.getDireccion().isBlank()) {
				cl2.setDireccion(cl.getDireccion());
			}
			if(!cl.getDocumento().isBlank()) {
				cl2.setDocumento(cl.getDocumento());
			}
			if(!cl.getTelefono().isBlank()) {
				cl2.setTelefono(cl.getTelefono());
			}
			return clienteRepository.registrarActualizarCliente(cl2);
		}else {
			return Utils.respuesta(false, "El cliente no existe", null);
			}
	}
	

}
