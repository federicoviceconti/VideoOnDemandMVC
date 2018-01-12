package com.dao.to;

import java.util.HashMap;
import java.util.Map;

public class FilmDto {
	private int id;
	private String nome;
	private Map<Integer, String> genere;
	private int anno, durata;
	private String cast, descrizione, fileName;
	
	public FilmDto(int id, String nome, int id_genere, String genere, int anno, int durata, String cast,
			String descrizione, String fileName) {
		super();
		Map<Integer, String> mapGenere = new HashMap<>();
		mapGenere.put(id_genere, genere);
		
		this.id = id;
		this.nome = nome;
		this.genere = mapGenere;
		this.anno = anno;
		this.durata = durata;
		this.cast = cast;
		this.descrizione = descrizione;
		this.setFileName(fileName);
	}
	public FilmDto(String nome, int idGenere, String genere, int anno, String fileName) {
		this.nome = nome;
		Map<Integer, String> mapGenere = new HashMap<>();
		mapGenere.put(idGenere, genere);
		
		this.nome = nome;
		this.genere = mapGenere;
		this.anno = anno;
		this.setFileName(fileName);
	}
	public FilmDto(String nome, int genere, int anno, String fileName) {
		this(nome, genere, "", anno, fileName);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome == null ? "" : nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Map<Integer, String> getGenere() {
		return genere;
	}
	public void setGenere(Map<Integer, String> genere) {
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
		return cast == null ? "" : cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public String getDescrizione() {
		return descrizione == null ? "" : descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getFileName() {
		return fileName == null ? "" : fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}	
}
