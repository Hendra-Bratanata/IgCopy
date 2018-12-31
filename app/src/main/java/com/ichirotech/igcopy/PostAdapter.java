package com.ichirotech.igcopy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    public PostAdapter(Context context) {
        this.context = context;
    }

    Context context;
    public ArrayList<Jadwal> getListPost() {
        return listPost;
    }

    public void setListPost(ArrayList<Jadwal> listPost) {
        this.listPost = listPost;
    }

    ArrayList<Jadwal> listPost;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.judulPost.setText(getListPost().get(i).judud);
        Glide.with(context)
                .load(getListPost().get(i).gambar)
                .into(viewHolder.imgPost);
        Glide.with(context)
                .load(getListPost().get(i).gambar)
                .into(viewHolder.photoView);

    }

    @Override
    public int getItemCount() {
        return getListPost().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.photoView)
        PhotoView photoView;
        @BindView(R.id.imgPost)
        CircleImageView imgPost;
        @BindView(R.id.tvJudulPost)
        TextView judulPost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
