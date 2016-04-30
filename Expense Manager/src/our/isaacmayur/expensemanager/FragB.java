package our.isaacmayur.expensemanager;



import java.util.ArrayList;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;

public class FragB extends Fragment{
	TextView tv1, tv2;
	ListView lv;
	String[] category;
	String[] date;
	int yesOrNo;
	int count;

	int[] cost;
	ArrayList<Integer> IdName;
	ArrayList<String> categoryName;
	ArrayList<Integer> costName;
	ArrayList<String> dateName;


//	ListView lv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 View rootView = inflater.inflate(R.layout.fragb, container,
		            false);
		    lv=(ListView) rootView.findViewById(R.id.listViewWhole);
		    //String[] values = new String[] { "Message1", "Message2", "Message3" };
		    //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
		      //  android.R.layout.simple_list_item_1, values);
		    //lv.setAdapter(adapter);
		    
			String res = "";
			categoryName = new ArrayList<String>();
			dateName = new ArrayList<String>();
			costName = new ArrayList<Integer>();
			IdName = new ArrayList<Integer>();
			
			DbClass dc = new DbClass(getActivity());
			dc.open();
			Cursor result = dc.categoryDetailsShowMethod();
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
		
		
		
			final TryAdapterFragmentB adapter = new TryAdapterFragmentB(getActivity(), categoryName, dateName,
					costName,IdName,count);
			lv.setAdapter(adapter);
			//lv.setOnItemClickListener(this);
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
							.findViewById(R.id.tvsingleCat_Date2);
					
					final TextView tvId = (TextView) view
							.findViewById(R.id.tvsingleCat_Id2);
					

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


		// TODO Auto-generated method stub
		//return inflater.inflate(R.layout.fragb, container,false);
	}


class TryAdapterFragmentB extends ArrayAdapter<String> {
	String[] category;
	String[] date;
	ArrayList<Integer> costName;
	ArrayList<String> dateName;
	ArrayList<String> categoryName;
	ArrayList<Integer> IdName;
	int count;
	Context context;

	public TryAdapterFragmentB(Context context, ArrayList<String> categoryName,
			ArrayList<String> dateName, ArrayList<Integer> costName,ArrayList<Integer> IdName,int count) {
		super(context, R.layout.singleshowdelcategory, R.id.tvsingleCat_category2,
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
			row = inflater.inflate(R.layout.singleshowdelcategory, parent, false);// reference
		} // to
			// linear
			// layout(parent)

		TextView tvtask = (TextView) row
				.findViewById(R.id.tvsingleCat_category2);
		tvtask.setText(categoryName.get(count-position-1));

		TextView tvclient = (TextView) row.findViewById(R.id.tvsingleCat_Date2);
		// tvclient.setText( dateName.get(position));

		String s = dateName.get(count-position-1);
		String t = s.replace("-", "   Time:");
		tvclient.setText("Date:" + t);

		TextView tvcost = (TextView) row.findViewById(R.id.tvsingleCat_Cost2);
		tvcost.setText("Cost:" + costName.get(count-position-1));

		TextView tvId = (TextView) row.findViewById(R.id.tvsingleCat_Id2);
		tvId.setText("Cost:" + IdName.get(count-position-1));
		
		
		return row;
	}

}

