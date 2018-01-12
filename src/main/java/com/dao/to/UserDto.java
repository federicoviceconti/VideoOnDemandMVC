package com.dao.to;

import java.net.PasswordAuthentication;

import com.common.Field;
import com.videoondemand.model.User;
import com.videoondemand.model.User.UserBuilder;

public class UserDto {
	private int id;
	private String username;
	private char[] password;
	private int ruolo;
	
	public static class UserBuilder {
		private int _id;
		private String _username;
		private char[] _password;
		private int _ruolo;
		public void set_id(int _id) {
			this._id = _id;
		}
		public UserBuilder setUsername(String _username) {
			this._username = _username;
			return this;
		}
		public UserBuilder setPassword(char[] _password) {
			this._password = _password;
			return this;
		}
		public UserBuilder setRuolo(int _ruolo) {
			this._ruolo = _ruolo;
			return this;
		}
		
		public UserDto build() {
			return new UserDto(this);
		}
	}
		
	private UserDto(UserBuilder userBuilder) {
		if(!userBuilder._username.isEmpty()) {
			this.username = userBuilder._username;
		} else {
			throw new UnsupportedOperationException("User not defined correctly!");
		}
		
		if(userBuilder._password != null) {
			this.password = userBuilder._password;
		}
		
		if(userBuilder._ruolo <= 0) {
			userBuilder._ruolo = Field.DB.Costant.USER_ROLE_TYPE;
		}
		
		this.ruolo = userBuilder._ruolo;
		this.id = userBuilder._id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public char[] getPassword() {
		return this.password;
	}

	public int getRuolo() {
		return ruolo;
	}

	public void setRuolo(int ruolo) {
		this.ruolo = ruolo;
	}
	
}
