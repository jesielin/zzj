package cc.shawn.zzj.module.social;

import android.support.v4.util.DebugUtils;
import android.view.View;

import java.util.List;

import cc.shawn.zzj.base.BaseRecyclerFragment;
import cc.shawn.zzj.bean.CommentConfig;
import cc.shawn.zzj.bean.SocialItem;
import cc.shawn.zzj.network.HttpResult;
import cc.shawn.zzj.network.Network;
import cc.shawn.zzj.util.DebugLog;
import rx.Subscriber;

/**
 * Created by shawn on 2017-02-12.
 */

public class SocialPresenter implements SocialContract.Presenter {

    private Network network;
    private int page = 1;
    private int rows = 2;
    private SocialContract.View mView;

    SocialPresenter(SocialContract.View homeView) {
        network = Network.getInstance();
        mView = homeView;
    }

    @Override
    public void showEditTextBody(CommentConfig commentConfig){
        if(mView != null){
            mView.updateEditTextBodyVisible(View.VISIBLE, commentConfig);
        }
    }

    @Override
    public void loadData(final int loadType) {
        if (loadType == BaseRecyclerFragment.TYPE_PULL_REFRESH) {
            page = 1;
        }
        network.getSocialItems(page, rows)
                .subscribe(new Subscriber<HttpResult<List<SocialItem>>>() {
                    @Override
                    public void onCompleted() {

                        DebugLog.e("network complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        DebugLog.e("network error");
                    }

                    @Override
                    public void onNext(HttpResult<List<SocialItem>> listHttpResult) {
                        for (SocialItem socialItem:listHttpResult.data){
                            DebugLog.e(socialItem.toString());
                        }
                        if (listHttpResult.data != null)
                            mView.update2loadData(loadType, listHttpResult.data);

                    }
                });
        page++;
        if (page >rows) {
            mView.disappareLoadMoreButton();
        }

    }

    @Override
    public void deleteSocialItem(String socialItemId) {

    }

    @Override
    public void addFavort(int socialItemPosition) {

    }

    @Override
    public void addComment(String comment, CommentConfig commentConfig) {

    }

    @Override
    public void deleteFavort(int socialItemPosition, String favortId) {

    }

    @Override
    public void deleteComment(int socialItemPosition, String socialCommentId) {

    }
}
