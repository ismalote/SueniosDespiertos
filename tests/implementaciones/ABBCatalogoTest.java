package implementaciones;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clases.*;

public class ABBCatalogoTest {

	private ABBCatalogo catalogo;
	
	@Before
	public void setUp() throws Exception {
		catalogo = new ABBCatalogo();
		catalogo.inicializar();
	}

	@Test
	public void test_inicializar() {
		assertNull(catalogo.raiz);
	}
	
	@Test
	public void test_agregarPrimerLibro(){
		Libro libro = new Libro("g","t","a",1);
		
		catalogo.agregarLibro(libro);
		
		assertNotNull(catalogo.raiz);
		assertNotNull(catalogo.hijoDer());
		assertNotNull(catalogo.hijoIzq());
		assertEquals(libro.getGenero(), catalogo.obtenerGenero().getNombre());
		assertEquals(libro, catalogo.obtenerGenero().getLibros().primero());
	}
	
	@Test
	public void test_eliminarUltimoGenero_RaizNula(){
		Libro libro = new Libro("g","t","a",1);
		
		catalogo.agregarLibro(libro);
		catalogo.eliminarGenero(catalogo.obtenerGenero());
		
		assertNull(catalogo.raiz);
	}
	
	@Test
	public void test_eliminarUltimoLibroDeUltimoGenero_RaizNula(){
		Libro libro = new Libro("g","t","a",1);
		
		catalogo.agregarLibro(libro);
		catalogo.eliminarLibro(libro);
		
		assertNull(catalogo.raiz);
	}

}
