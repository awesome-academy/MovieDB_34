package com.cris.nvh.moviedb.ui.producer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cris.nvh.moviedb.adapter.ProducerAdapter;
import com.cris.nvh.moviedb.databinding.FragmentProducerBinding;
import com.cris.nvh.moviedb.ui.moviedetails.MovieDetailsViewModel;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class ProducerFragment extends Fragment {
    private FragmentProducerBinding mBinding;
    private MovieDetailsViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentProducerBinding.inflate(inflater, container, false);
        mBinding.setMovieVM(mViewModel);
        mBinding.recyclerProducer.setAdapter(new ProducerAdapter());
        return mBinding.getRoot();
    }

    public static ProducerFragment newInstance() {
        return new ProducerFragment();
    }

    public void setViewModel(MovieDetailsViewModel viewModel) {
        mViewModel = viewModel;
    }
}
