package com.cris.nvh.moviedb.ui.info;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cris.nvh.moviedb.databinding.FragmentInfoBinding;
import com.cris.nvh.moviedb.ui.moviedetails.MovieDetailsViewModel;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class InfoFragment extends Fragment {
    private FragmentInfoBinding mBinding;
    private MovieDetailsViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentInfoBinding.inflate(inflater, container, false);
        mBinding.setMovieVM(mViewModel);
        return mBinding.getRoot();
    }

    public void setViewModel(MovieDetailsViewModel viewModel) {
        mViewModel = viewModel;
    }

    public static InfoFragment newInstance() {
        return new InfoFragment();
    }
}
