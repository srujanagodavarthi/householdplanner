		package com.budget.budgetplanner;
		
		import java.text.DecimalFormat;
		import android.app.Activity;
		import android.app.NotificationManager;
		import android.content.Intent;
		import android.os.Bundle;
		import android.view.View;
		import android.view.Window;
		import android.view.WindowManager;
		import android.view.View.OnClickListener;
		import android.widget.Button;
		import android.widget.EditText;
		import android.widget.TextView;
		 
		public class calculator extends Activity {
			LoginDataBaseAdapter loginDataBaseAdapter;
			
			
			Button button1,button2,button3;
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
				setContentView(R.layout.calci);
				getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title1);
				TextView tv2 = (TextView) findViewById(R.id.textView9);
		         tv2.setText("Budget Planner");
		         Button help = (Button) findViewById(R.id.Help);
			        help.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View arg0) {
							HelpDialog helpDialog = new HelpDialog(calculator.this);
							helpDialog.setTitle("Help ");
							helpDialog.show();           		
						}        
			        });
				
				getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
			}
			public void Loancalci(View v) {
				Intent intent = new Intent(this, LoanCalculatorActivity.class);
				startActivity(intent);
			}

			public void Fdcalci(View v) {
				Intent intent = new Intent(this, fdcalculator.class);
				startActivity(intent);
			}
			
			public void Rdcalci(View v) {
				Intent intent = new Intent(this, rdcalculator.class);
				startActivity(intent);
			}

		}