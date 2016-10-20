
	package com.budget.budgetplanner;

	import com.budget.budgetplanner.R;

	import android.app.Activity;
	import android.content.Intent;
	import android.os.Bundle;
	import android.view.View;
	import android.widget.Button;
	import android.widget.TextView;

	public class logout extends Activity
	{
		
		
		
		Button addtransaction,addbudget,addincome,viewchart;
		
		protected void onCreate(Bundle savedInstanceState)
		{
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.success);
			
			TextView tv = (TextView) findViewById(R.id.textView1);
			tv.setText("DOBBEYiiiiiiii ni income,"+getIntent().getExtras().getString("income"));
		}
}
