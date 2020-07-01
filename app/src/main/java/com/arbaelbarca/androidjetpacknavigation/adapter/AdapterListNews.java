package com.arbaelbarca.androidjetpacknavigation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arbaelbarca.androidjetpacknavigation.onclick.OnClickItem;
import com.arbaelbarca.androidjetpacknavigation.R;
import com.arbaelbarca.depedencymodule.model.modeltopheadlines.ArticlesItem;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterListNews extends RecyclerView.Adapter<AdapterListNews.ViewHolder> {

    private Context context;

    private ArrayList<ArticlesItem> articlesItemArrayList;

    public AdapterListNews(Context context) {
        this.context = context;
        articlesItemArrayList = new ArrayList<>();
    }

    public void setArticlesItemArrayList(ArrayList<ArticlesItem> articlesItemArrayList) {
        this.articlesItemArrayList = articlesItemArrayList;
    }

    private OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public ArrayList<ArticlesItem> getArticlesItemArrayList() {
        return articlesItemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ArticlesItem articlesItem = articlesItemArrayList.get(position);
        viewHolder.txtTitleNews.setText(articlesItem.getTitle());
        viewHolder.txtDescNews.setText(articlesItem.getDescription());

        if (articlesItem.getSource() != null)
            viewHolder.txtKategoriNews.setText(articlesItem.getSource().getName());

        Glide.with(context)
                .load(articlesItem.getUrlToImage())
                .into(viewHolder.imgNews);

        viewHolder.itemView.setOnClickListener(view -> {
            if (onClickItem != null)
                onClickItem.clickItem(position);
        });
    }

    @Override
    public int getItemCount() {
        return articlesItemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleNews, txtDescNews, txtTimeNews, txtKategoriNews;
        ImageView imgNews;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgNews = itemView.findViewById(R.id.imgNews);
            txtTitleNews = itemView.findViewById(R.id.txtTitleNews);
            txtDescNews = itemView.findViewById(R.id.txtDesc);
            txtKategoriNews = itemView.findViewById(R.id.txtKategori);
            txtTimeNews = itemView.findViewById(R.id.txtTime);
        }
    }
}
