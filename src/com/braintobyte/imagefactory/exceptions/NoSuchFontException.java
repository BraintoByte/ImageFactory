package com.braintobyte.imagefactory.exceptions;

public class NoSuchFontException extends Exception {
	
	public NoSuchFontException(String themeComponent) {
		super("No such font in theme component: " + themeComponent);
	}
}