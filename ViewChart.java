		package com.budget.budgetplanner;
		
		
		import java.util.ArrayList;
import java.util.List;
		
		import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
		
		import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
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
import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.MultipleCategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
		public class ViewChart extends Activity
		{
			LoginDataBaseAdapter loginDataBaseAdapter;
			private Spinner spinner1,spinner2,spinner3;
			private EditText edittext4;
			private TextView textview4,textview5;
			
			 //private int mBackgroundColor;
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    loginDataBaseAdapter=new LoginDataBaseAdapter(this);
			loginDataBaseAdapter=loginDataBaseAdapter.open();
			getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
			 requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 	
			    setContentView(R.layout.activity_main);
				getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title1);
			    TextView tv = (TextView) findViewById(R.id.textView9);	
			    tv.setText("Budget Planner");
			    Button help = (Button) findViewById(R.id.Help);
		        help.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						HelpDialog helpDialog = new HelpDialog(ViewChart.this);
						helpDialog.setTitle("Help ");
						helpDialog.show();           		
					}        
		        });
		   
		    addItemsOnSpinner1();
			textview4 = (TextView) findViewById(R.id.textView4);
			textview5 = (TextView) findViewById(R.id.textView5);	
			spinner1 = (Spinner) findViewById(R.id.spinner1);
			spinner2 = (Spinner) findViewById(R.id.spinner2);
			spinner3 = (Spinner) findViewById(R.id.spinner3);
		    textview4.setVisibility(View.INVISIBLE);
		    textview5.setVisibility(View.INVISIBLE);
		    spinner2.setVisibility(View.INVISIBLE);
		    spinner3.setVisibility(View.INVISIBLE);
		    
		    String username=getIntent().getExtras().getString("username");
		// Draw the Income vs Expense Chart
		
		// Getting reference to the button btn_chart
		   
		   
		    loadmonth();
		
			 addItemsOnSpinner2();
			 addItemsOnSpinner3();
		}
		
		public void addItemsOnSpinner2() {
		   spinner2 = (Spinner) findViewById(R.id.spinner2);
			List<String> list = new ArrayList<String>();
			//list.add("choose");
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
		
		
		public void loadmonth()
		{
			
			 spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener()
			   {
			        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
			        	if(parentView.getItemAtPosition(position).toString().equals("Month"))
		{
			 Button btnChart = (Button) findViewById(R.id.btn_chart);
			 Button bar = (Button) findViewById(R.id.bar);
			 textview4.setVisibility(View.VISIBLE);
			 textview5.setVisibility(View.VISIBLE);
			 spinner2.setVisibility(View.VISIBLE);
			spinner3.setVisibility(View.VISIBLE);
			  OnClickListener clickListener = new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						pieChartmonth();			
					
					}
					 
				};
			    
			    btnChart.setOnClickListener(clickListener);    
			    
		OnClickListener clickListener1 = new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						barChartmonth();			
					
					}
					 
				};
			    
			    bar.setOnClickListener(clickListener1);
		
		}
		else if(parentView.getItemAtPosition(position).toString().equals("Year"))
			{
				 Button btnChart = (Button) findViewById(R.id.btn_chart);
				 Button bar = (Button) findViewById(R.id.bar);
				 textview5.setVisibility(View.VISIBLE);
				 textview4.setVisibility(View.INVISIBLE);
				 spinner2.setVisibility(View.INVISIBLE);
			   	 spinner3.setVisibility(View.VISIBLE);
				 OnClickListener clickListener = new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							pieChartyear();			
						
						}
						 
					};
				    
				    btnChart.setOnClickListener(clickListener); 
				    
			OnClickListener clickListener1 = new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							barChartyear();			
						
						}
						 
					};
				    
				    bar.setOnClickListener(clickListener1);   
				    
				    
			}
			
		}
		
		public void onNothingSelected(AdapterView<?> parentView) {
			Toast.makeText(parentView.getContext(), 
				"Select one category",
		Toast.LENGTH_SHORT).show();
		// To do ...
			        }
			        
			        
			       
		
			    });
			 
			 
		
			   }
		
		
		public void addItemsOnSpinner1() {
		
			spinner1 = (Spinner) findViewById(R.id.spinner1);
			List<String> list = new ArrayList<String>();
			list.add("Choose");
		list.add("Month");
		list.add("Year");
		
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner1.setAdapter(dataAdapter);
		}
		
		
		public void pieChartmonth(){  
			
			
			
			String username=getIntent().getExtras().getString("username");
		
		String month=spinner2.getSelectedItem().toString();
		int year=Integer.valueOf(spinner3.getSelectedItem().toString());
		Cursor cursor= loginDataBaseAdapter.db.rawQuery("SELECT category FROM ADD_TRANS where username='"+username+"' and" +
		" month='"+month+"'  and year="+year+"  group by category order by category", null);
		
		String[] code = new String[cursor.getCount()]; 
		int j= 0;
		while(cursor.moveToNext()){
		    String category = cursor.getString(cursor.getColumnIndex("category"));
		    code[j] = category;
		    j++;
		}
		 Cursor cursor1;
		  
			int k=0;
		
				cursor1= loginDataBaseAdapter.db.rawQuery(
						"SELECT SUM(amount) FROM ADD_TRANS where username='"+username+"' and"+
		" month='"+month+"' and year="+year+" GROUP BY category order by category", null);
		 double[] distribution = new double[cursor1.getCount()]; 
		while(cursor1.moveToNext()){
			    Float amnt = cursor1.getFloat(cursor1.getColumnIndex("SUM(amount)"));
		   // double total=(subcategory.doubleValue()/10000)*100;
					    distribution[k] = amnt.doubleValue();
					   
					    k++;
					  
			    }
			
		
		if(cursor1.getCount()<1 && cursor.getCount()<1 ) 
		{
			 Toast.makeText(ViewChart.this,"No Pie chart available for the requested month and year:",Toast.LENGTH_SHORT).show();
		    }
				    
		 
			
			// Pie Chart Section Value
		   
		
		
		// Color of each Pie Chart Sections
		int[] colors = { Color.rgb(199,21,133), Color.rgb(255,69,0), Color.GREEN, Color.CYAN, Color.RED,
						 Color.YELLOW };
		
		// Instantiating CategorySeries to plot Pie Chart    	
		CategorySeries distributionSeries = new CategorySeries(" Android version distribution as on October 1, 2012");
		for(int i=0;i<distribution.length;i++){
			// Adding a slice with its values and name to the Pie Chart
		distributionSeries.add(code[i], distribution[i]);
		
		}   
		
		// Instantiating a renderer for the Pie Chart
		DefaultRenderer defaultRenderer  = new DefaultRenderer();    	
		for(int i = 0 ;i<distribution.length;i++){    		
			SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();    	
			seriesRenderer.setColor(colors[i]);
			seriesRenderer.setDisplayChartValues(true);
			// Adding a renderer for a slice
			defaultRenderer.addSeriesRenderer(seriesRenderer);
		}
		
		defaultRenderer.setChartTitle("Budget Planner");
		defaultRenderer.setChartTitleTextSize(20);
		
		defaultRenderer.setZoomButtonsVisible(true);
		defaultRenderer.setApplyBackgroundColor(true);
		defaultRenderer.setBackgroundColor(Color.rgb(255,250,240));
		
		// Creating an intent to plot bar chart using dataset and multipleRenderer    	
		Intent intent = ChartFactory.getPieChartIntent(getBaseContext(), distributionSeries , defaultRenderer, "Expenses");    	
		
		// Start Activity
			startActivity(intent);
			
			 
			
		}
		 
		
		public void pieChartyear(){  
			
		
			//String[] code={};
		//ArrayList<String> List=new ArrayList<String>();
		//String query="SELECT category FROM ADD_TRANS where username='"+username+"';";
		String username=getIntent().getExtras().getString("username");
		int year=Integer.valueOf(spinner3.getSelectedItem().toString());
		Cursor cursor= loginDataBaseAdapter.db.rawQuery(
			     "SELECT category FROM ADD_TRANS where username='"+username+"' and year="+year+"  group by category order by category", null);
		String[] code = new String[cursor.getCount()]; 
		int j= 0;
		while(cursor.moveToNext()){
		    String category = cursor.getString(cursor.getColumnIndex("category"));
		    code[j] = category;
		    j++;
		}
		 Cursor cursor1;
		  
			int k=0;
		
				cursor1= loginDataBaseAdapter.db.rawQuery(
						"SELECT SUM(amount) FROM ADD_TRANS where username='"+username+"' and  year="+year+" GROUP BY category order by category", null);
		 double[] distribution = new double[cursor1.getCount()]; 
		while(cursor1.moveToNext()){
			    Float amnt = cursor1.getFloat(cursor1.getColumnIndex("SUM(amount)"));
		   // double total=(subcategory.doubleValue()/10000)*100;
		distribution[k] = amnt.doubleValue();
		
			    k++;
		}
		
		
		if(cursor1.getCount()<1 && cursor.getCount()<1 ) 
		{
			 Toast.makeText(ViewChart.this,"No Pie chart available for the requested year:",Toast.LENGTH_SHORT).show();
				    }
		   
		 
			
			// Pie Chart Section Value
		   
		
		
		// Color of each Pie Chart Sections
		int[] colors = { Color.BLUE, Color.MAGENTA, Color.GREEN, Color.CYAN, Color.RED,
						 Color.YELLOW };
		
		// Instantiating CategorySeries to plot Pie Chart    	
		CategorySeries distributionSeries = new CategorySeries(" Android version distribution as on October 1, 2012");
		for(int i=0;i<distribution.length;i++){
			// Adding a slice with its values and name to the Pie Chart
		distributionSeries.add(code[i], distribution[i]);
		
		}   
		
		// Instantiating a renderer for the Pie Chart
		DefaultRenderer defaultRenderer  = new DefaultRenderer();    	
		for(int i = 0 ;i<distribution.length;i++){    		
			SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();    	
			seriesRenderer.setColor(colors[i]);
			seriesRenderer.setDisplayChartValues(true);
			
			// Adding a renderer for a slice
			defaultRenderer.addSeriesRenderer(seriesRenderer);
			defaultRenderer.setApplyBackgroundColor(true);
			defaultRenderer.setBackgroundColor(Color.rgb(25,25,112));
			
		}
		
		defaultRenderer.setChartTitle("BudgetPlanner");
		defaultRenderer.setChartTitleTextSize(20);
		defaultRenderer.setZoomButtonsVisible(true);    	    		
			
		// Creating an intent to plot bar chart using dataset and multipleRenderer    	
		Intent intent = ChartFactory.getPieChartIntent(getBaseContext(), distributionSeries , defaultRenderer, "Expenses");    	
		
		// Start Activity
			startActivity(intent);
			
		}
		
		public void barChartyear(){ 
			
			String username=getIntent().getExtras().getString("username");
		int year=Integer.valueOf(spinner3.getSelectedItem().toString());
			Cursor cursor= loginDataBaseAdapter.db.rawQuery(
				     "SELECT category FROM ADD_TRANS where username='"+username+"' and year="+year+"  group by category order by category", null);
		String[] category= new String[cursor.getCount()]; 
		int j= 0;
		while(cursor.moveToNext()){
		    String category1 = cursor.getString(cursor.getColumnIndex("category"));
		        category[j] = category1;
		        
		       
		        j++;
		}
		
		Cursor cursor2= loginDataBaseAdapter.db.rawQuery(
			 "SELECT budget FROM ADD_BUDGET b,ADD_TRANS t where b.username='"+username+"' " +
		"and b.category=t.category and b.year="+year+" GROUP BY b.category order by b.category ", null);
			double[] income = new double[cursor2.getCount()]; 
		   int m= 0;
		   while(cursor2.moveToNext()){
		       
		       Float amnt = cursor2.getFloat(cursor2.getColumnIndex("budget"));
		
			    income[m] = amnt.doubleValue();
			  
		       m++;
		   }
			 Cursor cursor1;
			  
				int k=0;
			
					cursor1= loginDataBaseAdapter.db.rawQuery(
							"SELECT SUM(amount) FROM ADD_TRANS where username='"+username+"' and year="+year+" GROUP BY category order by category", null);
		 double[] expense = new double[cursor1.getCount()]; 
		while(cursor1.moveToNext()){
			    Float amnt = cursor1.getFloat(cursor1.getColumnIndex("SUM(amount)"));
		   // double total=(subcategory.doubleValue()/10000)*100;
			    expense[k] = amnt.doubleValue();
			   
			    k++;
		}
		
		
		if(cursor1.getCount()<1 && cursor.getCount()<1 && cursor2.getCount()<1 ) 
		{
			 Toast.makeText(ViewChart.this,"No Bar chart available for the requested year:",Toast.LENGTH_SHORT).show();
		}
		
		// Creating an  XYSeries for Income
		XYSeries incomeSeries = new XYSeries("Budget");
		// Creating an  XYSeries for Expense
		XYSeries expenseSeries = new XYSeries("Expense");
		// Adding data to Income and Expense Series
		    for(int i=0;i<expense.length;i++){
		    incomeSeries.add(i,income[i]);
		        expenseSeries.add(i,expense[i]);
		    }
		  
		
		    // Creating a dataset to hold each series
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		// Adding Income Series to the dataset
		dataset.addSeries(incomeSeries);
		// Adding Expense Series to dataset
		dataset.addSeries(expenseSeries);
		
		
		
		// Creating XYSeriesRenderer to customize incomeSeries
		XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
		
		incomeRenderer.setColor(Color.rgb(130, 130, 230));
		incomeRenderer.setFillPoints(true);
		incomeRenderer.setLineWidth(2);
		incomeRenderer.setDisplayChartValues(true);
		
		// Creating XYSeriesRenderer to customize expenseSeries
		XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
		expenseRenderer.setColor(Color.rgb(40, 49, 1));
		expenseRenderer.setFillPoints(true);
		expenseRenderer.setLineWidth(2);
		expenseRenderer.setDisplayChartValues(true);
		
		// Creating a XYMultipleSeriesRenderer to customize the whole chart
		XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
		multiRenderer.setApplyBackgroundColor(true);
		multiRenderer.setBackgroundColor(Color.rgb(25,25,112));
		multiRenderer.setXLabels(0);
		multiRenderer.setChartTitle("Income vs Expense Chart");
		multiRenderer.setXTitle("Year"+year);
		multiRenderer.setYTitle("Amount in Rupees");
		multiRenderer.setZoomButtonsVisible(true);
		for(int i=0; i<expense.length;i++){
		    multiRenderer.addXTextLabel(i, category[i]);
		}
		
		// Adding incomeRenderer and expenseRenderer to multipleRenderer
		// Note: The order of adding dataseries to dataset and renderers to multipleRenderer
		// should be same
		multiRenderer.addSeriesRenderer(incomeRenderer);
		multiRenderer.addSeriesRenderer(expenseRenderer);
		
		// Creating an intent to plot bar chart using dataset and multipleRenderer
		Intent intent = ChartFactory.getBarChartIntent(getBaseContext(), dataset, multiRenderer, Type.DEFAULT);
		
		// Start Activity
		    startActivity(intent);
		
			
		}
		
		public void barChartmonth(){ 
			
			
			String username=getIntent().getExtras().getString("username");
		int year=Integer.valueOf(spinner3.getSelectedItem().toString());
		String month=spinner2.getSelectedItem().toString();
		Cursor cursor= loginDataBaseAdapter.db.rawQuery(
			     "SELECT category FROM ADD_TRANS where username='"+username+"' and year="+year+" and month='"+month+"' group by category order by category ", null);
		String[] category= new String[cursor.getCount()]; 
		int j= 0;
		while(cursor.moveToNext()){
		    String category1 = cursor.getString(cursor.getColumnIndex("category"));
		    category[j] = category1;
		    j++;
		}
		
		Cursor cursor2= loginDataBaseAdapter.db.rawQuery(
			     "SELECT SUM(budget) FROM ADD_BUDGET b,ADD_TRANS t where b.username='"+username+"' and b.category=t.category and b.year="+year+" and b.month='"+month+"' GROUP BY b.category order by b.category ", null);
			double[] income = new double[cursor2.getCount()]; 
		   int m= 0;
		   while(cursor2.moveToNext()){
		       
		       Float amnt = cursor2.getFloat(cursor2.getColumnIndex("SUM(budget)"));
		   // double total=(subcategory.doubleValue()/10000)*100;
			    income[m] = amnt.doubleValue();
			    
		
		
		       m++;
		   }
			 Cursor cursor1;
			  
				int k=0;
			
					cursor1= loginDataBaseAdapter.db.rawQuery(
							"SELECT SUM(amount) FROM ADD_TRANS where username='"+username+"' and year="+year+" and month='"+month+"' GROUP BY category order by category", null);
		 double[] expense = new double[cursor1.getCount()]; 
		while(cursor1.moveToNext()){
			    Float amnt = cursor1.getFloat(cursor1.getColumnIndex("SUM(amount)"));
				 
				    expense[k] = amnt.doubleValue();
				 
				    k++;
		    }
		   
		    if(cursor1.getCount()<1 && cursor.getCount()<1 && cursor2.getCount()<1 ) 
		    {
		    	 Toast.makeText(ViewChart.this,"No Bar chart available for the requested year and month:",Toast.LENGTH_SHORT).show();
				    }
		    
		  
		
		    // Creating an  XYSeries for Income
		XYSeries incomeSeries = new XYSeries("Budget");
		// Creating an  XYSeries for Expense
		XYSeries expenseSeries = new XYSeries("Expense");
		// Adding data to Income and Expense Series
		    for(int i=0;i<expense.length;i++){
		    incomeSeries.add(i,income[i]);
		        expenseSeries.add(i,expense[i]);
		    }
		  
		
		    // Creating a dataset to hold each series
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		// Adding Income Series to the dataset
		dataset.addSeries(incomeSeries);
		// Adding Expense Series to dataset
		dataset.addSeries(expenseSeries);
		
		// Creating XYSeriesRenderer to customize incomeSeries
		XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
		incomeRenderer.setColor(Color.rgb(130, 130, 230));
		incomeRenderer.setFillPoints(true);
		incomeRenderer.setLineWidth(2);
		incomeRenderer.setDisplayChartValues(true);
		
		// Creating XYSeriesRenderer to customize expenseSeries
		XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
		expenseRenderer.setColor(Color.rgb(238, 205, 134));
		expenseRenderer.setFillPoints(true);
		expenseRenderer.setLineWidth(2);
		expenseRenderer.setDisplayChartValues(true);
		
		// Creating a XYMultipleSeriesRenderer to customize the whole chart
		XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
		multiRenderer.setXLabels(0);
		multiRenderer.setChartTitle("Budget vs Expense Chart");
		multiRenderer.setXTitle("Year"+year);
		multiRenderer.setYTitle("Amount in Rupees");
		multiRenderer.setZoomButtonsVisible(true);
		multiRenderer.setApplyBackgroundColor(true);
		multiRenderer.setBackgroundColor(Color.rgb(25,25,112));
		for(int i=0; i<expense.length;i++){
		    multiRenderer.addXTextLabel(i, category[i]);
		}
		
		// Adding incomeRenderer and expenseRenderer to multipleRenderer
		// Note: The order of adding dataseries to dataset and renderers to multipleRenderer
		// should be same
		multiRenderer.addSeriesRenderer(incomeRenderer);
		multiRenderer.addSeriesRenderer(expenseRenderer);
		
		// Creating an intent to plot bar chart using dataset and multipleRenderer
		Intent intent = ChartFactory.getBarChartIntent(getBaseContext(), dataset, multiRenderer, Type.DEFAULT);
		
		// Start Activity
		    startActivity(intent);
		
			
		}
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		    getMenuInflater().inflate(R.menu.main, menu);
		    return true;
		}
		
			
		}