package com.budget.budgetplanner;

import com.budget.budgetplanner.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import android.graphics.Color;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.widget.ArrayAdapter;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class Success extends Activity
{
	
	
	LoginDataBaseAdapter loginDataBaseAdapter;
	
	
	Button addtransaction,addbudget,addincome,viewchart,addevent,csv,todo,calculator,grocery;
	private EditText edittext; 
	private int NOTIFY_ME_ID;
	  private int count=0;
	  private NotificationManager mgr=null;
	  private NotificationManager mgr1=null;
	  
	
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		final String username2=getIntent().getExtras().getString("username");
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 
		setContentView(R.layout.success);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title1);
		TextView tv2 = (TextView) findViewById(R.id.textView9);
         tv2.setText("Budget Planner");
         Button help = (Button) findViewById(R.id.Help);
	        help.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					HelpDialog helpDialog = new HelpDialog(Success.this);
					helpDialog.setTitle("Help ");
					helpDialog.show();           		
				}        
	        });
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		
		  mgr=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		  String query="SELECT month,date,year,event,name FROM ADD_Event where USERNAME='"+username2+"' ";
	         Cursor allrows =loginDataBaseAdapter.db.rawQuery(query, null);
	          System.out.println("COUNT : " + allrows.getCount());
	          final Calendar c = Calendar.getInstance();
	          	int year1 = c.get(Calendar.YEAR);
	          	int month1 = c.get(Calendar.MONTH)+1;
	          	int day1 = c.get(Calendar.DAY_OF_MONTH);
	      
	          	
	          if (allrows.moveToFirst()) {
	              do {
	            	 
	                   int month= allrows.getInt(0);
	                   int date=allrows.getInt(1);
	                   int year=allrows.getInt(2);
	                   String event=allrows.getString(3);
	                   String name=allrows.getString(4);
	                  
	                   	if(month==month1 && date==day1 && year==year1)
	                   	{
	                   		
	                   		Notification note=new Notification(R.drawable.reminder,"Notification message!"+event,System.currentTimeMillis());
	                	    PendingIntent i=PendingIntent.getActivity(this, 0,new Intent(this, HomeActivity.class),0);
	                	    note.setLatestEventInfo(this, "Remiander!!"+name,"   "+event, i);
	                	    note.number=++count;
	                	    note.vibrate=new long[] {500L, 200L, 200L, 500L};
	                	    note.flags|=Notification.FLAG_AUTO_CANCEL;	    
	                	   // startForeground(1987,note);
	                	    mgr.notify(NOTIFY_ME_ID, note);
	                	    NOTIFY_ME_ID=++NOTIFY_ME_ID;
	                   
	                   	}

	              } while (allrows.moveToNext());
	          }
	          allrows.close();
	          
	       
	  		  mgr1=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	  		final String username3=getIntent().getExtras().getString("username");
	  		  String qry="SELECT month,date,year,list,hours,minutes FROM ADD_ITEM where USERNAME='"+username3+"' ";
	  	         Cursor allrows1 =loginDataBaseAdapter.db.rawQuery(qry, null);
	  	          System.out.println("COUNT : " + allrows1.getCount());
	  	       
	  	          final Calendar c1 = Calendar.getInstance();
		  	  
		  	        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
		  	        
	  	          	int year = c1.get(Calendar.YEAR);
	  	          	int month = c1.get(Calendar.MONTH)+1;
	  	          	int day = c1.get(Calendar.DAY_OF_MONTH);
	  	            String time = dateFormat.format(c1.getTime());
	  	            int hour = Integer.parseInt(time);
	  	            int minute= c1.get(Calendar.MINUTE);
	  	            
	  	          //Toast.makeText(getApplicationContext(), "hour  "+hour, Toast.LENGTH_LONG).show();
	  	          //Toast.makeText(getApplicationContext(), "minute  "+minute, Toast.LENGTH_LONG).show();
	  	           
	  	      
	  	          	
	  	          if (allrows1.moveToFirst()) {
	  	              do {
	  	            	   
	  	                   int month2= allrows1.getInt(0);
	  	                   int date2=allrows1.getInt(1);
	  	                   int year2=allrows1.getInt(2);
	  	                   String tobuy=allrows1.getString(3);
	  	                   int hour1=allrows1.getInt(4);
	  	                   int minute1=allrows1.getInt(5);
	  	                 
	  	                   	if(month2==month && date2==day && year2==year&&hour==hour1&&minute==minute1)
	  	                   	{ 
	  	                   	// Toast.makeText(getApplicationContext(), "hour1 "+hour1, Toast.LENGTH_LONG).show();
		  		  	          //Toast.makeText(getApplicationContext(), "minute1 "+minute1, Toast.LENGTH_LONG).show();
							//int image = getApplicationContext().getResources().getIdentifier(, "drawable", getApplicationContext().getPackageName());
	  
	  	                   		Notification note=new Notification(R.drawable.purchase,"Buy!!"+tobuy,System.currentTimeMillis());
	  	                	    PendingIntent i=PendingIntent.getActivity(this, 0,new Intent(this, HomeActivity.class),0);
	  	                	   String pattern = System.getProperty("line.separator") + " ";
	  	                	    String y = tobuy.replaceAll(",", pattern);
	  	                	    note.setLatestEventInfo(this, "Buy  ","   "+y, i);
	  	                	    
	  	                	    note.number=++count;
	  	                	    note.vibrate=new long[] {500L, 200L, 200L, 500L};
	  	                	    note.flags|=Notification.FLAG_AUTO_CANCEL;	    
	  	                	   // startForeground(1987,note);
	  	                	    mgr1.notify(NOTIFY_ME_ID, note);
	  	                	    NOTIFY_ME_ID=++NOTIFY_ME_ID;	
	  	                   	 
	  	                     /*	if(tobuy.equals("Vegetables"))
	  	                   	  {
	  	                   		Notification note=new Notification(R.drawable.veg,"Buy!!"+description,System.currentTimeMillis());
	  	                	    PendingIntent i=PendingIntent.getActivity(this, 0,new Intent(this, HomeActivity.class),0);
	  	                	    note.setLatestEventInfo(this, "Today buy  "+description,"   "+tobuy, i);
	  	                	    note.number=++count;
	  	                	    note.vibrate=new long[] {500L, 200L, 200L, 500L};
	  	                	    note.flags|=Notification.FLAG_AUTO_CANCEL;	    
	  	                	   // startForeground(1987,note);
	  	                	    mgr1.notify(NOTIFY_ME_ID, note);
	  	                	    NOTIFY_ME_ID=++NOTIFY_ME_ID;	
	  	                   	  }
		  	              	if(tobuy.equals("Stationary"))
		                   	  {
		                   		Notification note=new Notification(R.drawable.stationary,"Buy!!"+description,System.currentTimeMillis());
		                	    PendingIntent i=PendingIntent.getActivity(this, 0,new Intent(this, HomeActivity.class),0);
		                	    note.setLatestEventInfo(this, "Today buy  "+description,"   "+tobuy, i);
		                	    note.number=++count;
		                	    note.vibrate=new long[] {500L, 200L, 200L, 500L};
		                	    note.flags|=Notification.FLAG_AUTO_CANCEL;	    
		                	   // startForeground(1987,note);
		                	    mgr1.notify(NOTIFY_ME_ID, note);
		                	    NOTIFY_ME_ID=++NOTIFY_ME_ID;	
		                   	  }
			  	          	if(tobuy.equals("Clothes"))
		                   	  {
		                   		Notification note=new Notification(R.drawable.clothes,"Buy!!"+description,System.currentTimeMillis());
		                	    PendingIntent i=PendingIntent.getActivity(this, 0,new Intent(this, HomeActivity.class),0);
		                	    note.setLatestEventInfo(this, "Today buy  "+description,"   "+tobuy, i);
		                	    note.number=++count;
		                	    note.vibrate=new long[] {500L, 200L, 200L, 500L};
		                	    note.flags|=Notification.FLAG_AUTO_CANCEL;	    
		                	   // startForeground(1987,note);
		                	    mgr1.notify(NOTIFY_ME_ID, note);
		                	    NOTIFY_ME_ID=++NOTIFY_ME_ID;	
		                   	  }
			  	                   	*/
	  	                   
	  	                   	}

	  	              } while (allrows1.moveToNext());
	  	          }
	  	          allrows1.close();
	  	          
	  	          
	    String username4=getIntent().getExtras().getString("username");
		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText("Welcome  "+username4);
		
		
		 addtransaction=(Button)findViewById(R.id.addtransaction);
	     addbudget=(Button)findViewById(R.id.addbudget);
	     addincome=(Button)findViewById(R.id.addincome);
	     viewchart=(Button)findViewById(R.id.viewchart);
	     addevent=(Button)findViewById(R.id.addevent);
	     csv=(Button)findViewById(R.id.csv);
	     todo=(Button)findViewById(R.id.todo);
	     calculator=(Button)findViewById(R.id.loan);
	     grocery=(Button)findViewById(R.id.grocery);
	     
	     
	     
	        final String category="Education";
			final String category1="Health";
		    final String category2="Food";
		    final String category3="Utilities";
		    final String category4="Entertainment";
		    final String category5="Loans";
		   final String category6="Other";
		
		  final String username="default";
		  
		  
		  //sub categories insertion method
		  
		  /*education sub catogories*/
		  final String subcategory1="Schoolfee";
		  final String subcategory2="School Uniform";
		  final String subcategory3="Sports,Music/Dance";
		  final String subcategory4="Excursion";
		  final String subcategory5="Other Education";
		
		  
		  /*Health sub catogories*/
		  
		  final String insurance="Health Insurance";
		  final String checkup="Health Checkups";
		  final String medicines="Medicines";
		  final String verternity="Veternity";
		  final String other="Other Health";

		  
		  /*Food sub catogories*/
		  final String Hotels="Hotels";
		  final String other1="Other Food";
		  
		 
		  /*Utilities sub catogories*/
		  final String electricty="electricty";
		  final String gas="Gas";
		  final String tv1="TV";
		  final String net="Net";
		  final String other2="Other Utilities";
		  
		 
		  /*Entertainment sub catogories*/
		  
		  final String movies="Movies";
		  final String vacation="Vacation";
		  final String other3="Other entertainment";
		  
		  
		  
		  /*Loans sub catogories*/
		  final String personal="Personal";
		  final String home="Home";
		  final String other4="Other Loans";
		  
		  /*----------------------Add colors-------------------*/
		/*  final int color1=Color.BLACK;
		  final int color2=Color.BLUE;
		  final int color3=Color.CYAN;
		  final int color4=Color.DKGRAY;
		  final int color5=Color.GREEN;
		  final int color6=Color.GRAY;
		  final int color7=Color.MAGENTA;
		  final int color8=Color.RED;
		  final int color9=Color.YELLOW;
		  final int color10=Color.TRANSPARENT;
		  final int color11=Color.LTGRAY;
		  final int color12=Color.WHITE;
		  final int color13=Color.BLACK;
		  final int color14=Color.BLUE;
		
		  */
		  
		     
		 String query1="SELECT * FROM ADD_CATEGORY,ADD_SUBCATPRE";
	         Cursor cursor =loginDataBaseAdapter.db.rawQuery(query1, null);
		     
			 if(cursor.getCount()<1) 
		    
			 { 
                 loginDataBaseAdapter.insertsubCategoryEdu(subcategory1,category,subcategory2,subcategory3,subcategory4,subcategory5);
		 
				 loginDataBaseAdapter.insertsubHealth(insurance,category1,checkup,medicines, verternity,other);
				 
				 loginDataBaseAdapter.insertsubCategoryfood(Hotels,category2,other1);
				 
				 loginDataBaseAdapter.insertsubUtil(electricty,category3,gas,tv1,net,other2);
				 
				 loginDataBaseAdapter.insertsubEnter(movies,category4,vacation,other3);
				 
				 loginDataBaseAdapter.insertsubLoan(personal,category5,home,other4);
		  
				 loginDataBaseAdapter.insertCategory(category,category1,category2,category3,category4,category5,category6,username);
					    	
				 //loginDataBaseAdapter.insertColor(color1,color2,color3,color4,color5,color6,color7,color8,color9,color10,color11,color12,color13,color14);
		    }
			 cursor.close();
			  
			  
			  addbudget.setOnClickListener(new View.OnClickListener() {
			 		public void onClick(View v) {
			 			// TODO Auto-generated method stub
			 			//Toast.makeText(getApplicationContext(), "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
			 			
			 			final String username=getIntent().getExtras().getString("username");
			 			
			 			 Cursor cursor1;
			 			loginDataBaseAdapter=new LoginDataBaseAdapter(Success.this);
			 			loginDataBaseAdapter=loginDataBaseAdapter.open();
			 			
			 		   cursor1= loginDataBaseAdapter.db.rawQuery("select homepay,partner_homepay,bonus,savings,other from ADD_INCOME where " +
			 		   	"username='"+username+"'", null);
			 		    					 
					    if(cursor1.getCount()>=1) 
				        {
					    	Intent addbudget=new Intent(getApplicationContext(),AddBudget.class);
				 			addbudget.putExtra("username",username);
				 			startActivity(addbudget);
				        }
					    
					    else
					    {
					    	final Dialog dialog = new Dialog(Success.this);
			            	dialog.setContentView(R.layout.incomewarning);
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
			 		});
				
		
	     
	            addincome.setOnClickListener(new View.OnClickListener() {
		 		public void onClick(View v) {
		 			final String username=getIntent().getExtras().getString("username");
		 		
			 			Intent addincome=new Intent(getApplicationContext(),AddIncome.class);
			 			addincome.putExtra("username",username);
			 			startActivity(addincome);
		 			
		 			
		 		}
		 		});
	           todo.setOnClickListener(new View.OnClickListener() {
		 		public void onClick(View v) {
		 			final String username=getIntent().getExtras().getString("username");
		 		
			 			Intent addincome=new Intent(getApplicationContext(),ToDo.class);
			 			addincome.putExtra("username",username);
			 			startActivity(addincome);
		 			
		 			
		 		}
		 		});
	     
	     addtransaction.setOnClickListener(new View.OnClickListener() {
		 		public void onClick(View v) {
		 			// TODO Auto-generated method stub
		 			final String username=getIntent().getExtras().getString("username");
		 			
		 			/// Create Intent for SignUpActivity  and Start The Activity
		 			Intent addtransaction=new Intent(getApplicationContext(),AddTransaction.class);
		 			addtransaction.putExtra("username",username);
		 			startActivity(addtransaction);
		 			}
		 		});

	    viewchart.setOnClickListener(new View.OnClickListener() {
		 		public void onClick(View v) {
		 			final String username=getIntent().getExtras().getString("username");
		 		
			 			Intent viewchart=new Intent(getApplicationContext(),ViewChart.class);
			 			viewchart.putExtra("username",username);
			 			startActivity(viewchart);
		 			
		 			
		 		}
		 		});
	    addevent.setOnClickListener(new View.OnClickListener() {
	 		public void onClick(View v) {
	 			final String username=getIntent().getExtras().getString("username");
	 		
		 			Intent event=new Intent(getApplicationContext(),Addevent.class);
		 			event.putExtra("username",username);
		 			startActivity(event);
	 			
	 			
	 		}
	 		});
	    csv.setOnClickListener(new View.OnClickListener() {
	 		public void onClick(View v) {
	 		//	Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_LONG).show();
	 			 File dbFile=getDatabasePath(LoginDataBaseAdapter.DATABASE_NAME);
	 	       // Databasehelper dbhelper = new Databasehelper(getApplicationContext());
	 	         File exportDir = new File(Environment.getExternalStorageDirectory(), "");      
	 	       
	 	        if (!exportDir.exists()) 
	 	        {
	 	            exportDir.mkdirs();
	 	        }
	 	       
                 String csv="csvname.csv";
	 	        File file = new File(exportDir, csv);
	 	       String savedFile1 = Environment.getExternalStorageDirectory() + getFileStreamPath(csv).toString();
	 	       Toast.makeText(Success.this, "Log is saved to " + savedFile1, Toast.LENGTH_LONG).show();
	 	        try 
	 	        {
	 	            file.createNewFile();                
	 	            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
	 	         //  Toast.makeText(getApplicationContext(), "Query", Toast.LENGTH_LONG).show();
	 	            Cursor curCSV = loginDataBaseAdapter.db.rawQuery("SELECT * FROM ADD_TRANS",null);
	 	            csvWrite.writeNext(curCSV.getColumnNames());
	 	            while(curCSV.moveToNext())
	 	            {
	 	               //Which column you want to exprort
	 	                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2),
	 	                		curCSV.getString(3),curCSV.getString(4),curCSV.getString(5),curCSV.getString(6)};
	 	                csvWrite.writeNext(arrStr);
	 	            }
	 	        //   Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
	 	            csvWrite.close();
	 	            curCSV.close();
	 	        }
	 	        catch(Exception sqlEx)
	 	        {
	 	            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
	 	        }
	 	       try 
               {
                   File sd = Environment.getExternalStorageDirectory();
                   File data = Environment.getDataDirectory();

                   if (sd.canWrite()) 
                   {
                     //  String currentDBPath = "\\data\\sharath.android.trail\\databases\\griet1";
                       String backupDBPath = "\\system\\etc\\sdcard_db";
                     //  File currentDB = new File(data, currentDBPath);
                       File backupDB = new File(sd, backupDBPath);

                       if (dbFile.exists()) {
                           @SuppressWarnings("resource")
						FileChannel src = new FileInputStream(dbFile).getChannel();
                           @SuppressWarnings("resource")
						FileChannel dst = new FileOutputStream(backupDB).getChannel();
                           dst.transferFrom(src, 0, src.size());
                           src.close();
                           dst.close();
                       }
                       boolean bool=true;
                       if(bool == true)
                       {
                           //Toast.makeText(Success.this, "Backup Complete", Toast.LENGTH_SHORT).show();
                           bool = false;
                       }
                   }               
               } 
               catch (Exception e) {
                   Log.w("Settings Backup", e);
               }
           
	 	    
	 		
	 		}
	    });
	    calculator.setOnClickListener(new View.OnClickListener() {
	 		public void onClick(View v) {
	 		//	final String username=getIntent().getExtras().getString("username");
	 		
		 			Intent loan=new Intent(getApplicationContext(),calculator.class);
		 			loan.putExtra("username",username);
		 			startActivity(loan);
	 			
	 			
	 		}
	 		});
	    
	    grocery.setOnClickListener(new View.OnClickListener() {
	 		public void onClick(View v) {
	 		       final String username=getIntent().getExtras().getString("username");
		 			Intent intent=new Intent(getApplicationContext(),Groceryremainder.class);
		 			intent.putExtra("username",username);
		 			startActivity(intent);
	 			
	 			
	 		}
	 		});
	    loginDataBaseAdapter.close();    
	     
	}
	
	
	

}
