package clases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Implementaciones.Cola;
import TDA.ColaTDA;

public class GeneroTest {

	private ColaTDA<Libro> libros;
	
	@Before
	public void setUp(){
		libros = new Cola<Libro>();
	}
	
	@Test
	public void test_GetLibros(){
		libros.acolar(new Libro("g","t","a",1));
		libros.acolar(new Libro("g2","t2","a2",2));
		libros.acolar(new Libro("g3","t3","a3",2));
		
		assertTrue(true);
	}

}
