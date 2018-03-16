package com.preprocess.model;

import java.util.*;

public class Data {
	private int rows;
	private int columns;	
	private List<String> attributes;
	private List<Integer> tags; //Tags for nominal(0) or numerics (1)
	private List<String[]> data;
	
	public Data(String[] attributes) {
		this.rows = 0;
		this.columns = attributes.length;
		this.attributes = Arrays.asList(attributes);
		this.tags = new ArrayList<>();
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
	
	public void addData(String[] values) {
		this.data.add(values);
		this.rows++;
	}
	
	public String getValue(int row, int column) {
		return this.data.get(row)[column];
	}
	
	public void modifyValue(int row, int column, String value) {
		this.data.get(row)[column] = value;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int index = 0; index < this.attributes.size() - 1; index++) {
			builder.append(this.attributes.get(index)).append(",");
		}
		builder.append(this.attributes.get(this.attributes.size() - 1)).append("\n");
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns - 1; j++) {
				builder.append(this.getValue(i, j)).append(",");
			}
			builder.append(this.getValue(i, this.columns - 1)).append("\n");
		}
		
		return builder.toString();
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
	
	public void determineTags() {
		if (this.rows > 0) {
			for (int i = 0; i < this.columns; i++) {
				String value = this.getValue(0, i);
				if (ValueTagger.isInteger(value) || ValueTagger.isDouble(value)) {
					this.tags.add(1);
				}
				else { 
					this.tags.add(0);
				}
			}
		}
	}
}
