package com.budget.budgetplanner;

import java.util.ArrayList;
import java.util.List;

import com.budget.budgetplanner.R;
import com.budget.budgetplanner.CustomOnItemSelectedListener;
import com.budget.budgetplanner.AddIncome;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;




public class AddIncome extends Activity
{
	
	EditText edittext1,edittext2,edittext3,edittext4,edittext5;
	Button btnSave,btnSave1,btnCancel;
	
	LoginDataBaseAdapter loginDataBaseAdapter;
	
		protected void onCreate(Bundle savedInstanceState)
		{
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			
			 requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 	
			    setContentView(R.layout.addincome);
				getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title1);
			    TextView tv = (TextView) findViewById(R.id.textView9);	
			    tv.setText("Budget Planner");
			    Button help = (Button) findViewById(R.id.Help);
		        help.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						HelpDialog helpDialog = new HelpDialog(AddIncome.this);
						helpDialog.setTitle("Help ");
						helpDialog.show();           		
					}        
		        });
			 final ArrayList<String> results = new ArrayList<String>();
		
		
			//Toast.makeText(getApplicationContext(), "enter values", Toast.LENGTH_LONG).show();
		// get Instance  of Database Adapter
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		
		// Get Refferences of Views
		edittext1=(EditText)findViewById(R.id.editText1);
		edittext2=(EditText)findViewById(R.id.editText2);
		edittext3=(EditText)findViewById(R.id.editText3);
		edittext4=(EditText)findViewById(R.id.editText4);
		edittext5=(EditText)findViewById(R.id.editText5);
		final String username1=getIntent().getExtras().getString("username");
		//Toast.makeText(getApplicationContext(), "enter values into toast", Toast.LENGTH_LONG).show();

		
		  
						btnSave=(Button)findViewById(R.id.button1);
						btnSave1=(Button)findViewById(R.id.button1);
						btnCancel=(Button)findViewById(R.id.button2);
						float hpay,phomepay,b,s,o;
					
						
							String query="select homepay,partner_homepay,bonus,savings,other from ADD_INCOME where username='"+username1+"'";
							Cursor c=loginDataBaseAdapter.db.rawQuery(query,null);
					        if(c.getCount()>=1) 
					        {
					        	if  (c.moveToFirst()) {
					                    do {
					                        hpay = c.getFloat(c.getColumnIndex("homepay"));
					                        phomepay= c.getFloat(c.getColumnIndex("partner_homepay"));
					                        b= c.getFloat(c.getColumnIndex("bonus"));
					                        s=c.getFloat(c.getColumnIndex("savings"));
					                        o=c.getFloat(c.getColumnIndex("other"));
					                        
					                    //    results.add("" + firstName + ",Age: " + age);
					                       results.add(""+hpay);
					                       results.add(""+phomepay);
					                       results.add(""+b);
					                       results.add(""+s);
					                       results.add(""+o);
		
					                    }while (c.moveToNext());
					                } 
					            
						            //this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,results));
						            //edittext1.settext(username);
						            edittext1.setText(results.get(0)); 
						            edittext2.setText(results.get(1));
						            edittext3.setText(results.get(2));
						            edittext4.setText(results.get(3));
						            edittext5.setText(results.get(4));
						            
						           
						        	
						              btnSave.setOnClickListener(new View.OnClickListener() {
						    			
										public void onClick(View v) {
											// TODO Auto-generated method stub
											
											final Dialog dialog = new Dialog(AddIncome.this);
					   	            		dialog.setContentView(R.layout.updateincome);
					   	            		
					   	            		Button ok=(Button)dialog.findViewById(R.id.ok);
					   	            		
					   	            		ok.setOnClickListener(new View.OnClickListener() {
					   	            			public void onClick(View v) 
					   	            			{
					   	            			
String username=getIntent().getExtras().getString("username");
									            	
									            	edittext1.getText().toString();
													edittext2.getText().toString();
													edittext3.getText().toString();
													edittext4.getText().toString();
													edittext5.getText().toString();
													if(edittext1.getText().toString().equals("")||edittext2.getText().toString().equals("")||edittext3.getText().toString().equals("")
															||edittext4.getText().toString().equals("")||edittext5.getText().toString().equals(""))
													{
														Toast.makeText(AddIncome.this, "Enter all values", Toast.LENGTH_LONG).show();
														return;
														
														
													}
													else
													{
									    			float homepay=Float.valueOf(edittext1.getText().toString());
									    			float partner_homepay=Float.valueOf(edittext2.getText().toString());
									    			float bonus=Float.valueOf(edittext3.getText().toString());
									    			float savings=Float.valueOf(edittext4.getText().toString());
									    			float other=Float.valueOf(edittext5.getText().toString());
									    			
									    		    float total=homepay+partner_homepay+bonus+savings+other;
										            // Save the Data in Database
												   loginDataBaseAdapter.updateIncome(username,homepay,partner_homepay,bonus,savings,other,total);
												  
												   Intent intent = new Intent(getApplicationContext(),Success.class); 
												   
										 			intent.putExtra("username",username);
										 	
												    startActivity(intent);
												    
												    Toast.makeText(AddIncome.this, "Income updated Successfully", Toast.LENGTH_LONG).show();
									            }
					   	         			    
					   	            			}
					   	            		});
					   	            		
					   	            		
					   	            		
					   	            		Button cancel=(Button)dialog.findViewById(R.id.cancel);
					   	            		
					   	            		cancel.setOnClickListener(new View.OnClickListener() {
					   	            			public void onClick(View v) 
					   	            			{
					   	            				
					   	            			
					   	         				Intent intent = new Intent(getApplicationContext(),Success.class); 
					   	         				intent.putExtra("username",username1);
					   	         			    startActivity(intent);
					   	            			}
					   	            		});
					   	            		
					   	            		dialog.show();
									            	
										}
												

									});
							
							       
							
					            }    
					            else
					            {
					           
									btnSave1.setOnClickListener(new View.OnClickListener() {
						
									public void onClick(View v) {
										// TODO Auto-generated method stub
										
										String username=getIntent().getExtras().getString("username");
					
					edittext1.getText().toString();
					edittext2.getText().toString();
					edittext3.getText().toString();
					edittext4.getText().toString();
					edittext5.getText().toString();
					if(edittext1.getText().toString().equals("")||edittext2.getText().toString().equals("")||edittext3.getText().toString().equals("")
							||edittext4.getText().toString().equals("")||edittext5.getText().toString().equals(""))
					{
						Toast.makeText(AddIncome.this, "Enter all values", Toast.LENGTH_LONG).show();
						return;
						
						
					}
					else{
						
					 
				    float homepay=Float.valueOf(edittext1.getText().toString());
					float partner_homepay=Float.valueOf(edittext2.getText().toString());
	    			float bonus=Float.valueOf(edittext3.getText().toString());
	    			float savings=Float.valueOf(edittext4.getText().toString());
	    			float other=Float.valueOf(edittext5.getText().toString());
					float total=homepay+partner_homepay+bonus+savings+other;
					
    			// Save the Data in Database
				    loginDataBaseAdapter.insertIncome(username,homepay,partner_homepay,bonus,savings,other,total);
				  
				   Intent intent = new Intent(getApplicationContext(),Success.class); 
				   intent.putExtra("username",username);
				    startActivity(intent);
	            
									}}
		             	});
		            }
  }
		
		public void Clear(View v)
		{
			
			edittext1=(EditText)findViewById(R.id.editText1);
			edittext1.setText("");
			edittext2=(EditText)findViewById(R.id.editText2);
			edittext2.setText("");
			edittext3=(EditText)findViewById(R.id.editText3);
			edittext3.setText("");
			edittext4=(EditText)findViewById(R.id.editText4);
			edittext4.setText("");
			edittext5=(EditText)findViewById(R.id.editText5);
			edittext5.setText("");
			
			
		}
		
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		loginDataBaseAdapter.close();
	}

	
}