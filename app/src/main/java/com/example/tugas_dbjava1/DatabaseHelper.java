package com.example.tugas_dbjava1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "db_mahasiswa";
    private static final String TABLE_NAME = "db_mahasiswa";
    private static final String FIELD_NOMOR = "nim";
    private static final String FIELD_NAMA = "nama";
    private static final String FIELD_TANGGAL = "tanggal_lahir";
    private static final String FIELD_JENKEL = "jenis_kelamin";
    private static final String FIELD_ALAMAT = "alamat";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "Create Table " + TABLE_NAME + "(" + FIELD_NOMOR + " INTEGER PRIMARY KEY," + FIELD_NAMA + " TEXT," + FIELD_TANGGAL + " DATE," + FIELD_JENKEL + " TEXT," + FIELD_ALAMAT + " TEXT" + ")";
        db.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = ("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    //insert data
    public void insert(DataMahasiswa dataMahasiswa) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_NOMOR, dataMahasiswa.getNomor());
        values.put(FIELD_NAMA, dataMahasiswa.getNama());
        values.put(FIELD_TANGGAL, dataMahasiswa.getTanggalLahir());
        values.put(FIELD_JENKEL, dataMahasiswa.getJenisKelamin());
        values.put(FIELD_ALAMAT, dataMahasiswa.getAlamat());
        db.insert(TABLE_NAME, null, values);
    }

    //read data
    public List<DataMahasiswa> selectUserData() {
        ArrayList<DataMahasiswa> userList = new ArrayList<DataMahasiswa>();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {FIELD_NOMOR, FIELD_NAMA, FIELD_TANGGAL, FIELD_JENKEL, FIELD_ALAMAT};
        Cursor c = db.query(TABLE_NAME, columns, null, null, null, null, null);
        while (c.moveToNext()) {
            int nim = c.getInt(0);
            String nama = c.getString(1);
            String tanggalLahir = c.getString(2);
            String jenisKelamin = c.getString(3);
            String alamat = c.getString(4);

            DataMahasiswa dataMahasiswa = new DataMahasiswa();
            dataMahasiswa.setNomor(nim);
            dataMahasiswa.setNama(nama);
            dataMahasiswa.setTanggalLahir(tanggalLahir);
            dataMahasiswa.setJenisKelamin(jenisKelamin);
            dataMahasiswa.setAlamat(alamat);
            userList.add(dataMahasiswa);
        }
        return userList;
    }

    //delete data
    public void delete(int nim) {
        SQLiteDatabase db = getWritableDatabase();
        String queryDelete = FIELD_NOMOR + "='" + nim + "'";
        db.delete(TABLE_NAME, queryDelete, null);
    }

    //update data
    public void update(DataMahasiswa dataMahasiswa) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_NAMA, dataMahasiswa.getNama());
        values.put(FIELD_TANGGAL, dataMahasiswa.getTanggalLahir());
        values.put(FIELD_JENKEL, dataMahasiswa.getJenisKelamin());
        values.put(FIELD_ALAMAT, dataMahasiswa.getAlamat());
        String queryUpdate = FIELD_NOMOR + "='" + dataMahasiswa.getNomor() + "'";
        db.update(TABLE_NAME, values, queryUpdate, null);
    }


}
