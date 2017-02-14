package cc.shawn.zzj.module.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.PtrHandler;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.shawn.zzj.R;
import cc.shawn.zzj.base.BaseFragment;
import cc.shawn.zzj.bean.IndexAdvert;
import cc.shawn.zzj.network.HttpResult;
import cc.shawn.zzj.network.Network;
import rx.Subscriber;

/**
 * Created by shawn on 2017-02-12.
 */

public class NewsFragment extends BaseFragment implements NewsContract.View {

    NewsContract.Presenter presenter;
    View contentView;

    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.listview)
    ListView listView;
    @BindView(R.id.refreshlayout)
    PtrClassicFrameLayout refreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter = new NewsPresenter(this);
        contentView = View.inflate(getActivity(), R.layout.fragment_news,null);

        ButterKnife.bind(this,contentView);
        final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 30 ; i ++){
            list.add("你好");
        }
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.test_list_item,android.R.id.text1,list);
        listView.setAdapter(arrayAdapter);

        refreshLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.refreshComplete();
                    }
                },2000);
            }
        });
        final int[] j = {1};
        refreshLayout.setLoadMoreEnable(true);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                for (int i = 0 ; i < 20 ; i++){

                    list.add("更多"+ j[0]);

                }
                j[0]++;
//                        arrayAdapter.addAll(new String[]{"更多","更多","更多","更多","更多","更多","更多","更多","更多","更多","更多","更多","更多","更多"});
                        arrayAdapter.notifyDataSetChanged();
                        refreshLayout.loadMoreComplete(true);
            }
        });
//        refreshLayout.setAutoLoadMoreEnable(true);
        return contentView;
    }
}
