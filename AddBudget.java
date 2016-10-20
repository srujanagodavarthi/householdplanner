package com.budget.budgetplanner;

import java.util.ArrayList;
import java.util.List;

import com.budget.budgetplanner.R;
import com.budget.budgetplanner.CustomOnItemSelectedListener;
import com.budget.budgetplanner.AddBudget;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class AddBudget extends Activity
{
	
	LoginDataBaseAdapter loginDataBaseAdapter;
	private Spinner spinner1, spinner2,spinner3;
	private EditText edittext,edittext1;
	private Button btnSubmit,button1;
	private TextView textview;
	private ToggleButton toggle1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 	
	    setContentView(R.layout.budget);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title1);
	    TextView tv = (TextView) findViewById(R.id.textView9);	
	    tv.setText("Budget Planner");
	    Button help = (Button) findViewById(R.id.Help);
        help.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				HelpDialog helpDialog = new HelpDialog(AddBudget.this);
				helpDialog.setTitle("Help ");
				helpDialog.show();           		
			}        
        });
		
		
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
	 	edittext = (EditText) findViewById(R.id.editText2);
		textview = (TextView) findViewById(R.id.textView5);
		edittext1=(EditText) findViewById(R.id.editText1);
		toggle1=(ToggleButton) findViewById(R.id.toggleButton1);
	//	edittext2=(EditText) findViewById(R.id.editText2);
		addItemsOnSpinner2();
		addItemsOnSpinner3();
		addListenerOnButton();
		
		
		List<String> lables = new ArrayList<String>();
		lables = getAllLabels();
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		  ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
	                android.R.layout.simple_spinner_item, lables);
		  dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  spinner1.setAdapter(dataAdapter);
		  spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener()
		   {
		        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        	if(parentView.getItemAtPosition(position).toString().equals("Addnew"))
		    		{
		        		String username=getIntent().getExtras().getString("username");
		        		//Toast.makeText(getApplicationContext(), "username"+username, Toast.LENGTH_LONG).show();
		        		
		                Intent intent = new Intent(AddBudget.this, Addcategory.class);
		                intent.putExtra("username2",username);
		                AddBudget.this.startActivity(intent);
		            }
		        	else   
		        	{
		        		    // Toast.makeText(parentView.getContext(), 
		        				//"OnItemSelectedListener : " + parentView.getItemAtPosition(position).toString(),
		        				//Toast.LENGTH_SHORT).show();
		        	}
		        }

		        public void onNothingSelected(AdapterView<?> parentView) {
		        	Toast.makeText(parentView.getContext(), 
	        				"Select one category",
	        				Toast.LENGTH_SHORT).show();
		            // To do ...
		        }

		    });
		  /*****************************************************WARINING TOGGLE ****************************************/

		textview.setVisibility(View.INVISIBLE);
	    edittext.setVisibility(View.INVISIBLE);
	   
        toggle1.setOnClickListener(new OnClickListener() {
              public void onClick(View v) {
               // TODO Auto-generated method stub
              if(toggle1.isChecked())
              {
                textview.setVisibility(View.VISIBLE);
      			edittext.setVisibility(View.VISIBLE);
               }
              
              else
              {
            	 
                textview.setVisibility(View.INVISIBLE);
      			edittext.setVisibility(View.INVISIBLE);
               }
           
                 }
             });


	}

	//add items into spinner dynamically
	public void addItemsOnSpinner2() {

		spinner2 = (Spinner) findViewById(R.id.spinner2);
		List<String> list = new ArrayList<String>();
		list.add("Jan");
		list.add("Feb");
		list.add("Mar");
		list.add("Apr");
		list.add("May");
		list.add("Jun");
		list.add("Jul");
		list.add("Aug");
		list.add("Sep");
		list.add("Oct");
		list.add("Nov");
		list.add("Dec");
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(dataAdapter);
	}

	
	public void addItemsOnSpinner3() {

		spinner3 = (Spinner) findViewById(R.id.spinner3);
		List<String> list = new ArrayList<String>();
		list.add("2013");
		list.add("2014");
		list.add("2015");
		list.add("2016");
		list.add("2017");
		list.add("2018");
		list.add("2019");
		list.add("2020");
		
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner3.setAdapter(dataAdapter);
	}

    
	
	public List<String> getAllLabels() {
		 
        List<String> lables = new ArrayList<String>();
        try {
        	//String username1="s";
        	String username1=getIntent().getExtras().getString("username1");
        	String username=getIntent().getExtras().getString("username");
        	
        	//String TABLE=LoginDataBaseAdapter.Category_CREATE;
        	String query="SELECT category FROM ADD_CATEGORY where USERNAME='"+username+"' or USERNAME='default'";
        	String query1="SELECT category FROM ADD_CATEGORY where USERNAME='"+username1+"'";
        //	String query1="select category from ADD_SUBCAT where USERNAME='"+username1+"'";
        //	String query2="select category from ADD_SUBCAT where USERNAME='"+username+"'";
        	
        	//Toast.makeText(getApplicationContext(), "username"+username, Toast.LENGTH_LONG).show();
      
        	
        	           //  "select category from ADD_SUBCAT where username='"+username1+"' and category not in (select category from usercat)";
       
            Cursor allrows =loginDataBaseAdapter.db.rawQuery(query, null);
            
           Cursor cur =loginDataBaseAdapter.db.rawQuery(query1, null);
         //   Cursor cur1 =loginDataBaseAdapter.db.rawQuery(query2, null);
       
            
            System.out.println("COUNT : " + allrows.getCount());
 
            if (allrows.moveToFirst()) {
                do {
 
                    String cat = allrows.getString(0);
                    
                    lables.add(cat);
 
                } while (allrows.moveToNext());
            }
            allrows.close();
            
           if (cur.moveToFirst()) {
                do {
 
                    String cat1 = cur.getString(0);
                    
                    lables.add(cat1);
 
                } while (cur.moveToNext());
            }
            
            
         
           cur.close();
        
        } catch (Exception e) {
        	Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }
        return lables;
    }

	
	//get the selected dropdown list value
	public void addListenerOnButton() {

		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		button1 = (Button) findViewById(R.id.button1);
		

		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			
				String username1=getIntent().getExtras().getString("username");
				int warning;
				edittext1.getText().toString();
				
				if(edittext1.getText().toString().equals(""))
				{
					edittext1.setError("Enter the value");
					//Toast.makeText(AddBudget.this, "Enter all values", Toast.LENGTH_LONG).show();
					return;
					
				}
				
				 if(!toggle1.isChecked())
	              {
					 
					 String category=spinner1.getSelectedItem().toString();
		    			float budget=Float.valueOf(edittext1.getText().toString());
		    			String month=spinner2.getSelectedItem().toString();
		    			int year=Integer.valueOf(spinner3.getSelectedItem().toString());
		    			warning=0;
		    			//Toast.makeText(AddBudget.this, "Ealues", Toast.LENGTH_LONG).show();
			            // Save the Data in Database
					   loginDataBaseAdapter.insertbudget(username1,category,budget,month,year,warning);
				//	   loginDataBaseAdapter.db.close();
					   Intent intent = new Intent(getApplicationContext(),Success.class); 
					   intent.putExtra("username",username1);
					    startActivity(intent); 
	                
	               }
				
				else
				{
    			String category=spinner1.getSelectedItem().toString();
    			float budget=Float.valueOf(edittext1.getText().toString());
    			String month=spinner2.getSelectedItem().toString();
    			int year=Integer.valueOf(spinner3.getSelectedItem().toString());
    			warning=Integer.valueOf(edittext.getText().toString());
    		//	Toast.makeText(AddBudget.this, " values", Toast.LENGTH_LONG).show();
	            // Save the Data in Database
			   loginDataBaseAdapter.insertbudget(username1,category,budget,month,year,warning);
		//	   loginDataBaseAdapter.db.close();
			   Intent intent = new Intent(getApplicationContext(),Success.class); 
			   intent.putExtra("username",username1);
			    startActivity(intent);
				
			
			}
			}
		});
		
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String username1=getIntent().getExtras().getString("username");
			   Intent intent = new Intent(getApplicationContext(),Addcategory.class); 
			   intent.putExtra("username",username1);
			    startActivity(intent);
				
				
			
			}
			

		});

	}

}