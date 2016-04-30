package our.isaacmayur.expensemanager;

import com.example.expensemanager.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Thread t=new Thread(){
			public void run(){
				try{  
					sleep(1500);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				finally{
					Intent i=new Intent(Splash.this,MainActivity.class);
					startActivity(i);
				}  
			} 
		};
		
		t.start();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
		
	}

}
