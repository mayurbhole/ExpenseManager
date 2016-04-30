package our.isaacmayur.expensemanager;

import com.example.expensemanager.R;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class ShowDeleteTogether extends FragmentActivity {
ViewPager vp=null;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.showdeletetogether);
		vp=(ViewPager) findViewById(R.id.pager);
		FragmentManager fm=getSupportFragmentManager(); 
		vp.setAdapter(new MyAdapter(fm));
		
	}

	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	finish();
	
	}
	

}
class MyAdapter extends FragmentPagerAdapter{

	public MyAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int i) {
		// TODO Auto-generated method stub
		Fragment f=null;
		if(i==0){
			f=new FragA();
			}
		if(i==1){
			f=new FragB();
		}
		return f;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	@Override
	public CharSequence getPageTitle(int pos) {
		// TODO Auto-generated method stub
		if(pos==0){
			return "Current Month Entries";
		}
		if(pos==1){
			return "All Entries";
		}
		return null;
	}
	
}