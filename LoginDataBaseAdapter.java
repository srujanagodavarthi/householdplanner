package com.budget.budgetplanner;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class LoginDataBaseAdapter 
{
		static final String DATABASE_NAME = "login.db";
		static final int DATABASE_VERSION = 1;
		public static final int NAME_COLUMN = 1;
		
		// TODO: Create public field for each column in your table.
		// SQL Statement to create a new database.
		static final String DATABASE_CREATE = "create table "+"LOGIN"+
		                             "(USERNAME  text primary key,PASSWORD text); ";
		
		static final String CREATE_INCOME = "create table "+"ADD_INCOME"+
		                                   "(username text,homepay float,partner_homepay float,bonus float,savings float,other float,"+
				                             "total float,foreign key(username) references LOGIN(USERNAME));";
		
		static final String Category_CREATE = "create table "+"ADD_CATEGORY"+"(category  text primary key,username text); ";
		static final String addcat="ADD_CATEGORY";
		
		static final String CREATE_BUDGET = "create table "+"ADD_BUDGET"+
                "(username text,category text,budget float,month text,year integer,warning integer,"+
				"foreign key(username) references LOGIN(USERNAME));";
		
		static final String CREATE_SUBCAT = "create table "+"ADD_SUBCAT"+
                "(USERNAME  text ,subcat text primary key,category text,foreign key(username) references LOGIN(USERNAME),foreign key(category) references ADD_CATEGORY(category)); ";
		// Variable to hold the database instance
		static final String CREATE_SUBCAT1 = "create table "+"ADD_SUBCATPRE"+ "(subcat text primary key ,category text); ";
		
		static final String CREATE_Trans = "create table "+"ADD_TRANS"+
                "(username text,category text,subcat text,amount float,month text,year integer,date integer,"+
				"foreign key(username) references LOGIN(USERNAME));";
		static final String CREATE_Colors="create table ADD_Colors(color integer)";
		static final String CREATE_Event = "create table "+"ADD_Event"+
                "(username text,event text,name text,month integer,date integer,year integer,"+
				"foreign key(username) references LOGIN(USERNAME));";
		static final String CREATE_TASK = "CREATE TABLE task_data (_id integer primary key autoincrement,task text not null,description text not null );";
		/*static final String CREATE_TRANS = "create table "+"ADD_TRANSACTION"+
               "(username text,category text primary key,subcat text,amount float,year integer,date integer,month text"+
				"foreign key(username) references LOGIN(USERNAME));";*/
		
		static final String Item = "create table ADD_ITEM"+
                "(username text,list text,month integer,date integer,year integer,hours integer,minutes integer,foreign key(username) references LOGIN(USERNAME));";
		
		public  SQLiteDatabase db;
		// Context of the application using the database.
		private final Context context;
		// Database open/upgrade helper
		private DataBaseHelper dbHelper;
		public  LoginDataBaseAdapter(Context _context) 
		{
			context = _context;
			dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public  LoginDataBaseAdapter open() throws SQLException 
		{
			db = dbHelper.getWritableDatabase();
			return this;
		}
		public void close() 
		{
			db.close();
		}

		public  SQLiteDatabase getDatabaseInstance()
		{
			return db;
		}

		public void insertEntry(String userName,String password)
		{
	       ContentValues newValues = new ContentValues();
			// Assign values for each row.
			newValues.put("USERNAME", userName);
			newValues.put("PASSWORD",password);
			 String where="USERNAME=?";
			 Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
			 if(cursor.getCount()==1) 
			 {
				 Toast.makeText(context, "Username already exists", Toast.LENGTH_LONG).show();
			 }
			 else
			 {
			    db.insert("LOGIN", null, newValues);
			///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
			 }
		}
		public void insertCategory(String category,String category1,String category2,String category3,String category4,String category5,String category6,String username)
		{
			 ContentValues newValues = new ContentValues();
				// Assign values for each row.
		        newValues.put("category",category);
		        newValues.put("username",username);
		        db.insert("ADD_CATEGORY", null, newValues);
		        newValues.put("username",username);
		        newValues.put("category",category1);
		        db.insert("ADD_CATEGORY", null, newValues);
		        newValues.put("category",category2);
		        newValues.put("username",username);
		        db.insert("ADD_CATEGORY", null, newValues);
		        newValues.put("category",category3);
		        newValues.put("username",username);
		        db.insert("ADD_CATEGORY", null, newValues);
		        newValues.put("category",category4);
		        newValues.put("username",username);
		        db.insert("ADD_CATEGORY", null, newValues);
		        newValues.put("category",category5);
		        newValues.put("username",username);
		        db.insert("ADD_CATEGORY", null, newValues);
		        newValues.put("category",category6);
		        newValues.put("username",username);
		  
		        db.insert("ADD_CATEGORY", null, newValues);
			     //Toast.makeText(context, "database", Toast.LENGTH_LONG).show();
		}
		
		
		public void insertcat(String category,String username)
		{
			 ContentValues newValues = new ContentValues();
				// Assign values for each row.
		        newValues.put("category",category);
		        newValues.put("username",username);
		        db.insert("ADD_CATEGORY", null, newValues);
		}
		/*******************************************insert Trascation values********************************/
		public void insertTrans(String username,String category,String subcat,Float amount,int month ,int date,int year)
		{
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
			 ContentValues newValues = new ContentValues();
				// Assign values for each row.
		        newValues.put("username",username);
		        newValues.put("category",category);
		        newValues.put("subcat",subcat);  
		        newValues.put("amount",amount);
		        newValues.put("month",month1);
		        newValues.put("year",year);
		        newValues.put("date",date);
			       
		        
		        
		        
		        db.insert("ADD_TRANS", null, newValues);
			
			
	
		
		}
		
		
		/****************************	sub catogories insertion******************/
	
		public void insertsubCategoryEdu(String subcategory1,String category,String subcategory2,String subcategory3,String subcategory4,String subcategory5)
		{
			 ContentValues newValues = new ContentValues();
				// Assign values for each row.
		        newValues.put("subcat",subcategory1);
		        newValues.put("category",category);
		        
		        db.insert("ADD_SUBCATPRE", null, newValues);
		        newValues.put("subcat",subcategory2);
		        newValues.put("category",category);
		        
		        db.insert("ADD_SUBCATPRE", null, newValues);
		        newValues.put("subcat",subcategory3);
		        newValues.put("category",category);
		        
		        db.insert("ADD_SUBCATPRE", null, newValues);
		        newValues.put("subcat",subcategory4);
		        newValues.put("category",category);
		        
		        db.insert("ADD_SUBCATPRE", null, newValues);
		        newValues.put("subcat",subcategory5);
		        newValues.put("category",category);
		        
		        db.insert("ADD_SUBCATPRE", null, newValues);
		     
			 //   Toast.makeText(context, "database", Toast.LENGTH_LONG).show();
		}
		
		public void insertsubHealth(String insurance,String category1,String checkup,String medicines,String verternity,String other)
		{
			 ContentValues newValues = new ContentValues();
				// Assign values for each row.
		        newValues.put("subcat",insurance);
		        newValues.put("category",category1);
		        
		        db.insert("ADD_SUBCATPRE", null, newValues);
		        newValues.put("subcat",checkup);
		        newValues.put("category",category1);
		        
		        db.insert("ADD_SUBCATPRE", null, newValues);
		        newValues.put("subcat", medicines);
		        newValues.put("category",category1);
		        
		        db.insert("ADD_SUBCATPRE", null, newValues);
		        newValues.put("subcat",verternity);
		        newValues.put("category",category1);
		        
		        db.insert("ADD_SUBCATPRE", null, newValues);
		        newValues.put("subcat",other);
		        newValues.put("category",category1);
		        
		        db.insert("ADD_SUBCATPRE", null, newValues);
		     
			   //  Toast.makeText(context, "database", Toast.LENGTH_LONG).show();
		}
		
		public void insertsubCategoryfood(String Hotels,String category2,String other1)
		{
			 ContentValues newValues = new ContentValues();
				// Assign values for each row.
		        newValues.put("subcat",Hotels);
		        newValues.put("category",category2);
		        
		        db.insert("ADD_SUBCATPRE", null, newValues);
		        newValues.put("subcat",other1);
		        newValues.put("category",category2);
		        
		        db.insert("ADD_SUBCATPRE", null, newValues);
		}
		public void insertsubEnter(String movies,String category4,String vacation,String other3)
		{
			 ContentValues newValues = new ContentValues();
				// Assign values for each row.
		        newValues.put("subcat",movies);
		        newValues.put("category",category4);
		        
		        db.insert("ADD_SUBCATPRE", null, newValues);
		        newValues.put("subcat",vacation);
		        newValues.put("category",category4);
		        
		        db.insert("ADD_SUBCATPRE", null, newValues);
		        
		        newValues.put("subcat",other3);
		        newValues.put("category",category4);
		        
		        db.insert("ADD_SUBCATPRE", null, newValues);
		}
		
		public void insertsubLoan(String personal,String category5,String home,String other4)
		{
			 ContentValues newValues = new ContentValues();
				// Assign values for each row.
		        newValues.put("subcat",personal);
		        newValues.put("category",category5);
		        
		        db.insert("ADD_SUBCATPRE", null, newValues);
		        newValues.put("subcat",home);
		        newValues.put("category",category5);
		        
		        db.insert("ADD_SUBCATPRE", null, newValues);
		        
		        newValues.put("subcat",other4);
		        newValues.put("category",category5);
		        
		        db.insert("ADD_SUBCATPRE", null, newValues);
		}
		public void insertsubUtil(String electricty,String category3,String gas,String tv1,String net,String other2)
		{
			 ContentValues newValues = new ContentValues();
				// Assign values for each row.
		        newValues.put("subcat",electricty);
		        newValues.put("category",category3);  
		        db.insert("ADD_SUBCATPRE", null, newValues);
		        
		        newValues.put("subcat", gas);
		        newValues.put("category",category3);
		         db.insert("ADD_SUBCATPRE", null, newValues);
		         
		         newValues.put("subcat",tv1);
			     newValues.put("category",category3);  
			     db.insert("ADD_SUBCATPRE", null, newValues);
			     
			     newValues.put("subcat",net);
			     newValues.put("category",category3);  
			     db.insert("ADD_SUBCATPRE", null, newValues);
			     
			     newValues.put("subcat",other2);
			     newValues.put("category",category3);  
			     db.insert("ADD_SUBCATPRE", null, newValues);
		}
		
/*******************************************end of predefine sub cat insertion*****************************/		
		public void insertsubcat(String username,String category,String sub1,String sub2,String sub3)
		{
			 ContentValues newValues = new ContentValues();
			   
				// Assign values for each row.
			    newValues.put("username",username);
			    newValues.put("category",category);
		        newValues.put("subcat",sub1);
		        db.insert("ADD_SUBCAT", null, newValues);
		        newValues.put("username",username);
			    newValues.put("category",category);
		        newValues.put("subcat", sub2);
		        db.insert("ADD_SUBCAT", null, newValues);
		        newValues.put("username",username);
			    newValues.put("category",category);
		        newValues.put("subcat",sub3);
		        db.insert("ADD_SUBCAT", null, newValues);
		       
			     //Toast.makeText(context, "database", Toast.LENGTH_LONG).show();
		}
		public void insertLabel(String newcategory){
	      
	         
	        ContentValues values = new ContentValues();
	        values.put("category", newcategory);
	         
				 db.insert("ADD_CATEGORY", null, values);
			///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
			
	          
	        // Inserting Row
	       
	        //db.close(); // Closing database connection
	    }
        
		
			
	
		public void insertIncome(String username,Float homepay,Float partner_homepay,Float bonus,Float savings,Float other,Float total)
		{
			
			 
			       ContentValues newValues = new ContentValues();
					// Assign values for each row.
			        newValues.put("username",username);
					newValues.put("homepay",homepay);
					newValues.put("partner_homepay",partner_homepay);
					newValues.put("bonus",bonus);
					newValues.put("savings",savings);
					newValues.put("other",other);
					newValues.put("total",total);
					
					
					// Insert the row into your table
					db.insert("ADD_INCOME", null, newValues);
			  
			///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
		}
		
		
		public void insertbudget(String username,String category,Float budget,String month,int year,int warning)
		{
			
			 
			       ContentValues newValues = new ContentValues();
					// Assign values for each row.
			        newValues.put("username",username);
					newValues.put("category",category);
					newValues.put("budget",budget);
					newValues.put("month",month);
					newValues.put("year",year);
					newValues.put("warning",warning);
					
					
					
					// Insert the row into your table
					db.insert("ADD_BUDGET", null, newValues);
			  
			///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
		}
		public void insertEvent(String username,String event,String name,int month,int date,int year)
		{
			 ContentValues newValues = new ContentValues();
				// Assign values for each row.
		        newValues.put("username",username);
		        newValues.put("event",event);
		        newValues.put("name",name);
		        newValues.put("month",month);  
		        newValues.put("date",date);  
		        newValues.put("year",year);
		        db.insert("ADD_Event", null, newValues);
			
			
		}
        
		/**************************************Insert Groceries***********************************/
		
		public void insertItem(String username,String list,int month,int date,int year,int hour,int minute)
		{
			 ContentValues newValues = new ContentValues();
				// Assign values for each row.
		        newValues.put("username",username);
		        newValues.put("list",list);
		        newValues.put("month",month);  
		        newValues.put("date",date);  
		        newValues.put("year",year);
		        newValues.put("hours",hour);  
		        newValues.put("minutes",minute); 
		        db.insert("ADD_ITEM", null, newValues);
			
			
		}

		
		/*---------------------INSERT COLOR-------------------------------*/
		
		public void insertColor(int color1,int color2,int color3,int color4,int color5,int color6,int color7,int color8,int color9,int color10,int color11,int color12,int color13,int color14)
		
		{
			  ContentValues newValues = new ContentValues();
				// Assign values for each row.
		        newValues.put("color",color1);
		        db.insert("ADD_Colors", null, newValues);
		        newValues.put("color",color2);
		        db.insert("ADD_Colors", null, newValues);
		        newValues.put("color",color3);
		        db.insert("ADD_Colors", null, newValues);
		        newValues.put("color",color4);
		        db.insert("ADD_Colors", null, newValues);
		        newValues.put("color",color5);
		        db.insert("ADD_Colors", null, newValues);
		        newValues.put("color",color6);
		        db.insert("ADD_Colors", null, newValues);
		        newValues.put("color",color7);
		        db.insert("ADD_Colors", null, newValues);
		        newValues.put("color",color8);
		        db.insert("ADD_Colors", null, newValues);
		        newValues.put("color",color9);
		        db.insert("ADD_Colors", null, newValues);
		        newValues.put("color",color10);
		        db.insert("ADD_Colors", null, newValues);
		        newValues.put("color",color11);
		        db.insert("ADD_Colors", null, newValues);
		        newValues.put("color",color12);
		        db.insert("ADD_Colors", null, newValues);
		        newValues.put("color",color13);
		        db.insert("ADD_Colors", null, newValues);
		        newValues.put("color",color14);
		        db.insert("ADD_Colors", null, newValues);
		        
		}
		
		
		public int deleteEntry(String UserName)
		{
			//String id=String.valueOf(ID);
		    String where="USERNAME=?";
		    int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
	       // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
	        return numberOFEntriesDeleted;
		}	
		public String getSinlgeEntry(String userName)
		{
			Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
	        if(cursor.getCount()<1) // UserName Not Exist
	        {
	        	cursor.close();
	        	return "NOT EXIST";
	        }
		    cursor.moveToFirst();
			String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
			cursor.close();
			return password;				
		}
		
		
		/*---------------------------------UPDATE FUNCTIONS-----------*/
		public void  updateEntry(String userName,String password)
		{
			// Define the updated row content.
			ContentValues updatedValues = new ContentValues();
			// Assign values for each row.
			updatedValues.put("USERNAME", userName);
			updatedValues.put("PASSWORD",password);
			
	        String where="USERNAME = ?";
		    db.update("LOGIN",updatedValues, where, new String[]{userName});			   
		}
		
		public void updateIncome(String username,Float homepay,Float partner_homepay,Float bonus,Float savings,Float other,Float total)
		{
			
			 
			       ContentValues newValues = new ContentValues();
					// Assign values for each row.
			        
					newValues.put("homepay",homepay);
					newValues.put("partner_homepay",partner_homepay);
					newValues.put("bonus",bonus);
					newValues.put("savings",savings);
					newValues.put("other",other);
					newValues.put("total",total);
					String username1="username=?";
					
					// Insert the row into your table
					db.update("ADD_INCOME",newValues,username1,new String[]{username});
					
				//	db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",new String[] { String.valueOf(contact.getID()) });
			  
			
		}
	   
		
		
		/**********************************TODO TASK FUNCTIONS***********************************************************/
		
		
		private static final String DATABASE_TABLE = "task_data";
		public static final String KEY_ROW_ID = "_id";
		public static final String KEY_TASK = "task";
		public static final String KEY_DESCRIPTION = "description";
		
		public long createTask(String task,String desciption)
		{
			ContentValues initialValues = new ContentValues();
			initialValues.put(KEY_TASK, task);
			initialValues.put(KEY_DESCRIPTION, desciption);
			return db.insert(DATABASE_TABLE, null, initialValues);
		}
		
		public boolean deleteTask(long id)
		{
			return db.delete(DATABASE_TABLE, KEY_ROW_ID + " = " + id, null) > 0;
		}
		
		public boolean updateTask(long id,String task,String desciption)
		{
			ContentValues initialValues = new ContentValues();
			initialValues.put(KEY_TASK, task);
			initialValues.put(KEY_DESCRIPTION, desciption);
			return db.update(DATABASE_TABLE, initialValues, KEY_ROW_ID + " = " + id, null) > 0;
		}
		
		public Cursor fetchAllTasks()
		{
			return db.query(DATABASE_TABLE, new String[]{KEY_ROW_ID,KEY_TASK,KEY_DESCRIPTION}, null, null, null, null, null);
		}
		
		public Cursor fetchTask(long id)
		{
			Cursor c = db.query(DATABASE_TABLE, new String[]{KEY_ROW_ID,KEY_TASK,KEY_DESCRIPTION}, KEY_ROW_ID + " = " + id, null, null, null, null);
			if(c != null)
			{
				c.moveToFirst();
			}
			return c;
		}
		
		
}

