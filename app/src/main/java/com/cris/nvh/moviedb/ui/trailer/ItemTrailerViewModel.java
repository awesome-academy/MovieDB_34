package com.cris.nvh.moviedb.ui.trailer;

import android.databinding.ObservableField;

import com.cris.nvh.moviedb.data.model.Video;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class ItemTrailerViewModel {
    public final ObservableField<Video> video;

    public ItemTrailerViewModel(){
        video = new ObservableField<>();
    }
}
