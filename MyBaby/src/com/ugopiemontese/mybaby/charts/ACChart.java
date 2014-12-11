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

public class ACChart extends LineChart {
	
	public ACChart(Context context) {
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

	    List min = Arrays.asList(7.6f, 8.6f, 9.5f, 10.5f, 11.5f, 12.3f, 13.5f, 14.4f, 15.4f, 16.4f, 17.3f, 18.3f, 19.2f, 20.1f, 21f, 21.8f, 22.7f, 23.5f, 24.3f, 25.1f, 25.8f, 26.5f, 27.1f, 27.7f, 28.3f, 28.8f, 29.3f, 29.7f);
	    List med = Arrays.asList(9.2f, 10.3f, 11.4f, 12.5f, 13.6f, 14.8f, 15.9f, 17f, 18.1f, 19.2f, 20.3f, 21.4f, 22.4f, 23.5f, 24.5f, 25.5f, 26.5f, 27.5f, 28.4f, 29.3f, 30.2f, 31f, 31.8f, 32.5f, 33.2f, 33.9f, 34.5f, 35.1f);
	    List max = Arrays.asList(10.8f, 12f, 13.3f, 14.5f, 15.8f, 17f, 18.3f, 19.6f, 20.8f, 22f, 23.3f, 24.5f, 25.7f, 26.9f, 28.1f, 29.2f, 30.3f, 31.4f, 32.5f, 33.5f, 34.5f, 35.5f, 36.4f, 37.3f, 38.2f, 39f, 39.7f, 40.5f);
	    
	    ArrayList<Entry> minValues = new ArrayList<Entry>();
	    for (int i = 14; i < 42; i++)
	    	minValues.add(new Entry((float)min.get(i-14), i-14));
	    LineDataSet setMin = new LineDataSet(minValues, "AC_Min");
	    setMin.setDrawCircles(false);
	    setMin.setDrawCubic(true);
	    setMin.setColor(Color.GRAY);
	    
	    ArrayList<Entry> medValues = new ArrayList<Entry>();
	    for (int i = 14; i < 42; i++)
	    	medValues.add(new Entry((float)med.get(i-14), i-14));
	    LineDataSet setMed = new LineDataSet(medValues, "AC_Med");
	    setMed.setDrawCircles(false);
	    setMed.setDrawCubic(true);
	    setMed.setColor(Color.BLACK);
	    
	    ArrayList<Entry> maxValues = new ArrayList<Entry>();
	    for (int i = 14; i < 42; i++)
	    	maxValues.add(new Entry((float)max.get(i-14), i-14));
	    LineDataSet setMax = new LineDataSet(maxValues, "AC_Max");
	    setMax.setDrawCircles(false);
	    setMax.setDrawCubic(true);
	    setMax.setColor(Color.GRAY);
	    
	    ArrayList<Entry> myValues = new ArrayList<Entry>();
	    myValues.add(new Entry((float)value, week));
	    LineDataSet set = new LineDataSet(myValues, "AC_Mine");
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
