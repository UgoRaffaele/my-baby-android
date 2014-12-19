package com.ugopiemontese.mybaby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class InsertActivity extends Activity {
	
	private EditText nome;
	private Spinner esame;
	private Spinner settimana;
	private EditText risultato;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		nome = (EditText) findViewById(R.id.nome);
		esame = (Spinner) findViewById(R.id.esame);
		settimana = (Spinner) findViewById(R.id.settimana);
		risultato = (EditText) findViewById(R.id.risultato);
		
		ImageButton send = (ImageButton) findViewById(R.id.send);
		
		send.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				if ((nome.getText().toString().length() > 0) 
						&& (risultato.getText().toString().length() > 0)) {
				
					Intent result = new Intent();  
					result.putExtra("ESAME", esame.getSelectedItem().toString());
					result.putExtra("SETTIMANA", settimana.getSelectedItem().toString() + "w00d");
	                result.putExtra("RISULTATO", risultato.getText().toString()); 
	                setResult(RESULT_OK, result);  
	                finish();
					
				} else {
					Toast.makeText(getApplicationContext(), "Compila tutti i campi!", Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
	}

}
