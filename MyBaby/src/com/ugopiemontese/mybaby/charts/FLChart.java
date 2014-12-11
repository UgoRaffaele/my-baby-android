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

public class FLChart extends LineChart {
	
	public FLChart(Context context) {
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

	    List min = Arrays.asList(1.16f, 1.46f, 1.75f, 2.03f, 2.31f, 2.58f, 2.85f, 3.11f, 3.37f, 3.62f, 3.86f, 4.1f, 4.32f, 4.54f, 4.76f, 4.96f, 5.16f, 5.35f, 5.53f, 5.7f, 5.87f, 6.02f, 6.17f, 6.3f, 6.43f, 6.55f, 6.65f, 6.75f);
	    List med = Arrays.asList(1.55f, 1.87f, 2.17f, 2.47f, 2.77f, 3.06f, 3.34f, 3.62f, 3.89f, 4.15f, 4.41f, 4.66f, 4.91f, 5.14f, 5.37f, 5.59f, 5.81f, 6.01f, 6.21f, 6.4f, 6.58f, 6.75f, 6.91f, 7.06f, 7.2f, 7.34f, 7.46f, 7.57f);
	    List max = Arrays.asList(1.95f, 2.28f, 2.6f, 2.91f, 3.22f, 3.53f, 3.83f, 4.12f, 4.41f, 4.69f, 4.96f, 5.23f, 5.49f, 5.74f, 5.99f, 6.23f, 6.45f, 6.68f, 6.89f, 7.09f, 7.29f, 7.47f, 7.65f, 7.82f, 7.98f, 8.12f, 8.26f, 8.39f);
	    
	    ArrayList<Entry> minValues = new ArrayList<Entry>();
	    for (int i = 14; i < 42; i++)
	    	minValues.add(new Entry((float)min.get(i-14), i-14));
	    LineDataSet setMin = new LineDataSet(minValues, "FL_Min");
	    setMin.setDrawCircles(false);
	    setMin.setDrawCubic(true);
	    setMin.setColor(Color.GRAY);
	    
	    ArrayList<Entry> medValues = new ArrayList<Entry>();
	    for (int i = 14; i < 42; i++)
	    	medValues.add(new Entry((float)med.get(i-14), i-14));
	    LineDataSet setMed = new LineDataSet(medValues, "FL_Med");
	    setMed.setDrawCircles(false);
	    setMed.setDrawCubic(true);
	    setMed.setColor(Color.BLACK);
	    
	    ArrayList<Entry> maxValues = new ArrayList<Entry>();
	    for (int i = 14; i < 42; i++)
	    	maxValues.add(new Entry((float)max.get(i-14), i-14));
	    LineDataSet setMax = new LineDataSet(maxValues, "FL_Max");
	    setMax.setDrawCircles(false);
	    setMax.setDrawCubic(true);
	    setMax.setColor(Color.GRAY);
	    
	    ArrayList<Entry> myValues = new ArrayList<Entry>();
	    myValues.add(new Entry((float)value, week));
	    LineDataSet set = new LineDataSet(myValues, "FL_Mine");
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
