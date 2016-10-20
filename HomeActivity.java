package com.budget.budgetplanner;



import java.util.Timer;
import java.util.TimerTask;

import com.budget.budgetplanner.R;

import com.budget.budgetplanner.HelpDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity 
{
	Button btnSignIn,btnSignUp;
	private ImageButton blue;

	LoginDataBaseAdapter loginDataBaseAdapter;
	 AnimationDrawable frameAnimation;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	     super.onCreate(savedInstanceState);
	     //setContentView(R.layout.main);
	     requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 
		 setContentView(R.layout.main);
		
	     getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title1);
		 TextView tv = (TextView) findViewById(R.id.textView9);		
	     tv.setText("Budget Planner");
	     Button help = (Button) findViewById(R.id.Help);
	        help.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					HelpDialog helpDialog = new HelpDialog(HomeActivity.this);
					helpDialog.setTitle("Help ");
					helpDialog.show();           		
				}        
	        });
         ImageView img= (ImageView) findViewById(R.id.splashImgView);
		    //  imageView.setImageDrawable(transition);
	     img.setBackgroundResource(R.drawable.splash_animation);
	    
	     
	     loginDataBaseAdapter=new LoginDataBaseAdapter(this);
	     loginDataBaseAdapter=loginDataBaseAdapter.open();
	     
	     // Get The Refference Of Buttons
	    
	     btnSignIn=(Button)findViewById(R.id.buttonSignIN);
	     btnSignUp=(Button)findViewById(R.id.buttonSignUP);
	     
	     btnSignUp.setOnClickListener(new View.OnClickListener() {
	    		public void onClick(View v) {
	    			
	    			// TODO Auto-generated method stub
	    			
	    			/// Create Intent for SignUpActivity  and Start The Activity
	    			Intent intentSignUP=new Intent(getApplicationContext(),SignUPActivity.class);
	    			startActivity(intentSignUP);
	    			}
	    		});
			
	    	MyAnimationRoutine mar = new MyAnimationRoutine();
	    	MyAnimationRoutine2 mar2 = new MyAnimationRoutine2();

	    	Timer t = new Timer(false);
	    	t.schedule(mar, 100);
	    	Timer t2 = new Timer(false);
	    	t2.schedule(mar2, 10000);

	    	}

	    	class MyAnimationRoutine extends TimerTask
	    	{
	    	MyAnimationRoutine()
	    	{
	    	}

	    	public void run()
	    	{
	    		  ImageView img= (ImageView) findViewById(R.id.splashImgView);
	    	// Get the background, which has been compiled to an AnimationDrawable object.
	    	AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

	    	// Start the animation (looped playback by default).
	    	frameAnimation.start();
	    	}
	    	}

	    	class MyAnimationRoutine2 extends TimerTask
	    	{
	    	MyAnimationRoutine2()
	    	{
	    	}

	    	public void run()
	    	{
	    	ImageView img = (ImageView)findViewById(R.id.splashImgView);
	    	// Get the background, which has been compiled to an AnimationDrawable object.
	    	AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

	    	// stop the animation (looped playback by default).
	    	frameAnimation.stop();
	    	}
	    	}
	
	  
	    // Set OnClick Listener on SignUp button 
	    	// Methos to handleClick Event of Sign In Button
	   public void signIn(View V)
	   {
			final Dialog dialog = new Dialog(HomeActivity.this);
			dialog.setContentView(R.layout.login);
		    dialog.setTitle("BudgetPlanner");
		    
	
		    // get the Refferences of views
		    final  EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
		    final  EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPasswordToLogin);
		    
			Button btnSignIn=(Button)dialog.findViewById(R.id.buttonLogIn);
				
			// Set On ClickListener
			btnSignIn.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// get The User name and Password
					String userName=editTextUserName.getText().toString();
					String password=editTextPassword.getText().toString();
					
					// fetch the Password form database for respective user name
					String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);
					
					// check if the Stored password matches with  Password entered by user
					if(password.equals(storedPassword))
					{
						Intent intent = new Intent(HomeActivity.this,Success.class);
					    intent.putExtra("username",editTextUserName.getText().toString());
					    startActivity(intent);

					}
					else
					{
						Toast.makeText(HomeActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
					}
				}
			});
			
			dialog.show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	    // Close The Database
		loginDataBaseAdapter.close();
	}
}
