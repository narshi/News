package com.reciproci.myapplication.network;

import com.reciproci.myapplication.models.News;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.reciproci.myapplication.constants.UrlConstants.NEWS_TYPE;

public interface ApiClient {
    @GET(NEWS_TYPE)
    Call<News> getNews(@Query("q") String newsType, @Query("apiKey") String apiKey);
}
