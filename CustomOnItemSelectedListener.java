package com.budget.budgetplanner;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
import android.app.Activity;

public class CustomOnItemSelectedListener implements OnItemSelectedListener{

	//AddBudget ab;

	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		//Toast.makeText(parent.getContext(), 
			//	"OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
				//Toast.LENGTH_SHORT).show();
		
	
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}