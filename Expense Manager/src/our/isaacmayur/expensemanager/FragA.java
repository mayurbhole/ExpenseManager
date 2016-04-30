package our.isaacmayur.expensemanager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.example.expensemanager.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FragA extends Fragment implements OnItemClickListener{
	ListView lv;
	TextView tv1, tv2;
	ListView lvList;
	String[] category;
	String[] date;
	int yesOrNo;
	int count;

	int[] cost;

	ArrayList<String> categoryName;
	ArrayList<Integer> costName;
	ArrayList<String> dateName;
	ArrayList<Integer> IdName;
	String years;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	    View rootView = inflater.inflate(R.layout.fraga, container,
	            false);
	    lv=(ListView) rootView.findViewById(R.id.listViewMonth);
	    //String[] values = new String[] { "Message1", "Message2", "Message3" };
	    //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
	      //  android.R.layout.simple_list_item_1, values);
	    //lv.setAdapter(adapter);
	    
		//	lv = (ListView) findViewById(R.id.lvCategoryList2month);
		String res = "";
		categoryName = new ArrayList<String>();
		dateName = new ArrayList<String>();
		costName = new ArrayList<Integer>();
		IdName = new ArrayList<Integer>();
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat month_date = new SimpleDateFormat("MM");
		String month_num = month_date.format(cal.getTime());
		SimpleDateFormat month_date2 = new SimpleDateFormat("MMMM");
		SimpleDateFormat year = new SimpleDateFormat("yyyy");

		Log.e("sum value", month_num);
		// int monthNum=Integer.parseInt(month_num);
		String month_name = month_date2.format(cal.getTime());
		 years = year.format(cal.getTime());///////////

		
			String wholeYear01 = years + month_num + "01";
			String wholeYear31 = years + month_num + "31";// ///
			// String wholeYear08 = years + month_num + 08;

			int date01 = Integer.parseInt(wholeYear01);
			int date07 = Integer.parseInt(wholeYear31);

		
		DbClass dc = new DbClass(getActivity());
		dc.open();
		
		Cursor result = dc.showResultInPdf(date01, date07);
		for (result.moveToFirst(); !result.isAfterLast(); result.moveToNext()) {
			categoryName.add(result.getString(1));
			costName.add(result.getInt(2));
			dateName.add(result.getString(3));
IdName.add(result.getInt(0));
			res = res + result.getInt(0) + " " + result.getString(1) + " "
					+ result.getInt(2) + " " + result.getString(3) + " "
					+ result.getString(4) + " " + ".......";
			Log.e("category", result.getString(1));// correct till hereS
			Log.e("date", result.getString(3));
			Log.e("cost", "cost" + result.getInt(2));
			Log.e("Id", ""+result.getInt(0));
		}
		count=categoryName.size();
		final TryAdapter2MonthFragmentA adapter = new TryAdapter2MonthFragmentA(getActivity(), categoryName, dateName,
				costName,IdName,count);
		lv.setAdapter(adapter);
		//lv.setOnItemClickListener(getActivity());
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int pos, long arg3) {
				/*
				 * TextView tvtask = (TextView)
				 * view.findViewById(R.id.tvsingleCat_category); String
				 * cat=tvtask.getText().toString(); String
				 * categoryDel=cat.replace("Category :","");
				 */

				/*
				 * TextView tvcost = (TextView)
				 * view.findViewById(R.id.tvsingleCat_Cost); String
				 * cost=tvcost.getText().toString(); String
				 * costing=cost.replace("Cost:",""); int
				 * costDel=Integer.parseInt(costing);
				 */

				final TextView tvclient = (TextView) view
						.findViewById(R.id.tvsingleCat_Date2month);
				
				final TextView tvId = (TextView) view
						.findViewById(R.id.tvsingleCat_Id2month);


				final AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
				b.setIcon(android.R.drawable.ic_dialog_alert);
				b.setMessage("Do you want to Delete the Selection?");
				b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						
						String date = tvclient.getText().toString();    //*********** I have changed type of tvclient and pos to final
						String dateDel1 = date.replace("Date:", "");

						String dateDel = dateDel1.replace("   Time:", "-");

						Log.e("check", dateDel);
						
						String sid = tvId.getText().toString();    //*********** I have changed type of tvclient and pos to final
						String sid1 = sid.replace("Cost:", "");
					//	int id=Integer.parseInt(sid1);
						
						

						DbClass dc = new DbClass(getActivity());// /chance of
																	// error
						dc.open();
						dc.categoryDelete(dateDel,sid1);
						dc.close();

					//	categoryName.remove(pos);

					//	dateName.remove(pos);
					//	costName.remove(pos);
						adapter.notifyDataSetChanged();
						Intent intent =new Intent(getActivity(),ShowDeleteTogether.class);
						getActivity().finish();
						startActivity(intent);

					}
				});
				b.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					
					}
				});

				b.show();

	/*			
					String date = tvclient.getText().toString();
					String dateDel1 = date.replace("Date:", "");

					String dateDel = dateDel1.replace("   Time:", "-");

					Log.e("check", dateDel);

					DbClass dc = new DbClass(ShowCategory.this);// /chance of
																// error
					dc.open();
					dc.categoryDelete(dateDel);
					dc.close();

					categoryName.remove(pos);

					dateName.remove(pos);
					costName.remove(pos);
					adapter.notifyDataSetChanged();

	*/				// Can't manage to remove an item here

			

			

				return false;
			}

		});

dc.close();
	

	
  
	    return rootView;
	    
	}

	
@Override
public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	// TODO Auto-generated method stub
	
}

}

class TryAdapter2MonthFragmentA extends ArrayAdapter<String> {
	String[] category;
	String[] date;
	ArrayList<Integer> costName;
	ArrayList<Integer> IdName;
	ArrayList<String> dateName;
	ArrayList<String> categoryName;
	int count;

	Context context;

	public TryAdapter2MonthFragmentA(Context context, ArrayList<String> categoryName,
			ArrayList<String> dateName, ArrayList<Integer> costName,ArrayList<Integer> IdName,int count) {
		super(context, R.layout.single_showdelete_category_month, R.id.tvsingleCat_category2month,
				categoryName);
		this.context = context;
		this.categoryName = categoryName;
		this.dateName = dateName;
		this.costName = costName;
		this.IdName=IdName;
		this.count=count;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = convertView;

		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.single_showdelete_category_month, parent, false);// reference
		} // to
			// linear
			// layout(parent)

		TextView tvtask = (TextView) row
				.findViewById(R.id.tvsingleCat_category2month);
		tvtask.setText(categoryName.get(count-position-1));

		TextView tvclient = (TextView) row.findViewById(R.id.tvsingleCat_Date2month);
		// tvclient.setText( dateName.get(position));

		String s = dateName.get(count-position-1);
		String t = s.replace("-", "   Time:");
		tvclient.setText("Date:" + t);

		TextView tvcost = (TextView) row.findViewById(R.id.tvsingleCat_Cost2month);
		tvcost.setText("Cost:" + costName.get(count-position-1));
		
		TextView tvId = (TextView) row.findViewById(R.id.tvsingleCat_Id2month);
		tvId.setText("Cost:" + IdName.get(count-position-1));

		

		return row;
	}

}

