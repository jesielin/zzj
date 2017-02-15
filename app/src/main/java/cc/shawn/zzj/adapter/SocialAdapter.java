package cc.shawn.zzj.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cc.shawn.zzj.R;
import cc.shawn.zzj.adapter.viewholder.HeaderViewHolder;
import cc.shawn.zzj.adapter.viewholder.SocialImageViewHolder;
import cc.shawn.zzj.adapter.viewholder.SocialViewHolder;
import cc.shawn.zzj.base.BaseRecyclerAdapter;
import cc.shawn.zzj.module.social.SocialPresenter;

/**
 * Created by shawn on 17/2/15.
 */

public class SocialAdapter extends BaseRecyclerAdapter<String,SocialViewHolder> {


    private SocialPresenter mPresenter;
    public SocialAdapter(SocialPresenter socialPresenter) {
        this.mPresenter = socialPresenter;
    }

    @Override
    public void remove(int position) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void addAll(List<String> strings) {

    }

    @Override
    public void add(String s) {

    }

    @Override
    public SocialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SocialViewHolder viewHolder = null;
        if (viewType == SocialViewHolder.TYPE_HEADER) {
            View headView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_social_header, parent, false);
            viewHolder = new HeaderViewHolder(headView);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_social, parent, false);
            viewHolder = new SocialImageViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SocialViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return SocialViewHolder.TYPE_HEADER;
        } else
            return SocialViewHolder.TYPE_IMAGE;

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
