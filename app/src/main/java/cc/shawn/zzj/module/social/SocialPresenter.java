package cc.shawn.zzj.module.social;

import android.accounts.NetworkErrorException;
import android.support.v4.util.DebugUtils;
import android.view.View;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import cc.shawn.zzj.base.BaseRecyclerFragment;
import cc.shawn.zzj.bean.CommentConfig;
import cc.shawn.zzj.bean.IndexAdvert;
import cc.shawn.zzj.bean.SocialItem;
import cc.shawn.zzj.bean.SocialTotal;
import cc.shawn.zzj.network.HttpResult;
import cc.shawn.zzj.network.Network;
import cc.shawn.zzj.util.DebugLog;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by shawn on 2017-02-12.
 */

public class SocialPresenter implements SocialContract.Presenter {

    private Network network;
    private int page = 1;
    private int rows = 10;
    private int totalPage = 0;
    private SocialContract.View mView;

    SocialPresenter(SocialContract.View homeView) {
        network = Network.getInstance();
        mView = homeView;
    }

    @Override
    public void showEditTextBody(CommentConfig commentConfig) {
        if (mView != null) {
            mView.updateEditTextBodyVisible(View.VISIBLE, commentConfig);
        }
    }

    @Override
    public void loadData(final int loadType) {
        if (loadType == BaseRecyclerFragment.TYPE_PULL_REFRESH) {
            page = 1;
        }
//        network.getSocialItems("EE2", page, rows)
//                .flatMap(new Func1<HttpResult<SocialTotal>, Observable<List<SocialItem>>>() {
//                    @Override
//                    public Observable<List<SocialItem>> call(final HttpResult<SocialTotal> socialTotalHttpResult) {
//                        return Observable.create(new Observable.OnSubscribe<List<SocialItem>>() {
//                            @Override
//                            public void call(Subscriber<? super List<SocialItem>> subscriber) {
//                                DebugLog.e(new Gson().toJson(socialTotalHttpResult));
//                                if (socialTotalHttpResult != null) {
//                                    if ("error".equals(socialTotalHttpResult.result)) {
//                                        subscriber.onError(new NetworkErrorException(socialTotalHttpResult.msg));
//                                    } else if (socialTotalHttpResult.data != null) {
//                                        totalPage = socialTotalHttpResult.data.total;
//                                        if (socialTotalHttpResult.data.list != null)
//                                            subscriber.onNext(socialTotalHttpResult.data.list);
//                                    }
//                                }
//                                subscriber.onCompleted();
//                            }
//                        });
//                    }
//                })
//                .subscribe(new Subscriber<List<SocialItem>>() {
//                    @Override
//                    public void onCompleted() {
//                        DebugLog.e("network complete");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        DebugLog.e("network error");
//                    }
//
//                    @Override
//                    public void onNext(List<SocialItem> socialItems) {
//                        for (SocialItem socialItem : socialItems) {
//                            DebugLog.e(socialItem.toString());
//                        }
//                        if (socialItems != null)
//                            mView.update2loadData(loadType, socialItems);
//                    }
//                });

        final OkHttpClient client = new OkHttpClient();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder().url("http://101.201.155.115:6068/getAllMoment?userUUID=EE2&page=1&rows=10&sign=123").build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        DebugLog.e("daadadsad:"+response.body().string());
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    DebugLog.e(e.getMessage());
                }

            }
        }).start();
        network.getSocialItems("EE2",2,10).subscribe(new Subscriber<HttpResult<SocialTotal>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HttpResult<SocialTotal> socialTotalHttpResult) {
                DebugLog.e("json:"+new Gson().toJson(socialTotalHttpResult));
            }
        });
        network.getIndexAdvert(1).subscribe(new Subscriber<HttpResult<List<IndexAdvert>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HttpResult<List<IndexAdvert>> listHttpResult) {
                DebugLog.e("dddd:"+new Gson().toJson(listHttpResult));
            }
        });
        page++;
        if (page > totalPage) {
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
