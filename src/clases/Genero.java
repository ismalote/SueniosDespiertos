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
		return this.CopiarColaLibros();
	}
	
	public void addLibro(Libro libro) {
		this.libros.Acolar(libro);
	}
	
	public void borrarLibro(Libro libro){
		ColaTDA aux = new Cola();
		aux.InicializarCola();	
		
		while(!libros.ColaVacia()){
			if(!libros.Primero().equals(libro)){
				aux.Acolar(libros.Primero());
			}
			libros.Desacolar();
		}
		
		while(!aux.ColaVacia()){
			libros.Acolar(aux.Primero());
			aux.Desacolar();
		}
	}
	
	private ColaTDA CopiarColaLibros() {
		ColaTDA colaAux = new Cola();
		colaAux.InicializarCola();
		
		ColaTDA colaARetornar = new Cola();
		colaARetornar.InicializarCola();
		
		while (!libros.ColaVacia()) {
			colaARetornar.Acolar(libros.Primero());
			colaAux.Acolar(libros.Primero());
			libros.Desacolar();
		}
		
		libros = colaAux;
		
		return colaARetornar;
	}
}
