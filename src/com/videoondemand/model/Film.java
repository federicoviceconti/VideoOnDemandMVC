package com.videoondemand.model;

public class Film {
	private int id;
	private String nome;
	private Genere genere;
	private int id_genere;
	private int anno, durata;
	private String cast, descrizione, fileName;
		
	public Film(int id, String nome, int genere, int anno, int durata, String cast,
			String descrizione, String fileName) {
		super();
		this.id = id;
		this.nome = nome;
		this.id_genere = genere;
		this.anno = anno;
		this.durata = durata;
		this.cast = cast;
		this.descrizione = descrizione;
		this.setFileName(fileName);
	}
	
	public Film(String nome, int id_genere, int anno, String fileName) {
		this.nome = nome;
		this.id_genere = id_genere;
		this.anno = anno;
		this.setFileName(fileName);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome != null ? nome : "";
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdGenere() {
		return id_genere;
	}
	public void setIdGenere(int genere) {
		this.id_genere = genere;
	}
	public Genere getGenere() {
		return genere;
	}
	public void setGenere(Genere genere) {
		this.genere = genere;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	public String getCast() {
		return cast != null ? cast : "";
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public String getDescrizione() {
		return descrizione != null ? descrizione : "";
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}	
}
