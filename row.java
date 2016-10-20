package com.budget.budgetplanner;




import com.budget.budgetplanner.R;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;


import android.app.ListActivity;
import android.content.Intent;



public class row extends Activity
{
	protected void onCreate(Bundle savedInstanceState)
	{
		LoginDataBaseAdapter mDbAdapter;
		setContentView(R.layout.row);
		Button row;
		 row=(Button)findViewById(R.id.row);
	
		// TODO Auto-generated method stub
		 row.setOnClickListener(new View.OnClickListener() {
		 		public void onClick(View v) {
		 			
		 		
			 			Intent event=new Intent(getApplicationContext(),NewTask.class);
			 			
			 			startActivity(event);
		 			
		 			
		 		}
		 		});
	}
}