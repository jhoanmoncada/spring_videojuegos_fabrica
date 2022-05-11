package com.example.videojuegos.Model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Alquiler {

	
	@Id
	@Column(name = "id_alquiler")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAlquiler;
	
	@ManyToOne
	@JoinColumn(name = "cliente", nullable = false, referencedColumnName = "id_cliente")
	@JsonBackReference
	private Cliente cliente;
	
	@ManyToMany
    @JoinTable(name = "alquiler_juegos",joinColumns = {@JoinColumn(name="id_alquiler")},inverseJoinColumns={@JoinColumn(name="id_juego")})    
	List<Juego> juegos;
	
	
	@Column(name = "fecha_prestamo", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@CreationTimestamp
	private Timestamp fechaPrestamo;
	
	private Date fechaEntrega;
	private Long total;
	private boolean finalizado;
	
	public Alquiler(Cliente cliente, List<Juego> juegos, Date fechaEntrega, boolean finalizado) {
		super();
		this.cliente = cliente;
		this.juegos = juegos;
		this.fechaEntrega = fechaEntrega;
		this.finalizado=finalizado;
		this.total = calcularTotal(juegos);
	}
	
	public Alquiler() {
		super();
	}
	
	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Integer getIdAlquiler() {
		return idAlquiler;
	}
	public void setIdAlquiler(Integer idAlquiler) {
		this.idAlquiler = idAlquiler;
	}
	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}
	public void setFechaPrestamo(Timestamp fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Juego> getJuegos() {
		return juegos;
	}
	public void setJuegos(List<Juego> juegos) {
		this.juegos = juegos;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
	
	public Long calcularTotal(List<Juego>juegos) {
		Long totall=(long) 0;
		for(Juego j: juegos) {
			totall+=j.getValor();
		}
		return totall;
	}
	
	
}
