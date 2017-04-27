package com.example.sudo.lv4;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Sudo on 27.4.2017..
 */

public class AdapterRss extends RecyclerView.Adapter<AdapterRss.ViewHolder> {

    ArrayList<ItemNews> itemNews;
    Context context;
    public AdapterRss(Context context, ArrayList<ItemNews>itemNews){
        this.context = context;
        this.itemNews = itemNews;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterRss.ViewHolder holder, int position) {
        final ItemNews current = itemNews.get(position);

        holder.Title.setText(current.getTitle());
        holder.Category.setText(current.getCategory());
        holder.pubDate.setText(current.getPubDate());
        holder.Description.setText(current.getDescription());

        Picasso.with(context).load(current.getImage()).into(holder.Image);
        holder.cvNewsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVijest = new Intent(context, Vijest.class);
                intentVijest.putExtra("url", current.getLink());
                context.startActivity(intentVijest);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemNews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Title, Category, pubDate, Description;
        ImageView Image;
        CardView cvNewsItem;
        public ViewHolder(View itemView) {
            super(itemView);
            Title = (TextView) itemView.findViewById(R.id.tvTitle);
            Category = (TextView) itemView.findViewById(R.id.tvCategory);
            pubDate = (TextView) itemView.findViewById(R.id.tvPubDate);
            Description = (TextView) itemView.findViewById(R.id.tvDescription);
            Image = (ImageView) itemView.findViewById(R.id.ivImage);
            cvNewsItem = (CardView) itemView.findViewById(R.id.cvNewsItem);
        }
    }
}
