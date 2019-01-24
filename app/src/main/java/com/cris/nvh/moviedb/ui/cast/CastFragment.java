package com.cris.nvh.moviedb.ui.cast;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cris.nvh.moviedb.adapter.ActorAdapter;
import com.cris.nvh.moviedb.databinding.FragmentCastBinding;
import com.cris.nvh.moviedb.ui.moviedetails.MovieDetailsViewModel;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class CastFragment extends Fragment {
    private MovieDetailsViewModel mViewModel;
    private FragmentCastBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentCastBinding.inflate(inflater, container, false);
        mBinding.setMovieVM(mViewModel);
        mBinding.recyclerActor.setAdapter(new ActorAdapter());
        return mBinding.getRoot();
    }

    public static CastFragment newInstance() {
        return new CastFragment();
    }

    public void setViewModel(MovieDetailsViewModel viewModel) {
        mViewModel = viewModel;
    }
}
