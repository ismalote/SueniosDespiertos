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
	
}
