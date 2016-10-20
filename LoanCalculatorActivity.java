		package com.budget.budgetplanner;
		
		import java.text.DecimalFormat;
		import android.app.Activity;
		import android.os.Bundle;
		import android.view.View;
		import android.widget.EditText;
		import android.widget.TextView;
		 
		public class LoanCalculatorActivity extends Activity {
		    private EditText mLoanAmount, mInterestRate, mLoanPeriod;
		    private TextView mMontlyPaymentResult, mTotalPaymentsResult;
		     
		    /** Initializes the app when it is first created. */
		    @Override
		    public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.loancalci);
		        
		        mLoanAmount = (EditText)findViewById(R.id.loan_amount);
		        mInterestRate = (EditText)findViewById(R.id.interest_rate);
		        mLoanPeriod = (EditText)findViewById(R.id.loan_period);
		        mMontlyPaymentResult = (TextView)findViewById(R.id.monthly_payment_result); 
		        mTotalPaymentsResult = (TextView)findViewById(R.id.total_payments_result);
		    }
		     
		    public void showLoanPayments(View clickedButton) {
		         
		    	 if(mLoanAmount.getText().toString().equals(""))
					{
		    		 mLoanAmount.setError("Enter the value");
						//Toast.makeText(AddBudget.this, "Enter all values", Toast.LENGTH_LONG).show();
						return;
						
					}
		    	 
		    	 if(mInterestRate.getText().toString().equals(""))
					{
		    		 mInterestRate.setError("Enter the value");
						//Toast.makeText(AddBudget.this, "Enter all values", Toast.LENGTH_LONG).show();
						return;
						
					}
		    	 
		    	 if(mLoanPeriod.getText().toString().equals(""))
					{
		    		 mLoanPeriod.setError("Enter the value");
						//Toast.makeText(AddBudget.this, "Enter all values", Toast.LENGTH_LONG).show();
						return;
						
					}
		    	
		    	double loanAmount = Float.parseFloat(mLoanAmount.getText().toString());
		        double interestRate = (Float.parseFloat(mInterestRate.getText().toString()));
		        double loanPeriod = Integer.parseInt(mLoanPeriod.getText().toString());
		        double r = interestRate/1200;
		        double r1 =  Math.pow(r+1,loanPeriod);
		         
		        double monthlyPayment = (double) ((r+(r/(r1-1))) * loanAmount);
		        double totalPayment = monthlyPayment * loanPeriod;
		         
		        mMontlyPaymentResult.setText(new DecimalFormat("##.##").format(monthlyPayment));
		        mTotalPaymentsResult.setText(new DecimalFormat("##.##").format(totalPayment));
		           }
		   
		}