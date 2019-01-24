package com.cris.nvh.moviedb.ui.cast;

import android.databinding.ObservableField;

import com.cris.nvh.moviedb.data.model.Cast;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class ItemCastViewModel {
    public final ObservableField<Cast> cast;

    public ItemCastViewModel() {
        cast = new ObservableField<>();
    }
}
