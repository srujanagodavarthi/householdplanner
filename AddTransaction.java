package com.budget.budgetplanner;

import java.util.ArrayList;
import java.util.List;

import com.budget.budgetplanner.R;
import com.budget.budgetplanner.CustomOnItemSelectedListener;
import com.budget.budgetplanner.AddTransaction;

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
import android.widget.TextView;
//import android.widget.EditText;
import android.widget.Spinner;
//import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.widget.DatePicker;

//import android.widget.ToggleButton;

public class AddTransaction extends Activity
{
	LoginDataBaseAdapter loginDataBaseAdapter;
	private Spinner spinner1,spinner2;
	
	private Button save;
	DatePicker datepicker;
	int year;
	int month;
	int day;
	private EditText edittext1;
 
	//private Button cancel;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 	
	    setContentView(R.layout.addtransaction);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title1);
	    TextView tv = (TextView) findViewById(R.id.textView9);	
	    tv.setText("Budget Planner");
	    Button help = (Button) findViewById(R.id.Help);
        help.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				HelpDialog helpDialog = new HelpDialog(AddTransaction.this);
				helpDialog.setTitle("Help ");
				helpDialog.show();           		
			}        
        });
		datepicker=(DatePicker) findViewById(R.id.datePicker1);
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		//addItemsOnSpinner2();
		addListenerOnButton();
		//to show current date
		
		addListenerOnSpinnerItemSelection();
		List<String> lables = new ArrayList<String>();
		//List<String> lables1 = new ArrayList<String>();
		
		lables = getAllLabels();
		//lables1 = getAllSubLabels();
		
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		
		  ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables);
		  //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);	
		  dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);	
		  
		  spinner1.setAdapter(dataAdapter);
	 
		  spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener()
		   {
		        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        	
		        		    // Toast.makeText(parentView.getContext(), 
		        			//	"OnItemSelectedListener : " + parentView.getItemAtPosition(position).toString(),
		        				//Toast.LENGTH_SHORT).show();
		        		     
		        		     List<String> lables1 = new ArrayList<String>();
		        		     lables1 = getAllSubLabels();
		        		     spinner2 = (Spinner) findViewById(R.id.spinner2);
		        		     ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(AddTransaction.this,
		        		                android.R.layout.simple_spinner_item, lables1);
		        		     //dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        		     dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
		        		     spinner2.setAdapter(dataAdapter1);
		        		     loginDataBaseAdapter.close();
		        		     
		       
		        }

		        public void onNothingSelected(AdapterView<?> parentView) {
		        	Toast.makeText(parentView.getContext(), 
	        				"Select one category",
	        				Toast.LENGTH_SHORT).show();
		            // To do ...
		        }

		    });

	}
	
	//function to display current date
	
	
	

	public List<String> getAllLabels() {
	
        List<String> lables = new ArrayList<String>();
        try {
        	//String username1="s";
        	String username=getIntent().getExtras().getString("username");
        	
        	String username1=getIntent().getExtras().getString("username");
        	//Toast.makeText(getApplicationContext(), "user name"+username1, Toast.LENGTH_LONG).show();
        	//String TABLE=LoginDataBaseAdapter.Category_CREATE;
        	String query="SELECT category FROM ADD_CATEGORY where USERNAME='"+username+"' or USERNAME='default'";
        	String query1="SELECT category FROM ADD_CATEGORY where USERNAME='"+username1+"'";
        	
        	           //  "select category from ADD_SUBCAT where username='"+username1+"' and category not in (select category from usercat)";
        	loginDataBaseAdapter.open();
            Cursor allrows =loginDataBaseAdapter.db.rawQuery(query, null);
           // Cursor cur =loginDataBaseAdapter.db.rawQuery(query1, null);
            
            
            System.out.println("COUNT : " + allrows.getCount());
 
            if (allrows.moveToFirst()) {
                do {
 
                    String cat = allrows.getString(0);
                    
                    lables.add(cat);
 
                } while (allrows.moveToNext());
            }
            allrows.close();
            
            loginDataBaseAdapter.close();
        } 
        catch (Exception e) {
        	Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }
        return lables;
    }
	
	
	//********************************subcategory display*************************************************
	public List<String> getAllSubLabels() {
		
        List<String> lables1 = new ArrayList<String>();
        try {
        	//String username1="s";
        	String username1=getIntent().getExtras().getString("username");
        	//Toast.makeText(getApplicationContext(), "user name"+username1, Toast.LENGTH_LONG).show();
        	//String TABLE=LoginDataBaseAdapter.Category_CREATE;
        	
        	String query1="select subcat from ADD_SUBCATPRE where category='"+spinner1.getSelectedItem()+"'";
        	String query2="select subcat from ADD_SUBCAT where category='"+spinner1.getSelectedItem()+"' and USERNAME='"+username1+"'";
        	
        	           //  "select category from ADD_SUBCAT where username='"+username1+"' and category not in (select category from usercat)";
        	loginDataBaseAdapter.open();
            
            Cursor cur =loginDataBaseAdapter.db.rawQuery(query1, null);
            
            
            if (cur.moveToFirst()) {
                do {
 
                    String cat1 = cur.getString(0);
                    
                    lables1.add(cat1);
 
                } while (cur.moveToNext());
            }
            
            
         
           cur.close();
           
           Cursor cur1 =loginDataBaseAdapter.db.rawQuery(query2, null);
           
           
           if (cur1.moveToFirst()) {
               do {

                   String cat2 = cur1.getString(0);
                   
                   lables1.add(cat2);

               } while (cur1.moveToNext());
           }
           
           
        
          cur1.close();
          loginDataBaseAdapter.close();
            
          //  loginDataBaseAdapter.db.close();
        } catch (Exception e) {
        	Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }
        return lables1;
    }
	
	public void addListenerOnSpinnerItemSelection(){
		
		spinner1 = (Spinner) findViewById(R.id.spinner1);
	
		spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		
		spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	}
	
	
	
	
	public Cursor sumAll(){

		 float columntotal = 0;
		 loginDataBaseAdapter.open();
		 Cursor cursor1 = loginDataBaseAdapter.db.rawQuery(
		     "SELECT SUM(amount) FROM ADD_TRANS", null);
		       if(cursor1.moveToFirst()) {
		         columntotal = cursor1.getFloat(0);
		     }
		   cursor1.close();

		 String  sumtotal = Float.toString((float)columntotal);  
		 loginDataBaseAdapter.close();
		// Toast.makeText(getApplicationContext(), "total"+sumtotal, Toast.LENGTH_LONG).show();
		  return cursor1;
		 
		 }
	
	public Cursor catsum(){
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		String category=spinner1.getSelectedItem().toString();
		loginDataBaseAdapter.open();
		 float columntotal = 0;
		 Cursor cursor1 = loginDataBaseAdapter.db.rawQuery(
		     "SELECT SUM(amount) FROM ADD_TRANS where category='"+category+"'", null);
		       if(cursor1.moveToFirst()) {
		         columntotal = cursor1.getFloat(0);
		     }
		   cursor1.close();

		 String  sumtotal = Float.toString((float)columntotal); 
		 loginDataBaseAdapter.close();
		// Toast.makeText(getApplicationContext(), "category"+category+" total:"+sumtotal, Toast.LENGTH_LONG).show();

		  return cursor1;

		 }
   
	
	//get the selected dropdown list value
	public void addListenerOnButton() {

		spinner1 = (Spinner) findViewById(R.id.spinner1);
		edittext1=(EditText)  findViewById(R.id.editText1);

		//spinner2 = (Spinner) findViewById(R.id.spinner2);
		
		save = (Button) findViewById(R.id.save);

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				
				edittext1.getText().toString();
				if(edittext1.getText().toString().equals(""))
				{
					edittext1.setError("Enter the value");
					//Toast.makeText(AddBudget.this, "Enter all values", Toast.LENGTH_LONG).show();
					return;
					
				}
				
				else
				{
				final ArrayList<String> results = new ArrayList<String>();
				final ArrayList<String> results1 = new ArrayList<String>();
				final ArrayList<String> results2 = new ArrayList<String>();
				final String username=getIntent().getExtras().getString("username");
    			final String category=spinner1.getSelectedItem().toString();
    			final String subcat=spinner2.getSelectedItem().toString();
    			final float amount=Float.valueOf(edittext1.getText().toString());
    			final int month=(datepicker.getMonth()+1);
    			final int date=datepicker.getDayOfMonth();
    			final int year=datepicker.getYear();
    		
    			
    			String month1=null;
    			
    			if(month==1)
    			{
    				month1="Jan";
    				
    				
    			}
    			else if(month==2)
    			{
    				month1="Feb";
    				
    				
    			}
    			else if(month==3)
    			{
    				month1="Mar";
    				
    				
    			}
    			else if(month==4)
    			{
    				month1="Apr";
    				
    				
    			}
    			else if(month==5)
    			{
    				month1="May";
    				
    				
    			}
    			else if(month==6)
    			{
    				month1="Jun";
    				
    				
    			}
    			if(month==7)
    			{
    				month1="Jul";
    				
    				
    			}
    			else if(month==8)
    			{
    				month1="Aug";
    				
    				
    			}
    			else if(month==9)
    			{
    				month1="Sep";
    				
    				
    			}
    			else if(month==10)
    			{
    				month1="Oct";
    				
    				
    			}
    			else if(month==11)
    			{
    				month1="Nov";
    				
    				
    			}
    			else if(month==12)
    			{
    				month1="Dec";
    				
    				
    			}
    			
    			
    			loginDataBaseAdapter.open();
	 		
 			 Cursor cr;
 			
 		   cr= loginDataBaseAdapter.db.rawQuery("select category from ADD_BUDGET where username='"+username+"' and category='"+category+"'" +
				"and year="+year+" and month='"+month1+"'", null);
 		    					 
		    if(cr.getCount()>=1) 
	        {
		    	
    			
   			 // Toast.makeText(AddTransaction.this,"Transaction:-------------" + String.valueOf(spinner1.getSelectedItem()),Toast.LENGTH_SHORT).show();
    			 Cursor cursor1;
    			  
    				
    float trans = 0;	
    	cursor1= loginDataBaseAdapter.db.rawQuery(
    "SELECT SUM(amount) FROM ADD_TRANS where username='"+username+"' and category='"+category+"' and year="+year+" and month='"+month1+"' GROUP BY category", null);
    					 
    				    if(cursor1.getCount()>=1) 
    			        {
    				    	//Toast.makeText(AddTransaction.this,"Transaction:-------------" +results1.get(0),Toast.LENGTH_SHORT).show();
    			        	if  (cursor1.moveToFirst()) {
    			                    do {
    			                       trans = cursor1.getFloat(cursor1.getColumnIndex("SUM(amount)"));
    			                       
    			                       results1.add(""+trans);
    			                      
    			                    }while (cursor1.moveToNext());
    			        
    			        }
    			        }
    				    
    				   // Toast.makeText(AddTransaction.this,"Transaction:-------------"+trans,Toast.LENGTH_SHORT).show();
    				    if(cursor1.getCount()<1) 
    			        {
    				    	//Toast.makeText(AddTransaction.this,"Transaction:-------------" +results1.get(0),Toast.LENGTH_SHORT).show();
    			        	if  (cursor1.moveToFirst()) {
    			                    do {
    			                       trans = cursor1.getFloat(cursor1.getColumnIndex("SUM(amount)"));
    			                       trans=0;
    			                       
    			                       results1.add(""+trans);
    			                      
    			                    }while (cursor1.moveToNext());
    			        
    			        }
    			        }
    				    
        // Toast.makeText(AddTransaction.this,"Transaction:-------------"+trans,Toast.LENGTH_SHORT).show();
   			float budget = 0;
   			
   			String query="select ((budget*warning)/100) from ADD_BUDGET b where username='"+username+"' and category='"+category+"'" +
   					"and year="+year+" and month='"+month1+"'";
			Cursor c=loginDataBaseAdapter.db.rawQuery(query,null);
	       if(c.getCount()>=1) 
	        {
	        	if  (c.moveToFirst()) {
	                    do {
	                       budget = c.getFloat(c.getColumnIndex("((budget*warning)/100)"));
	                     
	                       
	                       results.add(""+budget);
	                       
	                    }while (c.moveToNext());
	                } 
	        	
	        	
	        	
	       		
	        }
	       
	       int warning=0;
  			
  			String warn="select warning from ADD_BUDGET b where username='"+username+"' and category='"+category+"'" +
  					"and year="+year+" and month='"+month1+"'";
			Cursor cur=loginDataBaseAdapter.db.rawQuery(warn,null);
	       if(cur.getCount()>=1) 
	        {
	        	if  (cur.moveToFirst()) {
	                    do {
	                       warning = cur.getInt(cur.getColumnIndex("warning"));
	                     
	                       
	                       results2.add(""+warning);
	                       
	                    }while (cur.moveToNext());
	                } 
	        	
	        	
	        	
	       		
	        }
	                    
	        	//Toast.makeText(AddTransaction.this,"Budget:-------------" +budget,Toast.LENGTH_SHORT).show();
	        	
	       if(warning!=0)
	       {
   	            	
	        	if(budget<(trans+amount))
   	            	{
   	            		
   	            		final Dialog dialog = new Dialog(AddTransaction.this);
   	            		dialog.setContentView(R.layout.warning);
   	            		
   	            		Button ok=(Button)dialog.findViewById(R.id.ok);
   	            		
   	            		ok.setOnClickListener(new View.OnClickListener() {
   	            			public void onClick(View v) 
   	            			{
   	            				
   	            			loginDataBaseAdapter.insertTrans(username,category,subcat,amount,month,date,year);
   	         				sumAll();
   	         				catsum();
   	         				Intent intent = new Intent(getApplicationContext(),Success.class); 
   	         				intent.putExtra("username",username);
   	         			    startActivity(intent);
   	         			    Toast.makeText(getApplicationContext(), "Transaction added Successfully", Toast.LENGTH_LONG).show();
   	            			}
   	            		});
   	            		
   	            		
   	            		
   	            		Button cancel=(Button)dialog.findViewById(R.id.cancel);
   	            		
   	            		cancel.setOnClickListener(new View.OnClickListener() {
   	            			public void onClick(View v) 
   	            			{
   	            				
   	            			
   	         				Intent intent = new Intent(getApplicationContext(),AddTransaction.class); 
   	         				intent.putExtra("username",username);
   	         			    startActivity(intent);
   	            			}
   	            		});
   	            		
   	            		dialog.show();
   	            		
   	            	}  		
   	            	
			
   	            	else
   	            	{
	        	
	        		loginDataBaseAdapter.insertTrans(username,category,subcat,amount,month,date,year);
    				sumAll();
    				catsum();
    				Intent intent = new Intent(getApplicationContext(),Success.class); 
    				intent.putExtra("username",username);
    			    startActivity(intent);
    			    Toast.makeText(getApplicationContext(), "Transaction added Successfully", Toast.LENGTH_LONG).show();
   	            
			}
	        	
	        	
			}
	       
	       
	       else
       	{
       		  
	    	   loginDataBaseAdapter.insertTrans(username,category,subcat,amount,month,date,year);
				sumAll();
				catsum();
				Intent intent = new Intent(getApplicationContext(),Success.class); 
				intent.putExtra("username",username);
			    startActivity(intent);
			    Toast.makeText(getApplicationContext(), "Transaction added Successfully", Toast.LENGTH_LONG).show();

       		
       		
       	}
			
	        }
			
	    else
	    {
	    	final Dialog dialog = new Dialog(AddTransaction.this);
        	dialog.setContentView(R.layout.budgetwarning);
        	Button ok=(Button)dialog.findViewById(R.id.ok);
        	
        	ok.setOnClickListener(new View.OnClickListener() {
        			public void onClick(View v) 
        			{
        				Intent addbudget=new Intent(getApplicationContext(),Success.class);
    		 			addbudget.putExtra("username",username);
    		 			startActivity(addbudget);
        			}
        		});
        	dialog.show();
	    }
			}
				
			}
			//loginDataBaseAdapter.close();
		});
		//loginDataBaseAdapter.close();
	}
	
}