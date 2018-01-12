package com.dao;

import com.videoondemand.model.Genere;

import java.util.List;


public interface DaoGenere {
	List<Genere> getAllGeneri();
	Genere getGenereById(int id);
	boolean addGenere(Genere genere);
	boolean modifyGenere();
	boolean deleteGenere();
}
