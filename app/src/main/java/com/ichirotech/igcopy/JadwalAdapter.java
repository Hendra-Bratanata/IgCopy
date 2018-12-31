package com.ichirotech.igcopy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {

    Context context;

    public JadwalAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Jadwal> getListJadwal() {
        return listJadwal;
    }

    public void setListJadwal(ArrayList<Jadwal> listJadwal) {
        this.listJadwal = listJadwal;
    }

    ArrayList<Jadwal> listJadwal;



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.status,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.Status.setText(getListJadwal().get(i).judud);
        Glide.with(context)
                .load(getListJadwal().get(i).gambar)
                .into(viewHolder.imgView);
    }

    @Override
    public int getItemCount() {
        return getListJadwal().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        @BindView(R.id.imgView)
        CircleImageView imgView;
        @BindView(R.id.tvStatus)
        TextView Status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
