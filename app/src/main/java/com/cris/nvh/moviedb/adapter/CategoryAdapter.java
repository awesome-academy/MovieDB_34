package com.cris.nvh.moviedb.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cris.nvh.moviedb.R;
import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.databinding.ItemMovieBinding;
import com.cris.nvh.moviedb.databinding.LayoutCategoryBinding;
import com.cris.nvh.moviedb.util.MovieViewModel;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private ObservableList<ObservableList<Movie>> mMovies;
    private ObservableList<String> mCategories;

    public CategoryAdapter() {
        mMovies = new ObservableArrayList<>();
        mCategories = new ObservableArrayList<>();
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        LayoutCategoryBinding categoryBinding =
                DataBindingUtil.inflate(inflater, R.layout.layout_category, viewGroup, false);
        return new CategoryViewHolder(categoryBinding);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder genreViewHolder, int i) {
        genreViewHolder.bindData(mMovies.get(i), mCategories.get(i));
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public void update(ObservableList<ObservableList<Movie>> movies, ObservableList<String> categories) {
        mMovies.clear();
        mCategories = categories;
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        private LayoutCategoryBinding mCategoryBinding;

        public CategoryViewHolder(LayoutCategoryBinding binding) {
            super(binding.getRoot());
            mCategoryBinding = binding;
            mCategoryBinding.recyclerCategory.setAdapter(new MoviesAdapter());
        }

        public void bindData(ObservableList<Movie> movies, String category) {
            mCategoryBinding.textCategory.setText(category);
            mCategoryBinding.setCategoryMovies(movies);
        }

        public static class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
            private ObservableList<Movie> mMovies;

            public MoviesAdapter() {
                mMovies = new ObservableArrayList<>();
            }

            @Override
            public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                ItemMovieBinding binding = DataBindingUtil.inflate(
                        LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_movie,
                        viewGroup,
                        false
                );
                return new MovieViewHolder(binding);
            }

            @Override
            public void onBindViewHolder(MovieViewHolder movieViewHolder, int i) {
                movieViewHolder.bindData(mMovies.get(i));
            }

            @Override
            public int getItemCount() {
                return mMovies == null ? 0 : mMovies.size();
            }

            public void update(ObservableList<Movie> movies) {
                mMovies.clear();
                mMovies.addAll(movies);
                notifyDataSetChanged();
            }

            public static class MovieViewHolder extends RecyclerView.ViewHolder {
                private ItemMovieBinding mMovieBinding;

                public MovieViewHolder(ItemMovieBinding binding) {
                    super(binding.getRoot());
                    mMovieBinding = binding;
                }

                public void bindData(Movie movie) {
                    mMovieBinding.setMovieVM(new MovieViewModel());
                    mMovieBinding.getMovieVM().setMovie(movie);
                    mMovieBinding.executePendingBindings();
                }
            }
        }
    }
}
