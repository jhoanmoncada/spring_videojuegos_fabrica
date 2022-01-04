package com.example.videojuegos.Dto;

import java.util.Date;
import java.util.List;

import com.example.videojuegos.Model.Juego;


public class AlquilerDTO {

	private Date fechaEntrega;
	private List<Juego>juegos;
	private Integer idCliente;
	private Integer idAlquiler;
	
	public AlquilerDTO(Date fechaEntrega, List<Juego> juegos, Integer idCliente) {
		super();
		this.fechaEntrega = fechaEntrega;
		this.juegos = juegos;
		this.idCliente = idCliente;
	}
	
	
	
	public AlquilerDTO(Integer idAlquiler) {
		super();
		this.idAlquiler = idAlquiler;
	}

	public AlquilerDTO() {
		super();
	}
	
	public Integer getIdAlquiler() {
		return idAlquiler;
	}

	public void setIdAlquiler(Integer idAlquiler) {
		this.idAlquiler = idAlquiler;
	}



	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public List<Juego> getJuegos() {
		return juegos;
	}
	public void setJuegos(List<Juego> juegos) {
		this.juegos = juegos;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	
	
	
}
