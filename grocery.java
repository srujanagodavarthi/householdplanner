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

public class grocery extends Activity
{
	LoginDataBaseAdapter loginDataBaseAdapter;
	Spinner spinner1,spinner2;
	Button save;

	// Add button
	DatePicker datepicker;
	TimePicker timepicker;
	int year;
	int month;
	int day;
	private EditText edittext;
    private int NOTIFY_ME_ID;
	  private int count=0;
	  private NotificationManager mgr=null;
	
	
	// Declaring the String Array with the Text Data for the Spinners
	// Declaring the Integer Array with resource Id's of Images for the Spinners
	
	/*	String[] Items = { "Select items", "Grocery","vegetables","Stationary","Clothes"};
		Integer[] images = { 0, R.drawable.grocery,R.drawable.veg,R.drawable.stationary1,R.drawable.clothes};
		
		String[] SItems = { "Select items", "sugar","oil","cereals","snacks","salt"};
		Integer[] Simages = { 0, R.drawable.sugar,R.drawable.oil,R.drawable.cereals,R.drawable.snacks,R.drawable.salt};
	
		String[] VItems = { "Select items", "leafyVegetables","Vegetables","Fruits"};
		Integer[] Vimages = { 0, R.drawable.leafy,R.drawable.veg,R.drawable.fruits};
		
		String[] StItems = { "Select items", "Kids","business"};
		Integer[] Stimages = { 0, R.drawable.kidsstationary,R.drawable.stationary};
	
		String[] CItems = { "Select items", "Women","Men","Kids"};
		Integer[] Cimages = { 0, R.drawable.women,R.drawable.mens,R.drawable.kids};*/
	
		@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 	
		 setContentView(R.layout.save);
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title1);
		    TextView tv = (TextView) findViewById(R.id.textView9);	
		    tv.setText("Budget Planner");
		    Button help = (Button) findViewById(R.id.Help);
	        help.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					HelpDialog helpDialog = new HelpDialog(grocery.this);
					helpDialog.setTitle("Help ");
					helpDialog.show();           		
				}        
	        });
		
		 getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		 loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		 loginDataBaseAdapter=loginDataBaseAdapter.open();
		 String username=getIntent().getExtras().getString("username");
		 String list=getIntent().getExtras().getString("list");
	     datepicker=(DatePicker) findViewById(R.id.datePicker1);
	     timepicker=(TimePicker) findViewById(R.id.timePicker1);
         mgr=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);  
		    
		    // Toast.makeText(getApplicationContext(), "username"+username, Toast.LENGTH_LONG).show();
		    // Toast.makeText(getApplicationContext(), "list  "+list, Toast.LENGTH_LONG).show();
		 
	     // Get The Refference Of Buttons
         save=(Button)findViewById(R.id.save);
	    // Set OnClick Listener on SignUp button 
		  
		 save.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) 
			
			{
		
				String username=getIntent().getExtras().getString("username");
				String list=getIntent().getExtras().getString("list");
				 // Toast.makeText(getApplicationContext(), "list  "+list, Toast.LENGTH_LONG).show();
				int month=(datepicker.getMonth()+1);
				int date=datepicker.getDayOfMonth();
				int year=datepicker.getYear();
			    int hour=timepicker.getCurrentHour();
			    int minute=timepicker.getCurrentMinute();
			
				loginDataBaseAdapter.insertItem(username,list,month,date,year,hour,minute);
				Intent intent = new Intent(getApplicationContext(),Success.class); 
				intent.putExtra("username",username);
				//intent.putExtra("tobuy",cat2);
				//intent.putExtra("description",description);
				
			    startActivity(intent);
			    loginDataBaseAdapter.close();
				
				}
			});
		
		
							
					      
					
		    }
			public void note()
			{
				
				Notification note=new Notification(R.drawable.bp,"Status message!",System.currentTimeMillis());
			    PendingIntent i=PendingIntent.getActivity(this, 0,new Intent(this, HomeActivity.class),0);
			    note.setLatestEventInfo(this, "New Email","Unread Conversation", i);
			    note.number=++count;
			    note.vibrate=new long[] {500L, 200L, 200L, 500L};
			    note.flags|=Notification.FLAG_AUTO_CANCEL;	    
			   // startForeground(1987,note);
			    
			    mgr.notify(NOTIFY_ME_ID, note);
			    NOTIFY_ME_ID=++NOTIFY_ME_ID;
			}
			  public void notifyMe(View v) {
				 
				    Notification note=new Notification(R.drawable.purchase,"Status message!",System.currentTimeMillis());
				    PendingIntent i=PendingIntent.getActivity(this, 0,new Intent(this, HomeActivity.class),0);
				    note.setLatestEventInfo(this, "New Email","Unread Conversation", i);
				    note.number=++count;
				    note.vibrate=new long[] {500L, 200L, 200L, 500L};
				    note.flags|=Notification.FLAG_AUTO_CANCEL;	    
				   // startForeground(1987,note);
				   // mgr.notify(NOTIFY_ME_ID, note);

				    mgr.notify(NOTIFY_ME_ID, note);
				    NOTIFY_ME_ID=++NOTIFY_ME_ID;
				  }
				  
				  public void clearNotification(View v) {
				    mgr.cancel(NOTIFY_ME_ID);
				  }
		  
		   }


