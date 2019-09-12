package com.example.tugas_dbjava1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class List_Data extends AppCompatActivity implements View.OnClickListener,RecyclerViewAdapter.OnUserClickListener {
    Context context;
    List<DataMahasiswa> mData;
    RecyclerView rvData;
    Button mbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        context = List_Data.this;

        initComponent();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvData.setLayoutManager(layoutManager);

        setupRecyclerView();
    }

    private void initComponent() {

        rvData = findViewById(R.id.recycler_View);
        mbtn = findViewById(R.id.btn_input);
        mbtn.setOnClickListener(this);
    }

    private void setupRecyclerView() {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        mData = databaseHelper.selectUserData();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(context, mData,this);
        rvData.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onUserClick(DataMahasiswa mahasiswa, String action) {
        if(action.equals("Update")) {
           Intent intent = new Intent(List_Data.this, Dialog.class);
           Toast.makeText(context, mahasiswa.getNomor()+"", Toast.LENGTH_SHORT).show();
           intent.putExtra("nomor", mahasiswa.getNomor());
           intent.putExtra("nama", mahasiswa.getNama());
           intent.putExtra("tgl", mahasiswa.getTanggalLahir());
           intent.putExtra("jk", mahasiswa.getJenisKelamin());
           intent.putExtra("alamat", mahasiswa.getAlamat());
           startActivity(intent);
        }
        else if (action.equals("Lihat")){
            Intent intent = new Intent(List_Data.this, Detail_Data.class);
            Toast.makeText(context, mahasiswa.getNomor()+"", Toast.LENGTH_SHORT).show();
            intent.putExtra("nomor", mahasiswa.getNomor());
            intent.putExtra("nama", mahasiswa.getNama());
            intent.putExtra("tgl", mahasiswa.getTanggalLahir());
            intent.putExtra("jk", mahasiswa.getJenisKelamin());
            intent.putExtra("alamat", mahasiswa.getAlamat());
            startActivity(intent);
        }
        else if (action.equals("Hapus")){
            DatabaseHelper db = new DatabaseHelper(context);
            db.delete(mahasiswa.getNomor());
            Toast.makeText(context, "Data berhasil di hapus", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(List_Data.this, List_Data.class));

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_input:
                startActivity(new Intent(List_Data.this, Dialog.class));
                break;
        }
    }
}
