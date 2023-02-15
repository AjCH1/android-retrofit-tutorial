package edu.uci.swe264p.retrofit;

import com.squareup.picasso.Picasso;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder>{

    //Show a list of Movies
    private List<Movie> topRatedData;

    MovieListAdapter(List<Movie> data)
    {
        this.topRatedData = data;
    }

    //Displays the movie
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImage;

        TextView tvTitle;
        TextView tvReleaseDate;
        TextView tvVote;
        TextView tvOverview;

        ViewHolder(View itemView) {
            super(itemView);

            movieImage = itemView.findViewById((R.id.ivMovie));
/*
        Picasso.get()
                .load("https://media.geeksforgeeks.org/wp-content/cdn-uploads/logo-new-2.svg")
                .into(holder.movieImage);
 */
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseDate);
            tvVote = itemView.findViewById(R.id.tvVote);
            tvOverview = itemView.findViewById(R.id.tvOverview);
        }
    }

    @NonNull
    @Override
    //movie_row
    public MovieListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false);
        return new MovieListAdapter.ViewHolder(view);
    }


    //Bind to the elements inside movie_row
    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.ViewHolder holder, int position) {
        Movie movie = topRatedData.get(position);

        //ImageView
        holder.movieImage.setImageResource(R.drawable.ic_launcher_background);

        //Load from Picasso using the posterPath
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath())
                .into(holder.movieImage);

        //TextView
        holder.tvTitle.setText(movie.getTitle());
        holder.tvReleaseDate.setText(movie.getReleaseDate());
        holder.tvVote.setText(movie.getVoteAverage().toString());
        holder.tvOverview.setText(movie.getOverview());
    }

    @Override
    //if size == 0, then the list doesn't appear in the first place
    public int getItemCount() {
        return topRatedData.size();
    }
}
