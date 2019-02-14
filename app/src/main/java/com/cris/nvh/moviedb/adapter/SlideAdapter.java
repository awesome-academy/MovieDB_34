package com.cris.nvh.moviedb.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cris.nvh.moviedb.R;
import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.databinding.LayoutSlideMoviesBinding;
import com.cris.nvh.moviedb.util.MovieViewModel;

import java.util.List;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class SlideAdapter extends PagerAdapter implements View.OnClickListener {
    private ObservableList<Movie> mMovies;
    private static int sCurrentPosition;
    private TopTrendingClickListener mListener;

    public SlideAdapter(TopTrendingClickListener listener) {
        mListener = listener;
        mMovies = new ObservableArrayList<>();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutSlideMoviesBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(container.getContext()),
                R.layout.layout_slide_movies, container, true);
        if (binding.getMovieVM() == null) {
            binding.setMovieVM(new MovieViewModel());
        }
        binding.getMovieVM().setMovie(mMovies.get(position));
        binding.imageSlide.setOnClickListener(this);
        binding.executePendingBindings();
        return binding.getRoot();

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void update(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        mListener.onTopTrendingClick(mMovies.get(sCurrentPosition));
    }

    public interface TopTrendingClickListener {
        void onTopTrendingClick(Movie movie);
    }

    public static void setCurrentPosition(int position) {
        sCurrentPosition = position;
    }
}
