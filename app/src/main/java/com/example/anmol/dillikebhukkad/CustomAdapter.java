package com.example.anmol.dillikebhukkad;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anmol on 7/27/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Viewholder>{

    ArrayList<Post> postArrayList = new ArrayList<>();
    Context c;

    CustomAdapter(ArrayList<Post> postArrayList,Context c){

        this.postArrayList = postArrayList;
        this.c = c;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(c);
        View v = layoutInflater.inflate(R.layout.single_list_item,parent,false);
        Viewholder vh = new Viewholder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        Post p = postArrayList.get(position);
        holder.userName.setText(p.getUsername());
        holder.time.setText(p.getTime());
        holder.title.setText(p.getTitle());
        holder.content.setText(p.getContent());


    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView userName,time,title,content;
        ImageView dpiv,ivContent;
        ImageButton like,bMark;

        public Viewholder(View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.tvUsername);
            time = (TextView) itemView.findViewById(R.id.tvTime);
            title = (TextView) itemView.findViewById(R.id.tvTitle);
            content = (TextView) itemView.findViewById(R.id.tvContent);
            dpiv = (ImageView) itemView.findViewById(R.id.ivdp);
            ivContent = (ImageView) itemView.findViewById(R.id.ivContentPhoto);
            like = (ImageButton) itemView.findViewById(R.id.ibLike);
            bMark = (ImageButton) itemView.findViewById(R.id.ibBookmark);
        }
    }
}
