package our.isaacmayur.expensemanager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.expensemanager.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
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
import android.widget.TextView;
import android.widget.Toast;

public class EnterAmount extends Activity implements OnClickListener {
	Intent intent;
	Button save;
	Spinner spinner, spinnerCategory;
	EditText etamt, et_get_other;
	String date;
	String sAmt;
	String spinnerItemSelected;
	String spinnerItemSelectedCategory;
	// String category;
	int amt;
	int date2;
	TextView caategories, tv_cat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enteramount);
		save = (Button) findViewById(R.id.bsaveDb);

		
		// add_new_entry = (Button) findViewById(R.id.add_new_entry);
		caategories = (TextView) findViewById(R.id.tvCaategories);
		etamt = (EditText) findViewById(R.id.etAmount);
		spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
	//	et_get_other = (EditText) findViewById(R.id.et_get_other);
		spinner = (Spinner) findViewById(R.id.payment_spinner);
	//	tv_cat = (TextView) findViewById(R.id.tv_cat);
		List<String> sCategory = new ArrayList<String>();
		String[] categories = { "Household Expenses", "Food", "Bills",
				"Grocery", "Education", "Clothes", "Travel", "Entertainment",
				"Home Appliances", "Jewellery", "Medical Expenses", "Fuel",
				"Others" };
		sCategory.add("Household Expenses");
		sCategory.add("Food");
		sCategory.add("Bills");
		sCategory.add("Grocery");
		sCategory.add("Education");
		sCategory.add("Travel");
		sCategory.add("Clothes");
		sCategory.add("Entertainment");
		sCategory.add("Home Appliances");
		sCategory.add("Jewellery");
		sCategory.add("Medical Expenses");
		sCategory.add("Fuel");
		sCategory.add("Others");
		ArrayAdapter<String> sc = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, sCategory);
		spinnerCategory.setAdapter(sc);
		// spinnerCategory.setOnItemSelectedListener(this);

		List<String> l = new ArrayList<String>();
		l.add("Cash");
		l.add("Credit/Debit Card");
		l.add("Cheque");
		l.add("Net Banking");
		ArrayAdapter<String> a = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, l);
		spinner.setAdapter(a);
		// spinner.setOnItemSelectedListener(this);
		// add_new_entry.setOnClickListener(this);
		save.setOnClickListener(this);

		spinnerCategory.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent,
					View selectedItemView, int pos, long id) {
				spinnerItemSelectedCategory = parent.getItemAtPosition(pos)
						.toString();

				if (spinnerItemSelectedCategory.equals("Others")) {
					// ///////////add activity
					Intent other = new Intent(EnterAmount.this, Others.class);
					startActivity(other);
					finish();

				}

			}

			public void onNothingSelected(AdapterView<?> parentView) {
				spinnerItemSelectedCategory = "Household Expenses";
			}
		});

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent,
					View selectedItemView, int pos, long id) {
				spinnerItemSelected = parent.getItemAtPosition(pos).toString();
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});

		// intent = getIntent();
		// / category = intent.getStringExtra("categories");
		// caategories.setText(category);
		// Toast.makeText(getApplicationContext(), "3rd" + category,
		// Toast.LENGTH_SHORT).show();
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

	}

	/*
	 * @Override public void onBackPressed() { // TODO Auto-generated method
	 * stub super.onBackPressed();
	 * 
	 * Intent go_main = new Intent(EnterAmount.this, MainActivity.class);
	 * startActivity(go_main); finish(); }
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	finish();
	
	}
	private void vibrate(int ms) {
		((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(ms);
	} 

	@Override 
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bsaveDb: {
		//	vibrate(50);
			// /////seperate if for others and amount

			sAmt = etamt.getText().toString();
			// spinnerItemSelectedCategory=
			// et_get_other.getText().toString();////////////////
			Log.e("category", "Hello" + sAmt);
			try {
				amt = Integer.parseInt(sAmt);
				Log.e("amt is", "" + amt);

			} catch (Exception e) {

				// Toast.makeText(getApplicationContext(),
				// "Please insert the amount", Toast.LENGTH_SHORT).show();
			}

			DbClass dc = new DbClass(this);
			dc.open();

			if (amt == 0) {

				Toast.makeText(getApplicationContext(),
						"Please insert the amount", Toast.LENGTH_SHORT).show();
			}

			else {

				// if(spinnerItemSelectedCategory.equals(null))
				dc.categoryDetailsInsert(amt, spinnerItemSelectedCategory,
						date, spinnerItemSelected, date2);
				dc.close();
				Toast.makeText(getApplicationContext(), "Saved successfully",
						Toast.LENGTH_LONG).show();
				amt = 0;
				/*
				 * Intent intent = getIntent(); finish(); startActivity(intent);
				 */

				/*
				 * spinnerCategory.setVisibility(View.VISIBLE);
				 * caategories.setVisibility(View.VISIBLE);
				 * 
				 * et_get_other.setVisibility(View.GONE);
				 * tv_cat.setVisibility(View.GONE);
				 */
			}

			break;

		}
		}
	}

}
