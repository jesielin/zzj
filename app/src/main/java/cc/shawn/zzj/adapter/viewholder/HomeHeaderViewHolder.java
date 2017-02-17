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
import cc.shawn.zzj.adapter.BannerPagerAdapter;
import cc.shawn.zzj.widget.CommentListView;
import cc.shawn.zzj.widget.ExpandTextView;
import cc.shawn.zzj.widget.LoopViewPager;
import cc.shawn.zzj.widget.PraiseListView;
import cc.shawn.zzj.widget.SnsPopupWindow;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by shawn on 17/2/15.
 */

public class HomeHeaderViewHolder extends RecyclerView.ViewHolder {
    public final static int TYPE_URL = 0x01;
    public final static int TYPE_IMAGE = 0x02;
    public final static int TYPE_VIDEO = 0x03;
    public final static int TYPE_HEADER = 0x04;

    public int viewType;

    @BindView(R.id.viewpager)
    LoopViewPager viewPager;
    @BindView(R.id.indicator)
    CircleIndicator circleIndicator;



    public HomeHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.viewType = viewType;

        viewPager.setAdapter(new BannerPagerAdapter(5));
        circleIndicator.setViewPager(viewPager);

    }


}
