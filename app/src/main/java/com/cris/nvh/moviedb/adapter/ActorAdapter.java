package com.cris.nvh.moviedb.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cris.nvh.moviedb.R;
import com.cris.nvh.moviedb.data.model.Cast;
import com.cris.nvh.moviedb.databinding.ItemActorBinding;
import com.cris.nvh.moviedb.ui.cast.ItemCastViewModel;
import com.cris.nvh.moviedb.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.cris.nvh.moviedb.util.Constant.BASE_IMAGE_URL;
import static com.cris.nvh.moviedb.util.Constant.IMAGE_QUALITY_MAX;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ActorViewHolder> {
    private List<Cast> mActors;
    private OnClickActorListener mListener;

    public ActorAdapter(OnClickActorListener listener) {
        mActors = new ArrayList<>();
        mListener = listener;
    }

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ItemActorBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.item_actor, viewGroup, false);
        return new ActorViewHolder(binding, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder actorViewHolder, int i) {
        actorViewHolder.bindData(mActors.get(i));
    }

    @Override
    public int getItemCount() {
        return mActors != null ? mActors.size() : 0;
    }

    public void update(List<Cast> actors) {
        mActors.clear();
        mActors.addAll(actors);
        notifyDataSetChanged();
    }

    public static class ActorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemActorBinding mBinding;
        private ItemCastViewModel mViewModel;
        private OnClickActorListener mListener;

        public ActorViewHolder(ItemActorBinding binding, OnClickActorListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mListener = listener;
            mViewModel = new ItemCastViewModel();
            mBinding.setActorVM(mViewModel);
            mBinding.imageActor.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClickActor(mViewModel.cast.get());
        }

        public void bindData(Cast actor) {
            mViewModel.cast.set(actor);
            String source = StringUtils.append(BASE_IMAGE_URL, IMAGE_QUALITY_MAX, actor.getProfilePath());
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.default_poster)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            Glide.with(mBinding.imageActor)
                    .load(source)
                    .apply(requestOptions.circleCropTransform())
                    .into(mBinding.imageActor);
        }
    }

    public interface OnClickActorListener {
        void onClickActor(Cast cast);
    }
}
