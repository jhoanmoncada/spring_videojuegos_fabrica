package com.example.videojuegos.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.videojuegos.Utils;
import com.example.videojuegos.Model.Alquiler;
import com.example.videojuegos.Model.Cliente;
import com.example.videojuegos.Model.Juego;
import com.example.videojuegos.Dto.AlquilerDTO;
import com.example.videojuegos.Repository.AlquilerRepository;
import com.example.videojuegos.Repository.ClienteRepository;
import com.example.videojuegos.Repository.JuegoRepository;



@RestController
@RequestMapping(path="alquiler")
public class AlquilerController {
	
	
	@Autowired
	private AlquilerRepository alquirepo;
	
	@Autowired
	private ClienteRepository cliente;
	
	@Autowired
	private JuegoRepository juegorepo;
	
	
	//registrar un alquiler
	@PostMapping(path="/registrarAlquiler")
	public @ResponseBody Map<String, Object> registrarJuego (@RequestBody AlquilerDTO al) {
		try {
			List<Juego> juegosop= new ArrayList<Juego>();
			int contador=0;
			int recibidor=al.getJuegos().size();
			for(int i=0;i<recibidor;i++) {
				Optional<Juego> jg=juegorepo.findById(al.getJuegos().get(i).getIdJuego());
				if(jg.isPresent()&&jg.get().isDisponible()) {
					juegosop.add(jg.get());
					contador++;
				}
			}
			if(contador!=recibidor) {
				return Utils.respuesta(false,"No se encontraron algunos o todos losjuegos", null);
			}
			
			Optional<Cliente> clien = cliente.findById(al.getIdCliente());
			if(clien.isPresent()) {
				Alquiler alq=new Alquiler(clien.get(),juegosop,al.getFechaEntrega(),false);
				alquirepo.save(alq);
				for(int i=0;i<recibidor;i++) {
					Juego jue=juegosop.get(i);
					jue.setDisponible(false);
					juegorepo.save(jue);
				}
				
				return Utils.respuesta(true, "exito ", alq);
			}
			
			return Utils.respuesta(false, "cliente no existe ", null);
		}catch(Exception e) {
			return Utils.respuesta(false, "Error al registrar: "+e, null);
		}
	}
	
	//obtener todos los registros
	@GetMapping(path="/todosAlquileres")
	public @ResponseBody Map<String,Object> listarJuegos(){
		List <Alquiler> alquiler=new ArrayList<Alquiler>();
		try {
			alquiler=alquirepo.findAll();
			if(alquiler.size()==0) {
				return Utils.respuesta(false, "No existen registros", alquiler);
			}
			return Utils.respuesta(true,"Registros encontrados", alquiler);
		}catch(Exception e) {
			return Utils.respuesta(false, "Error al obtener los registros", null);
		}
	}
	
	//obtener todos los registros con prestamos activos
	@GetMapping(path="/prestamoAlquileres")
	public @ResponseBody Map<String,Object> listarAlquilados(){
		List <Alquiler> alquiler=new ArrayList<Alquiler>();
		
		List <Cliente> clientes=new ArrayList<Cliente>();
		List <Cliente> clientes2=new ArrayList<Cliente>();
		try {
			
			clientes=cliente.findAll();
			for(int i=0;i<clientes.size();i++) {
				List <Alquiler> alquiler2=new ArrayList<Alquiler>();
				alquiler=clientes.get(i).getAlquilados();
				for(int j=0;j<alquiler.size();j++) {
					if(!alquiler.get(j).isFinalizado()) {
						alquiler2.add(alquiler.get(j));
					}
				}
				if(alquiler2.size()>0) {
					clientes2.add(clientes.get(i));
				}
			}
			if(clientes2.size()>0) {
				return Utils.respuesta(true,"Alquileres por entregar", clientes2);
			}
			return Utils.respuesta(false,"No se encontraron alquileres activos", null);
		}catch(Exception e) {
			return Utils.respuesta(false, "Error al obtener los registros", null);
		}
	}
	
	//buscar un venta por fecha
	@GetMapping(path="/buscarAlquiler/{fecha}")
	public @ResponseBody Map<String,Object> listarCliente(@PathVariable String fecha){
		try {
			List <Alquiler> alquiler=new ArrayList<Alquiler>();
			alquiler=alquirepo.findAll();
			
			List <Alquiler> alquiler2=new ArrayList<Alquiler>();
			if(alquiler==null||alquiler.size()==0) {
				return Utils.respuesta(false, "No existen registros de alquileres", null);
			}
			SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
			Date date=sdformat.parse(fecha);
			for(int i=0;i<alquiler.size();i++) {
				Date date2=sdformat.parse(alquiler.get(i).getFechaPrestamo().toString());
				if(date2.compareTo(date)==0) {
					alquiler2.add(alquiler.get(i));
				}
			}
			
			if(alquiler2.size()>0) {
				return Utils.respuesta(true, "Registros encontrados", alquiler2);
			}
			
			return Utils.respuesta(false, "No existen registros", null);
		}catch(Exception e) {
			return Utils.respuesta(false, "Error al obtener los registros", null);
		}
	}
	
	//actualizar entrega
	@PutMapping(path="/entregarJuego")
	public @ResponseBody Map<String, Object> entregarJuego (@RequestBody AlquilerDTO al) {
		try {
			
			Optional<Alquiler> alq=alquirepo.findById(al.getIdAlquiler());
			if(alq.isPresent()) {
				if(!alq.get().isFinalizado()) {
					List<Juego> juegos=new ArrayList<Juego>();
					List<Juego> juegos2=alq.get().getJuegos();
					
					for(int i=0;i<juegos2.size();i++) {
						Optional<Juego> jg=juegorepo.findById(juegos2.get(i).getIdJuego());
						if(jg.isPresent()) {
							juegos.add(jg.get());
						}
					}
					
					Alquiler alq2=alq.get();
					alq2.setFinalizado(true);
					alquirepo.save(alq2);
					for(int i=0;i<juegos.size();i++) {
						Juego jue=juegos.get(i);
						jue.setDisponible(true);
						juegorepo.save(jue);
					}
					return Utils.respuesta(true, "Alquiler finalizado y Juegos entregados", null);
				}
				return Utils.respuesta(false, "Los juegos ya fueron entregados", null);
			}
			
			
			return Utils.respuesta(false, "No se encontraron registros", null);

		}catch(Exception e) {
			return Utils.respuesta(false, "Error al obtener los registros: "+e, null);
		}
	}

}
