package com.cris.nvh.moviedb.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cris.nvh.moviedb.R;
import com.cris.nvh.moviedb.data.model.Company;
import com.cris.nvh.moviedb.databinding.ItemProducerBinding;
import com.cris.nvh.moviedb.ui.producer.ItemProducerViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class ProducerAdapter extends RecyclerView.Adapter<ProducerAdapter.ProducerViewHolder> {
    private List<Company> mCompanies;

    public ProducerAdapter() {
        mCompanies = new ArrayList<>();
    }

    @NonNull
    @Override
    public ProducerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ItemProducerBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.item_producer, viewGroup, false);
        return new ProducerAdapter.ProducerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProducerViewHolder producerViewHolder, int i) {
        producerViewHolder.bindData(mCompanies.get(i));
    }

    @Override
    public int getItemCount() {
        return mCompanies != null ? mCompanies.size() : 0;
    }

    public void update(List<Company> companies) {
        mCompanies.clear();
        mCompanies.addAll(companies);
        notifyDataSetChanged();
    }


    public static class ProducerViewHolder extends RecyclerView.ViewHolder {
        private ItemProducerBinding mBinding;
        private ItemProducerViewModel mViewModel;

        public ProducerViewHolder(ItemProducerBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemProducerViewModel();
            mBinding.setProducerVM(mViewModel);
        }

        public void bindData(Company company) {
            mViewModel.company.set(company);
        }
    }
}
