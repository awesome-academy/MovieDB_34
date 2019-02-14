package com.cris.nvh.moviedb.ui.producer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cris.nvh.moviedb.adapter.ProducerAdapter;
import com.cris.nvh.moviedb.data.model.Company;
import com.cris.nvh.moviedb.databinding.FragmentProducerBinding;
import com.cris.nvh.moviedb.ui.moviedetails.MovieDetailsViewModel;
import com.cris.nvh.moviedb.ui.trailer.TrailerFragment;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class ProducerFragment extends Fragment implements ProducerAdapter.OnClickProducerListener {
    private FragmentProducerBinding mBinding;
    private MovieDetailsViewModel mViewModel;
    private OnProducerSelectedListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnProducerSelectedListener) context;
        } catch (ClassCastException e) {
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentProducerBinding.inflate(inflater, container, false);
        mBinding.setMovieVM(mViewModel);
        mBinding.recyclerProducer.setAdapter(new ProducerAdapter(this));
        return mBinding.getRoot();
    }

    @Override
    public void onClickProducer(Company company) {
        mListener.onProducerSelected(company);
    }

    public static ProducerFragment newInstance() {
        return new ProducerFragment();
    }

    public void setViewModel(MovieDetailsViewModel viewModel) {
        mViewModel = viewModel;
    }

    public interface OnProducerSelectedListener{
        void onProducerSelected(Company company);
    }
}
