package cc.shawn.zzj.module.social;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import cc.shawn.zzj.R;
import cc.shawn.zzj.base.BaseFragment;
import cc.shawn.zzj.base.BaseRecyclerAdapter;
import cc.shawn.zzj.base.BaseRecyclerFragment;

/**
 * Created by shawn on 2017-02-12.
 */

public class SocialFragment extends BaseRecyclerFragment implements SocialContract.View {

    SocialContract.Presenter presenter;
    View contentView;




    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_social;
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
