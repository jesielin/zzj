package cc.shawn.zzj.module.home;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.BindView;
import butterknife.ButterKnife;
import cc.shawn.zzj.R;
import cc.shawn.zzj.adapter.BannerPagerAdapter;
import cc.shawn.zzj.adapter.HomeAdapter;
import cc.shawn.zzj.adapter.SocialAdapter;
import cc.shawn.zzj.base.BaseFragment;
import cc.shawn.zzj.base.BaseRecyclerAdapter;
import cc.shawn.zzj.base.BaseRecyclerFragment;
import cc.shawn.zzj.module.social.SocialContract;
import cc.shawn.zzj.module.social.SocialPresenter;
import cc.shawn.zzj.util.DebugLog;
import cc.shawn.zzj.widget.LoopViewPager;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by shawn on 2017-02-12.
 */

public class HomeFragment extends BaseRecyclerFragment implements HomeContract.View {

    HomeContract.Presenter presenter;


    @Override
    public void onStart() {
        super.onStart();
        presenter = new HomePresenter(this);


    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new HomeAdapter(getActivity(),presenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected boolean isSwipeToDismissEnabled() {
        return false;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {

    }

    @Override
    public boolean canDismiss(int position) {
        return false;
    }

    @Override
    public void onDismiss(RecyclerView recyclerView, int[] reverseSortedPositions) {

    }
}
