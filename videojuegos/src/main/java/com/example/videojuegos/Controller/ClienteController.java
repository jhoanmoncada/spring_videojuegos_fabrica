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
		List <Cliente> clientes=new ArrayList<Cliente>();
		List <ClienteDTO> clientes2=new ArrayList<ClienteDTO>();
		try {
			clientes=cliente.findAll();
			if(clientes.size()==0) {
				return Utils.respuesta(false, "No existen registros", null);
			}
			for(int i=0;i<clientes.size();i++) {
			ClienteDTO cl=new ClienteDTO(clientes.get(i).getIdCliente(),clientes.get(i).getNombre(),clientes.get(i).getDocumento()
					,clientes.get(i).getCorreo(),clientes.get(i).getDireccion(),clientes.get(i).getTelefono());
			clientes2.add(cl);
			}
			return Utils.respuesta(true,"Registros encontrados", clientes2);
			
		}catch(Exception e) {
			return Utils.respuesta(false, "Error al obtener los registros", null);
		}
	}
	
	//buscar un cliente por id
	@GetMapping(path="/buscarCliente/{id}")
	public @ResponseBody Map<String,Object> listarCliente(@PathVariable Integer id){
		try {
			Optional<Cliente> clien = cliente.findById(id);
			if(clien.isPresent()) {
				ClienteDTO cl=new ClienteDTO(clien.get().getIdCliente(),clien.get().getNombre(),clien.get().getDocumento()
						,clien.get().getCorreo(),clien.get().getDireccion(),clien.get().getTelefono());
				return Utils.respuesta(true, "Registro encontrado", cl);
			}
			return Utils.respuesta(false, "Registro no encontrado", null);
		}catch(Exception e) {
			return Utils.respuesta(false, "Error al obtener los registros", null);
		}
	}
	
	//eliminar un cliente por id
	@DeleteMapping(path="/eliminarCliente/{id}")
	public @ResponseBody Map<String,Object> eliminarCliente(@PathVariable Integer id){
		try {
			Optional<Cliente> clien = cliente.findById(id);
			if(clien.isPresent()) {
				cliente.delete(clien.get());
				return Utils.respuesta(true, "Registro eliminado", clien);
			}
			return Utils.respuesta(false, "Registro no encontrado", null);
		}catch(Exception e) {
			return Utils.respuesta(false, "Error al eliminar el registro", null);
		}
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
