package cc.shawn.zzj.adapter.viewholder;

import android.view.View;
import android.view.ViewStub;

import cc.shawn.zzj.R;
import cc.shawn.zzj.widget.MultiImageView;

/**
 * Created by shawn on 17/2/15.
 */

public class SocialImageViewHolder extends SocialViewHolder {

    /** 图片*/
    public MultiImageView multiImageView;


    @Override
    public void initSubView(int viewType, ViewStub viewStub) {

        if(viewStub == null){
            throw new IllegalArgumentException("viewStub is null...");
        }
        viewStub.setLayoutResource(R.layout.viewstub_imgbody);
        View subView = viewStub.inflate();
        MultiImageView multiImageView = (MultiImageView) subView.findViewById(R.id.multiImagView);
        if(multiImageView != null){
            this.multiImageView = multiImageView;
        }
    }

    public SocialImageViewHolder(View itemView) {
        super(itemView, TYPE_IMAGE);
    }


}
