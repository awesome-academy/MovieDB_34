package com.cris.nvh.moviedb.util;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cris.nvh.moviedb.BuildConfig;
import com.cris.nvh.moviedb.R;
import com.cris.nvh.moviedb.adapter.ActorAdapter;
import com.cris.nvh.moviedb.adapter.CategoryAdapter;
import com.cris.nvh.moviedb.adapter.CategoryAdapter.CategoryViewHolder.MoviesAdapter;
import com.cris.nvh.moviedb.adapter.ProducerAdapter;
import com.cris.nvh.moviedb.adapter.SlideAdapter;
import com.cris.nvh.moviedb.adapter.TrailerAdapter;
import com.cris.nvh.moviedb.data.model.Cast;
import com.cris.nvh.moviedb.data.model.Company;
import com.cris.nvh.moviedb.data.model.Genre;
import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.model.Video;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.cris.nvh.moviedb.util.Constant.BASE_IMAGE_URL;
import static com.cris.nvh.moviedb.util.Constant.IMAGE_QUALITY_MAX;

public class BindingUtils {
    private static final int FIRST_INDEX = 0;
    private static final int DELAY = 5000;
    private static final int PLUS = 1;
    private static final int DURATION = 5000;

    @BindingAdapter("pagerAdapter")
    public static void bindPagerAdapter(ViewPager pager, List<Movie> movies) {
        SlideAdapter slideAdapter = (SlideAdapter) pager.getAdapter();
        if (slideAdapter != null && movies.size() > 0) {
            slideAdapter.update(movies);
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
        MoviesAdapter adapter = (MoviesAdapter) recycler.getAdapter();
        if (adapter != null) {
            adapter.update(movies);
        }
    }

    @BindingAdapter({"bindCategories", "bindCategoryString"})
    public static void bindRecyclerCategories(RecyclerView recycler,
                                              ObservableList<ObservableList<Movie>> movies,
                                              ObservableList<String> categories) {
        CategoryAdapter adapter = (CategoryAdapter) recycler.getAdapter();
        if (adapter != null) {
            adapter.update(movies, categories);
        }
    }

    @BindingAdapter("bindCategoryMovies")
    public static void bindRecyclerCategoryMovies(RecyclerView recycler,
                                                  ObservableList<Movie> movies) {
        CategoryAdapter.CategoryViewHolder.MoviesAdapter adapter =
                (CategoryAdapter.CategoryViewHolder.MoviesAdapter) recycler.getAdapter();
        if (adapter != null) {
            adapter.update(movies);
        }
    }

    @BindingAdapter("bindGenres")
    public static void bindGenres(RecyclerView recycler, ObservableList<Genre> genres) {
    }

    @BindingAdapter("bindVideos")
    public static void bindVideos(RecyclerView recycler, List<Video> videos) {
        TrailerAdapter adapter = (TrailerAdapter) recycler.getAdapter();
        if (adapter != null && videos != null) {
            adapter.update(videos);
        }
    }

    @BindingAdapter("bindProduces")
    public static void setProducesForRecyclerView(RecyclerView recyclerView,
                                                  List<Company> companies) {
        ProducerAdapter adapter = (ProducerAdapter) recyclerView.getAdapter();
        if (adapter != null && companies != null) {
            adapter.update(companies);
        }
    }

    @BindingAdapter("bindActors")
    public static void setActorsForRecyclerView(RecyclerView recyclerView,
                                                List<Cast> actors) {
        ActorAdapter adapter = (ActorAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.update(actors);
        }
    }

    @BindingAdapter("bindResults")
    public static void setResultsText(TextView textView, int size) {
        textView.setText(new StringBuilder("Result: ").append(size).toString());
    }

    @BindingAdapter({"bindActorName", "bindActorCharacter"})
    public static void setActorName(TextView textView, String actorName, String actorCharacter) {
        textView.setText(new StringBuilder(actorName).append("/").append(actorCharacter).toString());
    }

    @BindingAdapter("imageUrl")
    public static void bindImage(ImageView imageView, String url) {
        String source = StringUtils.append(BASE_IMAGE_URL, IMAGE_QUALITY_MAX, url);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.default_poster)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
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

    @BindingAdapter("youTubeThumbnailView")
    public static void setYouTubeThumbnail(YouTubeThumbnailView thumbnailView,
                                           final String videoKey) {
        YouTubeThumbnailView.OnInitializedListener listener =
                new YouTubeThumbnailView.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubeThumbnailView view,
                                                        final YouTubeThumbnailLoader loader) {
                        loader.setVideo(videoKey);
                        loader.setOnThumbnailLoadedListener(
                                new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                                    @Override
                                    public void onThumbnailLoaded(
                                            YouTubeThumbnailView youTubeThumbnailView, String s) {
                                        loader.release();
                                    }

                                    @Override
                                    public void onThumbnailError(YouTubeThumbnailView view,
                                                                 YouTubeThumbnailLoader.ErrorReason error) {
                                    }
                                });
                    }

                    @Override
                    public void onInitializationFailure(YouTubeThumbnailView view,
                                                        YouTubeInitializationResult result) {
                    }
                };
        thumbnailView.initialize(BuildConfig.YOUTUBE_API_KEY, listener);
    }
}
