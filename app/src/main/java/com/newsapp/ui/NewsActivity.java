package com.newsapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.newsapp.adapters.NewsAdapter;
import com.newsapp.models.Article;
import com.newsapp.models.News;
import com.newsapp.myapplication.R;
import com.newsapp.network.ApiClient;
import com.newsapp.network.RetrofitInstance;
import com.newsapp.ui.login.LoginActivity;

import org.jetbrains.annotations.NotNull;

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
    private Toolbar mTopToolbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        mAuth = FirebaseAuth.getInstance();
        apiClient= RetrofitInstance.getRetrofit().create(ApiClient.class);
        initUi();
        getNews();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorite) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(NewsActivity.this, "Logged Out Successfully", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    /**
     *
     */
    private void initUi() {
        mTopToolbar = findViewById(R.id.news_header);
        setSupportActionBar(mTopToolbar);
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
        Call<News> call = apiClient.getNews(NEWS_CATEGORY, "cf62cfd62e4348d5a6a5d278be24fc4f");
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
            public void onFailure(Call<News> call, @NotNull Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e(TAG, "onFailure: " + t.getMessage());
            }

        });
    }

}
