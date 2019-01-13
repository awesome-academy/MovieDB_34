package com.cris.nvh.moviedb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cris.nvh.moviedb.R;
import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.model.MovieResponse;

import java.util.List;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<MovieResponse> mMovieResponses;

    public CategoryAdapter(List<MovieResponse> movieResponses) {
        mMovieResponses = movieResponses;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.layout_category, viewGroup, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder genreViewHolder, int i) {
        genreViewHolder.bindData(mMovieResponses.get(i));
    }

    @Override
    public int getItemCount() {
        return mMovieResponses == null ? 0 : mMovieResponses.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        public CategoryViewHolder(View itemView) {
            super(itemView);
        }

        public void bindData(MovieResponse movieResponse) {
        }

        public static class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
            private List<Movie> mMovies;

            public MoviesAdapter(List<Movie> movies) {
                mMovies = movies;
            }

            @Override
            public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                return null;
            }

            @Override
            public void onBindViewHolder(MovieViewHolder movieViewHolder, int i) {
            }

            @Override
            public int getItemCount() {
                return mMovies == null ? 0 : mMovies.size();
            }

            public static class MovieViewHolder extends RecyclerView.ViewHolder {

                public MovieViewHolder(View itemView) {
                    super(itemView);
                }
            }
        }
    }
}
