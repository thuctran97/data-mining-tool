package com.preprocess.datautil;

import com.preprocess.model.*;
import java.io.*;


public class DataWriter {
	public static void write(String path, Data data) throws FileNotFoundException, IOException{
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(path)) ){
			
			for (int index = 0; index < data.getColumns() - 1; index++) {
				writer.write((data.getAttributes().get(index)));
				writer.write(",");
			}
			writer.write(data.getAttributes().get(data.getColumns() - 1));
			writer.newLine();
			for (int i = 0; i < data.getRows(); i++) {
				for (int j = 0; j < data.getColumns() - 1; j++) {
					writer.write(data.getValue(i, j));
					writer.write(",");
				}
				writer.append(data.getValue(i, data.getColumns() - 1));
				writer.newLine();				
			}
			System.out.println("Write data to " + path);			
		}
	}
}
