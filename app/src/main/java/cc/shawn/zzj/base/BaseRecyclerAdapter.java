package cc.shawn.zzj.base;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;

import java.util.Arrays;
import java.util.List;

import cc.shawn.zzj.adapter.NewsAdapter;

/**
 * Created by shawn on 17/2/15.
 */

public abstract class BaseRecyclerAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public abstract void remove(int position);
    public abstract void clear();

    public abstract void addAll(List<T> ts);
    public abstract void add(T t);
}
