package com.budget.budgetplanner;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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

public class Addcategory extends Activity
{
	LoginDataBaseAdapter loginDataBaseAdapter;
	// Add button
	Button btnadd;

	// Input text
	EditText newcategory,scategory1,scategory2,scategory3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 	
		 setContentView(R.layout.addcategory);
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title1);
		    TextView tv = (TextView) findViewById(R.id.textView9);	
		    tv.setText("Budget Planner");
		    Button help = (Button) findViewById(R.id.Help);
	        help.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					HelpDialog helpDialog = new HelpDialog(Addcategory.this);
					helpDialog.setTitle("Help ");
					helpDialog.show();           		
				}        
	        });
		
		//getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		// Spinner element
	

		// add button

		btnadd     = (Button) findViewById(R.id.btn_add);

		// new label input field
		newcategory = (EditText) findViewById(R.id.input_label);
		scategory1 = (EditText) findViewById(R.id.editText1);
		scategory2 = (EditText) findViewById(R.id.editText2);
		scategory3 = (EditText) findViewById(R.id.editText3);
		
		// Loading spinner data from database
		

		/**
		 * Add new label button click listener
		 * */
		btnadd.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
			        	String username=getIntent().getExtras().getString("username");
				//String username="s";
		    			String category=newcategory.getText().toString();
		    			String sub1=scategory1.getText().toString();
		    			String sub2=scategory2.getText().toString();
		    			String sub3=scategory3.getText().toString();
		    			Toast.makeText(getApplicationContext(), "Category added", Toast.LENGTH_LONG).show();
						String query="select category from ADD_CATEGORY where category='"+category+"' ";
						Cursor c=loginDataBaseAdapter.db.rawQuery(query,null);
				        if(c.getCount()==1) 
				        {
				        	Toast.makeText(getApplicationContext(), "Already category exists select new one", Toast.LENGTH_LONG).show();
				        	 Intent intent = new Intent(getApplicationContext(),Addcategory.class); 
				        	 startActivity(intent);
				        }
				        else if((category.equals(null)||category.equals(""))&&sub1!=null&&sub2!=null&&sub3!=null)
						{
		    				newcategory.setError("Enter the category");
							
							return;
							
							
						}
				        else if(category.equals(null)||category.equals("")&&(sub1.equals(null)||sub1.equals(""))&&(sub2.equals(null)||sub2.equals(""))&&(sub3.equals(null)||sub3.equals("")))
		    			{
                            newcategory.setError("Enter the category");
							
							return;
				        	
		    			}
				        
				        else if(sub1!=null&&sub2!=null&&sub3!=null)
				        {
				        	loginDataBaseAdapter.insertsubcat(username,category,sub1,sub2,sub3);
				        	loginDataBaseAdapter.insertcat(category,username);
						  
						   Intent intent = new Intent(getApplicationContext(),AddBudget.class);
						   intent.putExtra("username",username);
						   
						   Addcategory.this. startActivity(intent);
						   Toast.makeText(getApplicationContext(), "Category added", Toast.LENGTH_LONG).show();
				        
		            }
				
		    			else if((sub1.equals(null)||sub1.equals(""))&&(sub2.equals(null)||sub2.equals(""))&&(sub3.equals(null)||sub3.equals("")))
		    			{
		    				category=newcategory.getText().toString();
		    				loginDataBaseAdapter.insertcat(category,username);
		    				 Toast.makeText(getApplicationContext(), "Category added", Toast.LENGTH_LONG).show();
							   Intent intent = new Intent(getApplicationContext(),AddBudget.class);
							   intent.putExtra("username",username);
							   
							   Addcategory.this. startActivity(intent);
		    			}
		    			
		    			else if((sub1.equals(null)||sub1.equals(""))&&sub2!=null&&sub3!=null)
						{
		    				sub1="";
		    				
		    				category=newcategory.getText().toString();
			    			
			    			sub2=scategory2.getText().toString();
			    			sub3=scategory3.getText().toString();
		    				loginDataBaseAdapter.insertsubcat(username,category,sub1,sub2,sub3);
				        	loginDataBaseAdapter.insertcat(category,username);
						  
						   Intent intent = new Intent(getApplicationContext(),AddBudget.class);
						   intent.putExtra("username",username);
						   
						   Addcategory.this. startActivity(intent);
						  
							
						}
		    			
		    			else if((sub2.equals(null)||sub2.equals(""))&&sub1!=null&&sub3!=null)
						{
		    				sub2="";
		    				category=newcategory.getText().toString();
			    			sub1=scategory1.getText().toString();
			    			
			    			sub3=scategory3.getText().toString();
		    				loginDataBaseAdapter.insertsubcat(username,category,sub1,sub2,sub3);
				        	loginDataBaseAdapter.insertcat(category,username);
						  
						   Intent intent = new Intent(getApplicationContext(),AddBudget.class);
						   intent.putExtra("username",username);
						   
						   Addcategory.this. startActivity(intent);
						   
							
						}
		    			
		    			else if((sub3.equals(null)||sub3.equals(""))&&sub2!=null&&sub1!=null)
						{
		    				sub3="";
		    				
		    				category=newcategory.getText().toString();
			    			sub1=scategory1.getText().toString();
			    			sub2=scategory2.getText().toString();
			    			
		    				loginDataBaseAdapter.insertsubcat(username,category,sub1,sub2,sub3);
				        	loginDataBaseAdapter.insertcat(category,username);
						  
						   Intent intent = new Intent(getApplicationContext(),AddBudget.class);
						   intent.putExtra("username",username);
						   
						   Addcategory.this. startActivity(intent);
						   
							
						}
		    			
		    		   
			}

		   });
		            
		

		}
}