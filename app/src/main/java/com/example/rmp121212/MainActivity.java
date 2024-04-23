package com.example.rmp121212;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
public class MainActivity extends AppCompatActivity {
    public final String URL_API = "https://jsonplaceholder.typicode.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1=findViewById(R.id.firstbutton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataFromApi();
            }
        });
        Button button2=findViewById(R.id.secondbutton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPost();
            }
        });
        Button button3=findViewById(R.id.thirdbutton);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePost(3);
            }
        });
    }
    private void getDataFromApi () {
        Retrofit retrofit = RetrofitFactory.getRetrofit(URL_API);
        PlaceholderAPI placeholderAPI = retrofit.create(PlaceholderAPI.class);
        Call<List<PlaceHolderPost>> call = placeholderAPI.getPosts();
        call.enqueue(new Callback<List<PlaceHolderPost>>() {
            @Override
            public void onResponse(Call<List<PlaceHolderPost>> call, Response<List<PlaceHolderPost>> response) {
                if (response.isSuccessful()) {
                    List<PlaceHolderPost> posts = response.body();
                    Log.d("Success1", posts.get(3).getBody().toString());
                    TextView textView = findViewById(R.id.text);
                    textView.setText(posts.get(3).getBody().toString());
                }
            }
            @Override
            public void onFailure(Call<List<PlaceHolderPost>> call, Throwable t) {
                Log.e("Ошибка при запросе", t.getMessage());
            }
        });
    }
    private void createPost() {
        PlaceHolderPost newPost = new PlaceHolderPost();
        newPost.setTitle("AAA");
        newPost.setBody("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        newPost.setUserId(14);
        Retrofit retrofit = RetrofitFactory.getRetrofit(URL_API);
        PlaceholderAPI placeholderAPI = retrofit.create(PlaceholderAPI.class);
        Call<PlaceHolderPost> call = placeholderAPI.createOnePost(newPost);
        call.enqueue(new Callback<PlaceHolderPost>() {
            @Override
            public void onResponse(Call<PlaceHolderPost> call, Response<PlaceHolderPost> response) {
                if (response.isSuccessful()) {
                    PlaceHolderPost createdPost = response.body();
                    Log.d("Success2", "Created post ID: " + createdPost.getId());
                    TextView textView = findViewById(R.id.text);
                    textView.setText(createdPost.getBody().toString());
                }
            }
            @Override
            public void onFailure(Call<PlaceHolderPost> call, Throwable t) {
                Log.e("Ошибка при запросе", t.getMessage());
            }
        });
    }
    private void deletePost(int postId) {
        Retrofit retrofit = RetrofitFactory.getRetrofit(URL_API);
        PlaceholderAPI placeholderAPI = retrofit.create(PlaceholderAPI.class);
        Call<Void> call = placeholderAPI.deletePost(postId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("Success3", "Post deleted successfully");
                    TextView textView = findViewById(R.id.text);
                    textView.setText("Post deleted successfully");
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Ошибка при запросе", t.getMessage());
            }
        });
    }
}