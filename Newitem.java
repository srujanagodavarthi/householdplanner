package com.budget.budgetplanner;

import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Newitem extends Activity
{
	Groceryremainder gr=new Groceryremainder();
	ArrayAdapter<String> adapter1;
	LoginDataBaseAdapter loginDataBaseAdapter;
	// Add button
	Button btnadd;
    String joined;
	// Input text
	EditText edittext1,edittext2;
	Spinner spinner1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 	
		 setContentView(R.layout.newitem);
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title1);
		    TextView tv = (TextView) findViewById(R.id.textView9);	
		    tv.setText("Budget Planner");
		    Button help = (Button) findViewById(R.id.Help);
	        help.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					HelpDialog helpDialog = new HelpDialog(Newitem.this);
					helpDialog.setTitle("Help ");
					helpDialog.show();           		
				}        
	        });
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		// Spinner element
	

		// add button

		btnadd     = (Button) findViewById(R.id.button1);
	    
		// new label input field
		edittext1 = (EditText) findViewById(R.id.editText1);
		edittext2 = (EditText) findViewById(R.id.editText2);
		
		// Loading spinner data from database
		//adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, gr.list);


		/**
		 * Add new label button click listener
		 * */
		
		OnClickListener listener = new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username=getIntent().getExtras().getString("username");
				 StringBuilder r= new StringBuilder();
				 String item=edittext1.getText().toString();
				 String quantity=edittext2.getText().toString();
				 //gr.list.add(""+item+ "     "+quantity); 
	               //Seperator seperator=new Seperator("\n");  
	               r.append(System.getProperty("line.separator"));
	              joined=TextUtils.join(",",gr.list);
              //  Toast.makeText(Newitem.this, "list:"+joined, Toast.LENGTH_LONG).show();
			    
			     Intent intent = new Intent(getApplicationContext(),Groceryremainder.class); 
			     intent.putExtra("username",username);
			     intent.putExtra("item",item);
			     intent.putExtra("quantity",quantity);
			     Toast.makeText(Newitem.this, "list:"+item+quantity, Toast.LENGTH_LONG).show();
				 startActivity(intent); 
              		   
			}

		   };
		            
		btnadd.setOnClickListener(listener);
		//adapter1=gr.adapter;
		//setListAdapter(adapter1);
		

		}

	
}