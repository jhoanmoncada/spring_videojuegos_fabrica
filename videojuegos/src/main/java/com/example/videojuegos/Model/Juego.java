package com.example.videojuegos.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Juego {
	
	
	@Id
	@Column(name = "id_juego")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idJuego;
	
	
	private String nombre;
	private String anio;
	private String director;
	private String productor;
	private String protagonistas;
	private String tecnologia;
	private boolean disponible;
	private Integer valor;
	

	
	public Juego(String nombre, String anio, String director, String productor, String protagonistas, String tecnologia,
			Integer valor) {
		super();
		this.nombre = nombre;
		this.anio = anio;
		this.director = director;
		this.productor = productor;
		this.protagonistas = protagonistas;
		this.tecnologia = tecnologia;
		this.valor = valor;
	}
	public Juego() {
		super();
	}
	
	
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getProductor() {
		return productor;
	}
	public void setProductor(String productor) {
		this.productor = productor;
	}
	public String getProtagonistas() {
		return protagonistas;
	}
	public void setProtagonistas(String protagonistas) {
		this.protagonistas = protagonistas;
	}
	public String getTecnologia() {
		return tecnologia;
	}
	public void setTecnologia(String tecnologia) {
		this.tecnologia = tecnologia;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public Integer getIdJuego() {
		return idJuego;
	}
	public void setIdJuego(Integer idJuego) {
		this.idJuego = idJuego;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	
}
