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

	/**
	 * Agrega un {@link Libro} al {@link Genero}
	 * 
	 * @param libro
	 */
	public void addLibro(Libro libro) {
		this.libros.acolar(libro);
	}

	/**
	 * Borra un {@link Libro} que pertenezca a �ste {@link Genero}
	 * 
	 * @param libro
	 */
	public void borrarLibro(Libro libro) {
		/**
		 * Creamos una Cola auxiliar
		 */
		ColaTDA<Libro> aux = new Cola<Libro>();
		aux.inicializarCola();

		/**
		 * Hacemos una copia de los Libros de �ste G�nero en la Cola auxiliar, omitiendo los Libros que desamos borrar
		 */
		while (!libros.colaVacia()) {
			if (!libros.primero().equals(libro)) {
				aux.acolar(libros.primero());
			}
			libros.desacolar();
		}

		/**
		 * Volvemos a ubicar a los Libros en la Cola de Libros
		 */
		volverALlenarColaLibros(aux);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve una {@link Cola} que contiene los Libros de este G�nero.
	 * 
	 * @return Una copia de los Libros de �ste {@link Genero}
	 */
	public ColaTDA<Libro> getLibros() {
		/**
		 * Creamos una Cola auxiliar
		 */
		ColaTDA<Libro> colaAux = new Cola<Libro>();
		colaAux.inicializarCola();

		/**
		 * Creamos la instancia de Cola que se retornar�
		 */
		ColaTDA<Libro> colaARetornar = new Cola<Libro>();
		colaARetornar.inicializarCola();

		/**
		 * Copiamos los Libros en la Cola auxiliar y en la de retorno
		 */
		while (!libros.colaVacia()) {
			colaARetornar.acolar(libros.primero());
			colaAux.acolar(libros.primero());
			libros.desacolar();
		}

		/**
		 * Volvemos a ubicar a los Libros en la Cola de Libros
		 */
		volverALlenarColaLibros(colaAux);
		
		return colaARetornar;
	}
	
	private void volverALlenarColaLibros(ColaTDA<Libro> colaAux){
		while (!colaAux.colaVacia()){
			libros.acolar(colaAux.primero());
			colaAux.desacolar();
		}
	}
	
}
