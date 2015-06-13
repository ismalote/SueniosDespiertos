package clases;

public class Libro {
	
	private String genero;
	private String titulo;
	private String autor;
	private double precio;	
	
	public Libro(String gen, String title, String author, double d){
		this.genero=gen;
		this.titulo=title;
		this.autor=author;
		this.precio=d;
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
