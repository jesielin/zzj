package cc.shawn.zzj.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.malinskiy.superrecyclerview.swipe.SparseItemRemoveAnimator;
import com.malinskiy.superrecyclerview.swipe.SwipeDismissRecyclerViewTouchListener;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.shawn.zzj.R;

/**
 * Created by shawn on 17/2/15.
 */

public abstract class BaseRecyclerFragment  extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnMoreListener, SwipeDismissRecyclerViewTouchListener.DismissCallbacks{

    protected View contentView;
    @BindView(R.id.list)
    protected SuperRecyclerView mRecycler;
    protected SparseItemRemoveAnimator mSparseAnimator;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected Handler mHandler;
    protected BaseRecyclerAdapter mAdapter;

    public final static int TYPE_PULL_REFRESH = 0x0001;
    public final static int TYPE_LOAD_MORE = 0x0002;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = View.inflate(getActivity(),getLayoutId(),null);
        ButterKnife.bind(this,contentView);

        mAdapter = getAdapter();



        mLayoutManager = getLayoutManager();
        mRecycler.setLayoutManager(mLayoutManager);
        boolean dismissEnabled = isSwipeToDismissEnabled();
        if (dismissEnabled) {
            mRecycler.setupSwipeToDismiss(this);
            mSparseAnimator = new SparseItemRemoveAnimator();
            mRecycler.getRecyclerView().setItemAnimator(mSparseAnimator);
        }
        mRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    Glide.with(getActivity()).resumeRequests();
                }else{
                    Glide.with(getActivity()).pauseRequests();
                }

            }
        });

//        mHandler = new Handler(Looper.getMainLooper());
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mRecycler.setAdapter(mAdapter);
//                    }
//                });
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mAdapter.addAll(Arrays.asList("More stuff", "More stuff", "More stuff"));
//                    }
//                });
//            }
//        });
//        thread.start();
//        mRecycler.setRefreshing(true);

        mRecycler.hideProgress();
        mRecycler.getEmptyView().setVisibility(View.GONE);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setRefreshListener(this);
        mRecycler.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
        mRecycler.setupMoreListener(this, 1);
        return contentView;
    }

    protected abstract BaseRecyclerAdapter getAdapter();

    protected abstract int getLayoutId();

    protected abstract boolean isSwipeToDismissEnabled();

    protected abstract RecyclerView.LayoutManager getLayoutManager();



}