package our.isaacmayur.expensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DbClass {

	private static final int DATABASE_VERSION = 3;
	private static final String DB_NAME = "expensedatabase";
	private static final String DB_TABLE2 = "category";
	private static final String DB_TABLE1 = "total";

	static final String KEY_CID = "_cid";
	static final String KEY_CNAME = "CategoryName";
	static final String KEY_CAMOUNT = "AmountSpent";
	static final String KEY_CDATE = "Date";
	static final String KEY_CPAYMENTMODE = "PaymentMode";

	static final String KEY_TID = "_tid";
	static final String KEY_TEXP_LIMIT = "ExpenditureLimit";

	Context ourContext;
	DbHelper dbhelper; 
	SQLiteDatabase ourDatabase;

	public DbClass(Context context) {
		this.ourContext = context;
	}

	public DbClass open() throws SQLException {

		dbhelper = new DbHelper(ourContext);

		// SQLiteDatabase db = this.getWritableDatabase();
		ourDatabase = dbhelper.getWritableDatabase();
		// ourDatabase.execSQL("PRAGMA foreign_keys = ON;");
		return this;

	}

	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DB_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// String tableOne =
			// "CREATE TABLE total ( _tid INTEGER PRIMARY KEY,ExpenditureLimit INTEGER)";

			// db.execSQL(tableOne);
			// Log.d("table creation", "table 1 created");

			String tableTwo = "CREATE TABLE category ( _cid INTEGER PRIMARY KEY AUTOINCREMENT,CategoryName  TEXT NOT NULL, AmountSpent INTEGER, Date Text NOT NULL, PaymentMode Text NOT NULL,Date2 INTEGER)";

			db.execSQL(tableTwo);
			Log.d("table creation", "table 2 created");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			// db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE1);
			db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE2);
			// db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE2);

			Log.d("on upgrade", "table 2 created");
			// Create tables again
			onCreate(db);
		}

	}// static class ends here

	public void close() {
		dbhelper.close();
	}

	/*
	 * public void TotalInsert(int tid, int totalmoney) { ContentValues cv = new
	 * ContentValues(); cv.put(KEY_TID, tid); cv.put(KEY_TEXP_LIMIT,
	 * totalmoney);
	 * 
	 * ourDatabase.insert(DB_TABLE1, null, cv); }
	 */

	/*
	 * public Cursor TotalShowMethod() { String res = ""; Cursor c = null;
	 * 
	 * try { Log.i("inside total showed", "inside"); // final String MY_QUERY =
	 * "SELECT * FROM total"; String[] columns = new String[] {
	 * "_tid,ExpenditureLimit" }; c = ourDatabase.query(DB_TABLE1, columns,
	 * null, null, null, null, null);
	 * 
	 * boolean test = c.moveToFirst();
	 * 
	 * if (test == true) { Log.i("inside", "if"); Log.i("value", "value" +
	 * c.getInt(1)); return c; } else { Log.i("inside boolean", "cursor empty");
	 * } } catch (Exception e) { Log.i("inside catch", "error"); } return c;
	 * 
	 * }
	 */

	public void categoryDetailsInsert(int amt, String category, String date,
			String spinnerItemSelected, int date2) {
		ContentValues cv = new ContentValues();
		// cv.put(KEY_CID, 2);
		cv.put(KEY_CNAME, category);

		cv.put(KEY_CAMOUNT, amt);
		cv.put(KEY_CDATE, date);
		cv.put(KEY_CPAYMENTMODE, spinnerItemSelected);
		cv.put("Date2", date2);
		Log.e("insert","helloooo"+date2);
		ourDatabase.insert(DB_TABLE2, null, cv);

	}

	public Cursor categoryDetailsShowMethod() {
		String res = "";
		Cursor c = null;

		try {
			Log.i("inside total showed", "inside");
			// final String MY_QUERY = "SELECT * FROM total";
			String[] columns = new String[] { "_cid", "CategoryName",
					"AmountSpent", "Date", "PaymentMode " };
			c = ourDatabase.query(DB_TABLE2, columns, null, null, null, null,
					null);

			boolean test = c.moveToFirst();

			if (test == true) {
				Log.i("inside", "if");
				Log.i("value", "value" + c.getInt(2));
				return c;
			} else {
				Log.i("inside boolean", "cursor empty");
			}
		} catch (Exception e) {
			Log.i("inside catch", "error");
		}
		return c;
	}

	public void categoryDelete(String dateDel, String id) {
		// ourDatabase.delete(DB_TABLE2,KEY_CDATE +"="+dateDel, null);
		// ourDatabase.delete(DB_TABLE2, null, null);

		String[] whereArgs = new String[] { dateDel,id };
		ourDatabase.delete(DB_TABLE2, KEY_CDATE + "=? AND "+KEY_CID+"=?", whereArgs);
		
		
	//	String[] whereArgs = new String[] { dateDel,catDel,costDel };
	//	ourDatabase.delete(DB_TABLE2, KEY_CDATE + "=? AND "+KEY_CNAME+"=? AND "+KEY_CAMOUNT+"=?", whereArgs);
		Log.i("inside delete", dateDel);
	}

	public Cursor showResultInPdf(int date1, int date2) {
		Cursor c = null;
		try {
		//	String s = "select * FROM category where date2>="+date1;
			
		//	String s1 = "select * FROM category where date2>="+20140235 +" and date2<="+20140235;

		String s1 = "select * FROM category where Date2>="+date1 +" and Date2<="+date2;

			c = ourDatabase.rawQuery(s1, null);
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

				Log.e("category in db", c.getString(1));// correct till hereS
				Log.e("mode db", c.getString(4));
				Log.e("amt db", "amt" + c.getInt(2));
				Log.e("date db",   c.getString(3));
				

			}
			boolean test = c.moveToFirst();
			if (test == true) {
				Log.e("database", "show");
				Log.e("database", c.getString(0));
				return c;
			}
			
		} catch (Exception e) {
			Log.i("inside catch", "error");
		}
		return c;

	}
	// ////////////////working here...where clause of both dates
}