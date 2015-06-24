package clases;

import implementaciones.Cola;
import tdas.ColaTDA;

public class Genero {
	
	private String nombre;
	private ColaTDA libros;
	
	public Genero(String nombre){
		this.nombre=nombre;
		libros = new Cola();
		libros.inicializarCola();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ColaTDA getLibros() {
		return this.copiarColaLibros();
	}
	
	public void addLibro(Libro libro) {
		this.libros.acolar(libro);
	}
	
	public void borrarLibro(Libro libro){
		ColaTDA aux = new Cola();
		aux.inicializarCola();	
		
		while(!libros.colaVacia()){
			if(!libros.primero().equals(libro)){
				aux.acolar(libros.primero());
			}
			libros.desacolar();
		}
		
		while(!aux.colaVacia()){
			libros.acolar(aux.primero());
			aux.desacolar();
		}
	}
	
	private ColaTDA copiarColaLibros() {
		ColaTDA colaAux = new Cola();
		colaAux.inicializarCola();
		
		ColaTDA colaARetornar = new Cola();
		colaARetornar.inicializarCola();
		
		while (!libros.colaVacia()) {
			colaARetornar.acolar(libros.primero());
			colaAux.acolar(libros.primero());
			libros.desacolar();
		}
		
		libros = colaAux;
		
		return colaARetornar;
	}
}
