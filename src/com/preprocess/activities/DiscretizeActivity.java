package com.preprocess.activities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import com.preprocess.datautil.DataWriter;
import com.preprocess.model.Data;

class Range {
	private Double min,max;
	private int NumberOfInstance;
	public Range(Double min, Double max) {
		this.min=min;
		this.max=max;
		NumberOfInstance=0;
	}
	public Double getMin() {
		return min;
	}
	public Double getMax() {
		return max;
	}
	public int getNumberOfInstance() {
		return NumberOfInstance;
	}
	public void setNumberOfInstance() {
		NumberOfInstance+=1;
	}
}
public class DiscretizeActivity {
	private int binNumber;
	private String way;
	private Data data;
	private int depth;
	private TreeMap<Double, Range> valueMap;
	private List<Range> rangeList;
	private Data dataResults;
	public DiscretizeActivity(Data data, String Numb, String Way) {
		this.binNumber = Integer.parseInt(Numb);
		this.way = Way;
		this.data = data;
		this.dataResults=data;
	}
	public void setDepth(String Depth) {
		this.depth = Integer.parseInt(Depth);
	}
	public List<Double> sortedValueListInColummn(int column) {
		List<Double> valueList = new ArrayList<Double>();
		for (int i=0; i<data.getRows(); i++) 
			valueList.add(Double.parseDouble(data.getValue(i, column)));
		Collections.sort(valueList);
		return valueList;
	}
	public double rounDouble(Double value) {
		return (double)Math.round(value*100000)/100000;
	}
	public void widthBinning(List<Double> valueList) throws IOException {
		valueMap = new TreeMap<Double,Range>();
		rangeList = new ArrayList<Range>();
		Double rangeConst = (valueList.get(data.getRows()-1)-valueList.get(0))/binNumber;
		rangeConst= rounDouble(rangeConst);
		Double minRange = valueList.get(0);
		Double maxRange = valueList.get(0)+rangeConst;
		Range r = new Range(minRange,maxRange);
		rangeList.add(r);
		for (int i=0; i< valueList.size(); i++) {
			Double numb = valueList.get(i);
			while (numb> maxRange) {
				minRange= maxRange; 
				maxRange= rounDouble(maxRange+rangeConst);
				r = new Range(minRange,maxRange);
				rangeList.add(r);
			}
			r.setNumberOfInstance();
			valueMap.put(numb, r);
		}
	}
	private void depthBinning(List<Double> valueList) {
		valueMap = new TreeMap<Double,Range>();
		rangeList = new ArrayList<Range>();
		Double minRange = valueList.get(0);
		Double maxRange = valueList.get(depth-1);
		Range r = new Range(minRange,maxRange);
		rangeList.add(r);
		for (int i=0; i< valueList.size(); i++) {
			Double numb = valueList.get(i);
			if (r.getNumberOfInstance()>=depth) {
				minRange = valueList.get(i);
				if (i+depth-1< valueList.size()) maxRange = valueList.get(i+depth-1); else 
					maxRange = valueList.get(valueList.size()-1);
				r = new Range(minRange, maxRange);
				rangeList.add(r);
			}
			r.setNumberOfInstance();
			valueMap.put(numb, r);
		}

	}
	public void Process(String outputPath, String logPath) throws IOException {
		BufferedWriter logWriter = new BufferedWriter(new FileWriter(logPath));
		for (int i=0; i<data.getColumns(); i++) 
			if (data.getDataType(i)==1) {
				List<Double> valueList = sortedValueListInColummn(i);
				logWriter.write(data.getAttribute(i)+" ");
				
				if (way.equalsIgnoreCase("rong")) widthBinning(valueList); 
					else depthBinning(valueList);		
				for (int j=0; j<rangeList.size(); j++) 
					logWriter.write(rangeList.get(j).getMin()+"-"+rangeList.get(j).getMax()+":"+rangeList.get(j).getNumberOfInstance()+"  ");
				logWriter.newLine();
				for (int j=0; j<valueList.size(); j++) {
					Double value = Double.parseDouble(dataResults.getValue(j, i));
					String minRange= valueMap.get(value).getMin().toString();
					String maxRange= valueMap.get(value).getMax().toString();
					String range = minRange+"-"+maxRange;
					dataResults.setValue(j, i, range);
				}
			}
		DataWriter.write(outputPath, dataResults);
		logWriter.close();
	}
	
	
}
