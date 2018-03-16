package com.preprocess.datautil;

import com.preprocess.model.*;
import java.io.*;


public class DataReader {
	
	private static String[] parsingText(String text){
		String[] values = text.split(",");
		return values;
	}
	
	public static Data read(String path) throws FileNotFoundException, IOException {
		try(BufferedReader reader = new BufferedReader(new FileReader(path))){
			String line = reader.readLine();
			String[] attributes = parsingText(line); 
			Data data = new Data(attributes);
			while ((line = reader.readLine()) != null) {
				 String[] values = parsingText(line);
				 data.addData(values);
			}
			data.determineDataTypes();
			return data;
		}
	}
}
