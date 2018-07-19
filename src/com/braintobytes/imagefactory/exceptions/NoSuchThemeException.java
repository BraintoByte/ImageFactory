package com.braintobytes.imagefactory.exceptions;

public class NoSuchThemeException extends Exception {
	
	public NoSuchThemeException(String themeName) {
		super("No such theme: " + themeName);
	}
}