package cc.shawn.zzj.module.social;

import java.util.List;

import cc.shawn.zzj.base.BasePresenter;
import cc.shawn.zzj.base.BaseView;
import cc.shawn.zzj.bean.ComItem;
import cc.shawn.zzj.bean.CommentConfig;
import cc.shawn.zzj.bean.SocialItem;

/**
 * Created by shawn on 2017-02-12.
 */

public interface SocialContract {
    interface View extends BaseView {
        void update2DeleteCircle(String circleId);
//        void update2AddFavorite(int circlePosition, FavortItem addItem);
        void update2DeleteFavort(int circlePosition, String favortId);
        void update2AddComment(int circlePosition, ComItem addItem);
        void update2DeleteComment(int circlePosition, String commentId);
        void updateEditTextBodyVisible(int visibility, CommentConfig commentConfig);
        void update2loadData(int loadType, List<SocialItem> datas);
        void disappareLoadMoreButton();
        void setLoadMore();

    }

    interface Presenter extends BasePresenter{
        void showEditTextBody(CommentConfig commentConfig);
        void loadData(int loadType);
        void deleteSocialItem(final String socialItemId);
        void addFavort(final int socialItemPosition);
        void addComment(String comment,CommentConfig commentConfig);
        void deleteFavort(final int socialItemPosition, final String favortId);
        void deleteComment(final int socialItemPosition, final String socialCommentId);
    }
}
