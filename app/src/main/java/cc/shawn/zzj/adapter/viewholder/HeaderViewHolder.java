package cc.shawn.zzj.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;

/**
 * Created by shawn on 17/2/15.
 */

public class HeaderViewHolder extends SocialViewHolder {


    public HeaderViewHolder(View itemView) {
        super(itemView,TYPE_HEADER);
    }

    @Override
    public void initSubView(int viewType, ViewStub viewStub) {

    }
}
