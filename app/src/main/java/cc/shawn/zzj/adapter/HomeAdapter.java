package cc.shawn.zzj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import cc.shawn.zzj.R;
import cc.shawn.zzj.adapter.viewholder.HomeHeaderViewHolder;
import cc.shawn.zzj.adapter.viewholder.SocialImageViewHolder;
import cc.shawn.zzj.adapter.viewholder.SocialViewHolder;
import cc.shawn.zzj.base.BaseRecyclerAdapter;
import cc.shawn.zzj.module.home.HomeContract;
import cc.shawn.zzj.widget.GlideCircleTransform;

/**
 * Created by shawn on 17/2/15.
 */

public class HomeAdapter extends BaseRecyclerAdapter<String,RecyclerView.ViewHolder> {


    private final int TYPE_HEADER = 0x001;
    private final int TYPE_ITEM = 0x002;

    private HomeContract.Presenter mPresenter;
    private Context mContext;
    public HomeAdapter(Context context, HomeContract.Presenter socialPresenter) {
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
        if (viewType == TYPE_HEADER) {
            View headView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_header, parent, false);
            viewHolder = new HomeHeaderViewHolder(headView);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_social, parent, false);
            viewHolder = new SocialImageViewHolder(view);
        }
        return viewHolder;
    }

    public static final int HEADVIEW_SIZE = 1;
    public String url = "http://www.qqtouxiang.com/d/file/qinglv/20170212/9fd014a7c29552d364d58e5df64f0ed5.jpg";
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(getItemViewType(position) == TYPE_HEADER){

        }else{
            SocialViewHolder viewHolder = (SocialViewHolder)holder;
            Glide.with(mContext).load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.color.bg_no_photo).transform(new GlideCircleTransform(mContext)).into(viewHolder.ivHead);

        }

    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return TYPE_HEADER;
        } else
            return TYPE_ITEM;

    }

    @Override
    public int getItemCount() {
        return 20;
    }

}
