package com.preprocess.model;

import java.util.*;

public class Data {
	private int rows;
	private int columns;	
	private List<String> attributes;
	private int[] dataTypes; //Tags for nominal(0) or numerics (1)
	private List<String[]> data;

	public Data(String[] attributes) {
		this.rows = 0;
		this.columns = attributes.length;
		this.attributes = Arrays.asList(attributes);
		this.dataTypes = new int [this.attributes.size()];
		for (int i = 0; i < this.dataTypes.length; i++)
			this.dataTypes[i] = -1;
		this.data = new ArrayList<>();
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public String getAttribute(int column) {
		return attributes.get(column);
	}
	
	public List<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}
	

	public List<String[]> getData() {
		return data;
	}

	public void setData(List<String[]> data) {
		this.data = data;
	}
	
	public int[] getDataTypes() {
		return dataTypes;
	}
	
	public void addData(String[] values) {
		this.data.add(values);
		this.rows++;
	}
	
	public String getValue(int row, int column) {
		return this.data.get(row)[column];
	}
	
	public void setValue(int row, int column, String value) {
		this.data.get(row)[column] = value;
	}
	
	public int getDataType(int columns) {
		return this.dataTypes[columns];
	}
		
	public static class ValueTagger{
		public static boolean isInteger(String value) {
			try {
				Integer.parseInt(value);
				return true;
			} catch(NumberFormatException e) {
				return false;
			}
		}
		
		public static boolean isDouble(String value) {
			try {
				Double.parseDouble(value);
				return true;
			} catch(NumberFormatException e) {
				return false;
			}
		}
	}
	
	public void determineDataTypes() {
		if (this.rows > 0) {
			for (int i = 0; i < this.columns; i++) {
				String value = this.getValue(0, i);
				if (this.dataTypes[i] == -1  && (ValueTagger.isInteger(value) || ValueTagger.isDouble(value))) {
					this.dataTypes[i] = 1;
				}
				else { 
					this.dataTypes[i] = 0;
				}
			}
		}
	}
		

	
	
}
