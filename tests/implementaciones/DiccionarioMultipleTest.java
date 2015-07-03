package implementaciones;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import TDA.ConjuntoTDA;
import clases.Libro;

public class DiccionarioMultipleTest {

	private DiccionarioMultiple diccionario;
	
	@Before
	public void setUp() throws Exception {
		diccionario = new DiccionarioMultiple();
	}

	@Test
	public void test_inicializar(){
		assertNull(diccionario.priClave);
	}
	
	@Test
	public void test_diccionarioVacio(){
		assertTrue(diccionario.diccionarioVacio());
	}
	
	@Test
	public void test_agregar(){
		Libro esperado = new Libro("g","t","a",1);
		diccionario.agregar("clave", esperado);
		
		ConjuntoTDA<Libro> actuales = diccionario.recuperar("clave");
		Libro actual = actuales.elegir();
		
		assertNotNull(diccionario.priClave);
		assertEquals(esperado,actual); 
	}
	
	@Test
	public void test_eliminarClave() {
		diccionario.agregar("clave", new Libro("g","t","a",1));
		diccionario.eliminar("clave");
		
		assertNull(diccionario.priClave);
	}
	
	@Test
	public void test_eliminarClave_ConMuchas(){
		Libro l1 = new Libro("g","t","a",1);
		diccionario.agregar("clave", l1);
		diccionario.agregar("clave2", l1);
		diccionario.agregar("clave3", l1);
		
		diccionario.eliminar("clave2");
		
		ConjuntoTDA<String> actuales = diccionario.claves();
		
		boolean encontrado = false;
		
		while(!actuales.conjuntoVacio()){
			String sa = actuales.elegir();
			if(sa == "clave2"){
				encontrado = true;
			}
			actuales.sacar(sa);
		}
		
		assertFalse(encontrado);
	}
	
	@Test
	public void test_eliminarValor(){
		Libro l1 = new Libro("g","t","a",1);
		Libro l2 = new Libro("g2","t2","a2",1);
		
		diccionario.agregar("clave", l1);
		diccionario.agregar("clave", l2);
		
		diccionario.eliminarValor("clave", l1);
		
		ConjuntoTDA<Libro> actuales = diccionario.recuperar("clave");
		boolean encontrado = false;
		
		while(!actuales.conjuntoVacio()){
			Libro la = actuales.elegir();
			if (la.equals(l1)){
				encontrado = true;
			}
			actuales.sacar(la);
		}
		
		assertFalse(encontrado);
	}
	
	@Test
	public void test_eliminarUltimoEnClave(){
		Libro l1 = new Libro("g","t","a",1);
		Libro l2 = new Libro("g2","t2","a2",1);
		
		diccionario.agregar("clave", l1);
		diccionario.agregar("clave2", l2);
		
		diccionario.eliminarValor("clave", l1);
		
		ConjuntoTDA<String> actuales = diccionario.claves();
		boolean encontrado = false;
		
		while(!actuales.conjuntoVacio()){
			String sa = actuales.elegir();
			if(sa == "clave"){
				encontrado = true;
			}
			actuales.sacar(sa);
		}
		
		assertFalse(encontrado);
	}

}
