package cc.shawn.zzj.module.social;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.shawn.zzj.R;
import cc.shawn.zzj.base.BaseFragment;

/**
 * Created by shawn on 2017-02-12.
 */

public class SocialFragment extends BaseFragment implements SocialContract.View {

    SocialContract.Presenter presenter;
    View contentView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter = new SocialPresenter(this);
        contentView = View.inflate(getActivity(), R.layout.fragment_social,null);
        return contentView;
    }
}
