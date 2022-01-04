package com.example.videojuegos.Dto;

public class ClienteDTO {
	
	private Integer idCliente;
	private String nombre;
	private String documento;
	private String correo;
	private String direccion;
	private String telefono;
	
	public ClienteDTO(Integer idCliente, String nombre, String documento, String correo, String direccion,
			String telefono) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.documento = documento;
		this.correo = correo;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public ClienteDTO() {
		super();
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
