package com.reciproci.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.reciproci.myapplication.R;
import com.reciproci.myapplication.models.Article;
import com.reciproci.myapplication.models.News;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.CustomView> {
    private Context context;
    private List<Article> newsList;

    public NewsAdapter(Context context, List<Article> dataList) {
        this.context = context;
        this.newsList = dataList;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.news_item, parent, false);
        return new CustomView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomView holder, final int position) {
        holder.newsTitle.setText(newsList.get(position).getTitle());
        holder.newsDescription.setText(newsList.get(position).getDescription());
        Glide.with(context)
                .load(newsList.get(position).getUrlToImage())
                .into(holder.bannerImage);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    class CustomView extends RecyclerView.ViewHolder {
        ImageView bannerImage;
        TextView newsTitle;
        TextView newsDescription;

        private CustomView(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.txt_title);
            bannerImage = itemView.findViewById(R.id.img_banner);
            newsDescription = itemView.findViewById(R.id.txt_description);
        }
    }
}

