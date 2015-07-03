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
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

}
