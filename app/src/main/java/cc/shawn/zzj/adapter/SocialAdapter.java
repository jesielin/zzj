package cc.shawn.zzj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import cc.shawn.zzj.R;
import cc.shawn.zzj.adapter.viewholder.HeaderViewHolder;
import cc.shawn.zzj.adapter.viewholder.SocialImageViewHolder;
import cc.shawn.zzj.adapter.viewholder.SocialViewHolder;
import cc.shawn.zzj.base.BaseRecyclerAdapter;
import cc.shawn.zzj.module.social.SocialContract;
import cc.shawn.zzj.module.social.SocialPresenter;
import cc.shawn.zzj.widget.GlideCircleTransform;

/**
 * Created by shawn on 17/2/15.
 */

public class SocialAdapter extends BaseRecyclerAdapter<String,RecyclerView.ViewHolder> {




    private SocialContract.Presenter mPresenter;
    private Context mContext;
    public SocialAdapter(Context context,SocialContract.Presenter socialPresenter) {
        this.mPresenter = socialPresenter;
        this.mContext = context;
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == SocialViewHolder.TYPE_HEADER) {
            View headView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_social_header, parent, false);
            viewHolder = new HeaderViewHolder(headView);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_social, parent, false);
            viewHolder = new SocialImageViewHolder(view);
        }
        return viewHolder;
    }

    public static final int HEADVIEW_SIZE = 1;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(getItemViewType(position) == SocialViewHolder.TYPE_HEADER){

        }else{
            SocialViewHolder viewHolder = (SocialViewHolder)holder;
            Glide.with(mContext).load("http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E6%9D%8E%E5%86%B0%E5%86%B0&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=3651565664,3967726937&os=3219310922,2130346334&pn=22&rn=1&di=128635185280&ln=3936&fr=&fmq=1487207977247_R&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=11&pi=0&gsm=0&objurl=http%3A%2F%2Fwww.hubei88.com%2Fuploads%2Fallimg%2F170105%2F0T60945G_0.jpg&rpstart=0&rpnum=0&adpicid=0")
                    .diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.color.bg_no_photo).transform(new GlideCircleTransform(mContext)).into(viewHolder.ivHead);

        }

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
        return 20;
    }

}
