package utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Util {

	static File[] files;
	static boolean flag, flg;
	static NodeList nodes;
	static ArrayList<String> keys = new ArrayList<String>();
	static ArrayList<String> values = new ArrayList<String>();

	public static Boolean checkFileFormat(final String format) {
		File f = new File(".\\testData\\Data");
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File f, String name) {
				return name.endsWith(format);
			}
		};
		files = f.listFiles(filter);
		if (files != null && files.length > 0) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public static void iterateFileAndGetValues(String key1, String key2, String action) {

		String expression = "*//@" + key1 + "|*//@" + key2;
		if (flag) {
			for (File file : files) {
				try {
					Document doc = getDocument(file);
					XPath xpath = XPathFactory.newInstance().newXPath();
					XPathExpression expr = xpath.compile(expression);
					nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

					if (action.equalsIgnoreCase("print")) {
						printAttributeValues();
					} else if (action.equalsIgnoreCase("insert")) {
						insertSpecialCharValuesToExcel(file);						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			checkFileFormat("xml");
			iterateFileAndGetValues(key1, key2, action);
		}	
		if(!keys.isEmpty()|| !values.isEmpty()) {
			WriteExcelFile.writeValuesToExcel(keys,values);
		}
	}

	public static void printAttributeValues() {
		for (int i = 0; i < nodes.getLength(); i++)
			System.out.println(nodes.item(i).getNodeName() + " : " + nodes.item(i).getNodeValue());
	}

	public static void insertSpecialCharValuesToExcel(File file) {		
		for (int i = 0; i < nodes.getLength(); i++) {
			if (checkSpecialCharacters(nodes.item(i).getNodeValue())) {
				keys.add(file.getName());
				values.add(nodes.item(i).getNodeName() + "=" + nodes.item(i).getNodeValue());
			}
		}		
	}

	private static Document getDocument(File file) throws Exception {
		Document doc = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		db = factory.newDocumentBuilder();
		doc = db.parse(file);
		doc.getDocumentElement().normalize();
		return doc;
	}
	
	public static boolean checkSpecialCharacters(String str) {
		String specialCharacters=" !#$%&'()*+,-./:;<=>?@[]^`{|}~";
	    String str1[]=str.split("");
	    for (int i=0;i<str1.length;i++)
	    {
	    if (specialCharacters.contains(str1[i]))
	    {
	    	flg = true;
	        break;
	    }
	    else
	    	flg = false;
	    }
		return flg;
	}
}
