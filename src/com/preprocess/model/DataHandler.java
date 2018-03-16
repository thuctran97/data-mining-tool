package com.preprocess.model;

import java.util.*;

public class DataHandler {
	private Data data;
	private Map<String, Set<String>> valueListPerAttr;
	private Map<String, Integer> valueCounter;
	
	public DataHandler(Data data) {
		this.data = data;
		this.valueListPerAttr = new LinkedHashMap<>();
		for (int i = 0; i < data.getColumns(); i++) {
			this.valueListPerAttr.put(data.getAttribute(i), new HashSet<>());
		}		
		this.valueCounter = new HashMap<>();
		this.determineValueListOfAttrAndNumberOfEachValues(data);
	}
	
	private void determineValueListOfAttrAndNumberOfEachValues(Data data){		
		for (int i = 0; i < data.getRows(); i++) {
			for (int j = 0; j < data.getColumns(); j++) {
				String attr = data.getAttribute(j);
				String value = data.getValue(i, j);
				this.valueListPerAttr.get(attr).add(value);
				if (!this.valueCounter.containsKey(value)) {
					this.valueCounter.put(value, 0);
				} else {
					int count = valueCounter.get(value);
					this.valueCounter.put(value, count + 1);
				}
			}
		}
	}
		

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Map<String, Set<String>> getValueListPerAttr() {
		return valueListPerAttr;
	}

	public void setValueListPerAttr(Map<String, Set<String>> valueListPerAttr) {
		this.valueListPerAttr = valueListPerAttr;
	}

	public Map<String, Integer> getValueCounter() {
		return valueCounter;
	}

	public void setValueCounter(Map<String, Integer> valueCounter) {
		this.valueCounter = valueCounter;
	}
	
	
}
