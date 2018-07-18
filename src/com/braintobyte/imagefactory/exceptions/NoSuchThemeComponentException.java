package com.braintobyte.imagefactory.exceptions;

public class NoSuchThemeComponentException extends Exception {
	
	public NoSuchThemeComponentException(String themeComponent) {
		super("No such theme component: " + themeComponent);
	}
}