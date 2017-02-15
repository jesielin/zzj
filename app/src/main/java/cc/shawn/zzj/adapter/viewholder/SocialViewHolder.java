package cc.shawn.zzj.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.shawn.zzj.R;
import cc.shawn.zzj.widget.CommentListView;
import cc.shawn.zzj.widget.ExpandTextView;
import cc.shawn.zzj.widget.PraiseListView;
import cc.shawn.zzj.widget.SnsPopupWindow;

/**
 * Created by shawn on 17/2/15.
 */

public abstract class SocialViewHolder extends RecyclerView.ViewHolder {
    public final static int TYPE_URL = 0x01;
    public final static int TYPE_IMAGE = 0x02;
    public final static int TYPE_VIDEO = 0x03;
    public final static int TYPE_HEADER = 0x04;

    public int viewType;

    @BindView(R.id.viewStub)
    public ViewStub tvHead;
    @BindView(R.id.headIv)
    public TextView tvName;
    @BindView(R.id.urlTipTv)
    public TextView tvUrlTip;


    //动态内容
    @BindView(R.id.contentTv)
    public ExpandTextView tvContent;
    @BindView(R.id.deleteBtn)
    public TextView btnDelete;
    @BindView(R.id.snsBtn)
    public ImageView btnSns;
    @BindView(R.id.timeTv)
    public TextView tvTime;

    //点赞列表
    @BindView(R.id.praiseListView)
    public PraiseListView lvPraise;
    @BindView(R.id.digCommentBody)
    public LinearLayout llDigCommentBody;
    @BindView(R.id.lin_dig)
    public View digLine;

    //评论列表
    @BindView(R.id.commentList)
    public CommentListView lvComment;

    public SnsPopupWindow snsPopupWindow;



    public SocialViewHolder(View itemView,int viewType) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.viewType = viewType;

        ViewStub viewStub = (ViewStub) itemView.findViewById(R.id.viewStub);

        initSubView(viewType, viewStub);



        snsPopupWindow = new SnsPopupWindow(itemView.getContext());

    }

    public abstract void initSubView(int viewType, ViewStub viewStub);

}
