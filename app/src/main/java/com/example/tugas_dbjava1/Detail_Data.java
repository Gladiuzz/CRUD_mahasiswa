package com.example.tugas_dbjava1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Detail_Data extends AppCompatActivity {
    Context context;
    EditText mNomor, mNama, mTglLahir, mKelamin, mAlamat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);
        initComponent();
        showData();

    }


    private void showData() {
        if (getIntent().getIntExtra("nomor", 0) != 0) {
            mNomor.setText(getIntent().getIntExtra("nomor", 0) + "");
            mNama.setText(getIntent().getStringExtra("nama"));
            mTglLahir.setText(getIntent().getStringExtra("tgl"));
            mKelamin.setText(getIntent().getStringExtra("jk"));
            mAlamat.setText(getIntent().getStringExtra("alamat"));
        }
    }

    public void initComponent() {
        mNomor = findViewById(R.id.edt_nomor1);
        mNama = findViewById(R.id.edt_nama1);
        mTglLahir = findViewById(R.id.edt_tglLahir1);
        mKelamin = findViewById(R.id.edt_JK1);
        mAlamat = findViewById(R.id.edt_alamat1);
    }
}
