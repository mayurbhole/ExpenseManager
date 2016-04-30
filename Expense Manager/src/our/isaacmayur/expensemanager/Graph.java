package our.isaacmayur.expensemanager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.example.expensemanager.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Graph extends Activity implements OnClickListener {
	Button bPieWeek, bPieMonth;
	Cursor c1, c2, c3, c4, c5;
	Cursor cur1, cur2, cur3, cur4, cur5, cur6, cur7, cur8, cur9, cur10, cur11,
			cur12;

	int sum1 = 0;
	int sum2 = 0;
	int sum3 = 0;
	int sum4 = 0;

	int sum5 = 0;

	int msum1 = 0;
	int msum2 = 0;
	int msum3 = 0;
	int msum4 = 0;
	int msum5 = 0;
	int msum6 = 0;
	int msum7 = 0;
	int msum8 = 0;
	int msum9 = 0;
	int msum10 = 0;
	int msum11 = 0;
	int msum12 = 0;
	
	String years;

//	ArrayList<Integer> amount1, amount2, amount3, amount4, amount5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graph);
	
		Log.e("sum value", "" + sum1 + sum2 + sum3 + sum4 + sum5);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat month_date = new SimpleDateFormat("MM");
		String month_num = month_date.format(cal.getTime());
		SimpleDateFormat month_date2 = new SimpleDateFormat("MMMM");
		SimpleDateFormat year = new SimpleDateFormat("yyyy");

		Log.e("sum value", month_num);
		// int monthNum=Integer.parseInt(month_num);
		String month_name = month_date2.format(cal.getTime());
		 years = year.format(cal.getTime());///////////

	//	Toast.makeText(getApplicationContext(), month_num, Toast.LENGTH_LONG);
		// Toast.makeText(getApplicationContext(), month_name,
		// Toast.LENGTH_LONG);

		// String wholeYear1=years+month_num+01;
		// String wholeYear7=years+month_num+07;

		// String wholeYear8 = years + month_num +08;
		// String wholeYear14 = years + month_num + 14;

		// String wholeYear15 = years + month_num + 15;
		// String wholeYear21 = years + month_num + 21;

		// String wholeYear22 = years + month_num + 22;
		// String wholeYear28 = years + month_num + 28;

		// String wholeYear29 = years + month_num + 29;
		// String wholeYear30 = years + month_num + 30;

		// String wholeYear31 = years + month_num + 31;
		// Log.e("wholeYear1", wholeYear1);
		Log.e("monthnum", month_num + years);

		String wholeYear01 = years + month_num + "01";
		String wholeYear07 = years + month_num + "07";// ///
		// String wholeYear08 = years + month_num + 08;

		int date01 = Integer.parseInt(wholeYear01);
		int date07 = Integer.parseInt(wholeYear07);

		DbClass dc = new DbClass(this);
		dc.open();
		c1 = dc.showResultInPdf(date01, date07);

		for (c1.moveToFirst(); !c1.isAfterLast(); c1.moveToNext()) {
			sum1 = sum1 + c1.getInt(2);

			

		}
	//	Toast.makeText(getApplicationContext(), sum1 + "", Toast.LENGTH_LONG)
		//		.show();
		Log.e("sum 1stweek", sum1 + "");

		String wholeYear08 = years + month_num + "08";
		String wholeYear14 = years + month_num + 14;
		int date08 = Integer.parseInt(wholeYear08);
		int date14 = Integer.parseInt(wholeYear14);
		c2 = dc.showResultInPdf(date08, date14);

		for (c2.moveToFirst(); !c2.isAfterLast(); c2.moveToNext()) {
			sum2 = sum2 + c2.getInt(2);
		

		}
	
	//	Toast.makeText(getApplicationContext(), sum2 + "", Toast.LENGTH_LONG)
		//		.show();
		Log.e("sum 2ndweek", sum2 + "");

		String wholeYear15 = years + month_num + 15;
		String wholeYear21 = years + month_num + 21;
		int date15 = Integer.parseInt(wholeYear15);
		int date21 = Integer.parseInt(wholeYear21);
		c3 = dc.showResultInPdf(date15, date21);

		for (c3.moveToFirst(); !c3.isAfterLast(); c3.moveToNext()) {
			sum3 = sum3 + c3.getInt(2);
			

		}

	//	Toast.makeText(getApplicationContext(), sum3 + "", Toast.LENGTH_LONG)
		//		.show();

		String wholeYear22 = years + month_num + 22;
		String wholeYear28 = years + month_num + 28;
		int date22 = Integer.parseInt(wholeYear22);
		int date28 = Integer.parseInt(wholeYear28);
		c4 = dc.showResultInPdf(date22, date28);

		for (c4.moveToFirst(); !c4.isAfterLast(); c4.moveToNext()) {
			sum4 = sum4 + c4.getInt(2);
			

		}

		if (month_name.equals("April") || month_name.equals("June")
				|| month_name.equals("September")
				|| month_name.equals("November")) {

			String wholeYear29 = years + month_num + 29;
			String wholeYear30 = years + month_num + 30;
			int date29 = Integer.parseInt(wholeYear29);
			int date30 = Integer.parseInt(wholeYear30);

			c5 = dc.showResultInPdf(date29, date30);

			for (c5.moveToFirst(); !c5.isAfterLast(); c5.moveToNext()) {
				sum5 = sum5 + c5.getInt(2);
			

			}

			Log.e("sum5", sum5 + "");
			
			dc.close();

		} else if (month_name.equals("January") || month_name.equals("March")
				|| month_name.equals("May") || month_name.equals("July")
				|| month_name.equals("August") || month_name.equals("October")
				|| month_name.equals("December")) {

			String wholeYear29 = years + month_num + 29;
			String wholeYear31 = years + month_num + 31;
			int date29 = Integer.parseInt(wholeYear29);
			int date31 = Integer.parseInt(wholeYear31);

			c5 = dc.showResultInPdf(date29, date31);

			for (c5.moveToFirst(); !c5.isAfterLast(); c5.moveToNext()) {
				sum5 = sum5 + c5.getInt(2);
				

			}

			Log.e("sum5", sum5 + "");
			

			dc.close();

		} else {

			String wholeYear29 = years + month_num + 29;
			// String wholeYear29 = years + month_num + 31;
			int date29 = Integer.parseInt(wholeYear29);
			// int date31 = Integer.parseInt(wholeYear31);

			c5 = dc.showResultInPdf(date29, date29);

			for (c5.moveToFirst(); !c5.isAfterLast(); c5.moveToNext()) {
				sum5 = sum5 + c5.getInt(2);
				

			}
			dc.close();
		}
		
		
	
			
		String monthstart1=	years+"01"+"01";
		String monthend1=	years+"01"+"31";
		
		Log.e("one",monthstart1);
		Log.e("one",monthend1);

			int start1 = Integer.parseInt(monthstart1);
		int end1 = Integer.parseInt(monthend1);
		
		DbClass dc1 = new DbClass(this);
		dc1.open();
		cur1 = dc1.showResultInPdf(start1, end1);
		
		for (cur1.moveToFirst(); !cur1.isAfterLast(); cur1.moveToNext()) {
			msum1 = msum1 + cur1.getInt(2);
			

		}
	//	dc1.close();

		
	//	 DbClass dc2 = new DbClass(this);
	//	dc2.open();
		String monthstart2=	years+"02"+"01";
		String monthend2=	years+"02"+"29";

		int start2 = Integer.parseInt(monthstart2);
		int end2 = Integer.parseInt(monthend2);

		
		//cur2 = dc2.showResultInPdf(start2, end2);
		cur2 = dc1.showResultInPdf(start2, end2);

		for (cur2.moveToFirst(); !cur2.isAfterLast(); cur2.moveToNext()) {
			msum2 = msum2 + cur2.getInt(2);
			

		}
	//	dc2.close();
	//	 DbClass dc3 = new DbClass(this);
		//	dc3.open();
		String monthstart3=	years+"03"+"01";
		String monthend3=	years+"03"+"31";

		int start3 = Integer.parseInt(monthstart3);
		int end3 = Integer.parseInt(monthend3);

		
	//	cur3 = dc3.showResultInPdf(start3, end3);
		cur3 = dc1.showResultInPdf(start2, end2);

		for (cur3.moveToFirst(); !cur3.isAfterLast(); cur3.moveToNext()) {
			msum3 = msum3 + cur3.getInt(2);
			

		}
	//	dc3.close();
		// DbClass dc4 = new DbClass(this);
		//	dc4.open();
		String monthstart4=	years+"04"+"01";
		String monthend4=	years+"04"+"30";

		int start4 = Integer.parseInt(monthstart4);
		int end4 = Integer.parseInt(monthend4);

		
	//	cur4 = dc4.showResultInPdf(start4, end4);
		cur4 = dc1.showResultInPdf(start4, end4);
		for (cur4.moveToFirst(); !cur4.isAfterLast(); cur4.moveToNext()) {
			msum4 = msum4 + cur4.getInt(2);
			

		}
		//dc4.close();
		String monthstart5=	years+"05"+"01";
		String monthend5=	years+"05"+"31";

		int start5 = Integer.parseInt(monthstart5);
		int end5 = Integer.parseInt(monthend5);

		//DbClass dc5 = new DbClass(this);
		//dc5.open();
		cur5 = dc1.showResultInPdf(start5, end5);
		for (cur5.moveToFirst(); !cur5.isAfterLast(); cur5.moveToNext()) {
			msum5 = msum5 + cur5.getInt(2);
			

		}
		Log.e("sum 5 is",monthstart5);
		
		String monthstart6=	years+"06"+"01";
		String monthend6=	years+"06"+"30";

		int start6 = Integer.parseInt(monthstart6);
		int end6 = Integer.parseInt(monthend6);

		//DbClass dc5 = new DbClass(this);
		//dc5.open();
		cur6 = dc1.showResultInPdf(start6, end6);
		for (cur6.moveToFirst(); !cur6.isAfterLast(); cur6.moveToNext()) {
			msum6 = msum6 + cur6.getInt(2);
			

		}

		
		
		
		String monthstart7=	years+"07"+"01";
		String monthend7=	years+"07"+"31";

		int start7 = Integer.parseInt(monthstart7);
		int end7 = Integer.parseInt(monthend7);

		//DbClass dc5 = new DbClass(this);
		//dc5.open();
		cur7 = dc1.showResultInPdf(start7, end7);
		for (cur7.moveToFirst(); !cur7.isAfterLast(); cur7.moveToNext()) {
			msum7 = msum7 + cur7.getInt(2);
			

		}
		
		
		String monthstart8=	years+"08"+"01";
		String monthend8=	years+"08"+"31";

		int start8 = Integer.parseInt(monthstart8);
		int end8 = Integer.parseInt(monthend8);

		//DbClass dc5 = new DbClass(this);
		//dc5.open();
		cur8 = dc1.showResultInPdf(start8, end8);
		for (cur8.moveToFirst(); !cur8.isAfterLast(); cur8.moveToNext()) {
			msum8 = msum8 + cur8.getInt(2);
			

		}
		String monthstart9=	years+"09"+"01";
		String monthend9=	years+"09"+"30";

		int start9 = Integer.parseInt(monthstart9);
		int end9 = Integer.parseInt(monthend9);

		//DbClass dc5 = new DbClass(this);
		//dc5.open();
		cur9 = dc1.showResultInPdf(start9, end9);
		for (cur9.moveToFirst(); !cur9.isAfterLast(); cur9.moveToNext()) {
			msum9 = msum9 + cur9.getInt(2);
			

		}
		String monthstart10=	years+"10"+"01";
		String monthend10=	years+"10"+"31";

		int start10 = Integer.parseInt(monthstart10);
		int end10 = Integer.parseInt(monthend10);

		//DbClass dc5 = new DbClass(this);
		//dc5.open();
		cur10 = dc1.showResultInPdf(start10, end10);
		for (cur10.moveToFirst(); !cur10.isAfterLast(); cur10.moveToNext()) {
			msum10 = msum10 + cur10.getInt(2);
			

		}
		String monthstart11=	years+"11"+"01";
		String monthend11=	years+"11"+"30";

		int start11 = Integer.parseInt(monthstart11);
		int end11 = Integer.parseInt(monthend11);

		//DbClass dc5 = new DbClass(this);
		//dc5.open();
		cur11 = dc1.showResultInPdf(start11, end11);
		for (cur11.moveToFirst(); !cur11.isAfterLast(); cur11.moveToNext()) {
			msum11 = msum11 + cur11.getInt(2);
			

		}
		String monthstart12=	years+"12"+"01";
		String monthend12=	years+"12"+"31";

		int start12 = Integer.parseInt(monthstart12);
		int end12 = Integer.parseInt(monthend12);

		//DbClass dc5 = new DbClass(this);
		//dc5.open();
		cur12 = dc1.showResultInPdf(start12, end12);
		for (cur12.moveToFirst(); !cur12.isAfterLast(); cur12.moveToNext()) {
			msum12 = msum12 + cur12.getInt(2);
			

		}

		
		
		
		
		
		dc1.close();
		
		
		
		
		
		
		bPieWeek = (Button) findViewById(R.id.bPieWeek);
		bPieWeek.setOnClickListener(this);

		bPieMonth = (Button) findViewById(R.id.bPieMonth);
		bPieMonth.setOnClickListener(this);
	}

	private void vibrate(int ms) {
	    ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(ms);
	  }
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	finish();
	
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bPieWeek: {
			 //vibrate(50);
			PieChartWeek effort = new PieChartWeek();
			Intent effortIntent = effort.getIntent(this, sum1, sum2, sum3,
					sum4, sum5);
			startActivity(effortIntent);

	//		Toast.makeText(getApplicationContext(), "Cleared all",
		//			Toast.LENGTH_LONG).show();
			break;
		}
		case R.id.bPieMonth: {
			 //vibrate(50);
			PieChartMonth effort = new PieChartMonth();
			Intent effortIntent = effort.getIntent(this, msum1, msum2, msum3,
					msum4, msum5, msum6, msum7, msum8, msum9, msum10, msum11, msum12);
			startActivity(effortIntent);
			break;
		}
		}
	}
}