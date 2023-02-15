package edu.uci.swe264p.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MovieListActivity extends AppCompatActivity {
    static final String TAG = MovieListActivity.class.getSimpleName();
    static final String BASE_URL = "https://api.themoviedb.org/3/";
    static Retrofit retrofit = null;
    final static String API_KEY = "45a675817d425c1ee579d7f7044422e7";

    private RecyclerView recyclerView;
    private List<Movie> topRatedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        topRatedData = new ArrayList<>();
        //get the contents on onCreate()
        connect();
    }

    @SuppressLint("WrongViewCast")
    private void connect(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        Call<TopRatedResponse> call = movieApiService.getTopRatedMovies(API_KEY);

        //Asynchronous method
        call.enqueue(new Callback<TopRatedResponse>() {
            @Override
            public void onResponse(Call<TopRatedResponse> call, Response<TopRatedResponse> response) {

                for(int i = 0; i < response.body().getResults().size(); i++){
                    topRatedData.add(response.body().getResults().get(i));
                }

                recyclerView = findViewById(R.id.rvMovieList);
                recyclerView.setHasFixedSize(true);
                Context context = getApplicationContext();
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(new MovieListAdapter(topRatedData));
            }

            @Override
            public void onFailure(Call<TopRatedResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });

        recyclerView = findViewById(R.id.rvMovieList);
        recyclerView.setHasFixedSize(true);
        //creating new LinearLayoutManager is required
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MovieListAdapter(topRatedData));
    }
}
