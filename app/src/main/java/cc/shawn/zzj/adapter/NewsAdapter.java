package cc.shawn.zzj.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.shawn.zzj.R;
import cc.shawn.zzj.base.BaseRecyclerAdapter;
import cc.shawn.zzj.util.DebugLog;

/**
 * Created by shawn on 17/2/15.
 */

public class NewsAdapter extends BaseRecyclerAdapter<String,NewsAdapter.ViewHolder> {

    private ArrayList<String> list ;
    public NewsAdapter(){
        list = new ArrayList<>();
        for (int i = 0 ; i < 30 ; i ++){
            list.add("我在这里！");
        }
    }

    public ArrayList<String> getData(){
        return list;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_news,null);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void setDatas(List<String> datas) {

    }

    @Override
    public void remove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void clear() {
        int size = list.size();
        list.clear();
        notifyItemRangeRemoved(0, size);
    }

    @Override
    public void addAll(List<String> strings) {
        int startIndex = list.size();
        list.addAll(startIndex, strings);
        DebugLog.e("list:"+Arrays.asList(strings));
        notifyItemRangeInserted(startIndex, strings.size());
    }

    @Override
    public void add(String s) {
        int startIndex = list.size();
        list.add(startIndex,s);
        notifyItemRangeChanged(startIndex,1);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView tvTitle;
        @BindView(R.id.date)
        TextView tvDate;
        @BindView(R.id.subtitle)
        TextView tvSubTitle;
        @BindView(R.id.star)
        TextView tvStar;
        @BindView(R.id.image)
        ImageView ivImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
