package com.videoondemand.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Validate {
	public static String checkParams(HttpServletRequest req, String param, String def) {
		return req.getParameter(param) != null ? req.getParameter(param) : def;
	}
	
	public static String checkIfIsNullOrEmpty(String toCheck) {
		return toCheck == null || toCheck.isEmpty() ? "" : toCheck; 
	}
}
