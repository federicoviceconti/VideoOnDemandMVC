package com.common;

public class Field {
	public static final String KEY_LIST = "key_list";
	public static final String KEY_ERROR = "key_error";
	public static final String TYPE = "type";
	public static final String DELETE = "d";
	public static final String MODIFY = "m";
	public static final String ORDER = "o";
	public static final String DTO_MODIFY = "dto_mod";
	public static final String EMPTY = "";
	public static final String PARAM_GENERE = "genere";
	public static final 
		String PATH = "C:\\Users\\user\\Desktop\\Desktop\\Java EE\\apache-tomcat-8.0.47\\webapps\\OnDemandImage";
    public static final String ALL_GENERI = "all_generi";
    public static final String JPA_CONN = "VideoOnDemand_JPA";

    public static class DB {
		public static final String 
				ID = "id",
				USERNAME = "username",
				PASSWORD = "password",
				NOME = "nome",
				TITOLO = "titolo",
				GENERE = "genere",
				ID_GENERE = "id_genere",
				ANNO = "anno", 
				REGISTA = "regista", 
				CAST = "cast", 
				DURATA = "durata", 
				DESCRIZIONE = "descrizione",
				FILENAME = "filename";
		
		public static class Table {
			public static final String TABLE_FILM = "film",
				TABLE_GENERE = "genere";
		}
		
		public static class Costant {
			public static final int USER_ROLE_TYPE = 2, ADMIN_ROLE_TYPE = 1;
			public static final String ASC = "asc", DESC = "desc";
		}
	}
}
