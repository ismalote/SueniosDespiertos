package clases;

import Implementaciones.Cola;
import TDA.ColaTDA;

public class Genero {

	private String nombre;

	private ColaTDA<Libro> libros;

	public Genero(String nombre) {
		this(nombre,new Cola<Libro>());
	}
	
	Genero(String nombre, ColaTDA<Libro> libros){
		this.nombre = nombre;
		this.libros = libros;
		this.libros.inicializarCola();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ColaTDA<Libro> getLibros() {
		return this.copiarColaLibros();
	}

	public void addLibro(Libro libro) {
		this.libros.acolar(libro);
	}

	public void borrarLibro(Libro libro) {
		ColaTDA<Libro> aux = new Cola<Libro>();
		aux.inicializarCola();

		while (!libros.colaVacia()) {
			if (!libros.primero().equals(libro)) {
				aux.acolar(libros.primero());
			}
			libros.desacolar();
		}

		while (!aux.colaVacia()) {
			libros.acolar(aux.primero());
			aux.desacolar();
		}
	}

	private ColaTDA<Libro> copiarColaLibros() {
		ColaTDA<Libro> colaAux = new Cola<Libro>();
		colaAux.inicializarCola();

		ColaTDA<Libro> colaARetornar = new Cola<Libro>();
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
