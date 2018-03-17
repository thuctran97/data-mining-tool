package com.preprocess.activities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.preprocess.model.Data;

public class SummaryActivity {
	public static void WriteOutput(Data data, String path) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(path));
		writer.write(data.getRows()+"\n");
		writer.write(data.getColumns()+"\n");
		for (int i=0; i<data.getColumns(); i++) {
			if (data.getDataType(i)==1) writer.write(data.getAttribute(i)+" numeric\n"); 
			else writer.write(data.getAttribute(i)+" norminal\n");
		}
		writer.close();
	}


}
