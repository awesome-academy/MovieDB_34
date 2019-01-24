package com.cris.nvh.moviedb.ui.trailer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cris.nvh.moviedb.adapter.TrailerAdapter;
import com.cris.nvh.moviedb.databinding.FragmentTrailerBinding;
import com.cris.nvh.moviedb.ui.moviedetails.MovieDetailsViewModel;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class TrailerFragment extends Fragment implements TrailerAdapter.OnClickVideoItemListener {
    private FragmentTrailerBinding mBinding;
    private MovieDetailsViewModel mViewModel;
    private OnTrailerSelectedListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnTrailerSelectedListener) context;
        } catch (ClassCastException e) {
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentTrailerBinding.inflate(inflater, container, false);
        mBinding.setMovieVM(mViewModel);
        mBinding.recyclerTrailer.setAdapter(new TrailerAdapter(this));
        return mBinding.getRoot();
    }

    public void setViewModel(MovieDetailsViewModel viewModel) {
        mViewModel = viewModel;
    }

    public static TrailerFragment newInstance() {
        return new TrailerFragment();
    }

    @Override
    public void onClickTrailer(String videoKey) {
        mListener.onTrailerSelected(videoKey);
    }

    public interface OnTrailerSelectedListener {
        void onTrailerSelected(String videoKey);
    }
}
