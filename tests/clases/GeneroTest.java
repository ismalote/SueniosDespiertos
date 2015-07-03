package clases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Implementaciones.Cola;
import TDA.ColaTDA;

public class GeneroTest {

	private Genero genero;
	private ColaTDA<Libro> esperados;

	@Before
	public void setUp() {
		esperados = new Cola<Libro>();
		genero = new Genero("genero", esperados);
	}

	@Test
	public void test_getNombre(){
		String actual = genero.getNombre();
		
		assertEquals(genero.getNombre(), actual);
	}
	
	@Test
	public void test_getLibros() {
		esperados.acolar(new Libro("g", "t", "a", 1));
		esperados.acolar(new Libro("g2", "t2", "a2", 2));
		esperados.acolar(new Libro("g3", "t3", "a3", 2));

		ColaTDA<Libro> actuales = genero.getLibros();

		int i = 0;
		while (!esperados.colaVacia()) {
			assertEquals(esperados.primero(), actuales.primero());
			esperados.desacolar();
			actuales.desacolar();
			i++;
		}
		assertEquals(3, i);
	}

	@Test
	public void test_addLibro() {
		Libro esperado = new Libro("g", "t", "a", 1);
		genero.addLibro(esperado);

		Libro actual = esperados.primero();

		assertTrue(!esperados.colaVacia());
		assertTrue(esperado.equals(actual));
	}

	@Test
	public void test_borrarLibro_ColaTieneDuplicadaLaInstancia_TieneQueBorrarAmbos() {
		Libro esperado = new Libro("g", "t", "a", 1);

		esperados.acolar(esperado);
		esperados.acolar(esperado);
		esperados.acolar(new Libro("g3", "t3", "a3", 2));

		genero.borrarLibro(esperado);

		boolean encontrado = false;
		int cantLibros = 0;
		while (!esperados.colaVacia()) {
			if (esperado.equals(esperados.primero())) {
				encontrado = true;
			}
			esperados.desacolar();
			cantLibros++;
		}
		
		assertEquals(1,cantLibros);
		assertFalse(encontrado);
	}
	
	@Test
	public void test_borrarLibro_ColaTieneDosConDatosIguales_TieneQueBorrarAmbos() {
		Libro esperado = new Libro("g", "t", "a", 1);

		esperados.acolar(esperado);
		esperados.acolar(new Libro("g", "t", "a", 1));
		esperados.acolar(new Libro("g3", "t3", "a3", 2));

		genero.borrarLibro(esperado);

		boolean encontrado = false;
		int cantLibros = 0;
		while (!esperados.colaVacia()) {
			if (esperado.equals(esperados.primero())) {
				encontrado = true;
			}
			esperados.desacolar();
			cantLibros++;
		}
		
		assertEquals(1,cantLibros);
		assertFalse(encontrado);
	}


}
