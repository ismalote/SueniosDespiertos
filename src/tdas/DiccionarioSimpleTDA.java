package tdas;

public interface DiccionarioSimpleTDA {
	
	void InicializarDiccionario();
	
	void Agregar(Object clave, Object valor);
	
	void Eliminar(Object clave);
	
	Object Recuperar(Object clave);
	
	ConjuntoTDA Claves();
	
	boolean DiccionarioVacio();
}
