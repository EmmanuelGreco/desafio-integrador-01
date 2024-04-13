package com.educacionit.dao.model;

import java.util.List;

public class Pelicula {

	private int codigo;
	private String titulo;
	private String director;
	private String url;
	private byte[] imagen;
	private List<Genero> generos;	

	public Pelicula() {
		super();
	}

	public Pelicula(String titulo, String director, String url, byte[] imagen, List<Genero> generos) {
		super();
		this.titulo = titulo;
		this.director = director;
		this.url = url;
		this.imagen = imagen;
		this.generos = generos;
	}

	public Pelicula(int codigo, String titulo, String director, String url, byte[] imagen, List<Genero> generos) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.director = director;
		this.url = url;
		this.imagen = imagen;
		this.generos = generos;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}
}
