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
import com.example.videojuegos.Dto.JuegoDTO;
import com.example.videojuegos.Model.Alquiler;
import com.example.videojuegos.Model.Cliente;
import com.example.videojuegos.Model.Juego;
import com.example.videojuegos.Repository.JuegoRepository;


@RestController
@RequestMapping(path="juego")
public class JuegoController {
	
	@Autowired
	private JuegoRepository juegorepo;
	
	//registrar un juego
			@PostMapping(path="/registrarJuego")
			public @ResponseBody Map<String, Object> registrarJuego (@RequestBody Juego juego) {
				try {
					juego.setDisponible(true);
					juegorepo.save(juego);
					return Utils.respuesta(true,"Registro exitoso", juego);
				}catch(Exception e) {
					return Utils.respuesta(false, "Error al registrar", null);
				}
			}
			
			
			//obtener todos los registros
			@GetMapping(path="/todosJuegos")
			public @ResponseBody Map<String,Object> listarJuegos(){
				List <Juego> juegos=new ArrayList<Juego>();
				try {
					juegos=juegorepo.findAll();
					if(juegos.size()==0) {
						return Utils.respuesta(false, "No existen registros", null);
					}
					return Utils.respuesta(true,"Registros encontrados", juegos);
					
				}catch(Exception e) {
					return Utils.respuesta(false, "Error al obtener los registros "+e, null);
				}
			}
			
			//eliminar un juego por id
			@DeleteMapping(path="/eliminarJuego/{id}")
			public @ResponseBody Map<String,Object> eliminarCliente(@PathVariable Integer id){
				try {
					Optional<Juego> jue = juegorepo.findById(id);
					if(jue.isPresent()) {
						juegorepo.delete(jue.get());
						return Utils.respuesta(true, "Registro eliminado", jue);
					}
					return Utils.respuesta(false, "Registro no encontrado", null);
				}catch(Exception e) {
					return Utils.respuesta(false, "Error al eliminar el registro", null);
				}
			}
			
			//actualizar juego
			@PutMapping(path="/actualizarJuego")
			public @ResponseBody Map<String, Object> actualizarJuego (@RequestBody JuegoDTO ju) {
				try {
					Optional<Juego> jue = juegorepo.findById(ju.getIdJuego());
					if(jue.isPresent()) {
						
						Juego jg2=jue.get();
						if(ju.getValor()>0) {
							jg2.setValor(ju.getValor());
						}
						if(!ju.getDirector().isBlank()) {
							jg2.setDirector(ju.getDirector());
						}
						jg2.setDisponible(ju.isDisponible());
						
						if(!ju.getNombre().isBlank()) {
							jg2.setNombre(ju.getNombre());
						}
						if(!ju.getAnio().isBlank()) {
							jg2.setAnio(ju.getAnio());
						}
						if(!ju.getProtagonistas().isBlank()) {
							jg2.setProtagonistas(ju.getProtagonistas());
						}
						if(!ju.getDirector().isBlank()) {
							jg2.setDirector(ju.getDirector());
						}
						if(!ju.getTecnologia().isBlank()) {
							jg2.setTecnologia(ju.getTecnologia());
						}
						jg2.setIdJuego(ju.getIdJuego());
						juegorepo.save(jg2);
						return Utils.respuesta(true,"Registro actualizado", jg2);
						
					}
					return Utils.respuesta(false,"Registro no encontrado", null);
					
				}catch(Exception e) {
					
					return Utils.respuesta(false, "Error al modificar: "+e.getMessage(), null);
					
				}
			}
			
			
			//actualizar el precio de un juego
			@PutMapping(path="/actualizarPrecioJuego")
			public @ResponseBody Map<String, Object> actualizarPrecioJuego (@RequestBody JuegoDTO ju) {
				try {
					List<Juego> jue = juegorepo.findAll();
					List<Juego> juego= new ArrayList<Juego>();
					if(jue.size()>0) {
	
						for(int i=0;i<jue.size();i++) {
							if(ju.getNombre().trim().equalsIgnoreCase(jue.get(i).getNombre().trim())) {
								Juego jg2=jue.get(i);
								if(ju.getValor()!=null) {
									jg2.setValor(ju.getValor());
									juegorepo.save(jg2);
									juego.add(jg2);
								}
							}
						}
						if(juego.size()>0) {
							return Utils.respuesta(true,"Registro actualizado",juego);
						}
						return Utils.respuesta(false,"Juegos no encontrados",null);
					}
					return Utils.respuesta(false,"No existen juegos registrados", null);
				}catch(Exception e) {
					return Utils.respuesta(false, "Error al modificar: "+e.getMessage(), null);
				}
			}
			
			

}
