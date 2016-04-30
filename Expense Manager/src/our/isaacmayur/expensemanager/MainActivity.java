package our.isaacmayur.expensemanager;


import com.example.expensemanager.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	Button expLimit, category, moneySpent, bShowDelete,bgraph,bShowDeleteMonth;
	// ListView lv;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	//	bShowDelete = (Button) findViewById(R.id.bShowDelete);
		bgraph = (Button) findViewById(R.id.bgraph);
		bShowDeleteMonth = (Button) findViewById(R.id.bShowDeleteMonth);

		moneySpent = (Button) findViewById(R.id.bMoneySpent);
		category = (Button) findViewById(R.id.bCategory);
	//	bShowDelete.setOnClickListener(this);
		moneySpent.setOnClickListener(this);
		category.setOnClickListener(this);
		bgraph.setOnClickListener(this);
		bShowDeleteMonth.setOnClickListener(this);


	}
	/*
@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		finish();
	}
	*/
	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.bCategory: {
			// vibrate(50);
			intent = new Intent(MainActivity.this, EnterAmount.class);
			startActivity(intent);
		//	finish();
			break;
		}

	/*	case R.id.bShowDelete: {
			intent = new Intent(MainActivity.this, ShowDeleteCategory.class);
			startActivity(intent);
			break;
		}
		*/
		case R.id.bMoneySpent: {
			// vibrate(50);
			intent = new Intent(MainActivity.this, GenerateReport.class);
			startActivity(intent);
			break;
		}
		case R.id.bgraph: {
			// vibrate(50);
			intent = new Intent(MainActivity.this, Graph.class);
			startActivity(intent);
			break;
		}
		case R.id.bShowDeleteMonth: {
			// vibrate(50);
			intent = new Intent(MainActivity.this, ShowDeleteTogether.class);
			startActivity(intent);
			break;
		}


		}
	}
	
	private void vibrate(int ms) {
	    ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(ms);
	  }
	
	@Override  
    public boolean onCreateOptionsMenu(Menu menu) {  
        // Inflate the menu; this adds items to the action bar if it is present.  
        getMenuInflater().inflate(R.menu.main, menu);//Menu Resource, Menu  
        return true;  
    }  
	
	
	@Override  
    public boolean onOptionsItemSelected(MenuItem item) {   
        switch (item.getItemId()) {  
            case R.id.info:  
            
            	
            //	Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();  
            
            	showInstructions();
            	return true;     
  
            case R.id.about:  
                
            	intent = new Intent(MainActivity.this, AboutUs.class);
    			startActivity(intent);
            	
        //    	about_us();
            	
            //	Toast.makeText(getApplicationContext(),"Item 2 Selected",Toast.LENGTH_LONG).show();  
              return true;     
  /*            
            case R.id.item3:  
                Toast.makeText(getApplicationContext(),"Item 3 Selected",Toast.LENGTH_LONG).show();  
              return true;     
  
              default:  
                return super.onOptionsItemSelected(item);  
   
        */
        } 
        
        return true;
    }  
	


	
	private void showInstructions() {
	    TextView tv = new TextView(this);
	    tv.setMovementMethod(new ScrollingMovementMethod());
	    tv.setText(Html.fromHtml(getString(R.string.instructions_text)));
	    new AlertDialog.Builder(this)
	      .setTitle(R.string.instructions_title)
	      .setView(tv)
	      .setNegativeButton(R.string.OK, null)
	      .create().show();
	  }
	  
/*	
	private void about_us() {
	    TextView tv = new TextView(this);
	    tv.setMovementMethod(new ScrollingMovementMethod());
	    tv.setText(Html.fromHtml(getString(R.string.aboutUs_text)));
	    new AlertDialog.Builder(this)
	      .setTitle(R.string.aboutUs_title)
	      .setView(tv)
	      .setNegativeButton(R.string.OK, null)
	      .create().show();
	  }
*/	  
	
	
}
