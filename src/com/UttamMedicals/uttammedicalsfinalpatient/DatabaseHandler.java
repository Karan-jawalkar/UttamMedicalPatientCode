package com.UttamMedicals.uttammedicalsfinalpatient;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version

	private SQLiteDatabase sqLiteDB;

	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "UM_DATABASE";

	// Table Name REGISTER
	private static final String TABLE_REGISTER = "REGISTERATION";

	private static final String KEY_PATIENT_NAME = "NAME";
	private static final String KEY_DRNAME = "DRNAME";
	private static final String KEY_ADDRESS = "ADDRESS";
	private static final String KEY_PHONE = "PHONE";
	private static final String KEY_EMAIL = "EMAIL";
	private static final String KEY_PASSWORD = "PASSWORD";

	//Table name ORDER_HEADER
	private static final String TABLE_ORDER_HEADER = "ORDER_HEADER";
	private static final String KEY_ORDER_NO = "ORDER_NO";
	private static final String KEY_DATE = "DATE";
	private static final String KEY_TIME = "TIME";
	private static final String KEY_USERNAME = "USERNAME";
	private static final String KEY_REQ_DEL_DATE = "REQ_DEL_DATE";
	private static final String KEY_REQ_DEL_TIME_FROM = "REQ_DEL_TIME_FROM";
	private static final String KEY_REQ_DEL_TIME_TO = "REQ_DEL_TIME_TO";
	private static final String KEY_ACTUAL_DEL_DATE = "ACTUAL_DEL_DATE";
	private static final String KEY_ACTUAL_DEL_TIME = "ACTUAL_DEL__TIME";
	private static final String KEY_ORDER_STATUS = "ORDER_STATUS";


	//Table name ORDER_DETAIL_FINAL
	private static final String TABLE_ORDER_DETAIL_FINAL = "ORDER_DETAIL_FINAL";
	private static final String KEY_ORDER_NO_FK_FINAL = "ORDER_NO_FINAL";
	private static final String KEY_SR_NO = "SR_NO_FINAL";
	private static final String KEY_QTY = "QTY";
	private static final String KEY_ITEM_TYPE_FINAL = "ITEM_TYPE_FINAL";
	private static final String KEY_ITEM_DESCRIPTION_FINAL = "ITEM_DESCRIPTION_FINAL";
	private static final String KEY_ITEM_NO_FINAL = "ITEM_NO_FINAL";
	private static final String KEY_PACKED = "PACKED_FINAL";
	private static final String KEY_DELIVERY_DISPATCH = "DELIVERY_DISPATCH";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_REGISTER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_REGISTER + "("
				+ KEY_PATIENT_NAME  + " TEXT," + KEY_DRNAME + " TEXT," + KEY_ADDRESS + " TEXT, "
				+ KEY_PHONE + " TEXT," + KEY_EMAIL  + " TEXT," + KEY_PASSWORD + " TEXT"+ ")";
		db.execSQL(CREATE_REGISTER_TABLE);

		String CREATE_ORDER_HEADER = "CREATE TABLE IF NOT EXISTS " + TABLE_ORDER_HEADER + "("
				+ KEY_ORDER_NO + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_DATE + " TEXT, " + KEY_TIME + " TEXT, "
				+ KEY_USERNAME + " TEXT, " + KEY_REQ_DEL_DATE + " TEXT, " + KEY_REQ_DEL_TIME_FROM + " TEXT, " + KEY_REQ_DEL_TIME_TO
				+ " TEXT, " + KEY_ACTUAL_DEL_DATE + " TEXT, " + KEY_ACTUAL_DEL_TIME + " TEXT, " + KEY_ORDER_STATUS + " TEXT " +  ")";
		db.execSQL(CREATE_ORDER_HEADER);


		String CREATE_ORDER_DETAIL_FINAL = "CREATE TABLE " + TABLE_ORDER_DETAIL_FINAL + " ("
				+ KEY_SR_NO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ KEY_ITEM_NO_FINAL + " INTEGER, " + KEY_ITEM_TYPE_FINAL + " TEXT, " + KEY_ITEM_DESCRIPTION_FINAL + " TEXT, "
				+ KEY_QTY+ " TEXT, " +KEY_PACKED+ " TEXT," +KEY_DELIVERY_DISPATCH + " TEXT," + KEY_ORDER_NO_FK_FINAL + " INTEGER," + " FOREIGN KEY ("+KEY_ORDER_NO_FK_FINAL+") REFERENCES "+TABLE_ORDER_HEADER+" ("+KEY_ORDER_NO+"));";
		db.execSQL(CREATE_ORDER_DETAIL_FINAL);



	}
	//KEY_ORDER_NO_FK + "INTEGER,"+ " FOREIGN KEY ("+KEY_ORDER_NO_FK+") REFERENCES "+TABLE_ORDER_HEADER+" ("+KEY_ORDER_NO+") +

	public void insertEntry(String Name,String Drname, String address, String phone, String email, String Password)
	{

		//		INSERT INTO REGISTER(PHONE,EMAIL,DRNAME,NAME,PASSWORD,ADDRESS) 

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues newValues = new ContentValues();
		// Assign values for each row.
		newValues.put(KEY_PATIENT_NAME, Name);
		newValues.put(KEY_DRNAME, Drname);
		newValues.put(KEY_ADDRESS, address);
		newValues.put(KEY_PHONE,phone);
		newValues.put(KEY_EMAIL,email);	
		newValues.put(KEY_PASSWORD,Password);
		//Data 
		// Insert the row into your table
		db.insert(TABLE_REGISTER, null, newValues);
		db.close();
		//	Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
	}

	public void insertOrder_header(String current_date, String current_time,
			String userDetails, String desiredDate, String fromTime,
			String toTime) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_DATE, current_date);
		newValues.put(KEY_TIME, current_time);
		newValues.put(KEY_USERNAME, userDetails);
		newValues.put(KEY_REQ_DEL_DATE, desiredDate);
		newValues.put(KEY_REQ_DEL_TIME_FROM,fromTime);
		newValues.put(KEY_REQ_DEL_TIME_TO,toTime);
		db.insert(TABLE_ORDER_HEADER, null, newValues);	
		db.close();
	}

	//	public void insertOrder_detail(String type, String path, String list,int get_order_no_fromorderheader) {
	//		// TODO Auto-generated method stub
	//		SQLiteDatabase db = this.getWritableDatabase();
	//		ContentValues newValues = new ContentValues();
	//		newValues.put(KEY_ITEM_TYPE,type);
	//		newValues.put(KEY_ITEM_DESCRIPTION,path);
	//		newValues.put(KEY_ITEM_DESCRIPTION, list);
	//		newValues.put(KEY_ORDER_NO_FK, get_order_no_fromorderheader);
	//		//		newValues.put("KEY", value)
	//		db.insert(TABLE_ORDER_DETAIL, null, newValues);
	//
	//	}

	public void insertOrder_detail_final(String type, String path, String list,int get_order_no_fromorderheader, int i, String qty) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_ITEM_TYPE_FINAL,type);
		//newValues.put(KEY_ITEM_DESCRIPTION_FINAL,path);
		newValues.put(KEY_ITEM_DESCRIPTION_FINAL, list);
		newValues.put(KEY_ITEM_NO_FINAL, i);
		newValues.put(KEY_QTY, qty);
		newValues.put(KEY_ORDER_NO_FK_FINAL, get_order_no_fromorderheader);
		//		newValues.put("KEY", value)
		db.insert(TABLE_ORDER_DETAIL_FINAL, null, newValues);
		db.close();

	}

	public void insertOrder_detail_final_image(String type, String path,int get_order_no_fromorderheader, int i) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_ITEM_TYPE_FINAL,type);
		newValues.put(KEY_ITEM_DESCRIPTION_FINAL,path);
		newValues.put(KEY_ORDER_NO_FK_FINAL, get_order_no_fromorderheader);
		newValues.put(KEY_ITEM_NO_FINAL, i);
		//		newValues.put("KEY", value)
		db.insert(TABLE_ORDER_DETAIL_FINAL, null, newValues);
		db.close();

	}


	public void insertOrder_detail_final_both(String type, String path, String list, int get_order_no_fromorderheader, int i, String qty) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_ITEM_TYPE_FINAL,type);
		newValues.put(KEY_ITEM_DESCRIPTION_FINAL,path);
		newValues.put(KEY_ITEM_DESCRIPTION_FINAL, list);
		newValues.put(KEY_ITEM_NO_FINAL, i);
		newValues.put(KEY_QTY, qty);
		newValues.put(KEY_ORDER_NO_FK_FINAL, get_order_no_fromorderheader);
		//		newValues.put("KEY", value)
		db.insert(TABLE_ORDER_DETAIL_FINAL, null, newValues);
		db.close();

	}

	public void insertOrder_detail_final_bothImage(String type, String path, int get_order_no_fromorderheader,int i) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_ITEM_TYPE_FINAL,type);
		newValues.put(KEY_ITEM_DESCRIPTION_FINAL,path);
		newValues.put(KEY_ORDER_NO_FK_FINAL, get_order_no_fromorderheader);
		newValues.put(KEY_ITEM_NO_FINAL, i);
		//		newValues.put("KEY", value)
		db.insert(TABLE_ORDER_DETAIL_FINAL, null, newValues);
		db.close();
	}




	/*private static final String TABLE_ORDER_HEADER = "ORDER_HEADER";
	private static final String KEY_ORDER_NO = "ORDER_NO";
	private static final String KEY_DATE = "DATE";
	private static final String KEY_TIME = "TIME";
	private static final String KEY_USERNAME = "USERNAME";
	private static final String KEY_REQ_DEL_DATE = "REQ_DEL_DATE";
	private static final String KEY_REQ_DEL_TIME_FROM = "REQ_DEL_TIME_FROM";
	private static final String KEY_REQ_DEL_TIME_TO = "REQ_DEL_TIME_TO";
	private static final String KEY_ACTUAL_DEL_DATE = "ACTUAL_DEL_DATE";
	private static final String KEY_ACTUAL_DEL_TIME = "ACTUAL_DEL__TIME";
	private static final String KEY_ORDER_PACKED = "ORDER_PACKED";
	private static final String KEY_ORDER_DELIVERED = "ORDER_DELIVERED";
	private static final String KEY_ORDER_REJECTED = "ORDER_REJECTED";
	 */

	/*	public void insertPath(String Path){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues newValues = new ContentValues();
		newValues.put("PRESCRIPTION_PATH", Path);
		db.insert(TABLE_PRESCRIPTION, null, newValues);
	}*/

	public String check(String username){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor=db.query("ADMIN", null, " username=?", new String[]{username}, null, null, null);
		if(cursor.getCount()<1) // UserName Not Exist
		{
			cursor.close();
			return "NOT EXIST";
		}
		cursor.moveToFirst();
		String password= cursor.getString(cursor.getColumnIndex("Password"));
		cursor.close();
		return password;	
		
	}	

	/*	public String getPath(){
		SQLiteDatabase db = this.getReadableDatabase();
		String path = null;

		String execute_query = "SELECT PRECRIPTION_PATH FROM"+ TABLE_PRESCRIPTION;
		Cursor cursor = db.rawQuery(execute_query, null);

		if(cursor.moveToFirst()){
			do{

				path = cursor.getString(cursor.getColumnIndex("EMAIL"));
				if (path == "") {

					path = "fail";

				}


			}while(cursor.moveToNext());
		}

		return path;

	}
	 */
	public String getUsername()
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String USER_DETAILS = null;

		String execute_query = "SELECT EMAIL FROM " +TABLE_REGISTER;
		Cursor cursor = db.rawQuery(execute_query, null);

		if(cursor.moveToFirst()){
			do{

				USER_DETAILS = cursor.getString(cursor.getColumnIndex("EMAIL"));
				if (USER_DETAILS == "") {

					USER_DETAILS = "fail";

				}


			}while(cursor.moveToNext());
		}
		return USER_DETAILS;
	}

	public String getname()
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String USER_DETAILS = null;

		String execute_query = "SELECT NAME FROM " +TABLE_REGISTER;
		Cursor cursor = db.rawQuery(execute_query, null);

		if(cursor.moveToFirst()){
			do{

				USER_DETAILS = cursor.getString(cursor.getColumnIndex("NAME"));
				if (USER_DETAILS == "") {

					USER_DETAILS = "fail";

				}


			}while(cursor.moveToNext());
		}
		return USER_DETAILS;
	}
	public String getAdminUsername()
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String USER_DETAILS = null;

		String execute_query = "SELECT USERNAME FROM ADMIN";
		Cursor cursor = db.rawQuery(execute_query, null);

		if(cursor.moveToFirst()){
			do{

				USER_DETAILS = cursor.getString(cursor.getColumnIndex("USERNAME"));
				if (USER_DETAILS == "") {

					USER_DETAILS = "fail";

				}


			}while(cursor.moveToNext());
		}
		return USER_DETAILS;
	}
	public void insertadminEntry(String username, String password) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues newValues = new ContentValues();
		newValues.put("USERNAME",username);	
		newValues.put("PASSWORD",	password);
		//Data 
		// Insert the row into your table
		db.insert("ADMIN", null, newValues);
		//	Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();

	}
	public String insertOrder(String userDetails, String list_item1, String desiredDate, String fromTime, String toTime) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues newValues = new ContentValues();
		newValues.put("USERDETAILS",userDetails);
		newValues.put("LIST",list_item1);
		newValues.put("DESIRED_DATE",desiredDate);
		newValues.put("FROMTIME",fromTime);
		newValues.put("TOTIME",toTime);

		long sstr_long = db.insert("TEMP", null, newValues);
		if (sstr_long == -1) {
			return "fail";
		}
		return "success";
		
	}



	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public int get_orderno(String tm) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getReadableDatabase();
		int order_no = 0;
		//String USER_DETAILS = null;

		//String execute_query = "SELECT ORDER_NO FROM " + TABLE_ORDER_HEADER;
		String selectQuery = "SELECT ORDER_NO FROM ORDER_HEADER WHERE TIME='"+tm+"'";
		Cursor cursor = db.rawQuery(selectQuery, null);

		if(cursor.moveToFirst()){
			do{

				//order_no = cursor.getString(cursor.getColumnIndex("ORDER_NO"));
				order_no = cursor.getInt(cursor.getColumnIndex("ORDER_NO"));

			}while(cursor.moveToNext());
		}

		return order_no;
	}

	public String see_path_order_detail_final() {

		SQLiteDatabase db = this.getReadableDatabase();
		String path = null;

		String selectQuery = "SELECT ITEM_DESCRIPTION_FINAL FROM " + TABLE_ORDER_DETAIL_FINAL;
		Cursor cursor = db.rawQuery(selectQuery, null);

		if(cursor.moveToFirst()){
			do{

				path = cursor.getString(cursor.getColumnIndex("ITEM_DESCRIPTION_FINAL"));
				//order_no = cursor.getInt(cursor.getColumnIndex("ORDER_NO"));

			}while(cursor.moveToNext());
		}

		// TODO Auto-generated method stub
		return path+"null";
	}


	public String getAllData()
	{
		String AllLatAndLong ="";
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT ITEM_TYPE_FINAL, ITEM_DESCRIPTION_FINAL, ORDER_NO_FINAL, ITEM_NO_FINAL FROM ORDER_DETAIL_FINAL; ";
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				String str_ITEM_TYPE_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_TYPE_FINAL")) ;
				String str_ITEM_DESCRIPTION_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_DESCRIPTION_FINAL")) ;
				String str_ORDER_NO_FINAL = cursor.getString(cursor.getColumnIndex("ORDER_NO_FINAL")) ;
				String str_ITEM_NO_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_NO_FINAL")) ;
				//				String All=(str_long+"*"+str_lat)+" ";
				String All=(str_ITEM_TYPE_FINAL+"*"+str_ITEM_DESCRIPTION_FINAL+"*"+str_ORDER_NO_FINAL+"*"+str_ITEM_NO_FINAL)+"finsh";
				AllLatAndLong = AllLatAndLong + All;


			} while (cursor.moveToNext());
		}	
		cursor.close();

		return AllLatAndLong;
	}


	public String getpatientlist() {
		// TODO Auto-generated method stub

		String list ="";
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT NAME, DRNAME, ADDRESS, PHONE, EMAIL, PASSWORD FROM REGISTERATION; ";
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				String str_NAME = cursor.getString(cursor.getColumnIndex("NAME")) ;
				String str_DRNAME = cursor.getString(cursor.getColumnIndex("DRNAME")) ;
				String str_ADDRESS = cursor.getString(cursor.getColumnIndex("ADDRESS")) ;
				String str_PHONE = cursor.getString(cursor.getColumnIndex("PHONE")) ;
				String str_EMAIL = cursor.getString(cursor.getColumnIndex("EMAIL")) ;
				String str_PASSWORD = cursor.getString(cursor.getColumnIndex("PASSWORD")) ;
				//				String All=(str_long+"*"+str_lat)+" ";

				list = (str_NAME+"`"+str_DRNAME+"`"+str_ADDRESS+"`"+str_PHONE+"`"+str_EMAIL+"`"+str_PASSWORD+"`");
			} while (cursor.moveToNext());
		}	
		cursor.close();

		return list;		
	}

	public String getOrder() {
		// TODO Auto-generated method stub

		String order ="";
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT ITEM_TYPE_FINAL, ITEM_DESCRIPTION_FINAL, ORDER_NO_FINAL, ITEM_NO_FINAL FROM ORDER_DETAIL_FINAL; ";
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				String str_ITEM_TYPE_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_TYPE_FINAL")) ;
				String str_ITEM_DESCRIPTION_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_DESCRIPTION_FINAL")) ;
				String str_ORDER_NO_FINAL = cursor.getString(cursor.getColumnIndex("ORDER_NO_FINAL")) ;
				String str_ITEM_NO_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_NO_FINAL")) ;
				//				String All=(str_long+"*"+str_lat)+" ";
				order=(str_ORDER_NO_FINAL +"`"+str_ITEM_NO_FINAL+"`"+str_ITEM_TYPE_FINAL+"`"+str_ITEM_DESCRIPTION_FINAL );
			} while (cursor.moveToNext());
		}	
		cursor.close();

		return order;
	}


	// item details getting by its item id
	public String getItemsDetailsbyid(int item_id, int order_id) {
		// TODO Auto-generated method stub

		String order ="";
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT ITEM_TYPE_FINAL, ITEM_DESCRIPTION_FINAL, ORDER_NO_FINAL, ITEM_NO_FINAL FROM ORDER_DETAIL_FINAL WHERE ITEM_NO_FINAL = '"+item_id+"' AND  ORDER_NO_FINAL = '"+order_id+"'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				String str_ITEM_TYPE_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_TYPE_FINAL")) ;
				String str_ITEM_DESCRIPTION_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_DESCRIPTION_FINAL")) ;
				String str_ORDER_NO_FINAL = cursor.getString(cursor.getColumnIndex("ORDER_NO_FINAL")) ;
				String str_ITEM_NO_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_NO_FINAL")) ;
				//				String All=(str_long+"*"+str_lat)+" ";
				order=(str_ORDER_NO_FINAL +"`"+str_ITEM_NO_FINAL+"`"+str_ITEM_TYPE_FINAL+"`"+str_ITEM_DESCRIPTION_FINAL );
			} while (cursor.moveToNext());
		}	
		cursor.close();

		return order;
	}





	// order details getting by its order id
	public String getOrderDetailsbyid(int order_id) {
		// TODO Auto-generated method stub
		String allorder = "";
		String order ="";
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT ITEM_TYPE_FINAL, ITEM_DESCRIPTION_FINAL, ORDER_NO_FINAL, ITEM_NO_FINAL FROM ORDER_DETAIL_FINAL WHERE ORDER_NO_FINAL = '"+order_id+"'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				String str_ITEM_TYPE_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_TYPE_FINAL")) ;
				String str_ITEM_DESCRIPTION_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_DESCRIPTION_FINAL")) ;
				String str_ORDER_NO_FINAL = cursor.getString(cursor.getColumnIndex("ORDER_NO_FINAL")) ;
				String str_ITEM_NO_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_NO_FINAL")) ;
				//				String All=(str_long+"*"+str_lat)+" ";
				order=(str_ORDER_NO_FINAL +"`"+str_ITEM_NO_FINAL+"`"+str_ITEM_TYPE_FINAL+"`"+str_ITEM_DESCRIPTION_FINAL+"`" );
				allorder = allorder + order;
			} while (cursor.moveToNext());
		}	
		cursor.close();

		return allorder;
	}

	public String getOrderheader(int data) {
		// TODO Auto-generated method stub
		String order ="";
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT ORDER_NO, DATE, TIME, USERNAME, REQ_DEL_DATE, REQ_DEL_TIME_FROM, REQ_DEL_TIME_TO FROM ORDER_HEADER WHERE ORDER_NO = '"+data+"'";
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				String str_ORDER_NO = cursor.getString(cursor.getColumnIndex("ORDER_NO")) ;
				String str_DATE = cursor.getString(cursor.getColumnIndex("DATE")) ;
				String str_TIME = cursor.getString(cursor.getColumnIndex("TIME")) ;
				String str_USERNAME = cursor.getString(cursor.getColumnIndex("USERNAME")) ;
				String str_REQDATE = cursor.getString(cursor.getColumnIndex("REQ_DEL_DATE")) ;
				String str_REQTIMEFROM = cursor.getString(cursor.getColumnIndex("REQ_DEL_TIME_FROM")) ;
				String str_REQTIMETO = cursor.getString(cursor.getColumnIndex("REQ_DEL_TIME_TO")) ;
				//				String All=(str_long+"*"+str_lat)+" ";
				order=(str_ORDER_NO+"`"+ str_DATE +"`"+str_TIME+"`"+str_USERNAME+"`"+str_REQDATE+"`"+str_REQTIMEFROM+"`"+str_REQTIMETO );
			} while (cursor.moveToNext());
		}	
		cursor.close();

		return order;
	}

	public void updateOrderHeader(String actualDate, String actualTime, String id) {
		// TODO Auto-generated method stub

		SQLiteDatabase db = this.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put("ACTUAL_DEL_DATE", actualDate);
		values.put("ACTUAL_DEL__TIME", actualTime);

		db.update("ORDER_HEADER", values,KEY_ORDER_NO+"="+id , null);
		db.close();
	}

	public String getorderandusername() {
		// TODO Auto-generated method stub
		String order ="";
		String all ="";
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT USERNAME FROM ORDER_HEADER";
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				String str_USERNAME = cursor.getString(cursor.getColumnIndex("USERNAME")) ;
				//				String All=(str_long+"*"+str_lat)+" ";
				order=str_USERNAME+"`";
				all+=order;
			} while (cursor.moveToNext());
		}	
		cursor.close();

		return all;

	}


	public String getorderandid() {
		// TODO Auto-generated method stub
		String order ="";
		String all ="";
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT ORDER_NO FROM ORDER_HEADER";
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				String str_ORDER_NO = cursor.getString(cursor.getColumnIndex("ORDER_NO")) ;
				//				String All=(str_long+"*"+str_lat)+" ";
				order=str_ORDER_NO+"`";
				all+=order;
			} while (cursor.moveToNext());
		}	
		cursor.close();

		return all;

	}

	public String getOrderDetails(int data) {
		// TODO Auto-generated method stub
		String allorder = "";
		String order ="";
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT ITEM_DESCRIPTION_FINAL, ITEM_NO_FINAL, QTY FROM ORDER_DETAIL_FINAL WHERE ORDER_NO_FINAL = '"+data+"'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				String str_ITEM_DESCRIPTION_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_DESCRIPTION_FINAL")) ;
				String str_ITEM_NO_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_NO_FINAL")) ;
				String str_QTY = cursor.getString(cursor.getColumnIndex("QTY")) ;


				//				String All=(str_long+"*"+str_lat)+" ";
				order=(str_ITEM_NO_FINAL+"`"+str_ITEM_DESCRIPTION_FINAL+"`"+str_QTY)+"`";
				allorder = allorder + order;
			} while (cursor.moveToNext());
		}	
		cursor.close();

		return allorder;


		//return null;
	}

	// function to get item_no
	public String getitemno(int data) {
		// TODO Auto-generated method stub
		String all = "";
		String item_no ="";
		String type ="L";
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT ITEM_NO_FINAL FROM ORDER_DETAIL_FINAL WHERE ORDER_NO_FINAL='"+data+"' AND  ITEM_TYPE_FINAL = '"+type+"'  ";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				//String str_ITEM_DESCRIPTION_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_DESCRIPTION_FINAL")) ;
				String str_ITEM_NO_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_NO_FINAL")) ;
				//	String str_QTY = cursor.getString(cursor.getColumnIndex("QTY")) ;


				//				String All=(str_long+"*"+str_lat)+" ";
				item_no=str_ITEM_NO_FINAL+"`";
				all+= item_no;
				//order;
			} while (cursor.moveToNext());
		}	
		cursor.close();

		return all;
	}
	public String getitemname(int data) {
		// TODO Auto-generated method stub
		String all = "";
		String type = "L";
		String item_name ="";
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT ITEM_DESCRIPTION_FINAL FROM ORDER_DETAIL_FINAL WHERE ORDER_NO_FINAL='"+data+"' AND  ITEM_TYPE_FINAL = '"+type+"' ";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				//String str_ITEM_DESCRIPTION_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_DESCRIPTION_FINAL")) ;
				String str_ITEM_NO_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_DESCRIPTION_FINAL")) ;
				//	String str_QTY = cursor.getString(cursor.getColumnIndex("QTY")) ;


				//				String All=(str_long+"*"+str_lat)+" ";
				item_name=str_ITEM_NO_FINAL+"`";
				all+= item_name;
				//order;
			} while (cursor.moveToNext());
		}	
		cursor.close();

		return all;

	}

	public String getitemqty(int data) {
		// TODO Auto-generated method stub
		String all = "";
		String item_qty ="";
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT QTY FROM ORDER_DETAIL_FINAL WHERE ORDER_NO_FINAL='"+data+"' ";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				//String str_ITEM_DESCRIPTION_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_DESCRIPTION_FINAL")) ;
				String str_ITEM_NO_FINAL = cursor.getString(cursor.getColumnIndex("QTY")) ;
				//	String str_QTY = cursor.getString(cursor.getColumnIndex("QTY")) ;


				//				String All=(str_long+"*"+str_lat)+" ";
				item_qty=str_ITEM_NO_FINAL+"`";
				all+= item_qty;
				//order;
			} while (cursor.moveToNext());
		}	
		cursor.close();

		return all;

	}
	/*
	 private static final String KEY_ORDER_NO = "ORDER_NO";
	private static final String KEY_DATE = "DATE";
	private static final String KEY_TIME = "TIME";
	private static final String KEY_USERNAME = "USERNAME";
	private static final String KEY_REQ_DEL_DATE = "REQ_DEL_DATE";
	private static final String KEY_REQ_DEL_TIME_FROM = "REQ_DEL_TIME_FROM";
	private static final String KEY_REQ_DEL_TIME_TO = "REQ_DEL_TIME_TO";
	private static final String KEY_ACTUAL_DEL_DATE = "ACTUAL_DEL_DATE";
	private static final String KEY_ACTUAL_DEL_TIME = "ACTUAL_DEL__TIME";
	private static final String KEY_ORDER_PACKED = "ORDER_PACKED";
	private static final String KEY_ORDER_DELIVERED = "ORDER_DELIVERED";
	private static final String KEY_ORDER_REJECTED = "ORDER_REJECTED";

	 */
	/*
	public String getMessageByDate(String dateID) {
		String Message = null;
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT msg from MSG WHERE date='"+dateID+"'";
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				Message = cursor.getString(cursor.getColumnIndex("msg"));
			} while (cursor.moveToNext());
		}	
		cursor.close();
		return Message;
	}*/

	public String getitemnameimage(int data) {
		// TODO Auto-generated method stub
		//String all = "";
		String item_name ="";
		String type = "I";
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT ITEM_DESCRIPTION_FINAL FROM ORDER_DETAIL_FINAL WHERE ORDER_NO_FINAL='"+data+"' AND  ITEM_TYPE_FINAL = '"+type+"'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				//String str_ITEM_DESCRIPTION_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_DESCRIPTION_FINAL")) ;
				String str_ITEM_NO_FINAL = cursor.getString(cursor.getColumnIndex("ITEM_DESCRIPTION_FINAL")) ;
				//	String str_QTY = cursor.getString(cursor.getColumnIndex("QTY")) ;


				//				String All=(str_long+"*"+str_lat)+" ";
				item_name=str_ITEM_NO_FINAL;
				//	all+= item_name;
				//order;
			} while (cursor.moveToNext());
		}	
		cursor.close();

		return item_name;


	}

	public void updateStatus(int id,String status) {
		// TODO Auto-generated method stub

		SQLiteDatabase db = this.getWritableDatabase();
		
	//	Cursor c = db.rawQuery("UPDATE"+ TABLE_ORDER_DETAIL_FINAL + " SET " + KEY_PACKED + " = " + status + " WHERE " + KEY_ITEM_NO_FINAL + " = \""+id+"\"",null);
		String str = "UPDATE"+ TABLE_ORDER_DETAIL_FINAL + " SET " + KEY_PACKED + " = " + status + " WHERE " + KEY_ITEM_NO_FINAL + " = \""+id+"\"";
		
		//db.execSQL(c);
		db.execSQL(str);
//		ContentValues values = new ContentValues();
//		values.put("PACKED_FINAL", status);
//
//		db.update("ORDER_DETAIL_FINAL", values,"ITEM_NO_FINAL"+"="+id , null);
		db.close();

	}
//	
	public void updateThis(String id,String stustus) {
		// TODO Auto-generated method stub

		SQLiteDatabase db = this.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_PACKED, stustus);

		db.update("ORDER_DETAIL_FINAL", values,"ITEM_NO_FINAL"+"="+id , null);

	}
//	public void update_byID(int id,String stustus){
//		ContentValues values = new ContentValues();
//		values.put(KEY_PACKED, stustus);
//		sqLiteDB.update(TABLE_ORDER_DETAIL_FINAL, values, KEY_ITEM_NO_FINAL+"="+id, null);
//	}

	}