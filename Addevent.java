package com.budget.budgetplanner;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.budget.budgetplanner.R;


import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;


public class Addevent extends Activity  {
	
	LoginDataBaseAdapter loginDataBaseAdapter;
	Button save,cancel;
	DatePicker datepicker;
	private Spinner spinner1;
	int year;
	int month;
	int day;
	private EditText edittext;
	private int NOTIFY_ME_ID;
	  private int count=0;
	  private NotificationManager mgr=null;

	public void onCreate(Bundle savedInstanceState) 
	{
	     super.onCreate(savedInstanceState);
	     requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 	
		    setContentView(R.layout.addevent);
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title1);
		    TextView tv = (TextView) findViewById(R.id.textView9);	
		    tv.setText("Budget Planner");
		    Button help = (Button) findViewById(R.id.Help);
	        help.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					HelpDialog helpDialog = new HelpDialog(Addevent.this);
					helpDialog.setTitle("Help ");
					helpDialog.show();           		
				}        
	        });
	   //  getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
	     loginDataBaseAdapter=new LoginDataBaseAdapter(this);
			loginDataBaseAdapter=loginDataBaseAdapter.open();
	     datepicker=(DatePicker)findViewById(R.id.datePicker1);
	     edittext = (EditText) findViewById(R.id.editText1);
	     
		    mgr=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	     addItemsOnSpinner1();
	     String username=getIntent().getExtras().getString("username");
	   //  Toast.makeText(getApplicationContext(), "username"+username, Toast.LENGTH_LONG).show();
	   /*  Toast.makeText(getApplicationContext(), "event"+spinner1.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
	     Toast.makeText(getApplicationContext(), "name"+edittext.getText().toString(), Toast.LENGTH_LONG).show();
	     Toast.makeText(getApplicationContext(), "month"+(datepicker.getMonth()+1), Toast.LENGTH_LONG).show();
	     Toast.makeText(getApplicationContext(), "date"+datepicker.getDayOfMonth(), Toast.LENGTH_LONG).show();
	     Toast.makeText(getApplicationContext(), "year"+datepicker.getYear(), Toast.LENGTH_LONG).show();*/
	     
		  
	     
	     // Get The Refference Of Buttons
	     save=(Button)findViewById(R.id.save);
           cancel=(Button)findViewById(R.id.cancel);
           
         
			
	    // Set OnClick Listener on SignUp button 
	 save.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) 
		
		{
			
			edittext.getText().toString();
			if(edittext.getText().toString().equals(""))
			{
				edittext.setError("Enter the Description");
				//Toast.makeText(AddBudget.this, "Enter all values", Toast.LENGTH_LONG).show();
				return;
				
			}
			String username=getIntent().getExtras().getString("username");
			String event=spinner1.getSelectedItem().toString();
			String name=edittext.getText().toString();
			int month=(datepicker.getMonth()+1);
			int date=datepicker.getDayOfMonth();
			int year=datepicker.getYear();
			
			
			loginDataBaseAdapter.insertEvent(username,event,name,month,date,year);
			
			
			Intent intent = new Intent(getApplicationContext(),Success.class); 
			intent.putExtra("username",username);
			
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
		 
		    Notification note=new Notification(R.drawable.bp,"Status message!",System.currentTimeMillis());
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
	public void addItemsOnSpinner1() {

		spinner1 = (Spinner) findViewById(R.id.spinner1);
		List<String> list = new ArrayList<String>();
		list.add("BirthDay");
		list.add("MarriageDay");
		list.add("Pay Fee");
		list.add("Pay Bill");
		list.add("Other");
		
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		spinner1.setAdapter(dataAdapter);
	
		//
	}
	
	public void Clear(View v)
	{
		Time t=new Time();
		t.setToNow();
		edittext=(EditText)findViewById(R.id.editText1);
		edittext.setText("");
		
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setSelection(0);
		//final Calendar c = Calendar.getInstance();
        datepicker.updateDate(t.year,t.month,t.monthDay);
      	//int month1 = c.get(Calendar.MONTH)+1;
      	//int day1 = c.get(Calendar.DAY_OF_MONTH);
      	
		
		
		
	}
	
	
	// 

}
