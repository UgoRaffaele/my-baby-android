package com.ugopiemontese.mybaby;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.mikephil.charting.charts.LineChart;
import com.ugopiemontese.mybaby.charts.ACChart;
import com.ugopiemontese.mybaby.charts.BPDChart;
import com.ugopiemontese.mybaby.charts.FLChart;
import com.ugopiemontese.mybaby.charts.HCChart;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static final String PHP_API_URL = "http://my.domain.ext/my-baby/json.php";
	
	AsyncTask<Void, Void, Boolean> TaskAsincrono = null;
	
	protected static final int SELECT_PICTURE = 1234;
	private static Uri capturedImageUri = null;
	private String imageName;
	private String encodedImage;
	
	private static final String TAG_VALUES = "values";
	
	private ArrayList<ArrayList<String>> analisiArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageButton add = (ImageButton) findViewById(R.id.add);
				
		add.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
		        				
		        Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/jpeg");
                startActivityForResult(intent, SELECT_PICTURE);
				
			}
		});
		
		Spinner spinAnalisi = (Spinner) findViewById(R.id.spinnerData);
		spinAnalisi.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				drawGraph(i);
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {
				// Does nothing!
			}

		});
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == SELECT_PICTURE) {
            try {
            	capturedImageUri = data.getData();
            	imageName = capturedImageUri.getLastPathSegment();
            	InputStream imageStream = getContentResolver().openInputStream(capturedImageUri);
            	Bitmap photo = BitmapFactory.decodeStream(imageStream);            	
                ByteArrayOutputStream baos = new ByteArrayOutputStream();  
                photo.compress(Bitmap.CompressFormat.JPEG, 90, baos);
                byte[] byteArrayImage = baos.toByteArray();
                encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT); 
                TaskAsincrono = new TaskAsincrono().execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	
	private class TaskAsincrono extends AsyncTask<Void, Void, Boolean> {
		
		@Override
		protected void onPreExecute(){
			RelativeLayout data = (RelativeLayout) findViewById(R.id.data);
			data.setVisibility(View.GONE);
			RelativeLayout loading = (RelativeLayout) findViewById(R.id.loading);
			loading.setVisibility(View.VISIBLE);
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			
			HttpClient httpclient = new DefaultHttpClient();
    	    HttpPost httppost = new HttpPost(PHP_API_URL);
    	    String responseBody;

        	try {
        		
    	    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	    	
    	    	if (!imageName.isEmpty())
    	        	nameValuePairs.add(new BasicNameValuePair("b64_image_name", imageName));
    	    	
    	        if (!encodedImage.isEmpty())
    	        	nameValuePairs.add(new BasicNameValuePair("b64_image", encodedImage));

    	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    	        HttpResponse response = httpclient.execute(httppost);
    	        responseBody = EntityUtils.toString(response.getEntity());
    	        
    	    } catch (ClientProtocolException e) {
    	    	responseBody = "ClientProtocolException";
    	    	e.printStackTrace();
    	    } catch (IOException e) {
    	    	responseBody = "IOException";
    	    	e.printStackTrace();
    	    }
        	        				        	
        	if(responseBody.toString().equals("ClientProtocolException") || responseBody.toString().equals("IOException") || responseBody.toString().equals("[]"))
        		return false;
        	else {
        		
        		analisiArray = new ArrayList<ArrayList<String>>();
        		
        		try {
					
					JSONObject jsonObj = new JSONObject(responseBody);
					JSONArray values = jsonObj.getJSONArray(TAG_VALUES);
					
					for (int i = 0; i < values.length(); i++) {
						JSONArray val = values.getJSONArray(i);
						ArrayList<String> a = new ArrayList<String>();
						for (int y = 0; y < val.length(); y++) {
							String c = val.getString(y);
							a.add(c);
						}
						analisiArray.add(a);
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
        		
        		return true;
        	}
		}
		
		@Override
		protected void onPostExecute(final Boolean success) {
			if (isCancelled()) {
				return;
			}

			if(success) {
				
				encodedImage = null;
				
				Toast.makeText(getApplicationContext(), "Richiesta elaborata!", Toast.LENGTH_SHORT).show();

				ArrayList<String> analisiAvailable = new ArrayList<String>();
			    for (int i = 0; i < analisiArray.size(); i++) {
			        analisiAvailable.add((String) analisiArray.get(i).get(0));
			    }
			    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, analisiAvailable);
			    Spinner spinAnalisi = (Spinner) findViewById(R.id.spinnerData);
			    spinAnalisi.setAdapter(adapter);
			    
			    drawGraph(0);
			    
			    RelativeLayout data = (RelativeLayout) findViewById(R.id.data);
				data.setVisibility(View.VISIBLE);
				
			} else {
				Toast.makeText(getApplicationContext(), "Si è verificato un problema, riprova!", Toast.LENGTH_LONG).show();
			}
			
			RelativeLayout loading = (RelativeLayout) findViewById(R.id.loading);
			loading.setVisibility(View.GONE);
		}
		
	}
	
	protected void drawGraph(int i) {
		
		LineChart chart = (LineChart) findViewById(R.id.chart);
	    
	    switch (analisiArray.get(i).get(0)) {
	    case "AC":
	    	ACChart.setup(
		    		chart,
		    		Float.parseFloat(analisiArray.get(i).get(1)),
		    		Integer.parseInt(analisiArray.get(i).get(3).substring(0, 2))-14,
		    		getResources().getColor(R.color.primary_dark_material_dark)
		    );
	    	break;
	    case "BPD":
	    	BPDChart.setup(
		    		chart,
		    		Float.parseFloat(analisiArray.get(i).get(1)),
		    		Integer.parseInt(analisiArray.get(i).get(3).substring(0, 2))-14,
		    		getResources().getColor(R.color.primary_dark_material_dark)
		    );
	    	break;
	    case "FL":
	    	FLChart.setup(
		    		chart,
		    		Float.parseFloat(analisiArray.get(i).get(1)),
		    		Integer.parseInt(analisiArray.get(i).get(3).substring(0, 2))-14,
		    		getResources().getColor(R.color.primary_dark_material_dark)
		    );
	    	break;
	    case "HC":
	    	HCChart.setup(
		    		chart,
		    		Float.parseFloat(analisiArray.get(i).get(1)),
		    		Integer.parseInt(analisiArray.get(i).get(3).substring(0, 2))-14,
		    		getResources().getColor(R.color.primary_dark_material_dark)
		    );
	    	break;
	    default:
	    	chart.setVisibility(View.GONE);
	    }
	    
	    TextView value = (TextView) findViewById(R.id.value);
	    value.setText(analisiArray.get(i).get(1));
	    TextView week = (TextView) findViewById(R.id.week);
	    week.setText(analisiArray.get(i).get(3));
		
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		  
		if(TaskAsincrono != null && TaskAsincrono.getStatus() != AsyncTask.Status.FINISHED) {
			TaskAsincrono.cancel(true);
		}
		  
	}

}
