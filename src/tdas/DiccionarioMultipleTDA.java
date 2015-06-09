package tdas;

public interface DiccionarioMultipleTDA {
	
	void InicializarDiccionario();
	
	void Agregar(Object clave, Object valor);
	
	void Eliminar(Object clave);
	
	void EliminarValor(Object clave, Object valor);
	
	ConjuntoTDA Recuperar(Object clave);
	
	ConjuntoTDA Claves();
	
	boolean DiccionarioVacio();
}
