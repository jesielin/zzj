package cc.shawn.zzj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import cc.shawn.zzj.MyApp;
import cc.shawn.zzj.R;
import cc.shawn.zzj.adapter.viewholder.SocialHeaderViewHolder;
import cc.shawn.zzj.adapter.viewholder.SocialImageViewHolder;
import cc.shawn.zzj.adapter.viewholder.SocialViewHolder;
import cc.shawn.zzj.base.BaseRecyclerAdapter;
import cc.shawn.zzj.bean.ActionItem;
import cc.shawn.zzj.bean.ComItem;
import cc.shawn.zzj.bean.CommentConfig;
import cc.shawn.zzj.bean.SocialItem;
import cc.shawn.zzj.module.social.SocialContract;
import cc.shawn.zzj.util.DebugLog;
import cc.shawn.zzj.util.UrlUtils;
import cc.shawn.zzj.widget.CommentDialog;
import cc.shawn.zzj.widget.CommentListView;
import cc.shawn.zzj.widget.ExpandTextView;
import cc.shawn.zzj.widget.GlideCircleTransform;
import cc.shawn.zzj.widget.PraiseListView;
import cc.shawn.zzj.widget.SnsPopupWindow;

/**
 * Created by shawn on 17/2/15.
 */

public class SocialAdapter extends BaseRecyclerAdapter<SocialItem,RecyclerView.ViewHolder> {


    private List<SocialItem> items;

    private SocialContract.Presenter mPresenter;
    private Context mContext;
    public SocialAdapter(Context context,SocialContract.Presenter socialPresenter) {
        this.mPresenter = socialPresenter;
        this.mContext = context;
    }

    @Override
    public void setDatas(List<SocialItem> datas){
        this.items = datas;
    }

    @Override
    public void remove(int position) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void addAll(List<SocialItem> socialItems) {

    }

    @Override
    public void add(SocialItem socialItem) {

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == SocialViewHolder.TYPE_HEADER) {
            View headView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_social_header, parent, false);
            viewHolder = new SocialHeaderViewHolder(headView);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_social, parent, false);
            viewHolder = new SocialImageViewHolder(view);
        }
        return viewHolder;
    }

    public static final int HEADVIEW_SIZE = 1;
    public String url = "http://www.qqtouxiang.com/d/file/qinglv/20170212/9fd014a7c29552d364d58e5df64f0ed5.jpg";
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int i = 1;
        DebugLog.e("count:"+i);
        i++;
        if(getItemViewType(position) == SocialViewHolder.TYPE_HEADER){

        }else{
            SocialViewHolder viewHolder = (SocialViewHolder)holder;
            Glide.with(mContext).load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.color.bg_no_photo).transform(new GlideCircleTransform(mContext)).into(viewHolder.ivHead);

            final SocialItem socialItem = items.get(position-HEADVIEW_SIZE);
            viewHolder.tvName.setText(socialItem.momentOwner);
            viewHolder.tvTime.setText(socialItem.createDate);
            if(!TextUtils.isEmpty(socialItem.message)){
                viewHolder.tvContent.setExpand(socialItem.isExpand());
                viewHolder.tvContent.setExpandStatusListener(new ExpandTextView.ExpandStatusListener() {
                    @Override
                    public void statusChange(boolean isExpand) {
                        socialItem.setExpand(isExpand);
                    }
                });

                DebugLog.e("tvcontent:"+viewHolder.tvContent);
                DebugLog.e("socialItem:"+socialItem.message);

                viewHolder.tvContent.setText(UrlUtils.formatUrlString(socialItem.message));
                if (position % 2 == 0)
                    viewHolder.tvContent.setText(UrlUtils.formatUrlString("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈http://www.baidu.com哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"));
                viewHolder.tvContent.setVisibility(TextUtils.isEmpty(socialItem.message) ? View.GONE : View.VISIBLE);

                if(false){
                    viewHolder.btnDelete.setVisibility(View.VISIBLE);
                }else{
                    viewHolder.btnDelete.setVisibility(View.GONE);
                }

                viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //删除
                        if(mPresenter!=null){
//                            mPresenter.deleteCircle(circleId);
                        }
                    }
                });

                if(socialItem.hasComments()){
//                    if(hasFavort){//处理点赞列表
//                        viewHolder.lvPraise.setOnItemClickListener(new PraiseListView.OnItemClickListener() {
//                            @Override
//                            public void onClick(int position) {
//                                String userName = favortDatas.get(position).getUser().getName();
//                                String userId = favortDatas.get(position).getUser().getId();
//                                Toast.makeText(MyApp.getContext(), userName + " &id = " + userId, Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        viewHolder.lvPraise.setDatas(favortDatas);
//                        viewHolder.lvPraise.setVisibility(View.VISIBLE);
//                    }else{
//                        viewHolder.lvPraise.setVisibility(View.GONE);
//                    }

                    if(socialItem.hasComments()){//处理评论列表
                        viewHolder.lvComment.setOnItemClickListener(new CommentListView.OnItemClickListener() {
                            @Override
                            public void onItemClick(int commentPosition) {
                                ComItem commentItem = socialItem.comments.get(commentPosition);
                                if(false){//复制或者删除自己的评论

                                    CommentDialog dialog = new CommentDialog(mContext, mPresenter, commentItem, position-HEADVIEW_SIZE);
                                    dialog.show();
                                }else{//回复别人的评论
                                    if(mPresenter != null){
                                        CommentConfig config = new CommentConfig();
                                        config.socialPosition = position-HEADVIEW_SIZE;
                                        config.commentPosition = commentPosition;
                                        config.commentType = CommentConfig.Type.REPLY;
                                        mPresenter.showEditTextBody(config);
                                    }
                                }
                            }
                        });
                        viewHolder.lvComment.setOnItemLongClickListener(new CommentListView.OnItemLongClickListener() {
                            @Override
                            public void onItemLongClick(int commentPosition) {
                                //长按进行复制或者删除
                                ComItem commentItem = socialItem.comments.get(commentPosition);
                                CommentDialog dialog = new CommentDialog(mContext, mPresenter, commentItem, position-HEADVIEW_SIZE);
                                dialog.show();
                            }
                        });
                        viewHolder.lvComment.setDatas(socialItem.comments);
                        viewHolder.lvComment.setVisibility(View.VISIBLE);

                    }else {
                        viewHolder.lvComment.setVisibility(View.GONE);
                    }
                    viewHolder.llDigCommentBody.setVisibility(View.VISIBLE);
                }else{
                    viewHolder.llDigCommentBody.setVisibility(View.GONE);
                }


                final SnsPopupWindow snsPopupWindow = viewHolder.snsPopupWindow;
                //判断是否已点赞
//                String curUserFavortId = circleItem.getCurUserFavortId(DatasUtil.curUser.getId());
//                if(!TextUtils.isEmpty(curUserFavortId)){
//                    snsPopupWindow.getmActionItems().get(0).mTitle = "取消";
//                }else{
//                    snsPopupWindow.getmActionItems().get(0).mTitle = "赞";
//                }
                snsPopupWindow.update();
                snsPopupWindow.setmItemClickListener(new PopupItemClickListener(position-HEADVIEW_SIZE, socialItem, "1"));
                viewHolder.btnSns.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        //弹出popupwindow
                        snsPopupWindow.showPopupWindow(view);
                    }
                });
            }
        }

    }

    private class PopupItemClickListener implements SnsPopupWindow.OnItemClickListener{
        private String mFavorId;
        //动态在列表中的位置
        private int mSocialPosition;
        private long mLasttime = 0;
        private SocialItem mItem;

        public PopupItemClickListener(int socialPosition, SocialItem socialItem, String favorId){
            this.mFavorId = favorId;
            this.mSocialPosition = socialPosition;
            this.mItem = socialItem;
        }

        @Override
        public void onItemClick(ActionItem actionitem, int position) {
            switch (position) {
                case 0://点赞、取消点赞
                    if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
                        return;
                    mLasttime = System.currentTimeMillis();
                    if(mPresenter != null){
                        if ("赞".equals(actionitem.mTitle.toString())) {
                            mPresenter.addFavort(mSocialPosition);
                        } else {//取消点赞
                            mPresenter.deleteFavort(mSocialPosition, mFavorId);
                        }
                    }
                    break;
                case 1://发布评论
                    DebugLog.e("点击评论按钮");
                    if(mPresenter != null){
                        CommentConfig config = new CommentConfig();
                        config.socialPosition = mSocialPosition;
                        config.commentType = CommentConfig.Type.PUBLIC;
                        mPresenter.showEditTextBody(config);
                    }
                    break;
                default:
                    break;
            }
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

        if (items != null && items.size()>0) {
            DebugLog.e("item size:"+items.size());
            return items.size()+1;
        }
        else {
            DebugLog.e("00000000");
            return 0;
        }
    }

}
