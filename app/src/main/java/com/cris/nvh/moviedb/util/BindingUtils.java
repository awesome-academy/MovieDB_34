package com.cris.nvh.moviedb.util;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cris.nvh.moviedb.R;
import com.cris.nvh.moviedb.adapter.SlideAdapter;
import com.cris.nvh.moviedb.data.model.Cast;
import com.cris.nvh.moviedb.data.model.Company;
import com.cris.nvh.moviedb.data.model.Genre;
import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.model.Video;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.cris.nvh.moviedb.util.Constant.BASE_IMAGE_URL;
import static com.cris.nvh.moviedb.util.Constant.IMAGE_QUALITY_MAX;

public class BindingUtils {
    private static final int FIRST_INDEX = 0;
    private static final int LAST_INDEX = 6;
    private static final int DELAY = 5000;
    private static final int PLUS = 1;
    private static final int DURATION = 5000;

    @BindingAdapter("pagerAdapter")
    public static void bindPagerAdapter(ViewPager pager, List<Movie> movies) {
        SlideAdapter slideAdapter = (SlideAdapter) pager.getAdapter();
        if (slideAdapter != null && movies.size() > 0) {
            slideAdapter.update(movies.subList(FIRST_INDEX, LAST_INDEX));
        }
    }

    @BindingAdapter("spinnerAdapter")
    public static void bindSpinner(Spinner spinner, ObservableList<Genre> genreNames) {
        ObservableList<String> strings = new ObservableArrayList<>();
        for (Genre genre : genreNames) {
            strings.add(genre.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(spinner.getContext(),
                android.R.layout.simple_spinner_item, strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @BindingAdapter("bindMovies")
    public static void bindRecyclerMovies(RecyclerView recycler, ObservableList<Movie> movies) {
    }

    @BindingAdapter("bindCategories")
    public static void bindRecyclerCategories(RecyclerView recycler, List<ObservableList<Movie>> movies) {
    }

    @BindingAdapter("bindGenres")
    public static void bindGenres(RecyclerView recycler, ObservableList<Genre> genres) {
    }

    @BindingAdapter("bindVideos")
    public static void bindVideos(RecyclerView recycler, ObservableList<Video> videos) {
    }

    @BindingAdapter("bindProduces")
    public static void setProducesForRecyclerView(RecyclerView recyclerView,
                                                  ObservableList<Company> companies) {
    }

    @BindingAdapter("bindActors")
    public static void setActorsForRecyclerView(RecyclerView recyclerView,
                                                ObservableList<Cast> actors) {
    }

    @BindingAdapter("imageUrl")
    public static void bindImage(ImageView imageView, String url) {
        String source = StringUtils.append(BASE_IMAGE_URL, IMAGE_QUALITY_MAX, url);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.default_poster);
        Glide.with(imageView.getContext())
                .load(source)
                .apply(requestOptions)
                .into(imageView);
    }

    @BindingAdapter("switchImage")
    public static void bindToSwitchImage(final ViewPager pager, String message) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                pager.post(new Runnable() {
                    @Override
                    public void run() {
                        if (pager.getCurrentItem() > pager.getChildCount() + PLUS) {
                            pager.setCurrentItem(FIRST_INDEX);
                            return;
                        }
                        pager.setCurrentItem(pager.getCurrentItem() + PLUS);
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, DELAY, DURATION);
    }
}
