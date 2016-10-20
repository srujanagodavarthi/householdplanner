package com.budget.budgetplanner;



import java.util.ArrayList;
import java.util.List;

import com.budget.budgetplanner.R;

import com.budget.budgetplanner.Groceryremainder.MyAdapter;
import com.budget.budgetplanner.Groceryremainder;


//import com.edureka.custom_spinner.MainActivity.MyAdapter;

import android.app.Activity;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Groceryremainder extends ListActivity
{
	LoginDataBaseAdapter loginDataBaseAdapter;
	Spinner spinner1,spinner2;
	Button save,button1;
	ArrayList<String> list = new ArrayList<String>();

    /** Declaring an ArrayAdapter to set items to ListView */
    ArrayAdapter<String> adapter;
    String joined;
	private EditText edittext;

	
		String[] Items = { "Select items", "Grocery","vegetables","Stationary","Clothes"};
		Integer[] images = { 0, R.drawable.grocery,R.drawable.veg,R.drawable.stationary1,R.drawable.clothes};
		
		
		String[] SItems = { "Select items", "sugar","oil","cereals","snacks","salt"};
		Integer[] Simages = { 0, R.drawable.sugar,R.drawable.oil,R.drawable.cereals,R.drawable.snacks,R.drawable.salt};
	
		String[] VItems = { "Select items", "leafyVegetables","Vegetables","Fruits"};
		Integer[] Vimages = { 0, R.drawable.leafy,R.drawable.veg,R.drawable.fruits};
		
		String[] StItems = { "Select items", "Kids","business"};
		Integer[] Stimages = { 0, R.drawable.kidsstationary,R.drawable.stationary};
	
		String[] CItems = { "Select items", "Women","Men","Kids"};
		Integer[] Cimages = { 0, R.drawable.women,R.drawable.mens,R.drawable.kids};
	
		@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 	
		 setContentView(R.layout.grocery);
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title1);
		    TextView tv = (TextView) findViewById(R.id.textView9);	
		    tv.setText("Budget Planner");
		    Button help = (Button) findViewById(R.id.Help);
	        help.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					HelpDialog helpDialog = new HelpDialog(Groceryremainder.this);
					helpDialog.setTitle("Help ");
					helpDialog.show();           		
				}        
	        });
		
		 getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		 loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		 loginDataBaseAdapter=loginDataBaseAdapter.open();
		 String username=getIntent().getExtras().getString("username");
		 
		// Spinner element
	
		 spinner1 = (Spinner) findViewById(R.id.spinner1);
		 spinner2 = (Spinner) findViewById(R.id.spinner2);
		 edittext = (EditText) findViewById(R.id.editText1);
		 Button btn = (Button) findViewById(R.id.btnAdd);
		 Button button1 = (Button) findViewById(R.id.button1);
		 Button btnDel = (Button) findViewById(R.id.btnDel);
		 adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, list);
		 button1.setVisibility(View.INVISIBLE);
		
		 
		spinner1.setAdapter(new MyAdapter(Groceryremainder.this, R.layout.custom,
				Items));
		 spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener()
		   {
		        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) 
		        {
		        	if(parentView.getItemAtPosition(position).toString().equals("Grocery"))
		    		{
		        		/*Toast.makeText(parentView.getContext(), 
			        				"OnItemSelectedListener : " + parentView.getItemAtPosition(position).toString(),
			        				Toast.LENGTH_SHORT).show();*/
		        		spinner2.setAdapter(new MyAdapter1(Groceryremainder.this, R.layout.custom,
		        				SItems));
		        		
		    		}
		        	if(parentView.getItemAtPosition(position).toString().equals("vegetables"))
		    		{
		        		/*Toast.makeText(parentView.getContext(), 
			        				"OnItemSelectedListener : " + parentView.getItemAtPosition(position).toString(),
			        				Toast.LENGTH_SHORT).show();*/
		        		spinner2.setAdapter(new MyAdapter2(Groceryremainder.this, R.layout.custom,
		        				VItems));
		        		
		    		}
		        	if(parentView.getItemAtPosition(position).toString().equals("Stationary"))
		    		{
		        		/*Toast.makeText(parentView.getContext(), 
			        				"OnItemSelectedListener : " + parentView.getItemAtPosition(position).toString(),
			        				Toast.LENGTH_SHORT).show();*/
		        		spinner2.setAdapter(new MyAdapter3(Groceryremainder.this, R.layout.custom,
		        				StItems));
		        		
		    		}
		        	if(parentView.getItemAtPosition(position).toString().equals("Clothes"))
		    		{
		        		/*Toast.makeText(parentView.getContext(), 
			        				"OnItemSelectedListener : " + parentView.getItemAtPosition(position).toString(),
			        				Toast.LENGTH_SHORT).show();*/
		        		spinner2.setAdapter(new MyAdapter4(Groceryremainder.this, R.layout.custom,
		        				CItems));
		        		
		    		
		    		}
		        	
		        
		        }
		        
		      
		        

		        
		   });
							  OnClickListener listener = new OnClickListener() {
					              @Override
					              public void onClick(View v) 
					              {
					            	 StringBuilder r= new StringBuilder();
					                String cat1=spinner1.getSelectedItem().toString();
					  				String cat2=spinner2.getSelectedItem().toString();
					  				String description=edittext.getText().toString();
					  				String username=getIntent().getExtras().getString("username");
					  				String item=getIntent().getExtras().getString("item");
					  				String quantity=getIntent().getExtras().getString("quantity");
					  				
					  				if(description.equals(""))
									{
										edittext.setError("Enter the Description");
										//Toast.makeText(AddBudget.this, "Enter all values", Toast.LENGTH_LONG).show();
										return;
										
									}
					                      //list.add(edit.getText().toString());
					               list.add(""+cat2+ "     "+ description); 
					              // list.add(""+item+ "     "+ quantity);
					               //Seperator seperator=new Seperator("\n");  
					               r.append(System.getProperty("line.separator"));
					              joined=TextUtils.join(",",list);
				                 // Toast.makeText(Groceryremainder.this, "list:"+joined, Toast.LENGTH_LONG).show();
		                          edittext.setText("");
    		                      adapter.notifyDataSetChanged();
					              }
					      };
					      save=(Button)findViewById(R.id.save);
						    // Set OnClick Listener on SignUp button 
							  
							 save.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) 
								
								{
									
									edittext.getText().toString();
									 if(edittext.getText().equals(""))
										{
											edittext.setError("Enter the Description");
											//Toast.makeText(AddBudget.this, "Enter all values", Toast.LENGTH_LONG).show();
											return;
											
										}
									 
									String username=getIntent().getExtras().getString("username");
									/*String cat1=spinner1.getSelectedItem().toString();
									String cat2=spinner2.getSelectedItem().toString();
									String description=edittext.getText().toString();
									list.add(""+cat2+ "     "+ description); */
									//String newStr = list.replaceAll("\\n",",");
					                String joined=TextUtils.join(",",list);		
					                
									Intent intent = new Intent(getApplicationContext(),grocery.class); 
									intent.putExtra("username",username);
									intent.putExtra("list",joined);
									 // Toast.makeText(getApplicationContext(), "joined "+joined, Toast.LENGTH_LONG).show();
								    startActivity(intent);
								    
								    loginDataBaseAdapter.close();
									
									}
								});
					/** Defining a click event listener for the button "Delete" */
					OnClickListener listenerDel = new OnClickListener() {
						@Override
						public void onClick(View v) {
							/** Getting the checked items from the listview */
							SparseBooleanArray checkedItemPositions = getListView().getCheckedItemPositions();
							int itemCount = getListView().getCount();
							
							for(int i=itemCount-1; i >= 0; i--){
								if(checkedItemPositions.get(i)){	    				
									adapter.remove(list.get(i));
								}
							}	
							checkedItemPositions.clear();
						    adapter.notifyDataSetChanged();
						    
						   
						    
						}
					};          
					 button1.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								String username1=getIntent().getExtras().getString("username");
							   Intent intent = new Intent(getApplicationContext(),Newitem.class); 
							   intent.putExtra("username",username1);
							    startActivity(intent);
								
								
							
							}
							

						});
					
					/** Setting the event listener for the add button */
					btn.setOnClickListener(listener);
					
					/** Setting the event listener for the delete button */
					btnDel.setOnClickListener(listenerDel);    
					
					/** Setting the adapter to the ListView */
					setListAdapter(adapter);
					
							
							
					    }
	
		 
		    public class MyAdapter extends ArrayAdapter<String>{
		 
		        public MyAdapter(Context context, int textViewResourceId,   String[] objects) {
		            super(context, textViewResourceId, objects);
		        }
		 
		        @Override
		        public View getDropDownView(int position, View convertView,ViewGroup parent) {
		            return getCustomView(position, convertView, parent);
		        }
		 
		        @Override
		        public View getView(int position, View convertView, ViewGroup parent) {
		            return getCustomView(position, convertView, parent);
		        }
		 
		        public View getCustomView(int position, View convertView, ViewGroup parent) {
		 
		            LayoutInflater inflater=getLayoutInflater();
		            View row=inflater.inflate(R.layout.custom, parent, false);
		            TextView label=(TextView)row.findViewById(R.id.tvLanguage);
		            label.setText(Items[position]);
		 
		            ImageView icon=(ImageView)row.findViewById(R.id.imgLanguage);
		            icon.setImageResource(images[position]);
		 
		            return row;
		        }
		        
		    }
		    
		    
		    
		    public class MyAdapter1 extends ArrayAdapter<String>{
				 
		        public MyAdapter1(Context context, int textViewResourceId,   String[] objects) {
		            super(context, textViewResourceId, objects);
		        }
		 
		        @Override
		        public View getDropDownView(int position, View convertView,ViewGroup parent) {
		            return getCustomView(position, convertView, parent);
		        }
		 
		        @Override
		        public View getView(int position, View convertView, ViewGroup parent) {
		            return getCustomView(position, convertView, parent);
		        }
		 
		        public View getCustomView(int position, View convertView, ViewGroup parent) {
		 
		            LayoutInflater inflater=getLayoutInflater();
		            View row=inflater.inflate(R.layout.custom, parent, false);
		            TextView label=(TextView)row.findViewById(R.id.tvLanguage);
		            label.setText(SItems[position]);
		 
		            ImageView icon=(ImageView)row.findViewById(R.id.imgLanguage);
		            icon.setImageResource(Simages[position]);
		 
		            return row;
		        }
		    }
		    public class MyAdapter2 extends ArrayAdapter<String>{
				 
		        public MyAdapter2(Context context, int textViewResourceId,   String[] objects) {
		            super(context, textViewResourceId, objects);
		        }
		 
		        @Override
		        public View getDropDownView(int position, View convertView,ViewGroup parent) {
		            return getCustomView(position, convertView, parent);
		        }
		 
		        @Override
		        public View getView(int position, View convertView, ViewGroup parent) {
		            return getCustomView(position, convertView, parent);
		        }
		 
		        public View getCustomView(int position, View convertView, ViewGroup parent) {
		 
		            LayoutInflater inflater=getLayoutInflater();
		            View row=inflater.inflate(R.layout.custom, parent, false);
		            TextView label=(TextView)row.findViewById(R.id.tvLanguage);
		            label.setText(VItems[position]);
		 
		            ImageView icon=(ImageView)row.findViewById(R.id.imgLanguage);
		            icon.setImageResource(Vimages[position]);
		 
		            return row;
		        }
		    }
		    public class MyAdapter3 extends ArrayAdapter<String>{
				 
		        public MyAdapter3(Context context, int textViewResourceId,   String[] objects) {
		            super(context, textViewResourceId, objects);
		        }
		 
		        @Override
		        public View getDropDownView(int position, View convertView,ViewGroup parent) {
		            return getCustomView(position, convertView, parent);
		        }
		 
		        @Override
		        public View getView(int position, View convertView, ViewGroup parent) {
		            return getCustomView(position, convertView, parent);
		        }
		 
		        public View getCustomView(int position, View convertView, ViewGroup parent) {
		 
		            LayoutInflater inflater=getLayoutInflater();
		            View row=inflater.inflate(R.layout.custom, parent, false);
		            TextView label=(TextView)row.findViewById(R.id.tvLanguage);
		            label.setText(StItems[position]);
		 
		            ImageView icon=(ImageView)row.findViewById(R.id.imgLanguage);
		            icon.setImageResource(Stimages[position]);
		 
		            return row;
		        }
		       

		    }
		    public class MyAdapter4 extends ArrayAdapter<String>{
				 
		        public MyAdapter4(Context context, int textViewResourceId,   String[] objects) {
		            super(context, textViewResourceId, objects);
		        }
		 
		        @Override
		        public View getDropDownView(int position, View convertView,ViewGroup parent) {
		            return getCustomView(position, convertView, parent);
		        }
		 
		        @Override
		        public View getView(int position, View convertView, ViewGroup parent) {
		            return getCustomView(position, convertView, parent);
		        }
		 
		        public View getCustomView(int position, View convertView, ViewGroup parent) {
		 
		            LayoutInflater inflater=getLayoutInflater();
		            View row=inflater.inflate(R.layout.custom, parent, false);
		            TextView label=(TextView)row.findViewById(R.id.tvLanguage);
		            label.setText(CItems[position]);
		            ImageView icon=(ImageView)row.findViewById(R.id.imgLanguage);
		            icon.setImageResource(Cimages[position]);
		 
		            return row;
		        }
		    }
	       
		   }


