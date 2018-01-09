package com.facade;

public abstract class FacadeFactory {
	public enum FacadeType {
		FILM, USER
	}

	public static Facade getFacade(FacadeType type) {
		switch (type) {
		case FILM:
			return new FacadeFilmImpl();
		case USER:
			return new FacadeUserImpl();
		default:
			throw new UnsupportedOperationException();
		}
	}
}
