package com.example.tugas_dbjava1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Dialog extends AppCompatActivity{

    EditText mNomor, mNama, mTglLahir, mKelamin, mAlamat;
    Button msubmit;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        initComponent();
    }

    public void initComponent(){
        mNomor = findViewById(R.id.edt_nomor);
        mNama = findViewById(R.id.edt_nama);
        mTglLahir = findViewById(R.id.edt_tglLahir);
        mKelamin = findViewById(R.id.edt_JK);
        mAlamat = findViewById(R.id.edt_alamat);
        msubmit = findViewById(R.id.btn_sbmt);

        msubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputData();
            }
        });


    }

    public void inputData() {
        DatabaseHelper db = new DatabaseHelper(context);
        DataMahasiswa mData = new DataMahasiswa();

        mData.setNomor(Integer.parseInt(mNomor.getText().toString()));
        mData.setNama(mNama.getText().toString().trim());
        mData.setTanggalLahir(mTglLahir.getText().toString());
        mData.setJenisKelamin(mKelamin.getText().toString());
        mData.setAlamat(mAlamat.getText().toString().trim());

        db.insert(mData);
    }


}
