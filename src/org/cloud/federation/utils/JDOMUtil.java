package org.cloud.federation.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.DataConversionException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class JDOMUtil {

	/*
	 * getGraphicLISTEN
	 */
	public static String getGraphicLISTEN(String info) {
		org.jdom.Document document;
		Element racine;
		String type = null;
		try {
			org.jdom.input.SAXBuilder saxBuilder = new SAXBuilder();
			document = saxBuilder.build(new StringReader(info));
			racine = document.getRootElement();
			type = racine.getChild("TEMPLATE").getChild("GRAPHICS")
					.getChild("LISTEN").getText();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return type;
	}

	/*
	 * getGraphicPORT
	 */
	public static String getGraphicPORT(String info) {
		org.jdom.Document document;
		Element racine;
		String type = null;
		try {

			org.jdom.input.SAXBuilder saxBuilder = new SAXBuilder();
			document = saxBuilder.build(new StringReader(info));
			racine = document.getRootElement();
			type = racine.getChild("TEMPLATE").getChild("GRAPHICS")
					.getChild("PORT").getText();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return type;
	}

	/*
	 * getGraphicTYPE
	 */
	public static String getGraphicTYPE(String info) {
		org.jdom.Document document;
		Element racine;
		String type = null;
		try {

			org.jdom.input.SAXBuilder saxBuilder = new SAXBuilder();
			document = saxBuilder.build(new StringReader(info));
			racine = document.getRootElement();
			type = racine.getChild("TEMPLATE").getChild("GRAPHICS")
					.getChild("TYPE").getText();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return type;
	}

	/*
	 * getNICIP
	 */
	public static String getNICIP(String info) {
		org.jdom.Document document;
		Element racine;
		String type = null;
		try {
			org.jdom.input.SAXBuilder saxBuilder = new SAXBuilder();
			document = saxBuilder.build(new StringReader(info));
			racine = document.getRootElement();
			type = racine.getChild("TEMPLATE").getChild("NIC").getChild("IP")
					.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return type;
	}
	
	
	/*
	 * getNICIP6_LINK
	 */
	public static String getNICIP6_LINK(String info) {
		org.jdom.Document document;
		Element racine;
		String type = null;
		try {
			org.jdom.input.SAXBuilder saxBuilder = new SAXBuilder();
			document = saxBuilder.build(new StringReader(info));
			racine = document.getRootElement();
			type = racine.getChild("TEMPLATE").getChild("NIC").getChild("IP6_LINK")
					.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return type;
	}
	
	
	/*
	 * getNICIP6_LINK
	 */
	public static String getNICMAC(String info) {
		org.jdom.Document document;
		Element racine;
		String type = null;
		try {
			org.jdom.input.SAXBuilder saxBuilder = new SAXBuilder();
			document = saxBuilder.build(new StringReader(info));
			racine = document.getRootElement();
			type = racine.getChild("TEMPLATE").getChild("NIC").getChild("MAC")
					.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return type;
	}
	
	
	
	/*
	 * getNICIP6_LINK
	 */
	public static String getNICNETWORK(String info) {
		org.jdom.Document document;
		Element racine;
		String type = null;
		try {
			org.jdom.input.SAXBuilder saxBuilder = new SAXBuilder();
			document = saxBuilder.build(new StringReader(info));
			racine = document.getRootElement();
			type = racine.getChild("TEMPLATE").getChild("NIC").getChild("NETWORK")
					.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return type;
	}

	

}