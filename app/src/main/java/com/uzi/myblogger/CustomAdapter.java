package com.uzi.myblogger;

import android.content.ClipData;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.PostViewHolder>{
     MainActivity mainActivity;
    List<Item> items;

    public CustomAdapter(MainActivity mainActivity, List<Item> items) {
        this.mainActivity = mainActivity;
        this.items = items;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        final Item item = items.get(position);
        holder.postTtile.setText(item.getTitle());

        Document document = Jsoup.parse(item.getContent());
        holder.postDes.setText(document.text());

        Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
        final Matcher m = p.matcher(item.getContent());
        List<String> tokens = new ArrayList<>();
        while (m.find()){
            String token = m.group(1);
            tokens.add(token);
        }
        Glide.with(mainActivity).load(tokens.get(0)).into(holder.postImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity,Detail_Activity.class);
                intent.putExtra("url",item.getUrl());
                mainActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{
        ImageView postImage;
        TextView postTtile, postDes;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            postImage = (ImageView)itemView.findViewById(R.id.postImage);
            postTtile = (TextView) itemView.findViewById(R.id.postTitle);
            postDes = (TextView) itemView.findViewById(R.id.postDes);

        }
    }
}
