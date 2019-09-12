package com.example.tugas_dbjava1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.UserViewHolder> {
    Context context;
    List<DataMahasiswa> SelectUserdata;
    OnUserClickListener listener;

    public RecyclerViewAdapter(Context context, List<DataMahasiswa> SelectUserdata, OnUserClickListener listener){
        this.context = context;
        this.SelectUserdata = SelectUserdata;
        this.listener = listener;
    }


    public interface OnUserClickListener {
        void onUserClick(DataMahasiswa mahasiswa, String action);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_row, parent, false);
        UserViewHolder vh = new UserViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        final DataMahasiswa mMahasiswa = SelectUserdata.get(position);
        holder.mnama.setText(mMahasiswa.getNama()+"");
        holder.mlayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alertdialog = new AlertDialog.Builder(context);
                CharSequence[] CS = {"Lihat Data","Update Data","Hapus Data"};
                alertdialog.setTitle("Pilihan");
                alertdialog.setItems(CS, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                listener.onUserClick(mMahasiswa, "Lihat");
                                break;
                            case 1:
                                listener.onUserClick(mMahasiswa, "Update");
                                break;
                            case 2:
                                listener.onUserClick(mMahasiswa, "Hapus");

                        }
                    }
                });
                alertdialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return SelectUserdata.size();
    }




    public class UserViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mlayout1;
        TextView mnama;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mnama = itemView.findViewById(R.id.ctxtnama);
            mlayout1 = itemView.findViewById(R.id.layout1);
        }
    }
}
