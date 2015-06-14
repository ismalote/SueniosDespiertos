package clases;

import implementaciones.Cola;
import tdas.ColaTDA;

public class Genero {
	
	private String nombre;
	private ColaTDA libros;
	
	public Genero(String nombre){
		this.nombre=nombre;
		libros = new Cola();
		libros.InicializarCola();
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
	
	public void addLibro(Libro libro) {
		this.libros.Acolar(libro);
	}
	
	public void borrarLibro(Libro libro){
		ColaTDA aux = new Cola();
		aux.InicializarCola();
		
		while(!libros.ColaVacia()){
			if(libros.Primero().equals(libro)){
				libros.Desacolar();
			}else{
				aux.Acolar(libros.Primero());
				libros.Desacolar();
			}
		}
		while(!aux.ColaVacia()){
			libros.Acolar(aux.Primero());
			aux.Desacolar();
		}
	}
}
