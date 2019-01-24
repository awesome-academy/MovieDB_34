package com.cris.nvh.moviedb.ui.producer;

import android.databinding.ObservableField;

import com.cris.nvh.moviedb.data.model.Company;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class ItemProducerViewModel {
    public final ObservableField<Company> company;

    public ItemProducerViewModel() {
        company = new ObservableField<>();
    }
}
