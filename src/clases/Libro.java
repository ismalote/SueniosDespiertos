package clases;

public class Libro {

	private String genero;
	private String titulo;
	private String autor;
	private double precio;

	public Libro(String genero, String titulo, String autor, double precio) {
		this.genero = genero;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this){
			return true;
		}
		
		if (obj == null || obj instanceof Libro == false){
			return false;
		}
		
		Libro otro = (Libro)obj;
		return this.getAutor() == otro.getAutor() 
				&& this.getTitulo() == otro.getTitulo()
				&& this.getGenero() == otro.getGenero()
				&& this.getPrecio() == otro.getPrecio();
	}
}
