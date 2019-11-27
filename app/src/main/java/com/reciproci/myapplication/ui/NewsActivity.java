package com.reciproci.myapplication.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.reciproci.myapplication.BuildConfig;
import com.reciproci.myapplication.R;
import com.reciproci.myapplication.adapters.NewsAdapter;
import com.reciproci.myapplication.models.Article;
import com.reciproci.myapplication.models.News;
import com.reciproci.myapplication.network.ApiClient;
import com.reciproci.myapplication.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsActivity extends AppCompatActivity {
    private static final String NEWS_CATEGORY = "business";
    private static final String TAG = "NewsActivity";
    RecyclerView recyclerView;
    ArrayList<Article> newsArrayList = new ArrayList<>();
    Retrofit retrofit;
    private ApiClient apiClient;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initUi();
        apiClient = RetrofitInstance.getRetrofit().create(ApiClient.class);
        getNews();
    }

    /**
     *
     */
    private void initUi() {
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_circular);
    }

    /**
     *
     */
    private void initRecyclerView() {
        NewsAdapter newsAdapter = new NewsAdapter(this, newsArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(newsAdapter);
    }

    /**
     *
     */
    private void getNews() {
        Call<News> call = apiClient.getNews(NEWS_CATEGORY, BuildConfig.MY_API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.body() != null) {
                    progressBar.setVisibility(View.GONE);
                    newsArrayList = response.body().getArticles();

                }
                initRecyclerView();
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
