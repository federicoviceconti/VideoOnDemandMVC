package com.dao;

import java.util.List;

import com.videoondemand.model.Genere;

public interface DaoGenere {
	List<Genere> getAllGeneri();
	Genere getGenereById(int id);
	boolean addGenere(Genere genere);
	boolean modifyGenere();
	boolean deleteGenere();
}
