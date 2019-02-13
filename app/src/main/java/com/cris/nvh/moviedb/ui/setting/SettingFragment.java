package com.cris.nvh.moviedb.ui.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cris.nvh.moviedb.R;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class SettingFragment extends Fragment {
    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }
}
