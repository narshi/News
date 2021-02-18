package com.newsapp.network;
import com.newsapp.models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.newsapp.constants.UrlConstants.NEWS_TYPE;


public interface ApiClient {
    @GET(NEWS_TYPE)
    Call<News> getNews(@Query("q") String newsType, @Query("apiKey") String apiKey);
}
