package com.example.rmp121212;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlaceholderAPI {
    @GET("posts")
    Call<List<PlaceHolderPost>> getPosts();
    @POST("posts")
    Call<PlaceHolderPost> createOnePost(@Body PlaceHolderPost post);
    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int postId);
}