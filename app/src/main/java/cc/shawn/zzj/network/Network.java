package cc.shawn.zzj.network;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cc.shawn.zzj.bean.IndexAdvert;
import cc.shawn.zzj.bean.SocialItem;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;


/**
 * Created by shawn on 2017-02-07.
 */

public class Network {

    public static final String BASE_URL = "http://101.201.155.115:6068";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private HttpService httpService;

    //构造方法私有
    private Network() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        httpService = retrofit.create(HttpService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final Network INSTANCE = new Network();
    }

    //获取单例
    public static Network getInstance(){
        return SingletonHolder.INSTANCE;
    }


    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {

            return httpResult.data;
        }
    }


    private static String sign = "123";

    public Observable<HttpResult<List<IndexAdvert>>> getIndexAdvert(int position){
        return compose(httpService.getIndexAdvert(position, sign));
    }

    public Observable<HttpResult<Object>> login(String loginName, String identifyingCode){
        return compose(httpService.login(loginName,identifyingCode,sign));
    }

    public Observable<HttpResult<List<SocialItem>>> getSocialItems(int page,int rows){
        return compose(httpService.getSocialItems(page,rows,sign));
    }



    private  <T> Observable<T> compose(Observable<T> o){
        return o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
