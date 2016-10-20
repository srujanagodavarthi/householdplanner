				package com.budget.budgetplanner;
		
		import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
		 
		public class rdcalculator extends Activity {
		    private EditText edittext1, edittext2,edittext3;
		    private Spinner spinner1,spinner2;
		    private TextView mamount,minterest;
		    private Button button1,button2;
		     
		    /** Initializes the app when it is first created. */
		    @Override
		    public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.rdcalci);
		        
		        edittext1 = (EditText)findViewById(R.id.edittext1);
		        edittext2 = (EditText)findViewById(R.id.edittext2);
		        edittext3 = (EditText)findViewById(R.id.edittext3);
		        spinner1 = (Spinner)findViewById(R.id.spinner1);
		        spinner2 = (Spinner)findViewById(R.id.spinner2);
		      //  minterest = (TextView)findViewById(R.id.Minterest); 
		        mamount = (TextView)findViewById(R.id.Mamount);
		        addItemsOnSpinner1();
		        addItemsOnSpinner2();
		    }
		     
		   
		    public void Rdcalci(View clickedButton) 
		    
		    {
		    	if(edittext1.getText().toString().equals(""))
				{
					edittext1.setError("Enter the value");
					//Toast.makeText(AddBudget.this, "Enter all values", Toast.LENGTH_LONG).show();
					return;
					
				}
		    	
		    	if(edittext2.getText().toString().equals(""))
				{
					edittext2.setError("Enter the value");
					//Toast.makeText(AddBudget.this, "Enter all values", Toast.LENGTH_LONG).show();
					return;
					
				}
		    	
		    	if(edittext3.getText().toString().equals(""))
				{
					edittext3.setError("Enter the value");
					//Toast.makeText(AddBudget.this, "Enter all values", Toast.LENGTH_LONG).show();
					return;
					
				}
		    	
		    	
		    	double rdAmount = Float.parseFloat(edittext1.getText().toString());
		    	int months=Integer.parseInt(edittext3.getText().toString());
		        String period=spinner1.getSelectedItem().toString();
		        double Period=0;
		        if(period.equals("months"))
		        {
		        	Period=months;
		        }
		        if(period.equals("years"))
		        {
		        	Period=months*12;
		        }
		        
		        String frequency=spinner2.getSelectedItem().toString();
		        double interestRate = Float.parseFloat(edittext2.getText().toString());
	        	
	        	if(frequency.equals("monthly"))
		        {
	        		double x=1;
	        		double y=1;
		           double r=(interestRate)*(x/12);
		           double upper=rdAmount*(Math.pow(1+r,(Period/(12/y))-1));
		           double lower=1-(Math.pow(1+r,-1));
		           double 
		           rd=upper/lower;
			      // minterest.setText(new DecimalFormat("##.##").format(interestRate));
			        mamount.setText(new DecimalFormat("##.##").format(rd));
		         
		        }
	        	
	        	 if(frequency.equals("quaterly"))
                 { 
					double quaters = Math.floor(Period / 3);
					/*double rd=(rdAmount*(Math.pow(1+(interestRate/400),quaters)-1))/Math.floor((1-(Math.pow(1+(interestRate/400),-1/3))));
					double upper=(rdAmount*(Math.pow(1+(interestRate/400),quaters)-1));
					double lower=(1-Math.pow(1+(interestRate/400),-1/3));
					double ri=Math.pow(1+(interestRate/400),-1/3);
					Toast.makeText(getApplicationContext(), "math "+ri, Toast.LENGTH_LONG).show();
					Toast.makeText(getApplicationContext(), "upper value "+upper, Toast.LENGTH_LONG).show();
					Toast.makeText(getApplicationContext(), "lower value "+lower, Toast.LENGTH_LONG).show();
					*/double rd= rdAmount*((Math.pow(1+interestRate/400,quaters)-1)*(1200/interestRate+2));   
                     //  minterest.setText(new DecimalFormat("##.########").format(interestRate));
                         mamount.setText(new DecimalFormat("##.########").format(rd));
                  
                 }
		       if(frequency.equals("halfyearly"))
		        {
		          double x=6;
		          double y=2;
		          double  upper=rdAmount*(Math.pow((1+((interestRate/100)*(x/12))),((Period*(12/x))-1)));
		          double  lower=(Math.pow(1-(1+((interestRate/100)*(x/12))),-1/3));
		          double  rd=upper/lower;
			      //  minterest.setText(new DecimalFormat("##.##").format(interestRate));
			        mamount.setText(new DecimalFormat("##.##").format(rd));
		         
		        }
		        
		        if(frequency.equals("yearly"))
		        {
		        	double	rd=rdAmount*Math.pow(1+(interestRate),(Period/12));
		        	Toast.makeText(getApplicationContext(), "enter values into toast"+rd, Toast.LENGTH_LONG).show();
		        //	minterest.setText(new DecimalFormat("##.##").format(interestRate));
			        mamount.setText(new DecimalFormat("##.##").format(rd));
		           
		        }
		     }
		    public void addItemsOnSpinner1() {

				spinner1 = (Spinner) findViewById(R.id.spinner1);
				List<String> list = new ArrayList<String>();
				list.add("months");
				list.add("years");
				
				ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
				dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner1.setAdapter(dataAdapter);
			}
		    public void addItemsOnSpinner2() {

				spinner2 = (Spinner) findViewById(R.id.spinner2);
				List<String> list = new ArrayList<String>();
				list.add("monthly");
				list.add("quaterly");
				list.add("halfyearly");
				list.add("yearly");
				ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
				dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner2.setAdapter(dataAdapter);
			}
		    
		}