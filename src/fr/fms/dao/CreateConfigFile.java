package fr.fms.dao;

import java.io.*;
import java.util.Properties;

public class CreateConfigFile {

	public static void main(String[] args)throws IOException {


	}
	/**
	 * 
	 * @param fileName
	 * @return Properties
	 * @throws IOException
	 */
	public Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}
}
