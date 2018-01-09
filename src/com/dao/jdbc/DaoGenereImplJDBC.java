package com.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.common.Field;
import com.dao.DaoGenere;
import com.videoondemand.model.Genere;

public class DaoGenereImplJDBC implements DaoGenere{
	private static DaoGenere daoGenere;
	
	private static final String COUNT_NAME_FIELD = "size";
	private static final String INSERT_INTO_GENERE = "INSERT INTO genere(nome, descrizione) VALUES(?,?)";
	private static final String SELECT_ALL_GENERE = "SELECT id, nome, descrizione FROM genere";
	private static final String SELECT_ID_GENERE = "SELECT id, nome, descrizione FROM genere WHERE id=?";
	private static final String COUNT_FILM = "SELECT COUNT(id) as " + COUNT_NAME_FIELD + " FROM genere";
	
	public static synchronized DaoGenere getInstance() {
		return daoGenere == null ? daoGenere = new DaoGenereImplJDBC() : daoGenere;
	}
	
	private DaoGenereImplJDBC() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Genere> getAllGeneri() {		
		try(Connection connection = DaoFactoryJDBC.getConnection(); 
				PreparedStatement statement = connection.prepareStatement(SELECT_ALL_GENERE)) {
			ResultSet resultSet = statement.executeQuery();
			List<Genere> listGenere = new ArrayList<>(); 
			
			while(resultSet.next()) {
				listGenere.add(new Genere(
						resultSet.getInt(Field.DB.ID),
						resultSet.getString(Field.DB.NOME),
						resultSet.getString(Field.DB.DESCRIZIONE)
						)
				);
			}
			
			return listGenere;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Genere getGenereById(int id) {
		try(Connection connection = DaoFactoryJDBC.getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID_GENERE)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				int idGenere = resultSet.getInt(Field.DB.ID);
				String nome = resultSet.getString(Field.DB.NOME);
				String descrizione = resultSet.getString(Field.DB.DESCRIZIONE);
				
				if(idGenere > 0 && !nome.isEmpty() && !descrizione.isEmpty()) {
					return new Genere(id, nome, descrizione);	
				}
			}						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addGenere(Genere genere) {
		try (Connection connection = DaoFactoryJDBC.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_INTO_GENERE)) {
			statement.setString(1, genere.getNome());
			statement.setString(2, genere.getDescrizione());			
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean modifyGenere() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean deleteGenere() {
		throw new UnsupportedOperationException();
	}
}
