package com.udacity.kechagiaskonstantinos.popularmoviesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.kechagiaskonstantinos.popularmoviesapp.dao.Movie;

import static android.support.v7.widget.RecyclerView.VERTICAL;
import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by kechagiaskonstantinos on 27/02/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    private ItemClickListener mClickListener;
    private Movie[] mMoviesData;
    private Context context;

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(Movie movie);
    }

    public MoviesAdapter(Context context, ItemClickListener mClickListener){
        this.mClickListener = mClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.movie_list_item, viewGroup, false);
        view.setMinimumHeight(viewGroup.getMeasuredHeight()/2);
        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MoviesAdapterViewHolder holder, int position) {
        Movie movie = mMoviesData[position];
        if(context.getResources().getConfiguration().orientation == VERTICAL) {
            Picasso.with(context).load(movie.getPosterPath()).into(holder.mMovieImageView);
        }else {
            if(movie.getBackdropPath() == null)
                Picasso.with(context).load(movie.getPosterPath())
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)
                        .into(holder.mMovieImageView);
            else
                Picasso.with(context).load(movie.getBackdropPath())
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)
                        .into(holder.mMovieImageView);
        }

    }

    @Override
    public int getItemCount() {
        if (null == mMoviesData) return 0;
        return mMoviesData.length;
    }

    public void setMoviesData(Movie[] moviesData) {
        this.mMoviesData = moviesData;
        notifyDataSetChanged();
    }

    public class MoviesAdapterViewHolder extends ViewHolder implements View.OnClickListener{

        public final ImageView mMovieImageView;

        public MoviesAdapterViewHolder(View itemView) {
            super(itemView);
            mMovieImageView = itemView.findViewById(R.id.iv_rvMovie);
            mMovieImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(mMoviesData[getAdapterPosition()]);
        }
    }
}
