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
import com.github.mikephil.charting.utils.XLabels.XLabelPosition;
import com.github.mikephil.charting.utils.YLabels;
import com.github.mikephil.charting.utils.YLabels.YLabelPosition;

public class BPDChart extends LineChart {
	
	public BPDChart(Context context) {
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

	    List min = Arrays.asList(2.49f, 2.75f, 3.02f, 3.3f, 3.59f, 3.88f, 4.18f, 4.48f, 4.78f, 5.08f, 5.38f, 5.67f, 5.96f, 6.24f, 6.5f, 6.76f, 7f, 7.23f, 7.44f, 7.63f, 7.81f, 7.95f, 8.08f, 8.18f, 8.25f, 8.3f, 8.31f, 8.29f);
	    List med = Arrays.asList(3.04f, 3.32f, 3.61f, 3.91f, 4.21f, 4.52f, 4.84f, 5.16f, 5.48f, 5.8f, 6.11f, 6.42f, 6.73f, 7.02f, 7.31f, 7.58f, 7.84f, 8.09f, 8.31f, 8.52f, 8.71f, 8.88f, 9.02f, 9.14f, 9.23f, 9.29f, 9.32f, 9.32f);
	    List max = Arrays.asList(3.59f, 3.89f, 4.19f, 4.51f, 4.84f, 5.17f, 5.5f, 5.84f, 6.17f, 6.51f, 6.84f, 7.17f, 7.49f, 7.8f, 8.11f, 8.4f, 8.68f, 8.94f, 9.19f, 9.41f, 9.62f, 9.81f, 9.97f, 10.1f, 10.21f, 10.29f, 10.34f, 10.35f);
	    
	    ArrayList<Entry> minValues = new ArrayList<Entry>();
	    for (int i = 14; i < 42; i++)
	    	minValues.add(new Entry((float)min.get(i-14), i-14));
	    LineDataSet setMin = new LineDataSet(minValues, "PBD_Min");
	    setMin.setDrawCircles(false);
	    setMin.setDrawCubic(true);
	    setMin.setColor(Color.GRAY);
	    
	    ArrayList<Entry> medValues = new ArrayList<Entry>();
	    for (int i = 14; i < 42; i++)
	    	medValues.add(new Entry((float)med.get(i-14), i-14));
	    LineDataSet setMed = new LineDataSet(medValues, "PBD_Med");
	    setMed.setDrawCircles(false);
	    setMed.setDrawCubic(true);
	    setMed.setColor(Color.BLACK);
	    
	    ArrayList<Entry> maxValues = new ArrayList<Entry>();
	    for (int i = 14; i < 42; i++)
	    	maxValues.add(new Entry((float)max.get(i-14), i-14));
	    LineDataSet setMax = new LineDataSet(maxValues, "PBD_Max");
	    setMax.setDrawCircles(false);
	    setMax.setDrawCubic(true);
	    setMax.setColor(Color.GRAY);
	    
	    ArrayList<Entry> myValues = new ArrayList<Entry>();
	    myValues.add(new Entry((float)value, week));
	    LineDataSet set = new LineDataSet(myValues, "PBD_Mine");
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
