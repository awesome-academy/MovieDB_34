package com.cris.nvh.moviedb.ui.favorite;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cris.nvh.moviedb.R;
import com.cris.nvh.moviedb.adapter.CategoryAdapter.CategoryViewHolder.MoviesAdapter;
import com.cris.nvh.moviedb.data.model.Movie;
import com.cris.nvh.moviedb.data.repository.MovieRepository;
import com.cris.nvh.moviedb.data.source.local.FavoriteReaderDBHelper;
import com.cris.nvh.moviedb.data.source.local.LocalDataSource;
import com.cris.nvh.moviedb.data.source.remote.RemoteDataSource;
import com.cris.nvh.moviedb.databinding.FragmentFavoriteBinding;
import com.cris.nvh.moviedb.ui.moviedetails.MovieDetailsActivity;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class FavoriteFragment extends Fragment implements MoviesAdapter.MovieItemClickListener,
        FavoriteNavigator {
    private FavoriteViewModel mViewModel;
    private FragmentFavoriteBinding mBinding;
    private RefreshHomeData mListener;
    private int mSelectedMovieId;

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    public void setListener(RefreshHomeData listener) {
        mListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViewModel();
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite,
                container, false);
        mBinding.setFavoriteVM(mViewModel);
        mBinding.recyclerFavorities.setAdapter(new MoviesAdapter(this));
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.refreshFavoriteMovies();
    }

    public void refreshMovies() {
        if (mViewModel != null)
            mViewModel.refreshFavoriteMovies();
    }

    private void initViewModel() {
        FavoriteReaderDBHelper dbHelper = new FavoriteReaderDBHelper(getContext());
        MovieRepository movieRepository = MovieRepository.getInstance(
                LocalDataSource.getInstance(dbHelper),
                RemoteDataSource.getInstance());
        mViewModel = new FavoriteViewModel(movieRepository);
    }

    @Override
    public void onMovieItemClick(Movie movie) {
        startMovieDetailActivity(movie);
    }

    @Override
    public void onFavoriteImageClick(Movie movie) {
        mSelectedMovieId = movie.getId();
        openDialog();
    }

    @Override
    public void startMovieDetailActivity(Movie movie) {
        startActivity(MovieDetailsActivity.getIntent(getActivity(), movie));
    }

    private void openDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Are you sure??");
        Button buttonCancel = dialog.findViewById(R.id.button_cancel);
        Button buttonOk = dialog.findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.deleteFavoriteMovie(mSelectedMovieId);
                mViewModel.refreshFavoriteMovies();
                mListener.onRefreshHomeFragment();
                dialog.dismiss();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public interface RefreshHomeData {
        void onRefreshHomeFragment();
    }
}
