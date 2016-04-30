package our.isaacmayur.expensemanager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.expensemanager.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Others extends Activity implements OnClickListener {
	Spinner spinnerOther;
	String spinnerItemSelected, sAmt,sCategory;
	EditText etCategoryOthers, etAmountOthers;
	int date2, amt;
	String date;
	Button bSaveDBOthers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.others);
		spinnerOther = (Spinner) findViewById(R.id.spinnerOther);
		etCategoryOthers = (EditText) findViewById(R.id.etCategoryOthers);
		etAmountOthers = (EditText) findViewById(R.id.etAmountOthers);
		bSaveDBOthers = (Button) findViewById(R.id.bSaveDBOthers);
		List<String> l = new ArrayList<String>();
		l.add("Cash");
		l.add("Credit/Debit Card");
		l.add("Cheque");
		l.add("Net Banking");
		ArrayAdapter<String> a = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, l);
		spinnerOther.setAdapter(a);

		spinnerOther.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent,
					View selectedItemView, int pos, long id) {
				spinnerItemSelected = parent.getItemAtPosition(pos).toString();
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});

		final Calendar c = Calendar.getInstance();
		/*
		 * int yy = c.get(Calendar.YEAR); int mm = c.get(Calendar.MONTH); int dd
		 * = c.get(Calendar.DAY_OF_MONTH); date = dd + "/" + (mm + 1) + "/" +
		 * yy; Toast.makeText(getApplicationContext(), date, Toast.LENGTH_SHORT)
		 * .show();
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy-HH:mm:ss ");

		date = sdf.format(c.getTime());

		// Toast.makeText(getApplicationContext(), date,
		// Toast.LENGTH_LONG).show();

		int yy = c.get(Calendar.YEAR);
		int mm = c.get(Calendar.MONTH) + 1;
		int dd = c.get(Calendar.DAY_OF_MONTH);

		String s = yy + "" + (mm < 10 ? ("0" + mm) : (mm)) + ""
				+ (dd < 10 ? ("0" + dd) : (dd));
		// Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
		Log.e("datechange", s);
		// String s= new
		// StringBuilder().append(yy).append(mm).append(dd).toString();

		// Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
		date2 = Integer.parseInt(s);// integer
		// Toast.makeText(getApplicationContext(), "hello" + date2,
		// Toast.LENGTH_LONG).show();
		Log.e("integer2", "hello" + date2);
		bSaveDBOthers.setOnClickListener(this);
	}
	
	
@Override
public void onBackPressed() {  
	// TODO Auto-generated method stub
	super.onBackPressed();
	
	Intent goback= new Intent(Others.this,EnterAmount.class);
	startActivity(goback);
	finish();
}

private void vibrate(int ms) {
    ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(ms);
  }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bSaveDBOthers: {
			// vibrate(50);

			// /////seperate if for others and amount

			sAmt = etAmountOthers.getText().toString();
			sCategory=etCategoryOthers.getText().toString();
			// spinnerItemSelectedCategory=
			// et_get_other.getText().toString();////////////////
			// Log.e("category","Hello"+ spinnerItemSelectedCategory);
			try {
				amt = Integer.parseInt(sAmt);
				Log.e("amt is", "" + amt);

			} catch (Exception e) {

				// Toast.makeText(getApplicationContext(),
				// "Please insert the amount", Toast.LENGTH_SHORT).show();
			}

			DbClass dc = new DbClass(this);
			dc.open();
			if(amt==0||sCategory.compareTo("")==0){

				Toast.makeText(getApplicationContext(),"Please enter all the fields", Toast.LENGTH_SHORT).show();
			amt=0;
			sCategory="";
			}

			else {
				

				
			//	if(spinnerItemSelectedCategory.equals(null))
				dc.categoryDetailsInsert(amt, sCategory,
						date, spinnerItemSelected, date2);
				dc.close(); 
				Toast.makeText(getApplicationContext(), "Saved successfully", Toast.LENGTH_LONG).show();
				amt=0;
				sCategory="";
				
		/*		Intent goback= new Intent(Others.this,EnterAmount.class);
				startActivity(goback);
				finish();
		*/	
				break;
				

			}


		}
		}

	}
}
