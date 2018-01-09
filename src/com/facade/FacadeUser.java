package com.facade;

import com.dao.to.UserDto;

public interface FacadeUser extends Facade {
	UserDto login(String username, String password);
	boolean inserisciUtente(UserDto userDto);
}
