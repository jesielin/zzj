package cc.shawn.zzj.module.social;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.shawn.zzj.MyApp;
import cc.shawn.zzj.R;
import cc.shawn.zzj.adapter.SocialAdapter;
import cc.shawn.zzj.base.BaseFragment;
import cc.shawn.zzj.base.BaseRecyclerAdapter;
import cc.shawn.zzj.base.BaseRecyclerFragment;
import cc.shawn.zzj.bean.ComItem;
import cc.shawn.zzj.bean.CommentConfig;
import cc.shawn.zzj.bean.SocialItem;
import cc.shawn.zzj.module.MainActivity;
import cc.shawn.zzj.util.CommonUtils;
import cc.shawn.zzj.util.DebugLog;
import cc.shawn.zzj.widget.CommentListView;

/**
 * Created by shawn on 2017-02-12.
 */

public class SocialFragment extends BaseRecyclerFragment implements SocialContract.View {

    SocialContract.Presenter presenter;
    View contentView;

    @BindView(R.id.sendIv)
    ImageView sendIv;
    @BindView(R.id.titlebar)
    View titlebar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        sendIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter != null) {
                    //发布评论
                    String content = editText.getText().toString().trim();
                    if (TextUtils.isEmpty(content)) {
                        Toast.makeText(getActivity(), "评论内容不能为空...", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    presenter.addComment(content, commentConfig);
                }
                updateEditTextBodyVisible(View.GONE, null);
            }
        });
        mRecycler.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (edittextbody.getVisibility() == View.VISIBLE) {
                    updateEditTextBodyVisible(View.GONE, null);
                    return true;
                }
                return false;
            }
        });
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
        setViewTreeObserver();
        super.onStart();
        this.presenter = new SocialPresenter(this);
        onRefresh();
    }

    private int screenHeight;
    private int editTextBodyHeight;
    private int currentKeyboardH;
    @BindView(R.id.bodyLayout)
    RelativeLayout bodyLayout;
    private void setViewTreeObserver() {
        final ViewTreeObserver swipeRefreshLayoutVTO = bodyLayout.getViewTreeObserver();
        swipeRefreshLayoutVTO.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                bodyLayout.getWindowVisibleDisplayFrame(r);
                int statusBarH =  getStatusBarHeight();//状态栏高度
                int screenH = bodyLayout.getRootView().getHeight();
                if(r.top != statusBarH ){
                    //在这个demo中r.top代表的是状态栏高度，在沉浸式状态栏时r.top＝0，通过getStatusBarHeight获取状态栏高度
                    r.top = statusBarH;
                }
                int keyboardH = screenH - (r.bottom - r.top);
                DebugLog.d("screenH＝ "+ screenH +" &keyboardH = " + keyboardH + " &r.bottom=" + r.bottom + " &top=" + r.top + " &statusBarH=" + statusBarH);

                if(keyboardH == currentKeyboardH){//有变化时才处理，否则会陷入死循环
                    return;
                }

                currentKeyboardH = keyboardH;
                screenHeight = screenH;//应用屏幕的高度
                editTextBodyHeight = edittextbody.getHeight();

                if(keyboardH<150){//说明是隐藏键盘的情况
                    updateEditTextBodyVisible(View.GONE, null);
                    return;
                }
                //偏移listview
                if(mLayoutManager!=null && commentConfig != null){
                    ((LinearLayoutManager)mLayoutManager).scrollToPositionWithOffset(commentConfig.socialPosition + SocialAdapter.HEADVIEW_SIZE, getListviewOffset(commentConfig));
                }
            }
        });
    }
    /**
     * 测量偏移量
     * @param commentConfig
     * @return
     */
    private int getListviewOffset(CommentConfig commentConfig) {
        if(commentConfig == null)
            return 0;
        //这里如果你的listview上面还有其它占高度的控件，则需要减去该控件高度，listview的headview除外。
        //int listviewOffset = mScreenHeight - mSelectCircleItemH - mCurrentKeyboardH - mEditTextBodyHeight;
        int listviewOffset = screenHeight - selectCircleItemH - currentKeyboardH - editTextBodyHeight - titlebar.getHeight();
        if(commentConfig.commentType == CommentConfig.Type.REPLY){
            //回复评论的情况
            listviewOffset = listviewOffset + selectCommentItemOffset;
        }
        DebugLog.i("listviewOffset : " + listviewOffset);
        return listviewOffset;
    }

    /**
     * 获取状态栏高度
     * @return
     */
    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        DebugLog.e("context:" + getActivity());
        return new SocialAdapter(getActivity(), presenter);
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
        presenter.loadData(TYPE_PULL_REFRESH);

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

    @Override
    public void update2DeleteCircle(String circleId) {

    }

    @Override
    public void update2DeleteFavort(int circlePosition, String favortId) {

    }

    @Override
    public void update2AddComment(int circlePosition, ComItem addItem) {

    }

    @Override
    public void update2DeleteComment(int circlePosition, String commentId) {

    }

    private int selectCircleItemH;
    private int selectCommentItemOffset;

    private void measureCircleItemHighAndCommentItemOffset(CommentConfig commentConfig) {
        if (commentConfig == null)
            return;

        int firstPosition = ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
        //只能返回当前可见区域（列表可滚动）的子项
        View selectCircleItem = ((LinearLayoutManager) mLayoutManager).getChildAt(commentConfig.socialPosition + SocialAdapter.HEADVIEW_SIZE - firstPosition);

        if (selectCircleItem != null) {
            selectCircleItemH = selectCircleItem.getHeight();
        }

        if (commentConfig.commentType == CommentConfig.Type.REPLY) {
            //回复评论的情况
            CommentListView commentLv = (CommentListView) selectCircleItem.findViewById(R.id.commentList);
            if (commentLv != null) {
                //找到要回复的评论view,计算出该view距离所属动态底部的距离
                View selectCommentItem = commentLv.getChildAt(commentConfig.commentPosition);
                if (selectCommentItem != null) {
                    //选择的commentItem距选择的CircleItem底部的距离
                    selectCommentItemOffset = 0;
                    View parentView = selectCommentItem;
                    do {
                        int subItemBottom = parentView.getBottom();
                        parentView = (View) parentView.getParent();
                        if (parentView != null) {
                            selectCommentItemOffset += (parentView.getHeight() - subItemBottom);
                        }
                    } while (parentView != null && parentView != selectCircleItem);
                }
            }
        }
    }

    @BindView(R.id.editTextBodyLl)
    LinearLayout edittextbody;
    @BindView(R.id.circleEt)
    EditText editText;

    CommentConfig commentConfig;

    @Override
    public void updateEditTextBodyVisible(int visibility, CommentConfig commentConfig) {
        this.commentConfig = commentConfig;
        DebugLog.e("show edit");
        edittextbody.setVisibility(visibility);

        measureCircleItemHighAndCommentItemOffset(commentConfig);

        if (View.VISIBLE == visibility) {
            editText.requestFocus();
            //弹出键盘
            CommonUtils.showSoftInput(MyApp.getContext(), editText);
            ((MainActivity)getActivity()).hideNav();


        } else if (View.GONE == visibility) {
            //隐藏键盘
            CommonUtils.hideSoftInput(MyApp.getContext(), editText);
            ((MainActivity)getActivity()).showNav();
        }
    }

    @Override
    public void update2loadData(int loadType, List<SocialItem> datas) {
        DebugLog.e("loadtype:" + loadType);
        switch (loadType) {
            case BaseRecyclerFragment.TYPE_PULL_REFRESH:

                mAdapter.setDatas(datas);
                break;
            case BaseRecyclerFragment.TYPE_LOAD_MORE:
                mAdapter.addAll(datas);
                break;

        }
        mRecycler.setRefreshing(false);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void disappareLoadMoreButton() {
        mRecycler.setLoadingMore(false);
        mRecycler.removeMoreListener();
        mRecycler.hideMoreProgress();
    }

    @Override
    public void setLoadMore() {
        mRecycler.setOnMoreListener(this);
    }
}
