package com.example.videojuegos.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.videojuegos.Utils;
import com.example.videojuegos.Dto.ClienteDTO;
import com.example.videojuegos.Model.Cliente;
import com.example.videojuegos.Repository.Cliente.ClienteRepositoryDao;
import com.example.videojuegos.Service.ClienteService;


@RestController
@RequestMapping(path="cliente")
public class ClienteController {

	@Autowired
	private ClienteRepositoryDao cliente;
	
	//registrar un cliente
	@PostMapping(path="/registrarCliente")
	public @ResponseBody Map<String, Object> registrarCliente (@RequestBody Cliente cli) {
			ClienteService clienteService = new ClienteService(cliente);
			return clienteService.registrarCliente(cli);
	}
	
	//obtener todos los registros
	@GetMapping(path="/todosClientes")
	public @ResponseBody Map<String,Object> listarClientes(){
		ClienteService clienteService = new ClienteService(cliente);
		return clienteService.listarClientes();
	}
	
	//buscar un cliente por id
	@GetMapping(path="/buscarCliente/{id}")
	public @ResponseBody Map<String,Object> listarCliente(@PathVariable Integer id){
		ClienteService clienteService = new ClienteService(cliente);
		return clienteService.buscarCliente(id);
	}
	
	//eliminar un cliente por id
	@DeleteMapping(path="/eliminarCliente/{id}")
	public @ResponseBody Map<String,Object> eliminarCliente(@PathVariable Integer id){
		ClienteService clienteService = new ClienteService(cliente);
		return clienteService.eliminarCliente(id);	
	}
	
	//actualizar un cliente
	@PutMapping(path="/actualizarCliente")
	public @ResponseBody Map<String, Object> actualizarCliente (@RequestBody ClienteDTO cl) {
		try {
			Optional<Cliente> clien = cliente.findById(cl.getIdCliente());
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
				cliente.save(cl2);
				ClienteDTO cldto=new ClienteDTO(cl2.getIdCliente(),cl2.getNombre(),cl2.getDocumento()
						,cl2.getCorreo(),cl2.getDireccion(),cl2.getTelefono());
				return Utils.respuesta(true,"Registro actualizado", cldto);
			}
			return Utils.respuesta(false,"Registro no encontrado", null);
		}catch(Exception e) {
			return Utils.respuesta(false, "Error al modificar: "+e.getMessage(), null);
		}
	}
}
