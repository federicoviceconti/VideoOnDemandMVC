package com.facade;

import com.dao.DaoFactory;
import com.dao.DaoFactory.DaoType;
import com.dao.to.UserDto;
import com.videoondemand.model.User;

public class FacadeUserImpl implements FacadeUser {
	
	/**
	 * Login into our program. Return null if no user is founded
	 */
	@Override
	public UserDto login(String username, String password) {
		DaoFactory daoFactory = DaoFactory.getDao(DaoType.JDBCCONNECTION);
		User user = daoFactory.getDaoUtente().login(username, password.toCharArray());
		return user != null ? new UserDto.UserBuilder()
				.setUsername(user.getUsername())
				.build() : null;		
	}

	/**
	 * Insert user into db, return false is no user is inserted
	 */
	@Override
	public boolean inserisciUtente(UserDto userDto) {
		DaoFactory daoFactory = DaoFactory.getDao(DaoType.JDBCCONNECTION);
		return daoFactory.getDaoUtente().inserisciUtente(new User.UserBuilder()
				.setUsername(userDto.getUsername())
				.setPassword(userDto.getPassword())
				.build()
		) > 0;	
	}

}
