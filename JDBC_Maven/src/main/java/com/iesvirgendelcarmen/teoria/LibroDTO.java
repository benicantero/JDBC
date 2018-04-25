package com.iesvirgendelcarmen.teoria;

// los patrones DAO son clases sencillas con los atributos de las tablas.

public class LibroDTO {

	private String nombreLibro;
	private String nombreAutor;
	private String nombreCategoria;
	private String editorial;
	
	public LibroDTO(String nombreLibro, String nombreAutor, String nombreCategoria, String editorial) {
		this.nombreLibro = nombreLibro;
		this.nombreAutor = nombreAutor;
		this.nombreCategoria = nombreCategoria;
		this.editorial = editorial;
	}
	public String getNombreLibro() {
		return nombreLibro;
	}
	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}
	public String getNombreAutor() {
		return nombreAutor;
	}
	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	@Override
	public String toString() {
		return "LibroDTO [nombreLibro=" + nombreLibro + ", nombreAutor=" + nombreAutor + ", nombreCategoria="
				+ nombreCategoria + ", editorial=" + editorial + "]";
	}
	
	
}
