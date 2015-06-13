package clases;

import tdas.ColaTDA;

public class Genero {
	
	private String nombre;
	private ColaTDA libros;
	
	public Genero(String nombre, ColaTDA libros){
		this.nombre=nombre;
		this.libros=libros;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ColaTDA getLibros() {
		return libros;
	}
	
	public void setLibros(ColaTDA libros) {
		this.libros = libros;
	}
	
}
