package tdas;

public interface DiccionarioMultipleTDA {
	
	void inicializarDiccionario();
	
	void agregar(Object clave, Object valor);
	
	void eliminar(Object clave);
	
	void eliminarValor(Object clave, Object valor);
	
	ConjuntoTDA recuperar(Object clave);
	
	ConjuntoTDA claves();
	
	boolean diccionarioVacio();
}
