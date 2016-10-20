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
		 
		public class fdcalculator extends Activity {
		    private EditText edittext1, edittext2;
		    private Spinner spinner1;
		    private TextView mamount,minterest,myield;
		    private Button button1,button2;
		     
		    /** Initializes the app when it is first created. */
		    @Override
		    public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.fdcalci);
		        
		        edittext1 = (EditText)findViewById(R.id.edittext1);
		        edittext2 = (EditText)findViewById(R.id.edittext2);
		        spinner1 = (Spinner)findViewById(R.id.spinner1);
		        minterest = (TextView)findViewById(R.id.Minterest); 
		        mamount = (TextView)findViewById(R.id.Mamount);
		        myield = (TextView)findViewById(R.id.Myield);
		        addItemsOnSpinner2();
		        
		       
				
		    }
		     
		    
		    
		   
		    public void Fdcalci(View clickedButton) 
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
		    	 
		    	 
		    	double fdAmount = Float.parseFloat(edittext1.getText().toString());
		        double interestRate = (Float.parseFloat(edittext2.getText().toString()))/100;
		        String period=spinner1.getSelectedItem().toString();
		        double Period=(Integer.parseInt(period))/12;
		        double fd=fdAmount*(1+(interestRate*Period));
		        double maturityamount = fdAmount*Math.pow((1+(interestRate)/4),(Period*4));
		        double interest=maturityamount-fdAmount;
		        double annualinterest= interest/Period;
		        double annualinterestpercent=(annualinterest/fdAmount)*100;
				minterest.setText(new DecimalFormat("##.##").format(interest));
		        mamount.setText(new DecimalFormat("##.##").format(maturityamount));
		        myield.setText(new DecimalFormat("##.##").format(annualinterestpercent));
		        
		    
		          }
		    public void addItemsOnSpinner2() {

				spinner1 = (Spinner) findViewById(R.id.spinner1);
				List<String> list = new ArrayList<String>();
				
				list.add("12");
				list.add("15");
				list.add("18");
				list.add("21");
				list.add("24");
				list.add("27");
				list.add("30");
				list.add("33");
				list.add("36");
				list.add("39");
				list.add("42");
				list.add("45");
				list.add("48");
				list.add("51");
				list.add("54");
				list.add("57");
				list.add("60");
				list.add("63");
				list.add("66");
				list.add("69");
				list.add("72");
				list.add("75");
				list.add("78");
				list.add("81");
				list.add("84");
				list.add("87");
				list.add("90");
				list.add("93");
				list.add("96");
				list.add("99");
				list.add("102");
				list.add("105");
				list.add("108");
				list.add("111");
				list.add("114");
				list.add("117");
				list.add("120");
				
				
				ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
				dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner1.setAdapter(dataAdapter);
			}
		    
		    
		}