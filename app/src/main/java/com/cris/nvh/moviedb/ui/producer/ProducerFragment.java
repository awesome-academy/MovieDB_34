package com.cris.nvh.moviedb.ui.producer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cris.nvh.moviedb.R;
import com.cris.nvh.moviedb.ui.info.InfoFragment;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class ProducerFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_producer, container, false);
    }

    public static ProducerFragment newInstance(){
        return new ProducerFragment();
    }
}
