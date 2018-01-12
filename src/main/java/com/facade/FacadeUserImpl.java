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
		DaoFactory daoFactory = DaoFactory.getDao(DaoType.JPA);
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
		DaoFactory daoFactory = DaoFactory.getDao(DaoType.JPA);
		return daoFactory.getDaoUtente().inserisciUtente(FacadeUtils.trasformDtoIntoUser(userDto)) > 0;
	}

}
