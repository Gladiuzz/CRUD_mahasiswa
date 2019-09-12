package com.example.tugas_dbjava1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Dialog extends AppCompatActivity {

    EditText mNomor, mNama, mTglLahir, mKelamin, mAlamat;
    Button msubmit;
    Context context;
    String NOMOR = "nomor", NAMA = "nama", TANGGAL_LAHIR = "tgl", JENIS_KELAMIN = "jk", ALAMAT = "alamat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        context = this;

        initComponent();

        showData();

    }


    public void initComponent() {
        mNomor = findViewById(R.id.edt_nomor);
        mNama = findViewById(R.id.edt_nama);
        mTglLahir = findViewById(R.id.edt_tglLahir);
        mKelamin = findViewById(R.id.edt_JK);
        mAlamat = findViewById(R.id.edt_alamat);
        msubmit = findViewById(R.id.btn_sbmt);

        msubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getIntent().getIntExtra(NOMOR, 0) == 0) {
                    inputData();
                } else if (getIntent().getIntExtra(NOMOR, 0) != 0) {
                    updateData();
                }
            }
        });
    }

    private void showData() {
        if (getIntent().getIntExtra("nomor", 0) != 0) {
            mNomor.setText(getIntent().getIntExtra("nomor", 0) + "");
            mNama.setText(getIntent().getStringExtra("nama"));
            mTglLahir.setText(getIntent().getStringExtra("tgl"));
            mKelamin.setText(getIntent().getStringExtra("jk"));
            mAlamat.setText(getIntent().getStringExtra("alamat"));

            msubmit.setText("Update");
        }
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
        Toast.makeText(context, "Input berhasil", Toast.LENGTH_SHORT).show();

        mNomor.setText("");
        mNama.setText("");
        mTglLahir.setText("");
        mKelamin.setText("");
        mAlamat.setText("");
    }

    private void updateData() {
        DatabaseHelper db = new DatabaseHelper(context);
        DataMahasiswa mData = new DataMahasiswa();

        mData.setNomor(Integer.parseInt(mNomor.getText().toString()));
        mData.setNama(mNama.getText().toString().trim());
        mData.setTanggalLahir(mTglLahir.getText().toString());
        mData.setJenisKelamin(mKelamin.getText().toString());
        mData.setAlamat(mAlamat.getText().toString().trim());
        db.update(mData);

        Toast.makeText(context, "Update Berhasil", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Dialog.this, List_Data.class));
    }
}
