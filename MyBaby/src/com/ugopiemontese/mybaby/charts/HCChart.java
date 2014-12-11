package com.ugopiemontese.mybaby.charts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.YLabels;
import com.github.mikephil.charting.utils.XLabels.XLabelPosition;
import com.github.mikephil.charting.utils.YLabels.YLabelPosition;

public class HCChart extends LineChart {
	
	public HCChart(Context context) {
		super(context);
	}

	public static void setup(LineChart chart, Float value, int week, int color) {
		
		chart.setDescription("");
	    chart.setDrawLegend(false);
	    chart.setStartAtZero(false);
	    chart.setDrawYValues(false);
	    chart.setDoubleTapToZoomEnabled(false);
	    chart.setDrawGridBackground(false);
	    
	    XLabels labelsX = chart.getXLabels();
	    labelsX.setPosition(XLabelPosition.TOP);
	    
	    YLabels labelsY = chart.getYLabels();
	    labelsY.setPosition(YLabelPosition.RIGHT);

	    List min = Arrays.asList(9.7f, 10.7f, 11.8f, 12.9f, 13.9f, 15.1f, 16.2f, 17.3f, 18.4f, 19.5f, 20.5f, 21.6f, 22.6f, 23.6f, 24.5f, 25.4f, 26.2f, 27f, 27.7f, 28.3f, 28.9f, 29.4f, 29.8f, 30f, 30.2f, 30.3f, 30.3f, 30.1f);
	    List med = Arrays.asList(11.2f, 12.3f, 13.5f, 14.6f, 15.8f, 17f, 18.2f, 19.3f, 20.5f, 21.7f, 22.8f, 23.9f, 25f, 26.1f, 27.1f, 28.1f, 29f, 29.8f, 30.6f, 31.3f, 31.9f, 32.5f, 33f, 33.3f, 33.6f, 33.7f, 33.8f, 33.7f);
	    List max = Arrays.asList(12.7f, 13.9f, 15.1f, 16.4f, 17.6f, 18.9f, 20.1f, 21.4f, 22.7f, 23.9f, 25.1f, 26.3f, 27.5f, 28.6f, 29.7f, 30.7f, 31.7f, 32.6f, 33.5f, 34.3f, 35f, 35.6f, 36.2f, 36.6f, 36.9f, 37.2f, 37.3f, 37.3f);
	    
	    ArrayList<Entry> minValues = new ArrayList<Entry>();
	    for (int i = 14; i < 42; i++)
	    	minValues.add(new Entry((float)min.get(i-14), i-14));
	    LineDataSet setMin = new LineDataSet(minValues, "HC_Min");
	    setMin.setDrawCircles(false);
	    setMin.setDrawCubic(true);
	    setMin.setColor(Color.GRAY);
	    
	    ArrayList<Entry> medValues = new ArrayList<Entry>();
	    for (int i = 14; i < 42; i++)
	    	medValues.add(new Entry((float)med.get(i-14), i-14));
	    LineDataSet setMed = new LineDataSet(medValues, "HC_Med");
	    setMed.setDrawCircles(false);
	    setMed.setDrawCubic(true);
	    setMed.setColor(Color.BLACK);
	    
	    ArrayList<Entry> maxValues = new ArrayList<Entry>();
	    for (int i = 14; i < 42; i++)
	    	maxValues.add(new Entry((float)max.get(i-14), i-14));
	    LineDataSet setMax = new LineDataSet(maxValues, "HC_Max");
	    setMax.setDrawCircles(false);
	    setMax.setDrawCubic(true);
	    setMax.setColor(Color.GRAY);
	    
	    ArrayList<Entry> myValues = new ArrayList<Entry>();
	    myValues.add(new Entry((float)value, week));
	    LineDataSet set = new LineDataSet(myValues, "HC_Mine");
	    set.setDrawCircles(true);
	    set.setCircleSize(4f);
	    set.setCircleColor(color);
	    set.setDrawCubic(true);
	    
	    ArrayList<String> xVals = new ArrayList<String>();
	    for (int i = 14; i < 42; i++)
	    	xVals.add(String.valueOf(i));

	    ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
	    dataSets.add(setMin);
	    dataSets.add(setMed);
	    dataSets.add(setMax);
	    dataSets.add(set);

	    LineData data = new LineData(xVals, dataSets);
	    chart.setData(data);
	    		
	}

}
