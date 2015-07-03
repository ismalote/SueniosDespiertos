package clases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LibroTest {

	private Libro libro;
	
	@Before
	public void setUp(){
		libro = new Libro("g","t","a",1);
	}
	
	@Test
	public void test_getGenero() {
		assertEquals("g", libro.getGenero());
	}

	@Test
	public void test_getTitulo() {
		assertEquals("t", libro.getTitulo());
	}
	
	@Test
	public void test_getAutor() {
		assertEquals("a", libro.getAutor());
	}
	
	@Test
	public void test_getPrecio() {
		assertEquals(1, libro.getPrecio(),2);
	}
	
	@Test
	public void test_setGenero() {
		String genero = "g2";
		libro.setGenero(genero);
		assertEquals(genero, libro.getGenero());
	}

	@Test
	public void test_setTitulo() {
		String titulo = "t2";
		libro.setTitulo(titulo);
		assertEquals(titulo, libro.getTitulo());
	}
	
	@Test
	public void test_setAutor() {
		String autor = "a2";
		libro.setAutor(autor);
		assertEquals(autor, libro.getAutor());
	}
	
	@Test
	public void test_setPrecio() {
		double precio = 2;
		libro.setPrecio(precio);
		assertEquals(precio, libro.getPrecio(),2);
	}
	
	@Test
	public void test_equals_null_devuelve_false(){
		boolean areEqual = libro.equals(null);
		assertFalse(areEqual);
	}
	
	@Test
	public void test_equals_mismaInstancia_devuelve_true(){
		boolean areEqual = libro.equals(libro);
		assertTrue(areEqual);
	}
	
	@Test
	public void test_equals_otraClase_devuelve_false(){
		boolean areEqual = libro.equals("asdasd");
		assertFalse(areEqual);
	}
	
	@Test
	public void test_equals_otraInstanciaMismoEstado_devuelve_true(){
		Libro otro = new Libro(libro.getGenero(),libro.getTitulo(),libro.getAutor(),libro.getPrecio());
		boolean areEqual = libro.equals(otro);
		assertTrue(areEqual);
	}
	
	@Test
	public void test_equals_otraInstanciaDistintoGenero_devuelve_false(){
		Libro otro = new Libro("g2",libro.getTitulo(),libro.getAutor(),libro.getPrecio());
		boolean areEqual = libro.equals(otro);
		assertFalse(areEqual);
	}
	
	@Test
	public void test_equals_otraInstanciaDistintoTitulo_devuelve_false(){
		Libro otro = new Libro(libro.getGenero(),"t2",libro.getAutor(),libro.getPrecio());
		boolean areEqual = libro.equals(otro);
		assertFalse(areEqual);
	}
	
	@Test
	public void test_equals_otraInstanciaDistintoAutor_devuelve_false(){
		Libro otro = new Libro(libro.getGenero(),libro.getTitulo(),"a2",libro.getPrecio());
		boolean areEqual = libro.equals(otro);
		assertFalse(areEqual);
	}
	
	@Test
	public void test_equals_otraInstanciaDistintoPrecio_devuelve_false(){
		Libro otro = new Libro(libro.getGenero(),libro.getTitulo(),libro.getAutor(),10);
		boolean areEqual = libro.equals(otro);
		assertFalse(areEqual);
	}
}
