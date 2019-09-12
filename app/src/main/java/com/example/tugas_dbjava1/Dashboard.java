package com.example.tugas_dbjava1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout read, input, informasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        read = (RelativeLayout) findViewById(R.id.Lihat_Data);
        input = (RelativeLayout) findViewById(R.id.Input_data);
        informasi = (RelativeLayout) findViewById(R.id.Informasi);

        read.setOnClickListener(this);
        input.setOnClickListener(this);
        informasi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Lihat_Data:
                startActivity(new Intent(Dashboard.this, List_Data.class));
                break;
            case R.id.Input_data:
                startActivity(new Intent(Dashboard.this, Dialog.class));
                break;
//            case R.id.Informasi:
//                startActivity(new Intent(Dashboard.this,));
//                break;
        }
    }
}
