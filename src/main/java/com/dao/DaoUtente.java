package com.dao;

import com.videoondemand.model.User;

public interface DaoUtente {
	User login(String username, char[] password);
	int inserisciUtente(User user);
}
