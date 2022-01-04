package com.example.videojuegos.Dto;

public class JuegoDTO {

private Integer idJuego;
	
	
	private String nombre;
	private String anio;
	private String director;
	private String productor;
	private String protagonistas;
	private String tecnologia;
	private boolean disponible;
	private Integer valor;
	
	public JuegoDTO(Integer idJuego, String nombre, String anio, String director, String productor,
			String protagonistas, String tecnologia, boolean disponible, Integer valor) {
		super();
		this.idJuego = idJuego;
		this.nombre = nombre;
		this.anio = anio;
		this.director = director;
		this.productor = productor;
		this.protagonistas = protagonistas;
		this.tecnologia = tecnologia;
		this.disponible = disponible;
		this.valor = valor;
	}

	public JuegoDTO() {
		super();
	}

	public JuegoDTO(String nombre, Integer valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
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

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}
	
	
	
}
