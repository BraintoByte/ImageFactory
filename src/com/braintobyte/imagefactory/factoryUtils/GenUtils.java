package com.braintobyte.imagefactory.factoryUtils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class GenUtils {
	public static Object deepCopyBean(Object obj){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		XMLEncoder encoder = new XMLEncoder(out);
		encoder.writeObject(obj);
		encoder.close();
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		XMLDecoder decoder = new XMLDecoder(in);
		Object result = decoder.readObject();
		decoder.close();
		return result;
	}
}