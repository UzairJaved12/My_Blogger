package com.uzi.myblogger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class BloggerApi {
    private static final String TAG = "BloggerApi";

    private static final String key = "xxxxxxxxx";
    private static final String url =  "https://www.googleapis.com/blogger/v3/blogs/xxxxxxx/posts/";

    public static PostService postService = null;
    public static PostService getService(){
        if (postService == null){

            Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
            postService = retrofit.create(PostService.class);
        }
        return  postService;
    }

    public interface PostService{
        @GET ("?key="+key)
        Call<PostList> getPostList();
    }
}
