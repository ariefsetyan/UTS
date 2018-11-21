package com.example.arief.kasirsqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelper extends SQLiteOpenHelper {

    //DATABASE NAME
    public static final String DATABASE_NAME = "Kasir";
    //DATABASE VERSION
    public static final int DATABASE_VERSION = 1;

    //table user
    //TABLE NAME
    public static final String TABLE_USERS = "users";

    //TABLE USERS COLUMNS
    public static final String KEY_ID = "id";
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    //table barang
    //TABLE NAME
    public static final String TABLE_BARANGS = "barangs";
    //TABLE USERS COLUMNS
    public static final String ID_BARANG = "id";
    public static final String NAMA_BARANG = "namabarang";
    public static final String HARGA = "harga";
    public static final String STOK = "stok";
    public static final String ID_SUPPLIER_FK = "idsupplier";

    //table barang
    //TABLE NAME
    public static final String TABLE_SUPPLIER = "suppliers";
    //TABLE USERS COLUMNS
    public static final String ID_SUPPLIER = "id";
    public static final String NAMA_SUPPLIER = "namasupplier";
    public static final String ALAMAT_SUPPLIER = "alamatsupplier";
    public static final String NO_TELP = "notelp";

    public static final String TABLE_TRANSAKSI = "transaksis";
    //TABLE USERS COLUMNS
    public static final String ID_TRANSAKSI = "id";
    public static final String TANGGAL = "namasupplier";
    public static final String JUMLAH = "alamatsupplier";

    public static final String TABLE_DETIL_TRANSAKSI = "detiltransaksi";
    //TABLE USERS COLUMNS
    public static final String ID_TRANSAKSI_FK = "idtransaksi";
    public static final String ID_BARANG_FK = "idbarang";
    public static final String ID_USER_FK = "iduser";

    //SQL for creating users table
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT"
            + " ) ";

    //SQL for creating users table
    public static final String SQL_TABLE_BARANGS = " CREATE TABLE " + TABLE_BARANGS
            + " ( "
            + ID_BARANG + " INTEGER PRIMARY KEY, "
            + NAMA_BARANG + " TEXT, "
            + HARGA + " INTEGER, "
            + STOK + " INTEGER,"
            + ID_SUPPLIER_FK + " INTEGER,"
            + "FOREIGN KEY("+ID_SUPPLIER_FK+") REFERENCES suppliers("+ID_SUPPLIER+")"
            + " ) ";
//    public static final String SQL_TABLE_BARANGS = " CREATE TABLE " + TABLE_BARANGS
//            + " ( "
//            + ID_BARANG + " INTEGER PRIMARY KEY, "
//            + NAMA_BARANG + " TEXT, "
//            + HARGA + " INTEGER, "
//            + STOK + " INTEGER"
//            + " ) ";

    //SQL for creating users table
    public static final String SQL_TABLE_SUPPLIER = " CREATE TABLE " + TABLE_SUPPLIER
            + " ( "
            + ID_SUPPLIER + " INTEGER PRIMARY KEY, "
            + NAMA_SUPPLIER + " TEXT, "
            + ALAMAT_SUPPLIER + " TEXT, "
            + NO_TELP + " TEXT"
            + " ) ";

//    public static final String SQL_TABLE_TRANSAKSI = " CREATE TABLE " + TABLE_TRANSAKSI
//            + " ( "
//            + ID_TRANSAKSI + " INTEGER PRIMARY KEY, "
//            + TANGGAL + " TEXT, "
//            + JUMLAH + " INTEGER, "
//            + ID_BARANG + " INTEGER, "
//            + "FOREIGN KEY("+ID_BARANG_FK+") REFERENCES barangs("+ID_BARANG+")"
//            + " ) ";
    //SQL for creating users table
    public static final String SQL_TABLE_TRANSAKSI = " CREATE TABLE " + TABLE_TRANSAKSI
            + " ( "
            + ID_TRANSAKSI + " INTEGER PRIMARY KEY, "
            + TANGGAL + " TEXT, "
            + JUMLAH + " INTEGER "
            + " ) ";

    public static final String SQL_TABLE_DETIL_TRANSAKSI = " CREATE TABLE " + TABLE_DETIL_TRANSAKSI
            + " ( "
            + ID_TRANSAKSI_FK + " INTEGER, "
            + ID_BARANG_FK + " INTEGER, "
            + ID_USER_FK + " INTEGER, "
            + "FOREIGN KEY("+ID_TRANSAKSI_FK+") REFERENCES transaksis("+ID_TRANSAKSI+"),"
            + "FOREIGN KEY("+ID_BARANG_FK+") REFERENCES barangs("+ID_BARANG+"),"
            + "FOREIGN KEY("+ID_USER_FK+") REFERENCES users("+KEY_ID+")"
            + " ) ";

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
//        String sql = "CREATE TABLE"+DATABASE_NAME+"("+KEY_ID+"INTEGER PRIMARY KEY,"+COLUMN_NAME+"TEXT,"+COLUMN_NAME+")";
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
        sqLiteDatabase.execSQL(SQL_TABLE_BARANGS);
        sqLiteDatabase.execSQL(SQL_TABLE_SUPPLIER);
        sqLiteDatabase.execSQL(SQL_TABLE_TRANSAKSI);
        sqLiteDatabase.execSQL(SQL_TABLE_DETIL_TRANSAKSI);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_BARANGS);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_SUPPLIER);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_TRANSAKSI);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_DETIL_TRANSAKSI);
    }

    //using this method we can add users to user table
    public void addUser(User user) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(KEY_USER_NAME, user.userName);

        //Put email in  @values
        values.put(KEY_EMAIL, user.email);

        //Put password in  @values
        values.put(KEY_PASSWORD, user.password);

        // insert row
        long todo_id = db.insert(TABLE_USERS, null, values);
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{user.email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }

    public  void addbarang(Barang barang){
        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(ID_BARANG, barang.id);
        values.put(NAMA_BARANG, barang.namBarang);

        values.put(NAMA_BARANG, barang.namBarang);

        //Put email in  @values
        values.put(HARGA, barang.harga);

        //Put password in  @values
        values.put(STOK, barang.stok);

        //Put password in  @values
        values.put(ID_SUPPLIER_FK, barang.idSupplier);

        // insert row
        long todo_id = db.insert(TABLE_BARANGS, null, values);
    }

    public  void addsupplier(Supplier supplier){
        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(ID_SUPPLIER, supplier.id);

        values.put(NAMA_SUPPLIER, supplier.namaSuppier);

        values.put(ALAMAT_SUPPLIER, supplier.alamatSupplier);

        //Put email in  @values
        values.put(NO_TELP, supplier.noTelp);

        // insert row
        long todo_id = db.insert(TABLE_SUPPLIER, null, values);
    }

    public  void addtransaksi(Transaksi transaksi){
        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(ID_SUPPLIER, transaksi.id);

        values.put(NAMA_SUPPLIER, transaksi.tanggal);

        values.put(ALAMAT_SUPPLIER, transaksi.jumlah);

        // insert row
        long todo_id = db.insert(TABLE_TRANSAKSI, null, values);
    }
    public  void adddetiltransaksi(DetilTransaksi detilTransaksi){
        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(ID_SUPPLIER, detilTransaksi.idTransaksi);

        values.put(NAMA_SUPPLIER, detilTransaksi.idBarang);

        values.put(ALAMAT_SUPPLIER, detilTransaksi.idUser);

        // insert row
        long todo_id = db.insert(TABLE_DETIL_TRANSAKSI, null, values);
    }

}
